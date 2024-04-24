import React from 'react'
import { useNavigate } from 'react-router-dom';

export default function Logout() {
    const auth = localStorage.getItem('user');
    const navigate = useNavigate();
    const logout = () =>{
        localStorage.clear();
        navigate('/')
    }
    return (
        <div>
            <main>
                <div className='container'>
                    <section className='min-vh-100 d-flex flex-column align-items justify-content-center py-4'>
                        <div className='container'>
                            <div className='row justify-content-center'>
                                <div className='col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center'>
                                    <div className='d-flex justify-content-center py-4'><span>Tour Manager</span></div>

                                    <div className='col-lg-2 mb-4'>
                                        <div className='mt-2'>
                                            <button className='btn btn-danger' onClick={logout}>Logout</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </main>
        </div>
    )
}
