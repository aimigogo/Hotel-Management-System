import React, {useState} from "react";
import './Login.css';
import {ReactComponent as Logo} from "../assets/login-Logo.svg";

export default function Login(){
    const [username,setUsername]=useState('');
    const [password,setPassword]=useState('');

    const handleLogin=(event)=>{
        event.preventDeafult();
        console.log('Login attempt:',{username,password});
    };

    return(
        <div className="login">
            <div className="login-box">
                <div className="login-header">
                    <Logo className="login-logo"/>
                </div>
                <form onSubmit={handleLogin}>
                    <div className="input-group">
                        <label htmlFor="username">Username</label>
                        <input
                            type="text"
                            id="username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
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
                <button className="forgot-password-button">Forgot Password</button>
            </div>
        </div>

    );
}