import React, { useEffect, useState } from 'react'
import Navbar from './Navbar'
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import './Dashboard.css';
import { BiBath, BiCalendar, BiHotel, BiUser } from "react-icons/bi";
import Loading from './Loading';
import Foorter from './Foorter';
export default function Dashboard() {
    const [myData, setMyData] = useState([]);
    const [loading, setLoading] = useState([])
    const [search, setSearch] = useState('');
    console.log(search);
    useEffect(() => {
        getList();
    }, []);

    function getList() {
        setTimeout(() => {
            axios.get('http://localhost:8081/api/hotels')
                .then((res) => {
                    setMyData(res.data);
                    setLoading(false);
                })
                .catch((error) => {
                    console.error("Error fetching data:", error);
                });
        }, []);
    }
    // Delete Data 
    function deleteUser(id) {
        fetch(`http://localhost:8081/api/hotel/${id}`, {
            method: 'DELETE',
        }).then((result) => {
            result.json().then((resp) => {
                console.log(resp);
                getList();
            })
        })
    }
    return (
        <>
            <Navbar />
            <div className='container-md'>
                <div className='row '>
                    <div className='col-lg-12 '>
                        <div className='row mt-4 rounded-top justify-content-center'>

                            {/* <div className='col-xxl-3 col-md-4'>

                                <div className='card info-card sales-card '>
                                    <div className="card-body payout">
                                        <h5 className="card-title text-center"><BiHotel fontSize={60} />
                                            <h3>Hotels</h3>
                                        </h5>

                                        <div className="d-flex align-items-center">
                                            <div className="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                                <i className="bi bi-cart"></i>
                                            </div>

                                        </div>
                                    </div>
                                </div>

                            </div> */}

                            <div className='col-xxl-3 col-md-4'>
                                <Link className="navbar-brand" to="/Facilities">
                                    <div className='card info-card sales-card'>
                                        <div className="card-body sales">
                                            <h5 className="card-title text-center"><BiBath fontSize={60} />
                                                <h3>Facilities</h3>
                                            </h5>
                                            <div className="d-flex align-items-center">
                                                <div className="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                                    <i className="bi bi-cart"></i>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </Link>
                            </div>
                            <div className='col-xxl-3 col-md-4'>
                                <div className='card info-card sales-card'>
                                    <div className="card-body dealer">
                                        <h5 className="card-title text-center"><BiCalendar fontSize={60} />
                                            <h3>Bookings</h3>
                                        </h5>
                                        <div className="d-flex align-items-center">
                                            <div className="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                                <i className="bi bi-cart"></i>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className='col-xxl-3 col-md-4'>
                                <Link className="navbar-brand" to="/Guest">
                                    <div className='card info-card sales-card'>
                                        <div className="card-body holiday">
                                            <h5 className="card-title text-center"><BiUser fontSize={60} />
                                                <h3>Guest</h3>
                                            </h5>
                                            <div className="d-flex align-items-center">
                                                <div className="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                                    <i className="bi bi-cart"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </Link>
                            </div>


                        </div>
                    </div>
                </div>
            </div>
            <div className='container-md'>
                <div className='row rounded'>
                    <div className='col col-md-12'>
                        <div className='d-flex p-2 bd-highlight'>

                        </div>
                    </div>
                </div>
            </div>
            {/* Payout Report Generat Here */}
            <div className='container mt-4'>
                <div className='row'>
                    <div className='col col-md-4 mb-4'>
                        <input class="form-control mr-sm-4"
                            onChange={(e) => setSearch(e.target.value)}
                            type="search" placeholder="Availabe Data Search" aria-label="Search" />
                    </div>
                </div>
                <div className='row'>
                    <div className="table-responsive">
                        <table className="table table-striped table-bordered">
                            <thead>
                                <tr className='table-dark table-responsive text-center'>
                                    <th>Hotel ID</th>
                                    <th>Hotel Name</th>
                                    <th>Location</th>
                                    <th>Guest ID</th>
                                    <th>Action</th>

                                </tr>
                            </thead>
                            {loading ? (
                                <Loading />
                            ) : (
                                <tbody>
                                    {
                                        myData.filter((post) => {
                                            return search.toLocaleLowerCase() === '' ? post : post.hotelname.toLocaleLowerCase().includes(search)
                                        }).map((post) => {
                                            const { id, hotelname, location, guestId } = post;
                                            return (
                                                <tr key={id} className="table-light text-center">
                                                    <td>{id}</td>
                                                    <td>{hotelname}</td>
                                                    <td>{location}</td>
                                                    <td>{guestId}</td>
                                                    <td>
                                                        <Link className='btn btn-success m-2' to={`/update/${id}`}>Update</Link>
                                                        <button className='btn btn-danger' onClick={() => deleteUser(id)}>Delete</button>
                                                    </td>
                                                </tr>
                                            )
                                        })
                                    }
                                </tbody>
                            )}
                        </table>
                    </div>
                </div>
            </div>
            <Foorter />
        </>

    )
}
