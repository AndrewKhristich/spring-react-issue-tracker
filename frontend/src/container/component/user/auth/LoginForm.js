import React, {Component} from 'react';

class LoginForm extends Component {
    constructor(pros) {
        super(pros);
        this.state = {
            name: '',
            pass: ''
        };
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleNameChange = this.handleNameChange.bind(this);
        this.handlePassChange = this.handlePassChange.bind(this);
    }

    componentDidMount() {

    }

    handleSubmit() {
        let name = this.state.name;
        let pass = this.state.pass;
        this.props.onLogin(name, pass);
        this.props.history.push("/");
    }

    handleNameChange(event) {
        this.setState({name: event.target.value});
    }

    handlePassChange(event) {
        this.setState({pass: event.target.value});
    }

    render() {
        return (
            <div className="container">
                <form autoComplete="off" className="col-sm-3" onSubmit={this.handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">User name</label>
                        <input id="name" className="form-control" type="text" value={this.state.name} onChange={this.handleNameChange} placeholder="user name"/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">User Password</label>
                        <input id="pass" name="password" className="form-control" type="password" value={this.state.pass} onChange={this.handlePassChange} placeholder="password"/>
                    </div>
                    <button className="btn btn-primary" type="submit" value="Submit">Sign In</button>
                </form>
            </div>
        );
    }
}

export default LoginForm;
