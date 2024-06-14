import Sidebar from "./Sidebar";
import React from "react";
import "../css/SettingsPage.css";


const SettingsPage = () => {
    return (
        <div className='settings-page'>
            <Sidebar/>
            <div className="settings-content">
                <h2 className='text-center'>Settings</h2>
            </div>
        </div>
    );
}

export default SettingsPage;