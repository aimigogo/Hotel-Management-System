import {createContext, useContext, useState} from "react";

const RoomContext = createContext();

export const useRoomContext = () => useContext(RoomContext);

// Create a provider component
export const RoomProvider = ({ children }) => {
    const [room, setRoom] = useState([]);

    const handleRoomAdded = (newRoom) => {
        setRoom([...room, newRoom]);
    };

    return (
        <RoomContext.Provider value={{ room, setRoom, handleRoomAdded }}>
            {children}
        </RoomContext.Provider>
    );
};