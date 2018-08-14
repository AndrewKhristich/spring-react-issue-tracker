import {
    Route,
    NavLink,
    HashRouter
} from "react-router-dom";
import React, {Component} from 'react';
import axios from 'axios'
import Main from "./Main"
import LoginForm from "./component/LoginForm"


class MainHeader extends Component {

    constructor(props) {
        super(props);
        this.state = {
            name: '',
            logged: false
        };
    }

    componentDidMount() {
        if(!localStorage.getItem("accessToken")) {
            console.log("no tocken");
        } else { console.log("tocken : " + localStorage.getItem("accessToken")) }

        axios.get("http://localhost:8080/api/auth/check").then(res => {
            this.setState({name: res.data.username}); console.log("user : " + res.data.username)
        }, err => {console.log(err.message)});
    }

    render() {
        var username = this.state.name;
        var logged = this.state.logged;

        return (
            <HashRouter>

                <div>
                    <ul className="header">
                        <li><NavLink exact to="/">Issues</NavLink></li>
                        <li><NavLink exact to="/myissues">My Issues</NavLink></li>
                        <li><NavLink exact to={logged ? "/" : "/login"}>{logged ? username : "Sign In"}</NavLink></li>
                        </ul>
                    <div className="content">
                        <Route exact path="/" component={Main}/>
                        <Route path="/login" component={LoginForm}/>
                    </div>
                </div>

            </HashRouter>
        )
    }

}

export default MainHeader;