import React, {Component} from 'react';

class SingleIssue extends Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        var status = this.props.issue.status;

        return(
            <div className="issue-div col-sm-7">
                <div>
                    <span className={status==='Resolved' ? "glyphicon glyphicon-ok" : "glyphicon glyphicon-remove"}/>
                </div>
                <div>
                    <label className="issue-name">{this.props.issue.issueName}</label><br/>
                    <label className="issue-date">{this.props.issue.publishedAt}</label>
                </div>
            </div>
        )
    }

}

export default SingleIssue;