import Sidebar from "./Sidebar";
import React from "react";
import "../css/StockPage.css"

const StockPage = () => {
    return (
        <div className='stock-page'>
            <Sidebar/>
            <div className="stock-content">
                <h2 className='text-center'>Stock Management</h2>
            </div>
        </div>
    );
}

export default StockPage;