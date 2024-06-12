import axios from "axios";

const Rest_Api_Base_Url='http://localhost:8080/api/auth';

export const listEmployees=()=> axios.get(Rest_Api_Base_Url);
