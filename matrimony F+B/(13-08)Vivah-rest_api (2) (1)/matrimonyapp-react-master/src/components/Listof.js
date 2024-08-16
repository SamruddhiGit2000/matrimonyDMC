// import React, { Component } from 'react';
// import axios from 'axios';
// import { Link } from 'react-router-dom';
// import 'bootstrap/dist/css/bootstrap.min.css';
// import { Modal, Button } from 'react-bootstrap';
// import accept from '../assets/accept.png';
// import decline from '../assets/decline.png';
// import menu from '../assets/menu.png';
// import list from '../assets/list.css';

// class Listof extends Component {
//   constructor(props) {
//     super(props);
//     this.state = {
//       list: [],
//       interests: [],
//       id: props.match.params.profileIdParam,
//       selectedProfile: null,
//       showModal: false, // State to control modal visibility
//       searchTerm: '', // State to track search input
//     };
//   }

//   componentDidMount() {
//     this.getData().then(({ profiles, interests }) => {
//       this.setState({ list: profiles, interests });
//     });
//   }

//   getData = async () => {
//     const { id } = this.state;
//     try {
//       const response = await axios.get(`http://localhost:8080/api/profilesWithInterests/${id}`);
//       return response.data;
//     } catch (error) {
//       console.error('Error fetching data:', error);
//       alert('Failed to fetch profiles. Please try again later.');
//       return { profiles: [], interests: [] };
//     }
//   };

//   handleCheckboxChange = (actionProfileId, isChecked) => {
//     const { id, interests } = this.state;
//     const mat = {
//       userId: id,
//       interestedInUserId: actionProfileId,
//     };

//     const url = isChecked
//       ? 'http://localhost:8080/api/addInterest'
//       : 'http://localhost:8080/api/removeInterest';

//     axios.defaults.withCredentials = true;

//     axios
//       .post(url, mat)
//       .then((response) => {
//         console.log('Interest updated successfully', response.data);
//         alert('Interest updated successfully');

//         this.setState((prevState) => {
//           const updatedInterests = isChecked
//             ? [...prevState.interests, mat]
//             : prevState.interests.filter(
//                 (interest) => interest.interestedInUserId !== actionProfileId
//               );

//           return { interests: updatedInterests };
//         });
//       })
//       .catch((error) => {
//         console.error('Error updating interest', error.response.data);
//         alert('Error updating interest');
//       });
//   };

//   isInterested = (profileId) => {
//     return this.state.interests.some(
//       (interest) => interest.interestedInUserId === profileId
//     );
//   };

//   showDetails = (profile) => {
//     this.setState({ selectedProfile: profile, showModal: true });
//   };

//   hideDetails = () => {
//     this.setState({ selectedProfile: null, showModal: false });
//   };

//   // Handle search input change
//   handleSearchInputChange = (event) => {
//     this.setState({ searchTerm: event.target.value });
//   };

//   // Handle search form submission
//   handleSearchSubmit = async (event) => {
//     event.preventDefault();
//     const { searchTerm, id } = this.state;

//     try {
//       const response = await axios.get('http://localhost:8080/api/searchProfiles', {
//         params: {
//           firstName: searchTerm,
//           userId: id,
//         },
//       });

//       // Update the list with search results
//       this.setState({ list: response.data.profiles });
//     } catch (error) {
//       console.error('Error searching profiles:', error);
//       alert('Error searching profiles. Please try again later.');
//     }
//   };

//   render() {
//     const { list, selectedProfile, showModal, searchTerm } = this.state;

//     return (
//       <div>
//         <nav className="navbar navbar-expand-lg navbar-light bg-light">
//           <Link to="/details">
//             <img src={menu} width="40px" height="40px" alt="Menu" />
//           </Link>
//           <button
//             className="navbar-toggler"
//             type="button"
//             data-toggle="collapse"
//             data-target="#navbarSupportedContent"
//             aria-controls="navbarSupportedContent"
//             aria-expanded="false"
//             aria-label="Toggle navigation"
//           >
//             <span className="navbar-toggler-icon"></span>
//           </button>

//           <div className="collapse navbar-collapse" id="navbarSupportedContent">
//             <ul className="navbar-nav mr-auto">
//               <li className="nav-item">
//                 <Link to={`/intrested/${this.state.id}`} className="btn btn-link">
//                   Interested profiles
//                 </Link>
//               </li>
//               <li className="nav-item">
//                 <Link to="/accepted" className="btn btn-link">
//                   Accepted profiles
//                 </Link>
//               </li>
//               <li className="nav-item">
//                 <Link to="/rejected" className="btn btn-link">
//                   Rejected profiles
//                 </Link>
//               </li>
//               <li className="nav-item">
//                 <Link to="/dashboard" className="btn btn-link">
//                   Logout
//                 </Link>
//               </li>
//             </ul>
//             <form className="form-inline my-2 my-lg-0" onSubmit={this.handleSearchSubmit}>
//               <input
//                 className="form-control mr-sm-2"
//                 type="search"
//                 placeholder="Search by First Name"
//                 aria-label="Search"
//                 value={searchTerm}
//                 onChange={this.handleSearchInputChange}
//               />
//               <button className="btn btn-outline-success my-2 my-sm-0" type="submit">
//                 Search
//               </button>
//             </form>
//           </div>
//         </nav>

//         <table className="table list">
//           <thead>
//             <tr>
//               <td>Profile ID</td>
//               <td>First Name</td>
//               <td>Age</td>
//               <td>Salary</td>
//               <td>Occupation</td>
//               <td>Interested</td>
//               <td>Details</td> {/* New column */}
//             </tr>
//           </thead>
//           <tbody>
//             {list.map((item, index) => (
//               <tr key={index}>
//                 <td>{item.userId}</td>
//                 <td>{item.firstName}</td>
//                 <td>{item.age}</td>
//                 <td>{item.income}</td>
//                 <td>{item.occupation}</td>
//                 <td>
//                   <input
//                     type="checkbox"
//                     checked={this.isInterested(item.userId)}
//                     onChange={(e) =>
//                       this.handleCheckboxChange(item.userId, e.target.checked)
//                     }
//                   />
//                 </td>
//                 <td>
//                   <Button
//                     variant="info"
//                     onClick={() => this.showDetails(item)}
//                   >
//                     Show Details
//                   </Button>
//                 </td>
//               </tr>
//             ))}
//           </tbody>
//         </table>

//         {/* Modal for Profile Details */}
//         <Modal show={showModal} onHide={this.hideDetails}>
//           <Modal.Header>
//             <Modal.Title>Profile Details</Modal.Title>
//           </Modal.Header>
//           <Modal.Body>
//             {selectedProfile && (
//               <>
//                 <p><strong>First Name:</strong> {selectedProfile.firstName}</p>
//                 <p><strong>Last Name:</strong> {selectedProfile.lastName}</p>
//                 <p><strong>Gender:</strong> {selectedProfile.gender}</p>
//                 <p><strong>Date of Birth:</strong> {selectedProfile.dateOfBirth}</p>
//                 <p><strong>Marital Status:</strong> {selectedProfile.maritalStatus}</p>
//                 <p><strong>Address:</strong> {selectedProfile.address}</p>
//                 <p><strong>Religion:</strong> {selectedProfile.religion}</p>
//                 <p><strong>Religion Preference:</strong> {selectedProfile.religionPref}</p>
//                 <p><strong>Email:</strong> {selectedProfile.email}</p>
//                 <p><strong>Mobile Number:</strong> {selectedProfile.mobileNumber}</p>
//                 <p><strong>Education:</strong> {selectedProfile.education}</p>
//                 <p><strong>Occupation:</strong> {selectedProfile.occupation}</p>
//                 <p><strong>Income:</strong> {selectedProfile.income}</p>
//                 <p><strong>About Me:</strong> {selectedProfile.aboutMe}</p>
//                 <p><strong>Age:</strong> {selectedProfile.age}</p>
//               </>
//             )}
//           </Modal.Body>
//           <Modal.Footer>
//             <Button variant="secondary" onClick={this.hideDetails}>
//               Close
//             </Button>
//           </Modal.Footer>
//         </Modal>
//       </div>
//     );
//   }
// }

// export default Listof;
import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Modal, Button } from 'react-bootstrap';
import accept from '../assets/accept.png';
import decline from '../assets/decline.png';
import menu from '../assets/menu.png';
import '../assets/list.css'; // Ensure the CSS file path is correct

class Listof extends Component {
  constructor(props) {
    super(props);
    this.state = {
      list: [],
      interests: [],
      id: props.match.params.profileIdParam,
      selectedProfile: null,
      showModal: false,
      searchTerm: '',
    };
  }

  componentDidMount() {
    this.getData().then(({ profiles, interests }) => {
      this.setState({ list: profiles, interests });
    });
  }

  getData = async () => {
    const { id } = this.state;
    try {
      const response = await axios.get(`http://localhost:8080/api/profilesWithInterests/${id}`);
      return response.data;
    } catch (error) {
      console.error('Error fetching data:', error);
      alert('Failed to fetch profiles. Please try again later.');
      return { profiles: [], interests: [] };
    }
  };

  handleCheckboxChange = (actionProfileId, isChecked) => {
    const { id, interests } = this.state;
    const mat = {
      userId: id,
      interestedInUserId: actionProfileId,
    };

    const url = isChecked
      ? 'http://localhost:8080/api/addInterest'
      : 'http://localhost:8080/api/removeInterest';

    axios.defaults.withCredentials = true;

    axios
      .post(url, mat)
      .then((response) => {
        console.log('Interest updated successfully', response.data);
        alert('Interest updated successfully');

        this.setState((prevState) => {
          const updatedInterests = isChecked
            ? [...prevState.interests, mat]
            : prevState.interests.filter(
                (interest) => interest.interestedInUserId !== actionProfileId
              );

          return { interests: updatedInterests };
        });
      })
      .catch((error) => {
        console.error('Error updating interest', error.response.data);
        alert('Error updating interest');
      });
  };

  isInterested = (profileId) => {
    return this.state.interests.some(
      (interest) => interest.interestedInUserId === profileId
    );
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
    const { searchTerm, id } = this.state;

    try {
      const response = await axios.get('http://localhost:8080/api/searchProfiles', {
        params: {
          firstName: searchTerm,
          userId: id,
        },
      });

      this.setState({ list: response.data.profiles });
    } catch (error) {
      console.error('Error searching profiles:', error);
      alert('Error searching profiles. Please try again later.');
    }
  };

  render() {
    const { list, selectedProfile, showModal, searchTerm } = this.state;

    return (
      <div>
        <nav className="navbar navbar-expand-lg navbar-light bg-light shadow-sm">
          <Link to="/details">
            <img src={menu} width="40px" height="40px" alt="Menu" />
          </Link>
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
                <Link to={`/intrested/${this.state.id}`} className="nav-link">
                  Interested Profiles
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/accepted" className="nav-link">
                  Accepted Profiles
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/rejected" className="nav-link">
                  Rejected Profiles
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/dashboard" className="nav-link btn btn-outline-danger">
                  Logout
                </Link>
              </li>
            </ul>
            <form className="form-inline my-2 my-lg-0" onSubmit={this.handleSearchSubmit}>
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
            </form>
          </div>
        </nav>

        <div className="container mt-4">
          <table className="table table-striped table-bordered">
            <thead>
              <tr>
                <th>Profile ID</th>
                <th>First Name</th>
                <th>Age</th>
                <th>Salary</th>
                <th>Occupation</th>
                <th>Interested</th>
                <th>Details</th>
              </tr>
            </thead>
            <tbody>
              {list.map((item, index) => (
                <tr key={index}>
                  <td>{item.userId}</td>
                  <td>{item.firstName}</td>
                  <td>{item.age}</td>
                  <td>{item.income}</td>
                  <td>{item.occupation}</td>
                  <td>
                    <input
                      type="checkbox"
                      checked={this.isInterested(item.userId)}
                      onChange={(e) =>
                        this.handleCheckboxChange(item.userId, e.target.checked)
                      }
                    />
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
              ))}
            </tbody>
          </table>
        </div>

        <Modal show={showModal} onHide={this.hideDetails}>
          <Modal.Header closeButton>
            <Modal.Title>Profile Details</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            {selectedProfile && (
              <div>
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
              </div>
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

export default Listof;
