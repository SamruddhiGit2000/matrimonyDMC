import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';
import '../assets/login.css'; // Ensure this path is correct

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            formdata: {
                username: "",
                password: ""
            },
            profileData: null,
            errorMessage: "",
        };
    }

    login = (event) => {
        event.preventDefault();
        const { formdata } = this.state;

        axios.post('http://localhost:8080/api/login', formdata)
            .then((response) => {
                console.log(response.data);
                this.setState({ profileData: response.data });
                this.props.history.push('/listof/' + response.data.userId);
            })
            .catch((error) => {
                console.error("Login failed:", error);
                this.setState({ errorMessage: "Invalid username or password" });
            });
    }

    handleAdminLogin = () => {
        this.props.history.push('/adminLogin');
    }

    loginHandler = (event) => {
        const { formdata } = this.state;
        formdata[event.target.name] = event.target.value;
        this.setState({ formdata });
    }

    render() {
        const { errorMessage, profileData } = this.state;
        return (
            <div className="login">
                <h1 align="center">Matrimonial Login Form</h1>
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
        onClick={this.login}
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
        type='button'
        onClick={this.handleAdminLogin}
        className="btn btn-lg btn-warning"
        style={{
            backgroundColor: '#f4a261',
            color: 'white',
            borderRadius: '30px',
            padding: '10px 30px',
            marginRight: '15px',
            border: 'none',
            boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.1)',
        }}
    >
        Admin Login
    </button>
    <button
        type='reset'
        className="btn btn-lg btn-secondary"
        style={{
            backgroundColor: '#a8dadc',
            color: 'white',
            borderRadius: '30px',
            padding: '10px 30px',
            border: 'none',
            boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.1)',
        }}
    >
        Reset
    </button>
</div>

                    {/* <div>
                        <button onClick={this.login}>Login</button>
                        <button type='button' onClick={this.handleAdminLogin}>Admin Login</button>
                        <button type='reset'>Reset</button>
                    </div> */}
                </form>
                {errorMessage && <p className="text-danger">{errorMessage}</p>}
                {profileData && (
                    <div>
                        <h2>Welcome, {profileData.firstName} {profileData.lastName}</h2>
                        <p>Gender: {profileData.gender}</p>
                        <p>Address: {profileData.address}</p>
                        {/* Display other profile data as needed */}
                    </div>
                )}
            </div>
        )
    }
}

export default Login;
// import React, { Component } from 'react';
// import 'bootstrap/dist/css/bootstrap.min.css';
// import axios from 'axios';
// import '../assets/login.css'; // Ensure this path is correct

// class Login extends Component {
//     constructor(props) {
//         super(props);
//         this.state = {
//             formdata: {
//                 username: "",
//                 password: ""
//             },
//             profileData: null,
//             errorMessage: "",
//         };
//     }

//     login = (event) => {
//         event.preventDefault();
//         const { formdata } = this.state;

//         axios.post('http://localhost:8080/api/login', formdata)
//             .then((response) => {
//                 console.log(response.data);
//                 this.setState({ profileData: response.data });
//                 this.props.history.push('/listof/' + response.data.userId);
//             })
//             .catch((error) => {
//                 console.error("Login failed:", error);
//                 this.setState({ errorMessage: "Invalid username or password" });
//             });
//     }

//     handleAdminLogin = () => {
//         this.props.history.push('/adminLogin');
//     }

//     loginHandler = (event) => {
//         const { formdata } = this.state;
//         formdata[event.target.name] = event.target.value;
//         this.setState({ formdata });
//     }

//     render() {
//         const { errorMessage, profileData } = this.state;
//         return (
//             <div className="login-container d-flex justify-content-center align-items-center">
//                 <div className="login-box p-4 shadow-lg bg-white rounded">
//                     <h1 className="text-center mb-4">Matrimonial Login Form</h1>
//                     <form className='needs-validation' onSubmit={this.login}>
//                         <div className="form-group">
//                             <label htmlFor="username">User Name:</label>
//                             <input
//                                 type="text"
//                                 className="form-control custom-input"
//                                 id="username"
//                                 placeholder="User Name"
//                                 name="username"
//                                 onChange={this.loginHandler}
//                                 required
//                             />
//                             <div className="invalid-feedback">Please fill out this field.</div>
//                         </div>
//                         <div className="form-group">
//                             <label htmlFor="password">Password:</label>
//                             <input
//                                 type="password"
//                                 className="form-control custom-input"
//                                 id="password"
//                                 placeholder="Password"
//                                 name="password"
//                                 onChange={this.loginHandler}
//                                 required
//                             />
//                             <div className="invalid-feedback">Please fill out this field.</div>
//                         </div>
//                         <div className="button-group text-center mt-4">
//                             <button
//                                 type="submit"
//                                 className="btn btn-primary mx-2"
//                             >
//                                 Login
//                             </button>
//                             <button
//                                 type="button"
//                                 onClick={this.handleAdminLogin}
//                                 className="btn btn-secondary mx-2"
//                             >
//                                 Admin Login
//                             </button>
//                             <button
//                                 type="reset"
//                                 className="btn btn-light mx-2"
//                             >
//                                 Reset
//                             </button>
//                         </div>
//                     </form>
//                     {errorMessage && <p className="text-danger text-center mt-3">{errorMessage}</p>}
//                     {profileData && (
//                         <div className="profile-data mt-4 text-center">
//                             <h2>Welcome, {profileData.firstName} {profileData.lastName}</h2>
//                             <p>Gender: {profileData.gender}</p>
//                             <p>Address: {profileData.address}</p>
//                             {/* Display other profile data as needed */}
//                         </div>
//                     )}
//                 </div>
//             </div>
//         );
//     }
// }

// export default Login;
