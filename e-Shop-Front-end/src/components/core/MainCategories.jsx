import React, {Component} from "react";
import {Link} from "react-router-dom";
import {getCategories} from "../../services/component/category";
import {withTitleBar} from "../../hoc/withTitleBar";

class MainCategories extends Component {
    state = {
        categories: [],
    };

    async componentDidMount() {
        const {data: categories} = await getCategories();
        this.setState({categories});
    }

    render() {
        return (<div className="content-area">
            <div className="container" style={{maxWidth: "1250px"}}>
                {this.state.categories.map((cat) => {
                    return (<Link
                        key={cat.id}
                        className="box box__hover"
                        to={`/SubCategories/${cat.id}/${cat.name}`}
                    >
                        <div className="imgBox">
                            <img src={`data:image/jpeg;base64,${cat.attachmentURL}`}/>
                        </div>
                        <div className="catigore__name">{cat.name}</div>
                    </Link>);
                })}
            </div>
        </div>);
    }
}

export default withTitleBar(MainCategories, "Main Categories");
