import React, {Component} from "react";
import Joi from "joi-browser";
import CustomSelect from "./customSelect";
import {Button, InputAdornment, TextField} from "@material-ui/core";

class Form extends Component {
    state = {
        data: {}, errors: {},
    };

    validate = () => {
        const options = {abortEarly: false};
        const {error} = Joi.validate(this.state.data, this.schema, options);
        if (!error) return null;

        const errors = {};
        for (let item of error.details) {
            errors[item.path[0]] = item.message;
        }
        return errors;
    };

    validateProperty = ({name, value}) => {
        const obj = {[name]: value};
        const schema = {[name]: this.schema[name]};
        const {error} = Joi.validate(obj, schema);
        return error ? error.details[0].message : null;
    };

    handleSubmit = (e) => {
        e.preventDefault();

        const errors = this.validate();
        this.setState({errors: errors || {}});
        if (errors) return;

        this.doSubmit();
    };

    renderButton(label) {
        return (<Button
            variant="outlined"
            color="primary"
            {...(this.validate() ? {disabled: true} : {})}
            onClick={this.handleSubmit}
        >
            {label}
        </Button>);
    }

    handleChange = ({currentTarget: input}) => {
        const errors = {...this.state.errors};
        const errorMessage = this.validateProperty(input);
        if (errorMessage) errors[input.name] = errorMessage; else delete errors[input.name];

        const data = {...this.state.data};
        data[input.name] = input.value;

        this.setState({data, errors});
    };

    handleSelectChange = ({target: input}) => {
        const errors = {...this.state.errors};
        const errorMessage = this.validateProperty(input);
        if (errorMessage) errors[input.name] = errorMessage; else delete errors[input.name];

        const data = {...this.state.data};
        data[input.name] = input.value;

        this.setState({data, errors});
    };

    renderSelect(name, label, options) {
        const {data, errors} = this.state;

        return (<CustomSelect
            name={name}
            value={data[name]}
            label={label}
            options={options}
            onChange={this.handleSelectChange}
            error={errors[name]}
        />);
    }

    renderInput(name, label, type = "text", withDollar = false) {
        const {data, errors} = this.state;

        return (<TextField
            {...(errors[name] ? {error: true} : {})}
            label={label}
            variant="outlined"
            type={type}
            helperText={errors[name]}
            onChange={this.handleChange}
            value={data[name]}
            name={name}
            InputProps={withDollar ? {
                startAdornment: (<InputAdornment position="start">$</InputAdornment>),
            } : {}}
        />);
    }
}

export default Form;
