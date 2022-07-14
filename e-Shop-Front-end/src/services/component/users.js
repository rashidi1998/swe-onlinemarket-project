import http from "../generic/http";
import {baseURL} from "../../config.json";

const url = baseURL + "/customers";

export const addUser = (newUser) => {
    return http.post(`${url}/signup`, {
        username: newUser.name, password: newUser.password, email: newUser.email, phoneNumber: newUser.phoneNumber
    });
};

export const logIn = (user) => {
    return http.post(url + `/login`, {email: user.email, password: user.password});
};
