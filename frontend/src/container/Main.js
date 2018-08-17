import React, {Component} from 'react';
import axios from 'axios'
import { Redirect } from 'react-router'
import '../App.css';
import SingleIssue from './component/issue/SingleIssue';
import PageButtons from './component/PageButtons';

class Main extends Component {

    constructor(props) {
        super(props);
        this.state = {
            issues: new Array,
            pageable: null,
            page: {
                last: false,
                totalElements: 0,
                totalPages: 0,
                pageNumber: 0,
                pageSize: 5
            }
        };

        this.handleFirst = this.handleFirst.bind(this);
        this.handleLast = this.handleLast.bind(this);
        this.handleNext = this.handleNext.bind(this);
        this.handlePrev = this.handlePrev.bind(this);
        this.handleLogout = this.handleLogout.bind(this);
    }

    componentDidMount() {
        var pageNum = this.state.page.pageNumber;
        var pageSize = this.state.page.pageSize;
        this.updatePage(pageNum, pageSize);
    }

    handleLogout() {
        localStorage.removeItem("accessToken");
        this.props.history.push("/login");
    }

    handleNext() {
        var pageNum = this.state.page.pageNumber + 1;
        var pageSize = this.state.page.pageSize;
        this.updatePage(pageNum, pageSize);
    }

    handlePrev() {
        var pageNum = this.state.page.pageNumber - 1;
        var pageSize = this.state.page.pageSize;
        this.updatePage(pageNum, pageSize);
    }

    handleLast() {
        var pageNum = this.state.page.totalPages;
        var pageSize = this.state.page.pageSize;
        this.updatePage(pageNum, pageSize);
    }

    handleFirst() {
        var pageNum = 0;
        var pageSize = this.state.page.pageSize;
        this.updatePage(pageNum, pageSize);
    }


    updatePage = (pageNum, pageSize) => {
        axios.get("http://localhost:8080/api/issues?pageNum=" + pageNum + "&pageSize=" + pageSize)
            .then(res => {
                let data = res.data;
                this.setState({
                    issues: res.data.content,
                    page: {
                        last: data.last,
                        totalElements: data.totalElements,
                        totalPages: data.totalPages,
                        pageNumber: data.number,
                        pageSize: data.size
                    }
                });
            }, err => {
                console.log(err);
            })
    };

    createList = () => {
        let list = [];
        var issues = this.state.issues;

        for (let iss of issues) {
            list.push(<SingleIssue key={iss.id} issue={iss}/>)
        }
        return list;
    };

    render() {
        var issues = this.state.issues;
        var page = this.state.page;
        return (
            <div className="Main container">
                <div>
                    <ul>
                        {this.createList()}
                    </ul>
                </div>
                <div>
                    <PageButtons page={page}
                                 first={this.handleFirst}
                                 next={this.handleNext}
                                 prev={this.handlePrev}
                                 last={this.handleLast}/>
                </div>
                <button onClick={this.handleLogout}>Logout</button>
            </div>
        );
    }
}


export default Main;

