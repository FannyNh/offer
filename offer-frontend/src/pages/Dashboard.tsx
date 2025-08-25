import { useAuth } from "../hooks/useAuth";
import { useNavigate } from "react-router-dom";

const Dashboard = () => {
    const { state, logout } = useAuth();
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        navigate("/login");
    };

    return (
        <div className="p-6">
            <h1 className="text-3xl mb-4">Dashboard</h1>
            <p>Bienvenue, {state.user?.firstName || state.user?.email} !</p>

            <button
                onClick={handleLogout}
                className="mt-4 bg-red-600 text-white px-4 py-2 rounded"
            >
                Déconnexion
            </button>
        </div>
    );
};

export default Dashboard;
