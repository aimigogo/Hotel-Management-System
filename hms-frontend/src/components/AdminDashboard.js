import React,{useEffect, useState} from "react";
import Sidebar from "../components/Sidebar";
import "../css/AdminDashboard.css"
import {ReactComponent as AddUserIcon} from "../assets/person_add.svg";
import {listEmployees,updateEmployee,deleteEmployee} from '../Service/EmployeeService';
import {useNavigate} from "react-router-dom";
import{getSections,getShifts} from "../Service/EnumService";



const AdminDashboard=()=>{

    const [employees,setEmployees] = useState([])
    const [editingEmployeeId, setEditingEmployeeId] = useState(null);
    const [editableEmployee, setEditableEmployee] = useState({});
    const [sections, setSections] = useState([]);
    const [shifts, setShifts] = useState([]);

    const navigate=useNavigate();

    useEffect(() => {
        listEmployees().then((response)=>{
            const filteredEmployees=response.data.filter(employees=>employees.role !=='ADMIN');
            setEmployees(filteredEmployees);
        }).catch(error=>{
            console.error(error);
            console.error('Error fetching employee:', error);
        })
        getSections().then(data => {
            setSections(data);
        }).catch(error => {
            console.error('Error fetching sections:', error);
        });

        getShifts().then(data => {
            setShifts(data);
        }).catch(error => {
            console.error('Error fetching shifts:', error);
        });
    }, []);

    const handleUserAdded=(newEmployee)=>{
        setEmployees([...employees,newEmployee]);

    }
    const handleEditClick = (employee) => {
        setEditingEmployeeId(employee.id);
        setEditableEmployee(employee);
    };

    const handleSaveClick = async () => {
        try {
            await updateEmployee(editingEmployeeId, editableEmployee);
            setEmployees(employees.map(emp => emp.id === editingEmployeeId ? editableEmployee : emp));
            setEditingEmployeeId(null);
        } catch (error) {
            console.error('Error updating employee:', error);
        }
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setEditableEmployee({
            ...editableEmployee,
            [name]: value
        });
    };

    const handleDeleteButton=async(employeeId)=>{
        try {
            await deleteEmployee(employeeId);
            setEmployees(employees.filter(emp => emp.id !== employeeId));
        } catch (error) {
            console.error('Error deleting employee:', error);
        }
    }

    const navigateToAddUserForm = () => {
        navigate('/AddUserForm');
    };

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
                        <th>Section</th>
                        <th>Shift</th>
                        <th>
                            <button className="add-user-button" onClick={navigateToAddUserForm}>
                                <AddUserIcon /> Add User
                            </button>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    {employees.map(employee => (
                        <tr key={employee.id}>
                            <td>{employee.id}</td>
                            <td>
                                {editingEmployeeId === employee.id ? (
                                    <input
                                        type="text"
                                        name="name"
                                        value={editableEmployee.name}
                                        onChange={handleChange}
                                    />
                                ) : (
                                    employee.name
                                )}
                            </td>
                            <td>
                                {editingEmployeeId === employee.id ? (
                                    <input
                                        type="email"
                                        name="email"
                                        value={editableEmployee.email}
                                        onChange={handleChange}
                                    />
                                ) : (
                                    employee.email
                                )}
                            </td>
                            <td>
                                {editingEmployeeId === employee.id ? (
                                    <input
                                        type="section"
                                        name="section"
                                        value={editableEmployee.section}
                                        onChange={handleChange}
                                    />
                                ) : (
                                    employee.section
                                )}
                            </td>
                            <td>
                                {editingEmployeeId === employee.id ? (
                                    <input
                                        type="shift"
                                        name="shift"
                                        value={editableEmployee.shift}
                                        onChange={handleChange}
                                    />
                                ) : (
                                    employee.shift
                                )}
                            </td>
                            <td>
                                <div className="action-buttons">
                                    {editingEmployeeId === employee.id ? (
                                        <button className="save-button" onClick={handleSaveClick}>Save</button>
                                    ) : (
                                        <button className="edit-button" onClick={() => handleEditClick(employee)}>Edit</button>
                                    )}
                                    <button className="delete-button" onClick={() => handleDeleteButton(employee.id)}>Delete</button>
                                </div>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>


            </div>
        </div>
    )
}

export default AdminDashboard