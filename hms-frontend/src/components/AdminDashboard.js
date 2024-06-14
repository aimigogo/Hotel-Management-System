import React,{useEffect, useState} from "react";
import Sidebar from "../components/Sidebar";
import axios from "axios";
import "../css/AdminDashboard.css"
import {ReactComponent as AddUserIcon} from "../assets/person_add.svg";
// import AddUserForm from "../components/AddUserForm";
// import Modal from "./Modal";
import {listEmployees} from '../Service/EmployeeService';
import {Link} from "react-router-dom";


const AdminDashboard=()=>{

    const [employees,setEmployees] = useState([])
    // const [showAddUserModal, setShowAddUserModal] = useState(false);

    useEffect(() => {
        listEmployees().then((response)=>{
            const filteredEmployees=response.data.filter(employees=>employees.role !=='ADMIN');
            setEmployees(filteredEmployees);
        }).catch(error=>{
            console.error(error);
        })
    }, []);

    // const handleUserAdded=(newEmployee)=>{
    //     setEmployees([...employees,newEmployee]);
    //     setShowAddUserModal(false);
    // }
    // const toggleAddUserModal = () => {
    //     setShowAddUserModal(!showAddUserModal);
    // };

    return(
        <div className='admin-dashboard'>
            <Sidebar/>
            <div className="dashboard-content">
            <h2 className='text-center text-white'>List of Employees</h2>

            <table className='table table-striped table-bordered'>
                <thead>
                <tr>
                    <th>Employee Id</th>
                    <th>Employee Name</th>
                    <th>Employee Email</th>
                    <th>
                        <Link to="/AddUserForm" className="add-user-button">
                            <AddUserIcon /> Add User
                        </Link>
                    </th>
                </tr>
                </thead>
                <tbody>
                {
                    employees.map(employee=>
                        <tr key={employee.id}>
                            <td>{employee.id}</td>
                            <td>{employee.name}</td>
                            <td>{employee.email}</td>
                            <td>
                                <div className="action-buttons">
                                    <button className="edit-button">Edit</button>
                                    <button className="delete-button">Delete</button>
                                </div>
                            </td>
                        </tr>)
                }
                </tbody>
            </table>
            </div>
        </div>
    )
}

export default AdminDashboard