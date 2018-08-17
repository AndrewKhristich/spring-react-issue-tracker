import React, {Component} from 'react';
import './App.css';
import {ACCESS_TOKEN, API_BASE_URL} from "./constant/consts";
import axios from "axios/index";
import {NavLink} from "react-router-dom";
import HashRouter from "react-router-dom/es/HashRouter";
import Route from "react-router-dom/es/Route";
import Main from "./container/Main";
import LoginForm from "./container/component/user/auth/LoginForm";
import MyIssues from "./container/component/user/MyIssues"

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            username: null,
            log_username: '',
            log_password: '',
            logged: false,
            token: null
        };
        this.handleLogin = this.handleLogin.bind(this);
        this.handleLogout = this.handleLogout.bind(this);
        this.handleNameChange = this.handleNameChange.bind(this);
        this.handlePassChange = this.handlePassChange.bind(this);

    }

    componentDidMount() {
        this.checkAuth();
    }

    handleLogin(login, password) {
        axios.post(API_BASE_URL + "auth/signin", {username: login, password: password})
            .then(res => {
                localStorage.setItem("accessToken", res.data.accessToken);
                this.checkAuth();
                console.log("handle login");
            }, err => {
                this.setState({logged: false, logErr: true}); console.log("error" + " " + this.state.username);
            });
    }

    handleLogout() {
        localStorage.removeItem(ACCESS_TOKEN);
        this.setState({
            username: null,
            logged: false
        });
        // if (this.props.history.goTo("/"))
        // window.location.replace("/");
        this.props.history.goTo("/")
    }

    handleNameChange(event) {
        this.setState({log_username: event.target.value});
    }

    handlePassChange(event) {
        this.setState({log_password: event.target.value});
    }

    checkAuth() {
        let token = localStorage.getItem(ACCESS_TOKEN);
        if (token) {
            let config = {
                headers: {'Authorization': "Bearer " + token}
            };
            axios.get(API_BASE_URL + "auth/check", config).then(res => {
                console.log("user : " + res.data.username);
                this.setState({
                    logged: true,
                    username: res.data.username
                });
            }, err => {console.log(err.message)});
        }
    }

    render() {
        return(
            <div className="container">
                <HashRouter>

                    <div className="full-content col-sm-8 center-block">
                        <ul className="header">
                            <li><NavLink exact to="/">Issues</NavLink></li>
                            <li><NavLink exact to="/my">My Issues</NavLink></li>
                            {!this.state.logged ?
                                <li><NavLink exact to={"/login"}>{"Sign In"}</NavLink></li> : null}
                            {/*<li><NavLink exact to={"/"}>{this.state.username}</NavLink></li>*/}
                        </ul>
                        {this.state.logged ?
                            <div className="logout-div">
                                <span>{this.state.username}</span>
                                <button className="btn" onClick={this.handleLogout}>Logout</button>
                            </div>
                            : null}
                        <div className="content">
                            <Route exact path="/" render={(props) => <Main />}/>
                            <Route path="/my" render={(props) => <MyIssues logged={this.state.logged} {...props}/>}/>
                            <Route path="/login" render={(props) => <LoginForm onLogin={this.handleLogin}
                                                                               onNameChange={this.handleNameChange}
                                                                               onPassChange={this.handlePassChange}
                                                                               name={this.state.log_username}
                                                                               pass={this.state.log_password}
                                                                               {...props} />}/>
                        </div>
                    </div>

                </HashRouter>
            </div>
        )
    }
}

export default App;

