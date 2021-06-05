<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />

    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400&display=swap" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
</head>

<body style="font-family: 'Open Sans', sans-serif; max-width: 480px; margin: auto;">
    <div style="
        border: 2px solid purple;
        border-radius: 8px;
        margin-top: 2rem;
      ">
        <div style="
          text-align: center;
          border-bottom: 1px solid rgb(53, 49, 49);
          padding-bottom: 1rem;
          background-color: blanchedalmond;
          padding-top: 1rem;
          border-top-left-radius: 0.5rem;
          border-top-right-radius: 0.5rem;
        ">
            <span style="font-weight: bolder; font-size: 1.5rem">SunBeach Resort</span>
        </div>
        <div
            style="padding-top: 1rem;  background-color: white; color: black; padding-bottom: 20px;">
            <div style="font-weight: bolder; margin-left: 20px; font-size: 1.2rem; margin-bottom: 10px;">Customer
                Details :</div>
            <table
                style="width: 90%; margin: auto; border: 2px solid black; border-collapse: collapse; text-align: center;">
                <tr>
                    <td
                        style="border: 2px solid black; border-collapse: collapse; font-weight: bolder; text-align: left; padding-left: 20px;">
                        Name</td>
                    <td style="border: 2px solid black; border-collapse: collapse;">${order.guest.name}</td>
                </tr>
                <tr>
                    <td
                        style="border: 2px solid black; border-collapse: collapse; font-weight: bolder; text-align: left; padding-left: 20px;">
                        Mobile No</td>
                    <td style="border: 2px solid black; border-collapse: collapse; text-decoration: none;">${order.guest.mobileNo}</td>
                </tr>
                <tr>
                    <td
                        style="border: 2px solid black; border-collapse: collapse; font-weight: bolder; text-align: left; padding-left: 20px;">
                        Email</td>
                    <td style="border: 2px solid black; border-collapse: collapse; text-decoration: none;">${order.guest.email}</td>
                </tr>
                <tr>
                    <td
                        style="border: 2px solid black; border-collapse: collapse; font-weight: bolder; text-align: left; padding-left: 20px;">
                        Company</td>
                    <td style="border: 2px solid black; border-collapse: collapse;">${order.guest.company}</td>
                </tr>
                <tr>
                    <td
                        style="border: 2px solid black; border-collapse: collapse; font-weight: bolder; text-align: left; padding-left: 20px;">
                        Booked At</td>
                    <td style="border: 2px solid black; border-collapse: collapse;">${order.bookedAt}</td>
                </tr>
                <tr>
                    <td
                        style="border: 2px solid black; border-collapse: collapse; font-weight: bolder; text-align: left; padding-left: 20px;">
                        Order Id</td>
                    <td style="border: 2px solid black; border-collapse: collapse;">${order._id}</td>
                </tr>
                <tr>
                    <td
                        style="border: 2px solid black; border-collapse: collapse; font-weight: bolder; text-align: left; padding-left: 20px;">
                        Order Status</td>
                    <td style="border: 2px solid black; border-collapse: collapse;">${order.status?then('ACTIVE', 'Not Active')}</td>
                </tr>
            </table>
        </div>
        <hr style="margin: 0;">
        <div style="background-color: #34568B; padding-top: 10px; color: white; padding-bottom: 19px;">
            <div style="font-weight: bolder; margin-left: 20px; font-size: 1.2rem; margin-bottom: 10px;">Details :</div>
            <table
                style="width: 90%; margin: auto; border: 2px solid black; border-collapse: collapse; text-align: center;">
                <tr>
                    <th style="border: 2px solid black; border-collapse: collapse;">CheckIn</th>
                    <th style="border: 2px solid black; border-collapse: collapse;">CheckOut</th>
                    <th style="border: 2px solid black; border-collapse: collapse;">No Of Guests</th>
                    <th style="border: 2px solid black; border-collapse: collapse;">No Of Days</th>
                </tr>
                <tr>
                    <td style="border: 2px solid black; border-collapse: collapse;">${order.checkIn}</td>
                    <td style="border: 2px solid black; border-collapse: collapse;">${order.checkOut}</td>
                    <td style="border: 2px solid black; border-collapse: collapse;">${order.noOfGuests}</td>
                    <td style="border: 2px solid black; border-collapse: collapse;">${order.noOfDays}</td>
                </tr>
            </table>
        </div>
        <div
            style="background-color: #DFCFBE; padding-top: 10px; color: black;padding-top: 19px; padding-bottom: 20px;">
            <div style="font-weight: bolder; margin-left: 20px; font-size: 1.2rem; margin-bottom: 10px;">Room Details :
            </div>
            <table
                style="width: 90%; margin: auto; border: 2px solid black; border-collapse: collapse; text-align: center;">
                <tr>
                    <th style="border: 2px solid black; border-collapse: collapse;">Room No</th>
               
                    <th style="border: 2px solid black; border-collapse: collapse;">Price Per Day ($)</th>
                    <th style="border: 2px solid black; border-collapse: collapse;">Tax (%)</th>
                </tr>
                <tr>
                    <td style="border: 2px solid black; border-collapse: collapse;">${order.roomNo}</td>
                   
                    <td style="border: 2px solid black; border-collapse: collapse;">${order.amountDetails.amountPerDay}</td>
                    <td style="border: 2px solid black; border-collapse: collapse;">${order.amountDetails.tax}</td>
                </tr>
            </table>
        </div>
        <div style="background-color: #955251; padding-top: 10px; color: whitesmoke; padding-bottom: 20px;">
            <div style="font-weight: bolder; margin-left: 20px; font-size: 1.2rem; margin-bottom: 10px;">Amount Details
                :</div>
            <table
                style="width: 90%; margin: auto; border: 2px solid black; border-collapse: collapse; text-align: center;">
                <tr>
                    <th style="border: 2px solid black; border-collapse: collapse;">Total amount for Days ($)</th>
                    <th style="border: 2px solid black; border-collapse: collapse;">Discount (%)</th>
                    <th style="border: 2px solid black; border-collapse: collapse;">Final Amount ($)</th>
                    <th style="border: 2px solid black; border-collapse: collapse;">Due Amount ($)</th>
                    <th style="border: 2px solid black; border-collapse: collapse;">Total Save ($)</th>
                </tr>
                <tr>
                    <td style="border: 2px solid black; border-collapse: collapse;">${order.amountDetails.amountForDays}</td>
                    <td style="border: 2px solid black; border-collapse: collapse;">${order.amountDetails.discount}</td>
                    <td style="border: 2px solid black; border-collapse: collapse;">${order.amountDetails.finalAmount}</td>
                    <td style="border: 2px solid black; border-collapse: collapse;">${order.amountDetails.dueAmount}</td>
                    <td style="border: 2px solid black; border-collapse: collapse;">${order.amountDetails.savingAmount}</td>
                </tr>
            </table>
        </div>
        <hr style="margin: 0;">
        <div style="text-align: center;">
            <h4>Thank you for choosing SunBeach Resort</h2>
        </div>
    </div>
</body>

</html>