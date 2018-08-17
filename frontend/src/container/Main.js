import React, {Component} from 'react';
import axios from 'axios'
import '../App.css';
import SingleIssue from './component/issue/SingleIssue';
import PageButtons from './component/PageButtons';
import {ACCESS_TOKEN, API_BASE_URL, ISSUE_LIST_SIZE} from "../constant/consts";
import IssueList from "./component/issue/IssueList";

class Main extends Component {

    constructor(props) {
        super(props);
        this.state = {
            issues: [],
            page: {
                last: null,
                totalElements: null,
                totalPages: null,
                pageNumber: null,
                pageSize: null
            }
        };
    }

    componentDidMount() {
        let config = {
            headers: {'Authorization': "Bearer " + localStorage.getItem(ACCESS_TOKEN)}
        };

        axios.get(API_BASE_URL + "issues?pageNum=" + 0 + "&pageSize=" + ISSUE_LIST_SIZE, config)
            .then(res => {
                let page = new Object;
                page.last = res.data.last;
                page.totalElements = res.data.totalElements;
                page.totalPages = res.data.totalPages;
                page.pageNumber = res.data.number;
                page.pageSize = res.data.size;
                this.setState({
                    page: page,
                    issues: res.data.content
                });
            })
    }


    render() {
        var issues = this.state.issues;
        var page = this.state.page;
        return (
            <div className="Main container">
                <IssueList issues={issues} page={page}/>
            </div>
        );
    }
}


export default Main;

