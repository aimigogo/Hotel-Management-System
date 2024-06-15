import axios from "axios";


export const listRooms=()=> axios.get('http://localhost:8080/api/auth/rooms');

export const updateRoom = (roomId, roomData) => axios.put(`http://localhost:8080/api/admin/rooms/${roomId}`, roomData);

export const deleteRoom = (roomId) => axios.delete(`http://localhost:8080/api/admin/rooms/${roomId}`);
