import React, { useState } from "react";
import axios from "axios";
import "../css/AddRoomForm.css";
import { useNavigate, useLocation } from "react-router-dom";
import {useRoomContext} from "../context/RoomContext";

const AddRoomForm = () => {
    const [name, setName] = useState('');
    const [type, setType] = useState('');
    const [price, setPrice] = useState('');
    const [availability,setAvailability]=useState('');
    const [message,setMessage]=useState('');
    const navigate = useNavigate();
    const location = useLocation();
    const {handleRoomAdded} = useRoomContext();

    const handleAddRoom = async (event) => {
        event.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/api/auth/createRoom', {
                name, type, price,availability
            });
            if (response.data) {
                handleRoomAdded(response.data)
                setName('');
                setType('');
                setPrice('');
                setAvailability('');
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
                    <input
                        type="type"
                        placeholder="Type"
                        value={type}
                        onChange={(e) => setType(e.target.value)}
                        required
                    />
                    <input
                        type="price"
                        placeholder="Price"
                        value={price}
                        onChange={(e) => setPrice(e.target.value)}
                        required
                    />
                    <input
                        type="availability"
                        placeholder="availability"
                        value={availability}
                        onChange={(e) => setAvailability(e.target.value)}
                        required
                    />
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