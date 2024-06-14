import React, {useState} from "react";
import '../css/Login.css';
import {ReactComponent as Logo} from "../assets/login-Logo.svg";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import {useUser} from "../context/UserRoleContext";


export default function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');
    const navigate = useNavigate();
    const {setUserType} = useUser();

    const handleLogin = async (event) => {
        event.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/api/auth/login', {email, password});
            if (response.data.jwt) {
                setMessage(`Welcome! Your role is ${response.data.userRole}.`);
                // Store the JWT in localStorage or context if needed
                localStorage.setItem('token', response.data.jwt);
                setUserType(response.data.userRole);
                if (response.data.userRole === 'ADMIN') {
                    navigate("/AdminDashboard");
                } else {
                    navigate('/EmployeeDashboard');
                }
            } else {
                setMessage('Wrong email or password');
            }
        } catch (error) {
            setMessage('Wrong email or password');
        }
    };

    return (
        <div className="login">
            <div className="login-box">
                <div className="login-header">
                    <Logo className="login-logo"/>
                </div>
                <form onSubmit={handleLogin}>
                    <div className="input-group">
                        <label htmlFor="email">Email</label>
                        <input
                            type="text"
                            id="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>
                    <div className="input-group">
                        <label htmlFor="password">Password</label>
                        <input
                            type="password"
                            id="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>
                    <button type="submit" className="login-button">Login</button>
                </form>
                {message && <p>{message}</p>}
            </div>
        </div>
    );
}