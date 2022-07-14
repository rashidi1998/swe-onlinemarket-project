import http from "../generic/http";
import {baseURL} from "../../config.json";

const url = baseURL + "/categories";

export const getCategories = () => {
    return http.get(url + "/main");
};

export const getSubById = (id) => {
    return http.get(`${url}/subById/${id}`);
};
