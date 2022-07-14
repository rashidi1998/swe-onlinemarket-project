import React, {Component} from "react";
import profileimg from "../../images/profileimg.jpg";
import {Link} from "react-router-dom";

class Profile extends Component {
    state = {currentUser: {}};

    userDataUpdated = (user) => {
        const currentUser = user;
        this.setState({currentUser});
    };

    componentWillMount() {
        const user = localStorage.getItem("user");
        if (user) {
            const currentUser = JSON.parse(user);
            this.setState({currentUser: currentUser.customer});
        }
    }

    render() {
        return (<div className="container profile__container">
            <div className="profile__content">
                <div className="profile__image">
                    <img src={profileimg}/>
                </div>
                <div className="profile__name">
                    <div>
                        <i className="fa fa-user edit__icone give__color"/>
                        {": "}
                        {this.state.currentUser.username}
                    </div>
                    <div>
                        <i className="fa fa-envelope edit__icone give__color"/>
                        {": "}
                        {this.state.currentUser.email}
                    </div>
                    <div>
                        <i className="fa fa-mobile edit__icone give__color"/>
                        {": "}
                        {this.state.currentUser.phoneNumber}
                    </div>
                </div>
            </div>
            <div className="profile__links">
                <Link className="profile__link" to="/MainCategories">
                    Main
                </Link>
                <Link className="profile__link" to="/userOrderList">
                    Cart
                </Link>
            </div>
        </div>);
    }
}

export default Profile;
