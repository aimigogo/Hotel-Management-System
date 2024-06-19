import React, {useEffect, useState} from 'react';
import Sidebar from "../components/Sidebar";
import "../css/RoomPage.css"
import {listRooms,updateRoom,deleteRoom} from "../Service/RoomService";
import {useNavigate} from "react-router-dom";
import {ReactComponent as AddRoomIcon} from "../assets/add room.svg";
import {getAvailability, getType} from "../Service/EnumService";


const RoomPage = () => {
    const [room,setRoom]=useState([]);
    const [editingRoomId, setEditingRoomId] = useState(null);
    const [editableRoom, setEditableRoom] = useState({});
    const [available,setAvailable]=useState([]);
    const [types,setTypes]=useState([]);

    const navigate=useNavigate();

    useEffect(() => {
        listRooms()
            .then((response) => {
                setRoom(response.data);
            })
            .catch(error => {
                console.error('Error fetching rooms:', error);
            });

        getAvailability().then(data => {
                setAvailable(data);
            }).catch(error => {
                console.error('Error fetching availability:', error);
            });

        getType()
            .then(data => {
                setTypes(data);
            })
            .catch(error => {
                console.error('Error fetching types:', error);
            });
    }, []);



    const handleEditClick = (room) => {
        setEditingRoomId(room.id);
        setEditableRoom(room);
    };

    const handleSaveClick = async () => {
        try {
            await updateRoom(editingRoomId, editableRoom);
            setRoom(room.map(rm => rm.id === editingRoomId ? editableRoom : rm));
            setEditingRoomId(null);
        } catch (error) {
            console.error('Error updating room:', error);
        }
    };


    const handleChange = (e) => {
        const { name, value } = e.target;
        setEditableRoom(prevEditableRoom => ({
            ...prevEditableRoom,
            [name]: value
        }));
    };
    const handleDeleteButton=async(roomId)=>{
        try {
            await deleteRoom(roomId);
            setRoom(room.filter(room => room.id !== roomId));
        } catch (error) {
            console.error('Error deleting room:', error);
        }
    }

    const navigateToAddRoomForm = () => {
        navigate('/AddRoomForm');
    };

    return (
        <div className='room-page'>
            <Sidebar/>
            <div className="room-content">
            <h2 className='text-center'>Room Management</h2>
                <table className='table table-striped table-bordered'>
                    <thead>
                    <tr>
                        <th>Room Id</th>
                        <th>Room Name</th>
                        <th>Type</th>
                        <th>Price</th>
                        <th>Available</th>
                        <th>
                            <button className="add-room-button" onClick={navigateToAddRoomForm}>
                                <AddRoomIcon/> Add Room
                            </button>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    {room.map(room => (
                        <tr key={room.id}>
                            <td>{room.id}</td>
                            <td>
                                {editingRoomId === room.id ? (
                                    <input
                                        type="text"
                                        name="name"
                                        value={editableRoom.name || ''}
                                        onChange={handleChange}
                                    />
                                ) : (
                                    room.name
                                )}
                            </td>
                            <td>
                                {editingRoomId === room.id ? (
                                    <select
                                        name="type"
                                        value={editableRoom.type || ''}
                                        onChange={handleChange}
                                    >
                                        <option value="">Select Type</option>
                                        {types.map(type => (
                                            <option key={type} value={type}>
                                                {type}
                                            </option>
                                        ))}
                                    </select>
                                ) : (
                                    room.type
                                )}
                            </td>
                            <td>
                                {editingRoomId === room.id ? (
                                    <input
                                        type="number"
                                        name="price"
                                        value={editableRoom.price || ''}
                                        onChange={handleChange}
                                    />
                                ) : (
                                    room.price
                                )}
                            </td>
                            <td>
                                {editingRoomId === room.id ? (
                                    <select
                                        name="available"
                                        value={editableRoom.available || ''}
                                        onChange={handleChange}
                                    >
                                        <option value="">Select Availability</option>
                                        {available.map(avail => (
                                            <option key={avail} value={avail}>
                                                {avail}
                                            </option>
                                        ))}
                                    </select>

                                ) : (
                                    room.available
                                )}
                            </td>
                            <td>
                                <div className="action-buttons">
                                    {editingRoomId === room.id ? (
                                        <button className="save-button" onClick={handleSaveClick}>Save</button>
                                    ) : (
                                        <button className="edit-button" onClick={() => handleEditClick(room)}>Edit</button>
                                    )}
                                    <button className="delete-button" onClick={() => handleDeleteButton(room.id)}>Delete</button>
                                </div>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default RoomPage;