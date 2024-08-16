import React, { Component } from 'react';
import axios from 'axios';
import { Link, withRouter } from 'react-router-dom'; // Ensure `withRouter` is used if needed
import 'bootstrap/dist/css/bootstrap.min.css';
import accept from '../assets/accept.png';
import decline from '../assets/decline.png';
import menu from '../assets/menu.png';

class Intrested extends Component {

  constructor(props) {
    super(props);
    this.state = {
      acceptedlist: [],
      profileId: props.match.params.profileId || null,
    };
  }

  componentDidMount() {
    const { profileId } = this.state;
    console.log(profileId); // Should print the profileId from the URL
    if (profileId) {
      this.getData().then(response => {
        this.setState({ acceptedlist: response.data });
      }).catch(error => {
        console.error('Error fetching data:', error);
      });
    }
  }

  getData = () => {
    return axios.get(`http://localhost:8080/api/getInterestedProfiles/${this.state.profileId}`);
  }

  handleProfileAction = (actionProfileId, action) => {
    const { profileId } = this.state;
    const mat = { profileId, actionProfileId, actionProfileName: "", action };

    axios.put('http://10.117.189.210:9090/app/updateAcceptReject', mat)
      .then(response => {
        console.log('Action successful:', response.data);
        this.getData().then(response => {
          this.setState({ acceptedlist: response.data });
        });
      })
      .catch(error => {
        console.error('Error updating action:', error);
      });
  }

  render() {
    return (
      <div>
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
          <img src={menu} width="40px" height="40px" alt="Menu" />
          <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>

          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav mr-auto">
              
              <li className="nav-item">
                <button onClick={() => this.props.history.push(`/intrested/${this.state.profileId}`)} className="btn btn-link">Intrested profiles</button>
              </li>
              <li className="nav-item">
                <button onClick={() => this.props.history.push(`/accepted/${this.state.profileId}`)} className="btn btn-link">Accepted profiles</button>
              </li>
              <li className="nav-item">
                <button onClick={() => this.props.history.push(`/rejected/${this.state.profileId}`)} className="btn btn-link">Rejected profiles</button>
              </li>
              <li className="nav-item">
                <Link to="/dashboard" className="btn btn-link">Logout</Link>
              </li>
            </ul>
            <form className="form-inline my-2 my-lg-0">
              <input className="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" />
              <button className="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
          </div>
        </nav>

        <table className="table list">
          <thead>
            <tr>
              <th>Interested Profile Name</th>
              <th>Age</th>
              <th>Salary</th>
              <th>Occupation</th>
            </tr>
          </thead>
          <tbody>
            {this.state.acceptedlist.map((item, i) => (
              <tr key={i}>
                <td>{item.firstName}</td>
                <td>{item.age}</td>
                <td>{item.income}</td>
                <td>{item.occupation}</td>
                <td>
                  <button type="button" onClick={() => this.handleProfileAction(item.interestedProfileId, 'accept')}>
                    <img width="50px" height="50px" src={accept} alt="Accept" />
                  </button>
                  <button type="button" onClick={() => this.handleProfileAction(item.interestedProfileId, 'reject')}>
                    <img width="50px" height="50px" src={decline} alt="Decline" />
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}

export default withRouter(Intrested);
