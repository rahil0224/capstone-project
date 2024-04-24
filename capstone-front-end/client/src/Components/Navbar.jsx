import React from 'react'
import '../App.css'
import { Link } from 'react-router-dom'
import Login_icon from '../assets/login_icon.png'
export default function Navbar() {
  
    return (
        <>
            <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                <div className="container-md">

                    <Link className="navbar-brand" to="/Hotels">
                        Hotel
                    </Link>
                    <div className="flex-grow-2">
                    <Link className="navbar-brand" to="/Facilities">
                        Facilities 
                    </Link>
                    </div>
                   
                    <div className="flex-grow-2">
                    <Link className="navbar-brand" to="/Bookings">
                       Bookings
                    </Link>
                    </div>
                    <div className="flex-grow-2">
                    <Link className="navbar-brand" to="/Guest">
                      Guest
                    </Link>
                    </div>
                    <Link to="/Logout"><button className="btn btn-danger py-2 m-2">Logout</button></Link>
                </div>
            </nav>
        </>
    )
}
