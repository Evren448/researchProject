import React, { Component } from "react";
import { signup } from "../api/apiCalls";
import Input from '../components/Input';

import 'alertifyjs/build/css/alertify.css';
import alertify from "alertifyjs";



class UserSignupPage extends Component {
  state = {
    username: null,
    email: null,
    password: null,
    passwordRepeat: null,
    pendingApiCall: false,
    errors: {}
  };

  onChange = (event) => {
    const { name, value } = event.target;
    const errors = {...this.state.errors};
    errors[name] = undefined;

    if(name === 'password' || name === 'passwordRepeat'){
      if(name=== 'password' && value !== this.state.passwordRepeat){
        errors.passwordRepeat = 'Password Mismatch!';
      } else if (name === 'passwordRepeat' && value!== this.state.password){
        errors.passwordRepeat = 'Password Mismatch!';
      }
      else{
        errors.passwordRepeat = undefined;
      }
    }

    this.setState({
      [name]: value,
      errors
    });
  };

 

  onClickSignup = async (event) => {
    event.preventDefault();
    

    const { username, email, password } = this.state;
    

    const body = {
      username,
      email,
      password,
    };
    this.setState({ pendingApiCall: true });

    try{
      
      const response = await signup(body);
      alertify.success(username + " Your registration has been successfully completed.", 1);  

    } catch(error){
      if(error.response.data.validationErrors){
        this.setState({errors: error.response.data.validationErrors});
        

      }
      
    }
    this.setState({ pendingApiCall: false });

    
    // signup(body)
    //   .then((response) => {
    //     this.setState({ pendingApiCall: false });
    //   })
    //   .catch((error) => {
    //     this.setState({ pendingApiCall: false });
    //   });
  };




  render() {
    const { pendingApiCall,errors } = this.state;
    const { username, email, password, passwordRepeat } = errors;

    return (
      <div className="container">
        <form>
          <h1 className="text-center">Sign Up </h1>
          <Input name="username" label="Username" error={username} onChange={this.onChange}></Input>
          <Input name="email" label="E-Mail" error={email} onChange={this.onChange} type="email"></Input>
          <Input name="password" label="Password" error={password} onChange={this.onChange} type="password"></Input>
          <Input name="passwordRepeat" label="Password Repeat" error={passwordRepeat} onChange={this.onChange} type="password"></Input>

          <div className="text-center">
            <button
              className="btn btn-primary"
              //onClick={handleChange}
              onClick={this.onClickSignup}
              disabled={pendingApiCall || passwordRepeat!== undefined}
            >
              {pendingApiCall && <span className="spinner-border spinner-border-sm"></span>} Sign Up
            </button>
          </div>

        </form>
      </div>
    );
  }
}

export default UserSignupPage;
