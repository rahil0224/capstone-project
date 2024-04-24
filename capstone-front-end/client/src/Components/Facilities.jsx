import axios from 'axios';
import React, { useEffect, useState } from 'react'
import Navbar from './Navbar'
import { Link } from 'react-router-dom';
import Loading from './Loading';
import Foorter from './Foorter';
export default function Facilities() {
  const [getdata, setGetData] = useState([]);
  const [loading, setLoading] = useState([])
  const [search, setSearch] = useState('');
  console.log(getdata)
  useEffect(() => {
    getList();
  }, []);

  function getList() {
    setTimeout(() => {
        axios.get('http://localhost:8081/api/facility')
            .then((res) => {
              setGetData(res.data);
                setLoading(false);
            })
            .catch((error) => {
                console.error("Error fetching data:", error);
            });
    }, []);
  }
    // Delete Data 
    function deleteFacility(id) {
      fetch(`http://localhost:8081/api/facility/${id}`, {
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
        <div className='tainer'>
          <div className='row mt-2 p-2'>
            <div className='col-lg-12'>
              <Link to="/Hotels"><button className='btn btn-success'>Back</button></Link>
            </div>
          </div>
          <div className="container">
            <div className="row">
              <div className="col-12 mt-4">
                <input class="form-control mr-sm-4"
                  onChange={(e) => setSearch(e.target.value)}
                  type="search" placeholder="Availabe Data Search" aria-label="Search" />
              </div>
              <div className="data_table table-responsive mt-2">
                <table id="example" className="table table-responsive table-bordered">
                  <thead className="table-dark text-center">
                    <tr>
                      <th>ID</th>
                      <th>Facility Name</th>
                      <th>Facility Cost</th>
                      <th>Facility Description</th>
                      <th>Action</th>
                    </tr>
                  </thead>
                  {loading ? (
                    <Loading />
                  ) : (
                    <tbody>
                      {
                        getdata.filter((post) => {
                          return search.toLocaleLowerCase() === '' ? post : post.facilityname.toLocaleLowerCase().includes(search)
                        })
                          .map((post) => {
                            const { id, facilityname, facilitydescription, facilitycost } = post;
                            return (
                              <tr key={id} className="table-light text-center">
                                <td>{id}</td>
                                <td>{facilityname}</td>
                                <td>{facilitydescription}</td>
                                <td>{facilitycost}</td>
                                <td className='text-center'>
                                  <button className='btn btn-success m-2'>Update</button>
                                  <button className='btn btn-danger' onClick={() => deleteFacility(id)}>Delete</button>                                                </td>
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
        </div>
        <Foorter />
      </>
    )
  }

