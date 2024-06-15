import React, { useState } from "react";
import axios from "axios";
import "../css/AddStockItemForm.css";
import { useNavigate, useLocation } from "react-router-dom";

const AddStockItemForm = () => {
    const [name, setName] = useState('');
    const [quantity, setQuantity] = useState('');
    const [message,setMessage]=useState('');
    const navigate = useNavigate();
    const location = useLocation();
    const onStockItemAdded = location.state?.onStockItemAdded;

    const handleAddStockItem = async (event) => {
        event.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/api/auth/createStockItem', {
                name, quantity
            });
            if (response.data) {
                if (onStockItemAdded){
                    onStockItemAdded(response.data);
                }
                setName('');
                setQuantity('');
                setMessage('Stock Item added successfully!');
                setTimeout(() => {
                    setMessage('');
                    navigate('/StockPage');
                }, 1500);
            }
        } catch (error) {
            console.error('Error adding stock item:', error);
            setMessage('Error adding stock item. Please try again.');
        }
    };

    const handleCancel = () => {
        navigate('/StockPage');
    };

    return (
        <div className="add-stock-item-form-content">
            <div className="add-stock-item-form">
                <h2>Add New Stock Item</h2>
                {message && <p>{message}</p>}
                <form onSubmit={handleAddStockItem}>
                    <input
                        type="text"
                        placeholder="Name"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                    />
                    <input
                        type="quantity"
                        placeholder="Quantity"
                        value={quantity}
                        onChange={(e) => setQuantity(e.target.value)}
                        required
                    />

                    <div className="form-buttons">
                        <button type="submit">Add Stock Item</button>
                        <button type="button" onClick={handleCancel}>Cancel</button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default AddStockItemForm;