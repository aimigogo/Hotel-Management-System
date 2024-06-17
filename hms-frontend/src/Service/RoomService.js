import axios from "axios";


export const listRooms=()=> axios.get('http://localhost:8080/api/room/rooms');

export const updateRoom = (roomId, roomData) => axios.put(`http://localhost:8080/api/room/update/${roomId}`, roomData);

export const deleteRoom = (roomId) => axios.delete(`http://localhost:8080/api/room/delete/${roomId}`);
