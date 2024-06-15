import React, { useState } from "react";
import axios from "axios";
import "../css/AddUserForm.css";
import { useNavigate, useLocation } from "react-router-dom";

const AddUserForm = () => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');
    const navigate = useNavigate();
    const location = useLocation();
    const onUserAdded = location.state?.onUserAdded;

    const handleAddUser = async (event) => {
        event.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/api/auth/signup', {
                name, email, password
            });
            if (response.data) {
                if (onUserAdded){
                    onUserAdded(response.data);
                }
                setName('');
                setEmail('');
                setPassword('');
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