import React from 'react'
import Hero from '../Hero'
import Banner from '../Banner';
import NavBarRecep from './NavBarRecep';
import Footer from '../Footer.js';

export default function Welcome() {

    return (
        <>
        
        <NavBarRecep />
        
        <Hero hero="roomsHero1" >
        <Banner title="Welcome" subtitle="Receptionist !!" >

                

        </Banner>
        </Hero>
        
        <Footer />
        
        </>
        
    )
}