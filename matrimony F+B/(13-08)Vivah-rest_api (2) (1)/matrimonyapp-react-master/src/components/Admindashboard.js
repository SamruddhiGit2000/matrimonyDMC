import React, { Component } from 'react';
import axios from 'axios';
import { Button, Modal } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../assets/list.css'; // Update this path if necessary
import { Link } from 'react-router-dom';
//import { useNavigate } from 'react-router-dom';

class Admindashboard extends Component {
  constructor(props) {
    super(props);
    this.state = {
      list: [], // Initialize as an empty array
      selectedProfile: null,
      showModal: false,
      searchTerm: '',
    };
  }

  async componentDidMount() {
    const data = await this.getData();
    this.setState({ list: data }); // Directly set the list to the data array
  }

  getData = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/profiles');
      console.log('API Response:', response.data); // Log the response data
      return response.data; // Directly return the array
    } catch (error) {
      console.error('Error fetching data:', error);
      alert('Failed to fetch profiles. Please try again later.');
      return []; // Return an empty array if there's an error
    }
  };

  deleteProfile = (id) => {
    console.log('Deleting profile with ID:', id); // Log the profileId
    axios
      .delete(`http://localhost:8080/api/delete/${id}`)
      .then((response) => {
        if (response.status === 200) {
          console.log('Profile deleted successfully', response.data);
          alert(response.data); // Alert the success message
          this.setState((prevState) => ({
            list: prevState.list.filter(profile => profile.id !== id)
          }));
        } else {
          throw new Error('Profile not found');
        }
      })
      .catch((error) => {
        console.error('Error deleting profile', error);
        alert('Error deleting profile');
      });
  };


  showDetails = (profile) => {
    this.setState({ selectedProfile: profile, showModal: true });
  };

  hideDetails = () => {
    this.setState({ selectedProfile: null, showModal: false });
  };

  handleSearchInputChange = (event) => {
    this.setState({ searchTerm: event.target.value });
  };

  handleSearchSubmit = async (event) => {
    event.preventDefault();
    const { searchTerm } = this.state;
  
    if (!searchTerm) {
      // If the search term is empty, reset the list to the original data
      const data = await this.getData();
      this.setState({ list: data });
    } else {
      try {
        const response = await axios.get('http://localhost:8080/api/profilesSearch', {
          params: {
            firstName: searchTerm,
          },
        });
        this.setState({ list: response.data });
      } catch (error) {
        console.error('Error searching profiles:', error);
        alert('Error searching profiles. Please try again later.');
      }
    }
  };
  handleSearchInputChange = async (event) => {
    const { value } = event.target;
    this.setState({ searchTerm: value });
  
    if (event.key === '' || value === '') {
      await this.performSearch();
    }
  };
  performSearch = async () => {
    const { searchTerm } = this.state;
  
    if (!searchTerm) {
      // If the search term is empty, reset the list to the original data
      const data = await this.getData();
      this.setState({ list: data });
    } else {
      try {
        const response = await axios.get('http://localhost:8080/api/profilesSearch', {
          params: {
            firstName: searchTerm,
          },
        });
        this.setState({ list: response.data });
      } catch (error) {
        console.error('Error searching profiles:', error);
        alert('Error searching profiles. Please try again later.');
      }
    }
  };
  // handleSearchSubmit = async (event) => {
  //   event.preventDefault();
  //   const { searchTerm } = this.state;

  //   try {
  //     const response = await axios.get('http://localhost:8080/api/profilesSearch', {
  //       params: {
  //         firstName: searchTerm,
  //       },
  //     });
  //     this.setState({ list: response.data });

  //    // navigate('/Dashboard'); // Navigate to the dashboard after the search
  //   } catch (error) {
  //     console.error('Error searching profiles:', error);
  //     alert('Error searching profiles. Please try again later.');
  //   }
  // };

  render() {
    const { list = [], selectedProfile, showModal, searchTerm } = this.state;
    console.log('List Data:', list); // Log the data to verify it's correctly assigned

    return (
      <div>
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
          <button
            className="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>

          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav mr-auto">
              <li className="nav-item">
                <Link to="/dashboard" className="btn btn-link">
                  Logout
                </Link>
                
                {/* <Link to="/adminDashboard" className="btn btn-link">
                  Back
                </Link> */}
              </li>
            </ul>

            <form className="form-inline my-2 my-lg-0" onSubmit={this.handleSearchSubmit}>
  <input
    className="form-control mr-sm-2"
    type="search"
    placeholder="Search by First Name"
    aria-label="Search"
    value={this.state.searchTerm}
    onChange={this.handleSearchInputChange}
    onKeyPress={this.handleSearchInputChange} // Listen for keypress event
  />
  <button className="btn btn-outline-success my-2 my-sm-0" type="submit">
    Search
  </button>
</form>
 
            {/* <form className="form-inline my-2 my-lg-0" onSubmit={this.handleSearchSubmit}>
              <input
                className="form-control mr-sm-2"
                type="search"
                placeholder="Search by First Name"
                aria-label="Search"
                value={searchTerm}
                onChange={this.handleSearchInputChange}
              />
              <button className="btn btn-outline-success my-2 my-sm-0" type="submit">
                Search
              </button>
            </form> */}
          </div>
        </nav>

        <table className="table list">
          <thead>
            <tr>
              <th>Profile ID</th>
              <th>First Name</th>
              <th>Age</th>
              <th>Income</th>
              <th>Occupation</th>
              <th>Delete</th>
              <th>Details</th>
            </tr>
          </thead>
          <tbody>
            {list.length > 0 ? (
              list.map((item, index) => (
                <tr key={index}>
                  <td>{item.userId}</td>
                  <td>{item.firstName}</td>
                  <td>{item.age}</td>
                  <td>{item.income}</td>
                  <td>{item.occupation}</td>
                  <td>
                    <Button
                      variant="danger"
                      onClick={() => this.deleteProfile(item.userId)}
                    >
                      Delete
                    </Button>
                  </td>
                  <td>
                    <Button
                      variant="info"
                      onClick={() => this.showDetails(item)}
                    >
                      Show Details
                    </Button>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="7">No profiles available</td>
              </tr>
            )}
          </tbody>
        </table>

        {/* Modal for Profile Details */}
        <Modal show={showModal} onHide={this.hideDetails}>
          <Modal.Header>
            <Modal.Title>Profile Details</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            {selectedProfile && (
              <>
                <p><strong>First Name:</strong> {selectedProfile.firstName}</p>
                <p><strong>Last Name:</strong> {selectedProfile.lastName}</p>
                <p><strong>Gender:</strong> {selectedProfile.gender}</p>
                <p><strong>Date of Birth:</strong> {selectedProfile.dateOfBirth}</p>
                <p><strong>Marital Status:</strong> {selectedProfile.maritalStatus}</p>
                <p><strong>Address:</strong> {selectedProfile.address}</p>
                <p><strong>Religion:</strong> {selectedProfile.religion}</p>
                <p><strong>Religion Preference:</strong> {selectedProfile.religionPref}</p>
                <p><strong>Email:</strong> {selectedProfile.email}</p>
                <p><strong>Mobile Number:</strong> {selectedProfile.mobileNumber}</p>
                <p><strong>Education:</strong> {selectedProfile.education}</p>
                <p><strong>Occupation:</strong> {selectedProfile.occupation}</p>
                <p><strong>Income:</strong> {selectedProfile.income}</p>
                <p><strong>About Me:</strong> {selectedProfile.aboutMe}</p>
                <p><strong>Age:</strong> {selectedProfile.age}</p>
              </>
            )}
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={this.hideDetails}>
              Close
            </Button>
          </Modal.Footer>
        </Modal>
      </div>
    );
  }
}

export default Admindashboard;
