import React, { Component } from 'react';
import { Dropdown } from 'react-bootstrap';
import { withRouter } from 'react-router-dom';

class Details extends Component {
  constructor(props) {
    super(props);
    this.state = {
      currentUser: {
        firstName: 'John',
        lastName: 'Doe',
        email: 'john.doe@example.com',
        // Add more user details as needed
      },
    };
  }

  handleEditProfile = () => {
    // Navigate to the edit profile page
    this.props.history.push('/edit-profile'); // Ensure you have a route for this
  };

  render() {
    const { currentUser } = this.state;

    return (
      <div className="details-page">
        <h1>Profile Details</h1>

        {/* Menu Button */}
        <Dropdown>
          <Dropdown.Toggle variant="success" id="dropdown-basic">
            Menu
          </Dropdown.Toggle>

          <Dropdown.Menu>
            <Dropdown.Header>Current User Information</Dropdown.Header>
            <Dropdown.ItemText>
              <strong>First Name:</strong> {currentUser.firstName}
            </Dropdown.ItemText>
            <Dropdown.ItemText>
              <strong>Last Name:</strong> {currentUser.lastName}
            </Dropdown.ItemText>
            <Dropdown.ItemText>
              <strong>Email:</strong> {currentUser.email}
            </Dropdown.ItemText>

            <Dropdown.Divider />

            {/* Edit Profile Button */}
            <Dropdown.Item as="button" onClick={this.handleEditProfile}>
              Edit Profile
            </Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>

        {/* Rest of your component code */}
        <div className="profile-details">
          {/* Display selected profile information here */}
        </div>
      </div>
    );
  }
}

export default withRouter(Details);
