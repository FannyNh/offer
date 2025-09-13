import { useAuth } from "@/hooks/useAuth";
import { useNavigate } from "react-router-dom";

const Dashboard = () => {
    const { user, logout } = useAuth();
    const navigate = useNavigate();

    const handleLogout = async () => {
        await logout();
        navigate("/login");
    };

    return (
        <div className="p-6">
            <h1 className="text-3xl mb-4">Dashboard</h1>
            <p>
                Bienvenue,{" "}
                {user?.displayName || user?.email || "Utilisateur"} !
            </p>

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
