import { Navigate } from "react-router-dom";
import { useAuth } from "../hooks/useAuth";
import type {JSX} from "react";

interface Props {
    children: JSX.Element;
}

const PrivateRoute = ({ children }: Props) => {
    const { state } = useAuth();
    return state.token ? children : <Navigate to="/login" replace />;
};

export default PrivateRoute;
