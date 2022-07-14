import React from "react";

function Input({ name, type = "text", label, value, onChange, error, style }) {
  return (
    <div className="form-group">
      <label htmlFor={name}>{label}</label>
      <input
        type={type}
        className="form-control"
        id={name}
        value={value}
        onChange={onChange}
        placeholder={name}
        name={name}
        style={style}
      />
      {error && <div className="alert alert-danger">{error}</div>}
    </div>
  );
}

export default Input;
