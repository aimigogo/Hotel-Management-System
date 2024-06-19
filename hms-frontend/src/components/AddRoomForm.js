import React, { useState, useEffect } from "react";
import axios from "axios";
import "../css/AddRoomForm.css";
import { useNavigate } from "react-router-dom";
import { useRoomContext } from "../context/RoomContext";
import { getAvailability, getType } from "../Service/EnumService";

const AddRoomForm = () => {
    const [name, setName] = useState('');
    const [type, setType] = useState('');
    const [price, setPrice] = useState('');
    const [available, setAvailable] = useState('');
    const [message, setMessage] = useState('');
    const [types, setTypes] = useState([]);
    const [availabilities, setAvailabilities] = useState([]);
    const navigate = useNavigate();
    const { handleRoomAdded } = useRoomContext();

    useEffect(() => {
        getType().then(setTypes).catch(error => console.error('Error fetching types:', error));
        getAvailability().then(setAvailabilities).catch(error => console.error('Error fetching availabilities:', error));
    }, []);

    const handleAddRoom = async (event) => {
        event.preventDefault();
        try {
            const response = await axios.post(
                'http://localhost:8080/api/room/createRoom',{
                    name,type,price, available
                }
            );
            if (response.data) {
                handleRoomAdded(response.data);
                setName('');
                setType('');
                setPrice('');
                setAvailable('');
                setMessage('Room added successfully!');
                setTimeout(() => {
                    setMessage('');
                    navigate('/RoomPage');
                }, 1500);
            }
        } catch (error) {
            console.error('Error adding room:', error);
            setMessage('Error adding room. Please try again.');
        }
    };

    const handleCancel = () => {
        navigate('/RoomPage');
    };

    return (
        <div className="add-room-form-content">
            <div className="add-room-form">
                <h2>Add New Room</h2>
                {message && <p>{message}</p>}
                <form onSubmit={handleAddRoom}>
                    <input
                        type="text"
                        placeholder="Name"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                    />
                    <select
                        value={type}
                        onChange={(e) => setType(e.target.value)}
                        required
                    >
                        <option value="" disabled>Select Type</option>
                        {types.map(t => (
                            <option key={t} value={t}>{t}</option>
                        ))}
                    </select>
                    <input
                        type="number"
                        placeholder="Price"
                        value={price}
                        onChange={(e) => setPrice(e.target.value)}
                        required
                    />
                    <select
                        value={available}
                        onChange={(e) => setAvailable(e.target.value)}
                        required
                    >
                        <option value="" disabled>Select Availability</option>
                        {availabilities.map(a => (
                            <option key={a} value={a}>{a}</option>
                        ))}
                    </select>
                    <div className="form-buttons">
                        <button type="submit">Add Room</button>
                        <button type="button" onClick={handleCancel}>Cancel</button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default AddRoomForm;
