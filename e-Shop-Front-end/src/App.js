import {Link, NavLink, Redirect, Route, Switch} from "react-router-dom";
import MainCategories from "./components/core/MainCategories";
import React, {Component} from "react";
import NotFound from "./common/NotFound";
import LogIn from "./components/core/LogIn";
import SubCategories from "./components/core/SubCategories";
import UserItems from "./components/core/UserItems";
import SignUp from "./components/core/signUp";
import {FiDollarSign} from "react-icons/all";
import UserContext from "./context/userContext";
import UserOrderList from "./components/core/userOrderList";
import ProtectedRoute from "./common/protectedRoute";
import Profile from "./components/core/profile";
import profileimg from "./images/profileimg.jpg";
import "./App.css";

export class App extends Component {
    state = {currentUser: {}, bagItemsQuantity: 0, shoppingCartId: null};

    userDataUpdated = (user) => {
        const loginData = user;
        this.setState({
            currentUser: loginData.customer,
            bagItemsQuantity: loginData.bagItemsQuantity,
            shoppingCartId: loginData.shoppingCartId,
        });
    };

    updateNumber = (number) => {
        localStorage.setItem("user", JSON.stringify({
            customer: this.state.currentUser, shoppingCartId: this.state.shoppingCartId, bagItemsQuantity: number,
        }));
        this.setState({bagItemsQuantity: number});
    };

    updateShoppingCartId = (shoppingCartId) => {
        localStorage.setItem("user", JSON.stringify({
            customer: this.state.currentUser,
            shoppingCartId: shoppingCartId,
            bagItemsQuantity: this.state.bagItemsQuantity,
        }));
        this.setState({shoppingCartId: shoppingCartId});
    }

    componentWillMount() {
        const user = localStorage.getItem("user");
        if (user) {
            const loginData = JSON.parse(user);
            this.setState({
                currentUser: loginData.customer,
                bagItemsQuantity: loginData.bagItemsQuantity,
                shoppingCartId: loginData.shoppingCartId,
            });
        }
    }

    renderUserData = () => {
        const {username, customerId} = this.state.currentUser;
        if (customerId !== undefined) {
            return (<React.Fragment>
                {(<div className="shopping-cart__link">
                    <Link to="/userOrderList">
                        <i className="fa fa-shopping-cart"/>
                    </Link>
                    <div>{this.state.bagItemsQuantity}</div>
                </div>)}

                <div className="navBar__profile">
                    <img
                        src={profileimg}
                        className="rounded-circle"
                        height="25"
                        alt=""
                    />
                    {username}
                    <div className="sub-menu-1">
                        <ul>
                            {(<Link to="/profile">
                                <li>Profile</li>
                            </Link>)}
                            <Link to="/LogIn">
                                <li
                                    onClick={() => {
                                        localStorage.removeItem("user");
                                        let currentUser = {};
                                        this.setState({currentUser});
                                    }}
                                >
                                    Log Out
                                </li>
                            </Link>
                        </ul>
                    </div>
                </div>
            </React.Fragment>);
        }
        return (<div>
            <NavLink className="navbar-brand " to="/LogIn">
                Login
            </NavLink>
            <span className="right-border"/>
            <NavLink className="navbar-brand" to="/signUp">
                Sign Up
            </NavLink>
        </div>);
    };

    render() {
        return (<UserContext.Provider
            value={{
                currentUser: this.state.currentUser,
                shoppingCartId: this.state.shoppingCartId,
                bagItemsQuantity: this.state.bagItemsQuantity,
                updateNumber: this.updateNumber,
                updateShoppingCartId: this.updateShoppingCartId
            }}
        >
            <React.Fragment>
                <nav className="navbar navbar--green navbar__sticky">
                    <NavLink className="navbar-brand" to="/MainCategories">
                        BestPrice
                        <FiDollarSign className="doller--icon"/>
                    </NavLink>
                    {this.renderUserData()}
                </nav>
                <Switch>
                    <Route path="/signUp" exact component={SignUp}/>
                    <Route
                        path="/LogIn"
                        render={(props) => (
                            <LogIn onUpdateUserData={(user) => this.userDataUpdated(user)} {...props}/>)}
                    />
                    <ProtectedRoute path="/profile" component={Profile}/>
                    <ProtectedRoute
                        path="/userOrderList"
                        exact
                        component={UserOrderList}
                    />
                    <Route path="/Items/:id/:name" component={UserItems}/>
                    <Route path="/MainCategories" component={MainCategories}/>
                    <Route path="/SubCategories/:id/:name" component={SubCategories}/>
                    <Route path="/notFound" component={NotFound}/>
                    <Route path="/" exact component={MainCategories}/>
                    <Redirect to="/notFound"/>
                </Switch>
            </React.Fragment>
        </UserContext.Provider>);
    }
}
