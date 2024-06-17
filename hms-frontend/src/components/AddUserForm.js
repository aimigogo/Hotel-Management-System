import React, { useState } from "react";
import axios from "axios";
import "../css/AddUserForm.css";
import { useNavigate, useLocation } from "react-router-dom";
import {useUser} from "../context/UserContext";

const AddUserForm = () => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [section, setSection] = useState('');
    const [shift, setShift] = useState('');
    const [message, setMessage] = useState('');
    const navigate = useNavigate();
    const { handleUserAdded } = useUser();

    const handleAddUser = async (event) => {
        event.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/api/auth/signup', {
                name, email, password,section,shift
            });
            if (response.data) {
                handleUserAdded(response.data);
                setName('');
                setEmail('');
                setPassword('');
                setSection('');
                setShift('');
                setMessage('User added successfully!');
                setTimeout(() => {
                    setMessage('');
                    navigate('/AdminDashboard');
                }, 1500);
            }
        } catch (error) {
            console.error('Error adding user:', error);
            setMessage('Error adding user. Please try again.');
        }
    };

    const handleCancel = () => {
        navigate('/AdminDashboard');
    };

    return (
        <div className="add-user-form-content">
            <div className="add-user-form">
                <h2>Add New User</h2>
                {message && <p>{message}</p>}
                <form onSubmit={handleAddUser}>
                    <input
                        type="text"
                        placeholder="Name"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                    />
                    <input
                        type="email"
                        placeholder="Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                    <input
                        type="password"
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                    <input
                        type="section"
                        placeholder="Section"
                        value={section}
                        onChange={(e) => setSection(e.target.value)}
                        required
                    />
                    <input
                        type="shift"
                        placeholder="Shift"
                        value={shift}
                        onChange={(e) => setShift(e.target.value)}
                        required
                    />
                    <div className="form-buttons">
                        <button type="submit">Add User</button>
                        <button type="button" onClick={handleCancel}>Cancel</button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default AddUserForm;