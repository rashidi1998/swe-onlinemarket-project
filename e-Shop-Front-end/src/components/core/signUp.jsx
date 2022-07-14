import React from "react";
import Form from "../../common/form";
import * as Joi from "joi-browser";
import {addUser} from "../../services/component/users";
import {FaSignInAlt} from "react-icons/fa";

import {toast, ToastContainer} from "react-toastify";
import {isLoggedIn} from "../../services/generic/currentUserValidation";

class SignUp extends Form {

    state = {
        data: {email: "", name: "", phoneNumber: undefined, password: ""}, errors: {},
    };

    schema = {
        email: Joi.string().required().email().label("Email"),
        name: Joi.string().required().label("User Name"),
        phoneNumber: Joi.number().required().label("Phone Number"),
        password: Joi.string().required().min(8).label("Password"),
    };

    doSubmit = async () => {
        const newUser = {...this.state.data};
        await addUser(newUser)
            .then(({data: user}) => {
                if (user) {
                    this.props.history.replace("/logIn")
                } else {
                    toast.error("Email is Taken");
                }
            })
            .catch((response) => {
                if (response.response.status === 400) {
                    toast.error("Email is Taken");
                }
            });
    };

    render() {
        if (isLoggedIn()) this.props.history.replace("/MainCategories");
        return (<div className="center-box">
            <div className="login__box">
                <div className="login__form">
                    <h2 className="login__heading">
                        <FaSignInAlt className="login__icon"/>
                        SignUp
                    </h2>
                    <form noValidate autoComplete="off">
                        <div className="row">
                            <div className="col p-2">
                                {this.renderInput("email", "Email", "email")}
                            </div>
                            <div className="col p-2">
                                {this.renderInput("name", "Name")}
                            </div>
                        </div>
                        <div className="row">
                            <div className="col p-2">
                                {this.renderInput("phoneNumber", "Phone Number", 'number')}
                            </div>
                            <div className="col p-2">
                                {this.renderInput("password", "Password", "password")}
                            </div>
                        </div>
                        <div className="row sigin-button__container">
                            <div className="col-4 sigin__button">
                                {this.renderButton("Submit")}
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <ToastContainer
                position="top-center"
                autoClose={5000}
                hideProgressBar={false}
                newestOnTop={false}
                closeOnClick
                rtl={false}
                pauseOnFocusLoss
                draggable
                pauseOnHover
            />
        </div>);
    }
}

export default SignUp;
