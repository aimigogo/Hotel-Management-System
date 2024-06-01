import React, {useState} from 'react';
import './Sidebar.css';
import { ReactComponent as Logo } from '../assets/Logo.svg';
//import {ReactComponent as DashboardIcon } from '../assets/Inactive/dashboard.svg';
import {ReactComponent as DashboardIconActive } from '../assets/Active/dashboard.svg';
import {ReactComponent as RoomIcon } from '../assets/Inactive/hotel.svg';
//import {ReactComponent as RoomIconActive } from '../assets/Active/hotel_active.svg';
import {ReactComponent as AccountIcon } from '../assets/Inactive/account_balance.svg';
//import {ReactComponent as AccountIconActive } from '../assets/Active/account_balance_active.svg';
import {ReactComponent as UsersIcon } from '../assets/Inactive/people.svg';
//import {ReactComponent as UsersIconActive } from '../assets/Active/people_Active.svg';
import {ReactComponent as SettingsIcon } from '../assets/Inactive/settings.svg';
//import {ReactComponent as SettingsIconActive } from '../assets/Active/settings_active.svg';
import {ReactComponent as ExitIcon } from '../assets/Inactive/exit_to_app.svg';
//import {ReactComponent as ExitIconActive } from '../assets/Active/exit_to_app_active.svg';



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
                        <DashboardIconActive classname="sidebar-icon" />
                        Dashboard</a></li>
                <li className={`sidebar-item ${activeItem === 'rooms'? 'active':''}`}
                    onClick={()=>handleItemClick('rooms')}>
                    <a href="#rooms">
                        <RoomIcon classname="sidebar-icon"/>
                        {/*{`sidebar-icon ${activeItem ==='rooms' ? RoomIconActive:RoomIcon}`} />*/}
                        Rooms</a></li>
                <li className={`sidebar-item ${activeItem === 'accounts'? 'active':''}`}
                    onClick={()=>handleItemClick('accounts')}>
                    <a href="#accounts">
                        <AccountIcon classname="sidebar-icon"/>
                        {/*{`sidebar-icon ${activeItem ==='accounts' ? AccountIconActive:AccountIcon}`} />*/}
                        Accounts</a></li>
                <li className={`sidebar-item ${activeItem === 'users'? 'active':''}`}
                    onClick={()=>handleItemClick('users')}>
                    <a href="#users">
                        <UsersIcon classname="sidebar-icon"/>
                        {/*{`sidebar-icon ${activeItem ==='users' ? UsersIconActive:UsersIcon}`} />*/}
                        Users</a></li>
                <li className={`sidebar-item ${activeItem === 'settings'? 'active':''}`}
                    onClick={()=>handleItemClick('settings')}>
                    <a href="#settings">
                        <SettingsIcon classname="sidebar-icon"/>
                        {/*{'sidebar-icon ${activeItem ==='settings' ? SettingsIconActive:SettingsIcon}`} />*/}
                        Settings</a></li>
                <li className={`sidebar-item ${activeItem === 'exit'? 'active':''}`}
                    onClick={()=>handleItemClick('exit')}>
                    <a href="#exit">
                        <ExitIcon classname="sidebar-icon"/>
                                      {/*{`sidebar-icon ${activeItem ==='exit' ? ExitIconActive:ExitIcon}`} />*/}
                        Exit</a></li>
            </ul>
        </div>
    )
}