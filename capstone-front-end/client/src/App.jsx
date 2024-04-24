
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from './Components/Login';
import Dashboard from './Components/Dashboard';
import Facilities from './Components/Facilities';
import Logout from './Components/Logout';
import Bookings from './Components/Bookings';
import Guest from './Components/Guest';
import Signup from './Components/Signup';
import SecureRoute from './Components/SecureRoute';
import Update from './Components/Update';
import GuestUpdate from './Components/GuestUpdate';


function App() {
  return (
    <>
    <BrowserRouter>
    <Routes>
    <Route  element={<SecureRoute/>}>
      <Route path="/Hotels" element={<Dashboard/>}/>
      <Route path="/Facilities" element={<Facilities/>}/>
      <Route path="/logout" element={<Logout/>}/>
      <Route path="/Bookings" element={<Bookings/>}/>
      <Route path="/Guest" element={<Guest/>}/>
      <Route path="/update/:id" element={<Update/>}/>
      <Route path="/guestupdate/:id" element={<GuestUpdate/>}/>
      </Route>
      <Route path="/check_connection_string" element={<Signup/>}/>
      <Route path="/" element={<Login/>}/>
    </Routes>
    </BrowserRouter>
    </>
  );
}

export default App;
