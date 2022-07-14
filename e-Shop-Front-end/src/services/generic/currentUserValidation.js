import React from "react";

export const isLoggedIn = () => {
    return !!localStorage.getItem("user");
};
