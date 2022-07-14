import React, {Component} from "react";
import {Link} from "react-router-dom";
import {getSubById} from "../../services/component/category";
import {withTitleBar} from "../../hoc/withTitleBar";

class SubCategories extends Component {
    state = {
        categories: [],
    };

    async componentDidMount() {
        const {data: categories} = await getSubById(this.props.match.params.id);
        this.setState({categories});
    }

    render() {
        const Hoc = withTitleBar(() => <SubCategoryComponent {...this.state} />, this.props.match.params.name);
        return <Hoc/>;
    }
}

const SubCategoryComponent = ({categories}) => {
    return (<div className="container">
        <div className="container">
            {categories.map((cat) => {
                return (<Link
                    className="box box__hover"
                    to={`/Items/${cat.id}/${cat.name}`}
                >
                    <div className="imgBox">
                        <img src={`data:image/jpeg;base64,${cat.attachmentURL}`} alt="" srcset=""/>
                    </div>
                    <h1 className="catigore__name">{cat.name}</h1>
                </Link>);
            })}
        </div>
    </div>);
};

export default SubCategories;
