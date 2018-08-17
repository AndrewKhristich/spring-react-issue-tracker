import React, {Component} from 'react';

class PageButtons extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        var page = this.props.page;
        var btns = [];

        if (page.totalPages > 1) {
            if (page.pageNumber>1) {
                btns.push(<button key="first" type="button" onClick={this.props.first} className="btn btn-primary">{"<<"}</button>)
            }
            if (page.pageNumber !== 0) {
                btns.push(<button key="prev" type="button" onClick={this.props.prev} className="btn btn-primary">{"<"}</button>)
            }

            if (page.totalPages!==1) {
                btns.push(<button key="current" type="button" className="btn btn-primary">{page.pageNumber + 1}</button>);
            }


            if (!page.last) {
                btns.push(<button key="next" type="button" onClick={this.props.next} className="btn btn-primary">{">"}</button>)
            }
            if (page.pageNumber !== page.totalPages - 2 && !page.last) {
                btns.push(<button key="last" type="button" onClick={this.props.last} className="btn btn-primary">{">>"}</button>)
            }
        }

        return(
            <div className="btn-group">
                {btns}
            </div>
        )
    }
}

export default PageButtons