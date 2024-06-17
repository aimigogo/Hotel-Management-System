import axios from "axios";


export const listEmployees=()=> axios.get('http://localhost:8080/api/auth/employees');

export const updateEmployee = (userId, userData) => axios.put(`http://localhost:8080/api/auth/employees/update/${userId}`, userData);

export const deleteEmployee = (userId) => axios.delete(`http://localhost:8080/api/auth/employees/delete/${userId}`);