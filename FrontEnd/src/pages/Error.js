import React from 'react'
import Hero from '../components/Hero'
import Banner from '../components/Banner';
import { Link } from 'react-router-dom';
import {FaRegMeh} from 'react-icons/fa';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';

export default function Error() {
    return (
        <>
        <Navbar />
        <Hero hero="roomsHero" >
        <Banner title="ERROR 404 NOT FOUND" subtitle="You are lost !!">
                <FaRegMeh className="lost"></FaRegMeh>
                <Link to="/" className="btn btn-warning">
                      RETURN HOME
                </Link>
        </Banner>
        </Hero>
        <Footer />
        </>
    )
}