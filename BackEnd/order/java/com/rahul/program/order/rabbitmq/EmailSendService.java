package com.rahul.program.order.rabbitmq;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.rahul.program.order.model.Order;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
@Service
public class EmailSendService {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private Configuration config;

    //Consumes the message present in the queue in Rabbitmq
    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueueAndEmail(Order order) {
        //Adding order object to model, so that this data is used in template to set values for fields
        Map<String, Object> model = new HashMap<>();
        model.put("order", order);

        //Calling sendEmail method to send an email
        sendEmailForOrder(order, model);
       
    }

    //Sends email about order details
    public void sendEmailForOrder(Order order, Map<String, Object> model) {
        MimeMessage message = sender.createMimeMessage();
        try {
            //set mediatype
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            //Template configuration which is going to be rendered
            Template t = config.getTemplate("email-template.ftl");

            //Sending model to template and storing data in string
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

            //To email address
            helper.setTo(order.getGuest().getEmail());

            //Send html file data
            helper.setText(html, true);
           
            helper.setSubject("Room booked Successfully with Order id: " + order.get_id());
           
            helper.setFrom("rahulani359@gmail.com");
            sender.send(message);

        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

