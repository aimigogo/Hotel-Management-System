import {createContext, useContext, useState} from "react";

const StockItemContext = createContext();

export const useStockItemContext = () => useContext(StockItemContext);

// Create a provider component
export const StockItemProvider = ({ children }) => {
    const [stockItems, setStockItems] = useState([]);

    const handleStockItemAdded = (newStockItem) => {
        setStockItems([...stockItems, newStockItem]);
    };

    return (
        <StockItemContext.Provider value={{ stockItems, setStockItems, handleStockItemAdded }}>
            {children}
        </StockItemContext.Provider>
    );
};