import { useAuth } from "@/hooks/useAuth";
import { useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { getOfferById } from "@/api/offerApi";

interface Offer {
    id: number;
    name: string;
    title: string;
    description: string;
    userId: string;
}

const OfferDetail = () => {
    const { user } = useAuth();
    const { idOffer } = useParams();
    const navigate = useNavigate();

    const [offer, setOffer] = useState<Offer | null>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchOffer = async () => {
            try {
                if (!idOffer) return;

                const data = await getOfferById(Number(idOffer));
                setOffer(data);
            } catch (err) {
                console.error("Erreur lors du chargement de l'offre :", err);
            } finally {
                setLoading(false);
            }
        };

        fetchOffer();
    }, [idOffer]);

    return (
        <div className="p-6">
            <button
                onClick={() => navigate("/dashboard")}
                className="mb-4 bg-gray-300 px-3 py-1 rounded hover:bg-gray-400"
            >
                ← Retour
            </button>

            <h1 className="text-3xl mb-4">Détails de l’offre</h1>
            {JSON.stringify(offer)}
            <p>Connecté en tant que : {user?.displayName || user?.email}</p>

            {loading ? (
                <p>Chargement...</p>
            ) : !offer ? (
                <p>Aucune offre trouvée.</p>
            ) : (
                <div className="p-4 border rounded shadow-sm bg-white text-purple-900">
                    <h2 className="text-2xl font-bold mb-2">{offer.title}</h2>
                    <p className="text-gray-700 mb-4">{offer.description}</p>

                    <p className="text-sm text-gray-500">
                        Auteur : {offer.userId}
                    </p>

                    <div className="mt-4 border-t pt-3 text-sm text-gray-400">
                        <strong>ID interne :</strong> {offer.id}
                    </div>
                </div>
            )}
        </div>
    );
};

export default OfferDetail;
