import React, { useEffect, useState } from "react";
import Login_icon from "../assets/login_icon.png";
import { Link, useNavigate } from "react-router-dom";
export default function Signup() {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();
    useEffect(()=>{
        const auth = localStorage.getItem('user');
        if(auth){
            navigate('/')
        }
    },[])
    const collectData = async () => {
        if (name.trim() === '' || email.trim() === '' || password.trim() === '') {
            // At least one of the input fields is empty, do not submit data
            alert('Please fill in all fields.');
            return;
        }
        console.warn(name, email, password);
        let result = await fetch("http://localhost:7081/register", {
            method: "post",
            body: JSON.stringify({ name, email, password }),
            headers: {
                "Content-Type": "application/json",
            },
        });
        result = await result.json();
        console.warn(result);
        localStorage.setItem("user", JSON.stringify(result));
        navigate('/');
    };
    useEffect(() => {
        const auth = localStorage.getItem("user");
        if (auth) {
            navigate('/');
        }
    }, []);
    return (
        <>
            <main>
                <div className="container">
                    <section className="min-vh-100 d-flex flex-column align-items justify-content-center py-4">
                        <div className="container">
                            <div className="row justify-content-center">
                                <div className="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">
                                    <div className="d-flex justify-content-center py-4">
                                        <Link className="logo d-flex align-items-center w-auto">
                                            <img src={Login_icon} className="" alt="React logo" />
                                            <span>Hensex Console</span>
                                        </Link>
                                    </div>

                                    <div className="card mb-4">
                                        <div className="card-body">
                                            <div className="pt-4 pb-2">
                                                <h5 className="card-title text-center pb-0 fs-4">
                                                    SignUp to Your Account
                                                </h5>
                                                <p className="text-center small"></p>
                                            </div>
                                            <form className="row g-3 needs-validation">
                                                <div className="col-12">
                                                    <label className="form-label">User Name</label>
                                                    <div className="input-group has-validation">
                                                        <input
                                                            type="text"
                                                            name="username"
                                                            value={name}
                                                            className="form-control"
                                                            required
                                                            onChange={(e) => setName(e.target.value)}
                                                        />
                                                        <div className="invalid-feedback">
                                                            Please enter your username.
                                                        </div>
                                                    </div>
                                                </div>
                                                <div className="col-12">
                                                    <label className="form-label">User Id</label>
                                                    <div className="input-group has-validation">
                                                        <input
                                                            type="text"
                                                            name="username"
                                                            className="form-control"
                                                            required
                                                            value={email}
                                                            onChange={(e) => setEmail(e.target.value)}
                                                        />
                                                        <div className="invalid-feedback">
                                                            Please enter your username.
                                                        </div>
                                                    </div>
                                                </div>
                                                <div className="col-12">
                                                    <label className="form-label">Password</label>

                                                    <input
                                                        type="password"
                                                        name="password"
                                                        className="form-control"
                                                        required
                                                        value={password}
                                                        onChange={(e) => setPassword(e.target.value)}
                                                    />
                                                    <div className="invalid-feedback">
                                                        Please enter your password!
                                                    </div>
                                                </div>
                                                <div className="col-12">
                                                    <div className="form-check">
                                                        <input
                                                            className="form-check-input"
                                                            type="checkbox"
                                                            name="remember"
                                                            value="true"
                                                            id="rememberMe"
                                                        />
                                                        <label className="form-check-label">
                                                            Remember me
                                                        </label>
                                                    </div>
                                                </div>
                                                <div className="col-12">
                                                    <button
                                                        className="btn btn-primary w-100"
                                                        type="button"
                                                        onClick={collectData}
                                                    >
                                                        SignUp
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </main>
        </>
    );
}
