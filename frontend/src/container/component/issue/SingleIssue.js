import React, {Component} from 'react';

class SingleIssue extends Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        var status = this.props.issue.status;

        return(
            <div className="issue-div" >
                <div>
                    <span className={status==='Resolved' ? "glyphicon glyphicon-ok" : "glyphicon glyphicon-remove"}/>
                </div>
                <div>
                    <label>{this.props.issue.issueName}</label><br/>
                    <label>{this.props.issue.publishedAt}</label>
                </div>
            </div>
        )
    }

}

export default SingleIssue;