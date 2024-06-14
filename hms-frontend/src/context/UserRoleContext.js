import React, { createContext, useState, useContext } from 'react';

const UserRoleContext = createContext();

export const useUser = () => {
    return useContext(UserRoleContext);
};

export const UserProvider = ({ children }) => {
    const [userType, setUserType] = useState(''); // 'admin' or 'employee'

    return (
        <UserRoleContext.Provider value={{ userType, setUserType }}>
            {children}
        </UserRoleContext.Provider>
    );
};