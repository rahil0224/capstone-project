import React from "react";
import "./loading.css";
export default function Loading() {
    return (
        <div>
            <div className="container">
                <div className="row">
                    <div className="col-lg-12">
                        <button className="btn btn-dark" type="button" disabled>
                            <span className="spinner-border spinner-border-sm" role="status" aria-hidden="true"> </span> Loading... </button>
                    </div>
                </div>
            </div>
        </div>
    );
}
