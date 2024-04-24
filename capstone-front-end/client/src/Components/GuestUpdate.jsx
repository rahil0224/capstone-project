import React, { useState } from 'react'
import { useParams } from 'react-router-dom'
import { useNavigate } from "react-router-dom";


function GuestUpdate() {
    const { id } = useParams();
    const [guestmyData, setMyData] = useState({
        id: id,
        guestname: '',
        address: ''
    });

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setMyData({ ...guestmyData, [name]: value });
    };
    const navigate = useNavigate();
    const handleSubmit = (event) => {
        event.preventDefault();
        fetch('http://localhost:8082/api/guest/' + id, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',

            },
            body: JSON.stringify(guestmyData),
        })
            .then((response) => {
                if (response.ok) {

                    alert('Update successful');
                    navigate("/Guest");
                   
                } else {
                    console.error('Update failed');
                }
            })
            .catch((error) => {
                // Handle fetch errors
                console.error('Error:', error);
            });
    };


    return (
        <div>
            <div className='container-md'>
                <div className='container-md'>
                    <div className='row rounded'>
                        <div className='col col-md-12'>
                            <div className='d-flex p-2 bd-highlight'>

                            </div>
                        </div>
                    </div>
                </div>
                <div className='container mt-4'>
                    <div className='row'>
                        <h3 className='text-center mt-4 mb-4'>Guest Records Update</h3>
                        <div className="table-responsive">
                            <form onSubmit={handleSubmit}>
                                <table className="table table-striped table-bordered">
                                    <thead>
                                        <tr className='table-dark table-responsive text-center'>
                                            <th>Guest Data Update</th>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input type="text" name="name" className='form-control' value={guestmyData.id} disabled onChange={handleInputChange} />
                                                <br />
                                                <input type="text" name="guestname" className='form-control' value={guestmyData.guestname} placeholder='Hotel Name' onChange={handleInputChange} />
                                                <br />
                                                <input type="text" name="address" className='form-control' value={guestmyData.address}  placeholder='Location' onChange={handleInputChange} />
                                                <br />
                                                <button className='btn btn-success' >Update</button>
                                            </td>
                                        </tr>
                                    </thead>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default GuestUpdate
