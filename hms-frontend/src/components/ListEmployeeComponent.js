import React, {useEffect, useState} from 'react';
import {listEmployees} from '../Service/EmployeeService';

const ListEmployeeComponent=()=>{

    const [employees,setEmployees] = useState([])

    useEffect(() => {
        listEmployees().then((response)=>{
            setEmployees(response.data);
        }).catch(error=>{
            console.error(error);
        })
    }, []);

    return(
        <div className='container'>
            <h2 className='text-center text-white'>List of Employees</h2>
            <table className='table table-striped table-bordered'>
                <thead>
                <tr>
                    <th>Employee Id</th>
                    <th>Employee Name</th>
                    <th>Employee Email</th>
                </tr>
                </thead>
                <tbody>
                {
                    employees.map(employee=>
                        <tr key={employee.id}>
                            <td>{employee.id}</td>
                            <td>{employee.name}</td>
                            <td>{employee.email}</td>
                        </tr>)
                }
                </tbody>
            </table>
        </div>
    )
}

export default ListEmployeeComponent