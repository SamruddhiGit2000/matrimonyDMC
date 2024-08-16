import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';
import '../assets/login.css'; // Ensure this path is correct
import { Link } from 'react-router-dom/cjs/react-router-dom.min';

class Adminlogin extends Component {
    constructor(props) {
        super(props);
        this.state = {
            formdata: {
                username: "",
                password: ""
            },
            adminData: null,
            errorMessage: ""
        };
    }

    adminLogin = (event) => {
        event.preventDefault();
        const { formdata } = this.state;

        axios.post('http://localhost:8080/api/adminLogin', formdata)
            .then((response) => {
                console.log(response.data);
                this.setState({ adminData: response.data });
                this.props.history.push('/adminDashboard'); // Redirect to the admin dashboard
            })
            .catch((error) => {
                console.error("Admin login failed:", error);
                this.setState({ errorMessage: "Invalid username or password" });
            });
    }

    loginHandler = (event) => {
        const { formdata } = this.state;
        formdata[event.target.name] = event.target.value;
        this.setState({ formdata });
    }

    render() {
        const { errorMessage, adminData } = this.state;
        return (
            <div className="login">
                <h1 align="center">Admin Login Form</h1>
                <form className='needs-validation container'>
                    <div className="form-group">
                        <label>User Name:</label>
                        <input type="text" className="form-control mb-2" placeholder="User Name" name="username" onChange={this.loginHandler} required />
                        <div className="valid-feedback">Valid.</div>
                        <div className="invalid-feedback">Please fill out this field.</div>
                    </div>
                    <div className="form-group">
                        <label>Password:</label>
                        <input type="password" className="form-control mb-2" placeholder="Password" name="password" onChange={this.loginHandler} required />
                        <div className="valid-feedback">Valid.</div>
                        <div className="invalid-feedback">Please fill out this field.</div>
                    </div>
                    <div className="button-group" style={{ textAlign: 'center', marginTop: '20px' }}>
    <button
        onClick={this.adminLogin}
        className="btn btn-lg btn-danger"
        style={{
            backgroundColor: '#e63946',
            color: 'white',
            borderRadius: '30px',
            padding: '10px 30px',
            marginRight: '15px',
            border: 'none',
            boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.1)',
        }}
    >
        Login
    </button>
    <button
        type='reset'
        className="btn btn-lg btn-secondary"
        style={{
            backgroundColor: '#a8dadc',
            color: 'white',
            borderRadius: '30px',
            padding: '10px 30px',
            marginRight: '15px',
            border: 'none',
            boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.1)',
        }}
    >
        Reset
    </button>
    <button
        type="submit"
        className='btn btn-lg btn-primary'
        style={{
            backgroundColor: '#457b9d',
            color: 'white',
            borderRadius: '30px',
            padding: '10px 30px',
            border: 'none',
            boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.1)',
        }}
    >
        <Link
            to="/login"
            style={{
                color: 'white',
                textDecoration: 'none',
            }}
        >
            Back to Login
        </Link>
    </button>
</div>

                    {/* <div>
                        <button onClick={this.adminLogin}>Login</button>
                        <button type='reset'>Reset</button>
                        <button type="submit"className='btn btn-primary'value={"Back"}><Link to="/login" value="Back to login">Back to login</Link></button>
                    </div> */}
                </form>
                {errorMessage && <p className="text-danger">{errorMessage}</p>}
                {adminData && (
                    <div>
                        <h2>Welcome, Admin {adminData.username}</h2>
                        {/* Display other admin-specific data as needed */}
                    </div>
                )}
            </div>
        )
    }
}

export default Adminlogin;
