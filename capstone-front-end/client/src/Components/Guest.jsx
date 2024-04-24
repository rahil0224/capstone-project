import axios from 'axios';
import React, { useEffect, useState } from 'react'
import Navbar from './Navbar'
import { Link } from 'react-router-dom';
import Loading from './Loading';
import Foorter from './Foorter';
export default function Guest() {
  const [getdata, setGetData] = useState([]);
  const [payin, setPayIN] = useState([]);
  const [loading, setLoading] = useState([])
  const [search, setSearch] = useState('');
  console.log(payin)
  useEffect(() => {
    getList(); 
  }, []);
 
  function getList() {
    setTimeout(() => {
        axios.get('http://localhost:8082/api/guests')
            .then((res) => {
              setGetData(res.data);
                setLoading(false);
            })
            .catch((error) => {
                console.error("Error fetching data:", error);
            });
    }, []);
  }
  function deleteUser(id) {
    fetch(`http://localhost:8082/api/guest/${id}`, {
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
        <div className='row mt-2 p-2'>
          <div className='col-lg-12'>
            <Link to="/Hotels"><button className='btn btn-success'>Back</button></Link>
          </div>
        </div>
        <div className='row mt-2 p-2'>
          <div className='row rounded'>
            <div className='col col-md-6 '>
              <div className='d-flex p-2 bd-highlight'>
                <div className="input-group input-group-sm">
                  <input className="my-2 my-lg-6 form-control-sm " placeholder='Search..' type="text" class="form-control" aria-label="Sizing example input"
                     onChange={(e) => setSearch(e.target.value)}
                  />
                </div>
              </div>
            </div>
          </div>

          <div className='col-lg-12 mt-3 table-responsive'>
            <table className="table table-striped table-bordered">
              <thead>
                <tr className='table-dark  table-responsive text-center'>
                  <th>Guest-ID</th>
                  <th>Guest Name</th>
                  <th>Guest Address</th>
                  <th>Action</th>
                </tr>
              </thead>
              {loading ? (
                <Loading />
              ) : (
                <tbody>
                  {
                    getdata.filter((post) => {
                      return search.toLocaleLowerCase() === '' ? post : post.guestname.toLocaleLowerCase().includes(search)
                  }).map((post) => {
                      const { id, guestname, address } = post;
                      return (
                        <tr key={id} className="table-light text-center">
                          <td>{id}</td>
                          <td>{guestname}</td>
                          <td>{address}</td>
                          <td className='text-center'>
                          <Link className='btn btn-success m-2' to={`/guestupdate/${id}`}>Update</Link>
                          <button className='btn btn-danger' onClick={() => deleteUser(id)}>Delete</button>                                              </td>
                        </tr>
                      )
                    })
                  }
                </tbody>
              )}
            </table>
          </div>
        </div>
      </div >
      <Foorter />
    </>
  )
}
