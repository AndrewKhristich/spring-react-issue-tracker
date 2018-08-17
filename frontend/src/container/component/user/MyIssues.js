import React, {Component} from 'react';
import '../../../App.css';
import axios from 'axios'
import {ISSUE_LIST_SIZE, API_BASE_URL, ACCESS_TOKEN} from "../../../constant/consts";
import IssueList from "../issue/IssueList";


class MyIssues extends Component {

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
        if (localStorage.getItem(ACCESS_TOKEN)==null) {
            this.props.history.push("/login");
        } else {

            let config = {
                headers: {'Authorization': "Bearer " + localStorage.getItem(ACCESS_TOKEN)}
            };

            axios.get(API_BASE_URL + "issues/myissues?pageNum=" + 0 + "&pageSize=" + ISSUE_LIST_SIZE, config)
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
    }

    findCurrentUserIssues(user) {

    }

    render() {
        let page = this.state.page;
        let issues = this.state.issues;
        return (
            <div>
                <IssueList page={page} issues={issues}/>
            </div>
        )
    }

}

export default MyIssues;