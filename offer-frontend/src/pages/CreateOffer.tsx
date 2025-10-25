import { useAuth } from "@/hooks/useAuth";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { createOffer } from "@/api/offerApi";

interface NewOffer {
    name: string;
    title: string;
    description: string;
    userId: string;
}

const CreateOffer = () => {
    const { user, logout } = useAuth();
    const navigate = useNavigate();
    const [offer, setOffer] = useState<NewOffer>({
        name: "",
        title: "",
        description: "",
        userId: user?.uid || ""
    });
    const [loading, setLoading] = useState(false);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = e.target;
        setOffer(prev => ({ ...prev, [name]: value }));
    };
    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setLoading(true);
        try {
            await createOffer(offer); // Appelle ton API pour créer l'offre
            navigate("/dashboard");   // Retour au dashboard après création
        } catch (err: any) {
            alert(err.message || "Erreur lors de la création de l'offre");
        } finally {
            setLoading(false);
        }
    };


    return (
        <div className="p-6">
            <h1 className="text-3xl mb-4">CreateOffer</h1>
            {JSON.stringify(user)}
            <div className="mt-6">
                <form onSubmit={handleSubmit} className="bg-white p-6 rounded-2xl shadow space-y-4">
                    <input
                        name="name"
                        value={offer.name}
                        onChange={handleChange}
                        placeholder="Nom de l'offre"
                        className="border p-2 w-full rounded"
                        required
                    />
                    <input
                        name="title"
                        value={offer.title}
                        onChange={handleChange}
                        placeholder="Titre de l'offre"
                        className="border p-2 w-full rounded"
                        required
                    />
                    <textarea
                        name="description"
                        value={offer.description}
                        onChange={handleChange}
                        placeholder="Description de l'offre"
                        className="border p-2 w-full rounded"
                        required
                    />
                    <button
                        type="submit"
                        disabled={loading}
                        className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 w-full rounded"
                    >
                        {loading ? "Création..." : "Créer l'offre"}
                    </button>
                </form>
            </div>
        </div>
    );
};

export default CreateOffer;
