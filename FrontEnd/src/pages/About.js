
import React from 'react'

import {Link} from 'react-router-dom';
import about from '../images/about.svg';
import customer from '../images/customers/customer.svg';

import Navbar from '../components/Navbar';
import Footer from '../components/Footer';

function About() {
return (
  <>
  <Navbar />
<div className="container aboutus">
<div className="row">
<div className="col-md-6 col-12 my-auto">
<img src={about} alt="about us" className="img-fluid" />

</div>
<div className="col-md-6 col-12 my-auto">
<h1 className="display-4 text-center my-5">About Us </h1>
<p className="lead text-justify text-center">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptas officia eligendi dolorem molestias nesciunt aliquam deserunt velit fuga quidem atque?</p>
<div className="text-center col-md-6 col-12 mx-auto">

</div>
</div>
</div>
<div className="about_company">
<h1 className="display-4">About Company</h1>
<div className="pt-4">
<p className="about_info">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eaque, illum delectus sequi necessitatibus cum laudantium incidunt minima, voluptatibus voluptas repellat officia doloremque magnam quis, accusamus tenetur, quasi doloribus iusto quia distinctio labore optio reprehenderit aperiam suscipit dignissimos aliquid! Odit distinctio quam, excepturi repellendus sunt magni adipisci sit architecto placeat tempore numquam, ipsam nobis vitae aperiam reprehenderit inventore ipsum facilis? Sapiente iure id explicabo. Omnis, architecto quaerat! Architecto error ducimus consequuntur, asperiores fugiat nostrum veniam eaque aspernatur ab quas aliquam ipsa! Odit expedita voluptate, mollitia tenetur eveniet quisquam a veritatis. In, odit. Enim aliquid voluptates vitae pariatur facilis beatae odio labore est, voluptatem officiis! Maiores, iure molestias aliquam suscipit rem impedit veritatis architecto delectus molestiae tempora inventore beatae consectetur facere voluptas dolores labore, laboriosam officiis non alias minima dolorum fuga corrupti blanditiis. Laudantium consequatur, aspernatur beatae dicta atque labore. Molestiae illum, possimus, officia sit nihil, cupiditate nesciunt, consequatur nulla deleniti veniam modi maxime impedit delectus iste totam earum vel repellendus doloribus in quae fuga. Dignissimos, unde eius voluptatem iusto est nesciunt quaerat temporibus velit doloribus a id laborum repellendus fugit nihil, animi facilis quisquam nemo tempora accusantium doloremque libero magnam dolorum veritatis vel aspernatur. Enim reiciendis laborum, expedita illum, aliquam eaque!</p>
</div>
</div>
<div className="testimony">
<h2 className="display-4 mb-4">What Our Customers say</h2>
<div className="row mb-5">
<div className="col-md-10 col-12 mx-auto">
<div id="carouselExampleCaptions" className="carousel slide" data-ride="carousel">
<ol className="carousel-indicators">
<li data-target="#carouselExampleCaptions" data-slide-to="0" className="active"></li>

</ol>
<div className="carousel-inner card border-0 shadow-lg p-4">
<div className="carousel-item active text-center">
<div className="row">
<div className="col-md-8 col-12 my-auto">
<img src={customer} className="text-center img-fluid" width="450" height="400" alt="customer1" />
</div>
<div className="col-md-4 col-12 my-auto">
<div className="text-dark">
<h3 className="font-weight-bolder ">Customer ABC</h3>
<p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
</div>
</div>
</div>
</div>



</div>


</div>
</div>
</div>
</div>


</div>
<Footer />
</>
)
}
export default About;