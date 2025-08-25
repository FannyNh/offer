import { createContext, useReducer, type ReactNode, useEffect } from "react";
import type {User} from "../types/auth";

interface AuthState {
    user: User | null;
    token: string | null;
}

type AuthAction =
    | { type: "LOGIN"; payload: { user: User; token: string } }
    | { type: "LOGOUT" };

const initialState: AuthState = {
    user: null,
    token: localStorage.getItem("token"),
};

const AuthContext = createContext<{
    state: AuthState;
    login: (user: User, token: string) => void;
    logout: () => void;
}>({
    state: initialState,
    login: () => {},
    logout: () => {},
});

const authReducer = (state: AuthState, action: AuthAction): AuthState => {
    switch (action.type) {
        case "LOGIN":
            return { user: action.payload.user, token: action.payload.token };
        case "LOGOUT":
            return { user: null, token: null };
        default:
            return state;
    }
};

export const AuthProvider = ({ children }: { children: ReactNode }) => {
    const [state, dispatch] = useReducer(authReducer, initialState);

    useEffect(() => {
        if (state.token) {
            localStorage.setItem("token", state.token);
        } else {
            localStorage.removeItem("token");
        }
    }, [state.token]);

    const login = (user: User, token: string) => {
        dispatch({ type: "LOGIN", payload: { user, token } });
    };

    const logout = () => {
        dispatch({ type: "LOGOUT" });
    };

    return (
        <AuthContext.Provider value={{ state, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};

export default AuthContext;
