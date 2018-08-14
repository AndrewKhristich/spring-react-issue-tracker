import React, {Component} from 'react';
import axios from 'axios'

class LoginForm extends Component {
    constructor(pros) {
        super(pros);
        this.state = {
            username: '',
            password: '',
            logged: false,
            logErr: false,
            user: ''
        };
        this.handleNameChange = this.handleNameChange.bind(this);
        this.handlePassChange = this.handlePassChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleLogout = this.handleLogout.bind(this);
    }

    handleSubmit(event) {
        axios.post("http://localhost:8080/api/auth/signin", {username: this.state.username, password: this.state.password})
            .then(res => {
                this.setState({logged: true, logErr: false});
                localStorage.setItem("accessToken", res.data.accessToken);
                }, err => {
                this.setState({logged: false, logErr: true}); console.log("error" + " " + this.state.username);
            });
    }

    handleLogout() {
        axios.get("http://localhost:8080/logout")
            .then(res => {}, err => {});
    }

    handleNameChange(event) {
        this.setState({username: event.target.value});
    }

    handlePassChange(event) {
        this.setState({password: event.target.value});
    }

    render() {
        return (
            <div className="container">
                <form onSubmit={this.handleSubmit}>
                    <input type="text" value={this.state.username} onChange={this.handleNameChange} placeholder="user name"/>
                    <input type="password" value={this.state.password} onChange={this.handlePassChange} placeholder="password"/>
                    <button type="submit" value="Submit">Sign In</button>
                </form>
                <span>{this.state.user}</span>
                <button onClick={this.handleLogout()}>logout</button>
            </div>
        );
    }
}

export default LoginForm;
