import React, {useState} from 'react';
import {Link} from "react-router-dom";
import '../css/Sidebar.css';
import { ReactComponent as Logo } from '../assets/Logo.svg';
import {ReactComponent as DashboardIcon } from '../assets/Inactive/dashboard.svg';
import {ReactComponent as DashboardIconActive } from '../assets/Active/dashboard.svg';
import {ReactComponent as RoomIcon } from '../assets/Inactive/hotel.svg';
import {ReactComponent as RoomIconActive } from '../assets/Active/hotel.svg';
import {ReactComponent as StockIcon } from '../assets/Inactive/store.svg';
import {ReactComponent as StockIconActive } from '../assets/Active/store.svg';
import {ReactComponent as SettingsIcon } from '../assets/Inactive/settings.svg';
import {ReactComponent as SettingsIconActive } from '../assets/Active/settings.svg';
import {ReactComponent as ExitIcon } from '../assets/Inactive/exit_to_app.svg';
import {ReactComponent as ExitIconActive } from '../assets/Active/exit_to_app.svg';
import {useUser} from "../context/UserRoleContext";



export default function Sidebar() {
    const [activeItem,setActiveItem]=useState('dashboard');
    const {userType}=useUser();

    const handleItemClick=(item)=>{
        setActiveItem(item);

    }
    return (
        <div className="sidebar">
            <div className="sidebar-header">
                <Logo className="sidebar-logo" />
            </div>
            <ul className="sidebar-links">
                <li className={`sidebar-item ${activeItem === 'dashboard'? 'active':''}`}
                    onClick={()=>handleItemClick('dashboard')}>
                    <Link to={userType === 'ADMIN'?"/AdminDashboard":"/EmployeeDashboard"} >
                        {activeItem === 'dashboard' ? <DashboardIconActive className="sidebar-icon" />
                            : <DashboardIcon className="sidebar-icon" />}
                        Dashboard</Link></li>
                <li className={`sidebar-item ${activeItem === 'rooms' ? 'active' : ''}`}
                    onClick={() => handleItemClick('rooms')}>
                    <Link to="/RoomPage">
                        {activeItem === 'rooms' ? <RoomIconActive className="sidebar-icon" />
                            : <RoomIcon className="sidebar-icon" />}
                        Rooms
                    </Link>
                </li>
                <li className={`sidebar-item ${activeItem === 'stock'? 'active':''}`}
                    onClick={()=>handleItemClick('stock')}>
                    <Link to="/StockPage">
                        {activeItem === 'stock' ? <StockIconActive className="sidebar-icon" />
                            : <StockIcon className="sidebar-icon" />}
                        Stock
                </Link>
            </li>
                <li className={`sidebar-item ${activeItem === 'settings'? 'active':''}`}
                    onClick={()=>handleItemClick('settings')}>
                    <Link to="/SettingsPage">
                        {activeItem === 'settings' ? <SettingsIconActive className="sidebar-icon" />
                            : <SettingsIcon className="sidebar-icon" />}
                        Settings
                    </Link>
                </li>
                <li className={`sidebar-item ${activeItem === 'exit'? 'active':''}`}
                    onClick={()=>handleItemClick('exit')}>
                    <Link to="/Login">
                        {activeItem === 'exit' ? <ExitIconActive className="sidebar-icon" />
                            : <ExitIcon className="sidebar-icon" />}
                        Exit
                    </Link>
                </li>
            </ul>
        </div>
    )
}