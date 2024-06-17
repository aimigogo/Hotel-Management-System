import React, {useEffect, useState} from "react";
import Sidebar from "./Sidebar";
import {ReactComponent as CalendarIcon} from "../assets/calendar_month.svg";

import{listEmployees} from "../Service/EmployeeService";
import "../css/EmployeeDashboard.css"

const EmployeeDashboard=()=>{

    const [employees,setEmployees] = useState([])

    useEffect(() => {
        listEmployees().then((response)=>{
            setEmployees(response.data);
        }).catch(error=>{
            console.error(error);
        })
    }, []);

    return(
        <div className="employee-dashboard">
            <Sidebar/>
            <div className="dashboard-content">
                <h2 className='text-center text-white'><CalendarIcon/>Weekly Schedule</h2>
                <table className='table table-striped table-bordered'>
                    <thead>
                    <tr>
                        <th>Employee Name</th>
                        <th>Monday</th>
                        <th>Tuesday</th>
                        <th>Wednesday</th>
                        <th>Thursday</th>
                        <th>Friday</th>
                        <th>Saturday</th>
                        <th>Sunday</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        employees.map(employees=>
                        <tr key={employees.id}>
                            <td>{employees.name}</td>
                            <td>{employees.section}<br />{employees.shift}</td>
                            <td>{employees.section}<br />{employees.shift}</td>
                            <td>{employees.section}<br />{employees.shift}</td>
                            <td>{employees.section}<br />{employees.shift}</td>
                            <td>{employees.section}<br />{employees.shift}</td>
                            <td>{employees.section}<br />{employees.shift}</td>
                            <td>{employees.section}<br />{employees.shift}</td>
                        </tr>
                        )
                    }
                    </tbody>
                </table>
            </div>

        </div>
    )
}
export default EmployeeDashboard