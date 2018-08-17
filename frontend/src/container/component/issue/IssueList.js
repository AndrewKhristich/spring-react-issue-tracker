import React, {Component} from 'react';
import '../../../App.css';
import SingleIssue from './SingleIssue';
import {ISSUE_LIST_SIZE} from "../../../constant/consts";
import PageButtons from "../PageButtons";


class IssueList extends Component{

    constructor(props) {
        super(props);
        this.state = {
          page: null
        };
        this.handleFirst = this.handleFirst.bind(this);
        this.handleLast = this.handleLast.bind(this);
        this.handleNext = this.handleNext.bind(this);
        this.handlePrev = this.handlePrev.bind(this);
        this.handleLogout = this.handleLogout.bind(this);
    }

    componentDidMount() {
        this.setState({
            page: this.props.page
        })
    }

    handleLogout() {
        localStorage.removeItem("accessToken");
        this.props.history.push("/login");
    }

    handleNext() {
        var pageNum = this.props.page.pageNumber + 1;
        var pageSize = this.props.page.pageSize;
        this.updatePage(pageNum, pageSize);
    }

    handlePrev() {
        var pageNum = this.props.page.pageNumber - 1;
        var pageSize = this.props.page.pageSize;
        this.updatePage(pageNum, pageSize);
    }

    handleLast() {
        var pageNum = this.props.page.totalPages;
        var pageSize = this.props.page.pageSize;
        this.updatePage(pageNum, pageSize);
    }

    handleFirst() {
        var pageNum = 0;
        var pageSize = this.props.page.pageSize;
        this.updatePage(pageNum, pageSize);
    }

    createList = () => {
        let list = [];
        let issues = this.props.issues;
        for (let iss of issues) {
            list.push(<SingleIssue key={iss.id} issue={iss}/>)
        }

        return list;
    };

    render() {
        var page = this.props.page;
        return(
            <div className="issue-list">
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
            </div>
        )
    }

}

export default IssueList;