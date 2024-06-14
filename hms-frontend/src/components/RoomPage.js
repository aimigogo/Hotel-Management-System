import React from 'react';
import Sidebar from "../components/Sidebar";
import "../css/RoomPage.css"
const RoomPage = () => {
    return (
        <div className='room-page'>
            <Sidebar/>
            <div className="room-content">
            <h2 className='text-center'>Room Management</h2>
            </div>
        </div>
    );
}

export default RoomPage;