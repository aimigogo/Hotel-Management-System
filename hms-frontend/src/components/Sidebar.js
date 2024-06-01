import React, {useState} from 'react';
import './Sidebar.css';
import { ReactComponent as Logo } from '../assets/Logo.svg';
import {ReactComponent as DashboardIcon } from '../assets/Inactive/dashboard.svg';
import {ReactComponent as DashboardIconActive } from '../assets/Active/dashboard.svg';
import {ReactComponent as RoomIcon } from '../assets/Inactive/hotel.svg';
import {ReactComponent as RoomIconActive } from '../assets/Active/hotel.svg';
import {ReactComponent as AccountIcon } from '../assets/Inactive/account_balance.svg';
import {ReactComponent as AccountIconActive } from '../assets/Active/account_balance.svg';
import {ReactComponent as UsersIcon } from '../assets/Inactive/people.svg';
import {ReactComponent as UsersIconActive } from '../assets/Active/people.svg';
import {ReactComponent as SettingsIcon } from '../assets/Inactive/settings.svg';
import {ReactComponent as SettingsIconActive } from '../assets/Active/settings.svg';
import {ReactComponent as ExitIcon } from '../assets/Inactive/exit_to_app.svg';
import {ReactComponent as ExitIconActive } from '../assets/Active/exit_to_app.svg';



export default function Sidebar() {
    const [activeItem,setActiveItem]=useState('dashboard');

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
                    <a href="#dashboard">
                        {activeItem === 'dashboard' ? <DashboardIconActive className="sidebar-icon" />
                            : <DashboardIcon className="sidebar-icon" />}
                        Dashboard</a></li>
                <li className={`sidebar-item ${activeItem === 'rooms'? 'active':''}`}
                    onClick={()=>handleItemClick('rooms')}>
                    <a href="#rooms">
                        {activeItem === 'rooms' ? <RoomIconActive className="sidebar-icon" />
                            : <RoomIcon className="sidebar-icon" />}
                        Rooms</a></li>
                <li className={`sidebar-item ${activeItem === 'accounts'? 'active':''}`}
                    onClick={()=>handleItemClick('accounts')}>
                    <a href="#accounts">
                        {activeItem === 'accounts' ? <AccountIconActive className="sidebar-icon" />
                            : <AccountIcon className="sidebar-icon" />}
                        Accounts</a></li>
                <li className={`sidebar-item ${activeItem === 'users'? 'active':''}`}
                    onClick={()=>handleItemClick('users')}>
                    <a href="#users">
                        {activeItem === 'users' ? <UsersIconActive className="sidebar-icon" />
                            : <UsersIcon className="sidebar-icon" />}
                        Users</a></li>
                <li className={`sidebar-item ${activeItem === 'settings'? 'active':''}`}
                    onClick={()=>handleItemClick('settings')}>
                    <a href="#settings">
                        {activeItem === 'settings' ? <SettingsIconActive className="sidebar-icon" />
                            : <SettingsIcon className="sidebar-icon" />}
                        Settings</a></li>
                <li className={`sidebar-item ${activeItem === 'exit'? 'active':''}`}
                    onClick={()=>handleItemClick('exit')}>
                    <a href="#exit">
                        {activeItem === 'exit' ? <ExitIconActive className="sidebar-icon" />
                            : <ExitIcon className="sidebar-icon" />}
                        Exit</a></li>
            </ul>
        </div>
    )
}