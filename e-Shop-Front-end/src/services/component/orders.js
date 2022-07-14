import http from "../generic/http";
import {baseURL} from "../../config.json";

const url = baseURL + "/shoppingCart";

export const add = (order) => {
    return http.post(`${url}/addCartItem`, order);
};

export const update = (order) => {
    return http.post(url + "/updateQuantity", order);
};

export const getOrderItems = (id) => {
    return http.get(`${url}/getShoppingCartDetails/${id}`);
};
