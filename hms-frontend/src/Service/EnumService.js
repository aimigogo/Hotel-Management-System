import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/enums';

export const getSections = async () => {
    try {
        const response = await axios.get(`${API_BASE_URL}/sections`);
        return response.data;
    } catch (error) {
        console.error('Error fetching sections:', error);
        throw error;
    }
};

export const getShifts = async () => {
    try {
        const response = await axios.get(`${API_BASE_URL}/shifts`);
        return response.data;
    } catch (error) {
        console.error('Error fetching shifts:', error);
        throw error;
    }
};
    export const getAvailability = async () => {
        try {
            const response = await axios.get(`${API_BASE_URL}/availability`);
            return response.data;
        } catch (error) {
            console.error('Error fetching availability:', error);
            throw error;
        }
    };
    export const getType = async () => {
        try {
            const response = await axios.get(`${API_BASE_URL}/type`);
            return response.data;
        } catch (error) {
            console.error('Error fetching type:', error);
            throw error;
        }
    };


