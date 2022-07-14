import React from "react";
import { FormControl, InputLabel, MenuItem, Select } from "@material-ui/core";

const CustomSelect = ({ name, label, options, error, ...rest }) => {
  return (
    <div className="form-group">
      <FormControl variant="outlined">
        <InputLabel id={name}>{label}</InputLabel>
        <Select name={name} error={error} label={label} {...rest}>
          {options.map((option) => (
            <MenuItem value={option.id}>{option.name}</MenuItem>
          ))}
        </Select>
      </FormControl>
    </div>
  );
};

export default CustomSelect;
