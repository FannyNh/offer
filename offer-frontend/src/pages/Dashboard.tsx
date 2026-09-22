import { useAuth } from "@/hooks/useAuth";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { getOffers } from "@/api/offerApi";

interface Offer {
    id: number;
    name: string;
    title: string;
    description: string;
    userId: string;
}

const Dashboard = () => {
    const { user, logout,backendUser } = useAuth();
    const navigate = useNavigate();
    const [offers, setOffers] = useState<Offer[]>([]);
    const [loading, setLoading] = useState(true);

    const handleLogout = async () => {
        await logout();
        navigate("/login");
    };

    useEffect(() => {
        const fetchOffers = async () => {
            try {
                const data = await getOffers();
                setOffers(data);
            } catch (err) {
                console.error(err);
            } finally {
                setLoading(false);
            }
        };
        fetchOffers();
    }, []);

    return (
        <div className="p-6">
            <h1 className="text-3xl mb-4">Dashboard</h1>
            <p>Bienvenue, {user?.displayName || user?.email || "Utilisateur"} !</p>

            <button
                onClick={handleLogout}
                className="mt-4 bg-red-600 text-white px-4 py-2 rounded"
            >
                Déconnexion
            </button>
            <div className="mt-6">
                <div className="flex items-center justify-between mb-3">
                    <button
                        onClick={() => navigate("/create-offer")}
                        className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
                    >
                        + Créer une offre
                    </button>
                </div>
            </div>

            <div className="mt-6">
                <h2 className="text-xl font-semibold mb-3">Offres</h2>
                {loading ? (
                    <p>Chargement...</p>
                ) : offers.length === 0 ? (
                    <p>Aucune offre disponible.</p>
                ) : (
                    <>
                        <ul className="space-y-3">

                            {offers.map((offer) => (
                                <li
                                    key={offer.id}
                                    className="p-4 border rounded shadow-sm bg-white text-purple-900"
                                    onClick={() => navigate(`/offers/${offer.id}`)}
                                >
                                    <h3 className="text-lg font-bold">{offer.title}</h3>
                                    {JSON.stringify(offer)}
                                    <p className="text-gray-600">{offer.description}</p>
                                    <p className="text-sm text-gray-500">Auteur: {offer.userId}</p>
                                </li>
                            ))}
                        </ul>
                    </>

                )}
            </div>
        </div>
    );
};

export default Dashboard;
