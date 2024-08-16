import React, { Component } from 'react';

class EditProfile extends Component {
  constructor(props) {
    super(props);
    this.state = {
      firstName: 'John',
      lastName: 'Doe',
      email: 'john.doe@example.com',
      // Add other fields as needed
    };
  }

  handleInputChange = (event) => {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  };

  handleSubmit = (event) => {
    event.preventDefault();
    // Implement profile update logic here
    console.log('Profile updated with:', this.state);
  };

  render() {
    const { firstName, lastName, email } = this.state;

    return (
      <div>
        <h2>Edit Profile</h2>
        <form onSubmit={this.handleSubmit}>
          <div>
            <label>First Name:</label>
            <input
              type="text"
              name="firstName"
              value={firstName}
              onChange={this.handleInputChange}
            />
          </div>
          <div>
            <label>Last Name:</label>
            <input
              type="text"
              name="lastName"
              value={lastName}
              onChange={this.handleInputChange}
            />
          </div>
          <div>
            <label>Email:</label>
            <input
              type="email"
              name="email"
              value={email}
              onChange={this.handleInputChange}
            />
          </div>
          <button type="submit">Save</button>
        </form>
      </div>
    );
  }
}

export default EditProfile;
