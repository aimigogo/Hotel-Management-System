import React, { createContext, useContext, useState } from 'react';


const UserContext = createContext();

export const useUser = () => useContext(UserContext);

export const UserProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const [userType,setUserType]=useState('');
    const [employees,setEmployees]=useState([]);


    const updateUser = (userData) => {
        setUser(userData);
    };

    const handleUserAdded = (newEmployee) => {
        setEmployees([...employees, newEmployee]);
    };

    return (
        <UserContext.Provider value={{ user, updateUser, userType,setUserType, employees,setEmployees, handleUserAdded }}>
            {children}
        </UserContext.Provider>
    );
};