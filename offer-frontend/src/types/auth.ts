export interface User {
    id: number;
    email: string;
    firstName?: string;
    lastName?: string;
}

export interface AuthResponse {
    token: string;
    user: User;
}

export interface LoginPayload {
    email: string;
    password: string;
}

export interface RegisterPayload extends LoginPayload {
    firstName: string;
    lastName: string;
}
