import React, {useEffect, useState} from "react";
import axios from "axios";
import "../css/AddUserForm.css";
import { useNavigate } from "react-router-dom";
import {useUser} from "../context/UserContext";
import { getSections, getShifts} from "../Service/EnumService";

const AddUserForm = () => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [section, setSection] = useState('');
    const [shift, setShift] = useState('');
    const [sections, setSections] = useState([]);
    const [shifts, setShifts] = useState([]);
    const [message, setMessage] = useState('');
    const navigate = useNavigate();
    const { handleUserAdded } = useUser();

    useEffect(() => {
        getSections().then(setSections).catch(error => console.error('Error fetching sections:', error));
        getShifts().then(setShifts).catch(error => console.error('Error fetching Shifts:', error));
    }, []);

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
                    <select
                        value={section}
                        onChange={(e) => setSection(e.target.value)}
                        required
                    >
                        <option value="" disabled>Select Section</option>
                        {sections.map(sec=>(
                            <option key={sec} value={sec}>{sec}</option>
                        ))}
                    </select>
                    <select
                        value={shift}
                        onChange={(e) => setShift(e.target.value)}
                        required
                    >
                        <option value="" disabled>Select Shift</option>
                        {shifts.map(shift=>(
                            <option key={shift} value={shift}>{shift}</option>
                        ))}
                    </select>
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