import axios from "axios";
import { AuthResponse, LoginPayload, RegisterPayload } from "@/types/auth";

const API_URL = "http://localhost:8080/api"; // À adapter

export const loginApi = async (payload: LoginPayload): Promise<AuthResponse> => {
    const { data } = await axios.post<AuthResponse>(`${API_URL}/auth/login`, payload);
    return data;
};

export const registerApi = async (payload: RegisterPayload): Promise<AuthResponse> => {
    const { data } = await axios.post<AuthResponse>(`${API_URL}/auth/register`, payload);
    return data;
};
