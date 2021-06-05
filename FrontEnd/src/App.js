
import './App.css';
import Home from  "./pages/Home";

import {Route, Switch} from 'react-router-dom';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';

import Login from "./components/Login";
import WelcomeRecep from "./components/Receptionist/WelcomeRecep";
import AvailableRooms from "./components/Receptionist/AvailableRooms";
import Booking from "./components/Receptionist/Booking";
import TotalRooms from "./components/Receptionist/TotalRooms";
import ActiveOrders from "./components/Receptionist/ActiveOrders";
import ActiveOrder from "./components/Receptionist/ActiveOrder";
import Orders from "./components/Receptionist/Orders";
import About from "./pages/About";
import WelcomeManager from './components/Manager/WelcomeManager';
import AllRooms from './components/Manager/AllRooms';
import CurrentBookings from './components/Manager/CurrentBookings';
import TotalOrders from './components/Manager/TotalOrders';
import Gallery from './components/DashBoard';
import Success from './components/Success';
import Checkout from "./components/Receptionist/checkout"

import Welcome from './components/Admin/Welcome';
import Employee from './components/Admin/Employee';
import AllEmployees from './components/Admin/AllEmployees';
import Rooms from './components/Admin/Rooms';
import RoomList from './components/Admin/RoomList';
import AllOrders from './components/Admin/AllOrders';
import Contact from './pages/Contact';
import Error from './pages/Error';


function App() {
  return (
    <>
        <Switch>
        <Route exact path="/receptionist" component={WelcomeRecep}/>
        <Route exact path="/receptionist/gallery" component={Gallery}/>
        <Route exact path="/receptionist/totalrooms" component={TotalRooms}/> 
        <Route exact path="/receptionist/search" component={AvailableRooms}/>
        <Route exact path="/receptionist/Booking" component={Booking}/>
        <Route exact path="/receptionist/activeorders" component={ActiveOrders}/>
        <Route exact path="/receptionist/Orders" component={Orders}/>
        <Route exact path="/receptionist/Success" component={Success}/>
        <Route exact path="/receptionist/checkout" component={Checkout}/>
        <Route exact path="/receptionist/activeorder" component={ActiveOrder}/>


        <Route exact path="/manager" component={WelcomeManager}/>
        <Route exact path="/manager/allrooms" component={AllRooms}/> 
        <Route exact path="/manager/currentorders" component={CurrentBookings}/>
        <Route exact path="/manager/orders" component={TotalOrders}/>

        <Route exact path="/admin" component={Welcome}/>
        <Route exact path="/admin/employee" component={Employee}/> 
        <Route exact path="/admin/allemployee" component={AllEmployees}/>
        <Route exact path="/admin/rooms" component={Rooms}/>
        <Route path = "/admin/edit/:roomNo" exact component = { Rooms }/>
        <Route path = "/admin/update/:registeredNo" exact component = { Employee}/>
        <Route exact path="/admin/allrooms" component={RoomList}/>
        <Route exact path="/admin/Orders" component={AllOrders}/>
        

    
    <Route exact path="/" component={Home}/>
    <Route exact path="/about" component={About}/>
    <Route exact path="/contact" component={Contact}/>
    <Route exact path="/login" component={Login}/>
    <Route component={Error}/>
    </Switch>
    </>
  );
}

export default App;
