import React, {useRef, useEffect} from 'react'
import { useHistory } from 'react-router-dom';
import NavBarRecep from './NavBarRecep';
import Footer from '../Footer';

function Checkout() {
  const paypal = useRef();
  const history = useHistory();
  const orderdetails = JSON.parse(localStorage.getItem('order'));
  const money = localStorage.getItem('money');
  console.log(money);
  useEffect(() => {
    window.paypal
      .Buttons({
        createOrder: (data, actions, err) => {
          return actions.order.create({
            intent: "CAPTURE",
            purchase_units: [
              {
                description: "Name of room/service",
                amount: {
                  currency_code: "CAD",
                  value: money ,
                },
              },
            ],
          });
        },
        onApprove: async (data, actions) => {
          const order = await actions.order.capture();
           alert("Payment Successful");
           localStorage.removeItem('money');
           
            
  
           history.push("/receptionist/activeorder");
            
         

        },
        onError: (err) => {
          console.log(err);
          
        },
      })
      .render(paypal.current);
  }, []);

  return (
    <div>
      <NavBarRecep />
      <div className="roomsHero1" ref={paypal}></div>
      <Footer />
    </div>
  );
    
}

export default Checkout
