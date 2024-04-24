import axios from 'axios';
import React, { useEffect, useState } from 'react'
import Navbar from './Navbar'
import { Link } from 'react-router-dom';
import Foorter from './Foorter';
import Loading from './Loading';
import Dropdown from 'react-bootstrap/Dropdown';
export default function Bookings() {
  const [myData, setMyData] = useState([]);;
  const [dealer, setDealer] = useState([]);
  const [loading, setLoading] = useState([])
  const [selectedHotelId, setSelectedHotelId] = useState('');
  const [hotelDetails, setHotelDetails] = useState(null);
  console.log(dealer)
  useEffect(() => {
    getList();
  }, []);

  useEffect(() => {
    // Fetch hotel details when selectedHotelId changes
    if (selectedHotelId) {
      fetchHotelDetails(selectedHotelId);
    } else {
      // Clear hotel details if no hotel is selected
      setHotelDetails(null);
    }
  }, [selectedHotelId]);

  function getList() {

    setTimeout(() => {
      axios.get('http://localhost:8083/api/bookings')
        .then((res) => {
          setMyData(res.data);
          setLoading(false);
        })
        .catch((error) => {
          console.error("Error fetching data:", error);
        });
    }, []);
  }
  const fetchHotelDetails = async (id) => {
    try {
     
      const response = await axios.get(`http://localhost:8081/api/hotel/${id}`);
            setHotelDetails(response.data);
      
      console.log(response.data);
    } catch (error) {

      console.error('Error fetching hotel details:', error);
    }
  };

  const handleHotelChange = (event) => {
    setSelectedHotelId(event.target.value);
  };

  return (
    <>
      <Navbar />
      <div className='container-md'>
        <div className='row mt-2 p-2'>
          <div className='col-lg-12'>
            <Link to="/Hotels"><button className='btn btn-success'>Back</button></Link>
          </div>
        </div>
        <div className='row rounded'>
          <div className='row'>
            <div className='col col-md-4 mb-4'>
              <input class="form-control mr-sm-4"
                onChange={(e) => setDealer(e.target.value)}
                type="search" placeholder="Availabe Data Search" aria-label="Search" />
            </div>
          </div>
        </div>
        <div className='col-lg-12 mt-3 table-responsive'>
          <table className="table  table-striped">
            <thead>
              <tr className='table-dark table-responsive'>
                <th>ID</th>
                <th>Check In Time</th>
                <th>Check Out Time</th>
                <th> Hotel ID</th>
                <th>Facility_id</th>
                <th>Guest_id</th>
              </tr>
            </thead>
            {loading ? (
              <Loading />
            ) : (
              <tbody>
                {
                  myData.map((item) => {
                    return (
                      <>
                        <tr key={item.id} className="table-light">
                          <td>{item.id}</td>
                          <td>{item.checkintime}</td>
                          <td>{item.checkouttime}</td>
                          <td>
                          <label htmlFor="hotelSelect">Select a Hotel:</label>
                  <select id="hotelSelect" onChange={handleHotelChange} value={selectedHotelId}>
                    <option value="">Select Hotel</option>
                    {myData.map((hotel) => (
                      <option key={hotel.id} value={hotel.id}>
                        {hotel.name}
                      </option>
                    ))}
                  </select>

                  {hotelDetails && (
                    <div>
                      <h2>{hotelDetails.name}</h2>
                      <p>{hotelDetails.details}</p>
                      {/* Render additional details as needed */}
                    </div>
                  )}

                          </td>
                          <td>{item.facility_id}</td>
                          <td>{item.guest_id}</td>
                        </tr>
                      </>
                    );
                  })}
              </tbody>
            )}
          </table>

        </div>
      </div>
      <Foorter />
    </>
  )
}
