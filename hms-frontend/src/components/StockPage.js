import Sidebar from "./Sidebar";
import React, {useEffect, useState} from "react";
import "../css/StockPage.css"
import {listStockItems, updateStockItem,deleteStockItem} from "../Service/StockService";
import {useNavigate} from "react-router-dom";
import {ReactComponent as AddStockIcon} from "../assets/add stock.svg";


const StockPage = () => {

    const [stockItem,setStockItem]=useState([]);
    const [editingStockItemId, setEditingStockItemId] = useState(null);
    const [editableStockItem, setEditableStockItem] = useState({});

    const navigate=useNavigate();

    useEffect(() => {
        listStockItems().then((response)=>{
            setStockItem(response.data);
        }).catch(error=>{
            console.error(error);
            console.error('Error fetching stock items:', error);
        })
    }, []);
    const handleStockItemAdded=(newStockItem)=>{
        setStockItem([...stockItem,newStockItem]);

    }

    const handleEditClick = (stock) => {
        setEditingStockItemId(stock.id);
        setEditableStockItem(stock);
    };

    const handleSaveClick = async () => {
        try {
            await updateStockItem(editingStockItemId, editableStockItem);
            setStockItem(stockItem.map(stockIt => stockIt.id === editingStockItemId ? editableStockItem : stockIt));
            setEditingStockItemId(null);
        } catch (error) {
            console.error('Error updating Stock item:', error);
        }
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setEditableStockItem({
            ...editableStockItem,
            [name]: value
        });
    };

    const handleDeleteButton=async(stockId)=>{
        try {
            await deleteStockItem(stockId);
            setStockItem(stockItem.filter(stock => stock.id !== stockId));
        } catch (error) {
            console.error('Error deleting stock item:', error);
        }
    }

    const navigateToAddStockItemForm = () => {
        navigate('/AddStockItemForm',{state:{onStockItemAdded:handleStockItemAdded}});
    };

    return (
        <div className='stock-page'>
            <Sidebar/>
            <div className="stock-content">
                <h2 className='text-center'>Stock Management</h2>
                <table className='table table-striped table-bordered'>
                    <thead>
                    <tr>
                        <th>Stock Id</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>
                            <button className="add-stock-item-button" onClick={navigateToAddStockItemForm}>
                               <AddStockIcon/> Add Stock Item
                            </button>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    {stockItem.map(stockItem => (
                        <tr key={stockItem.id}>
                            <td>{stockItem.id}</td>
                            <td>
                                {editingStockItemId === stockItem.id ? (
                                    <input
                                        type="text"
                                        name="name"
                                        value={editableStockItem.name}
                                        onChange={handleChange}
                                    />
                                ) : (
                                    stockItem.name
                                )}
                            </td>
                            <td>
                                {editingStockItemId === stockItem.id ? (
                                    <input
                                        type="quantity"
                                        name="quantity"
                                        value={editableStockItem.quantity}
                                        onChange={handleChange}
                                    />
                                ) : (
                                    stockItem.quantity
                                )}
                            </td>
                            <td>
                                <div className="action-buttons">
                                    {editingStockItemId === stockItem.id ? (
                                        <button className="save-button" onClick={handleSaveClick}>Save</button>
                                    ) : (
                                        <button className="edit-button" onClick={() => handleEditClick(stockItem)}>Edit</button>
                                    )}
                                    <button className="delete-button" onClick={() => handleDeleteButton(stockItem.id)}>Delete</button>
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

export default StockPage;