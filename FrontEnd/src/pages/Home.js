import React from 'react'
import Hero from "../components/Hero";
import Banner from '../components/Banner';
import {Link} from 'react-router-dom';
import Services from '../components/Services';
import Navbar from '../components/Navbar';



export default function Home()  {
  return (
    <>
    <Navbar />
    <Hero>
    <Banner title="SunBeach Resort" subtitle="Rooms starting at $25">
    <Link to="/login" className="btn btn-primary">Login</Link>     
    </Banner>
    </Hero>
    
    <Services />
    
    </>

  );
}
