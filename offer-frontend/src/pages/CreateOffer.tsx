import { useAuth } from "@/hooks/useAuth";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { createOffer } from "@/api/offerApi";
import {NewOffer} from "@/types/offer";



const CreateOffer = () => {
    const { user, logout,backendUser } = useAuth();
    const navigate = useNavigate();
    const [offer, setOffer] = useState<NewOffer>({
        name: "",
        title: "",
        description: "",
        userId: backendUser?.userId
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
                <form onSubmit={handleSubmit} className="bg-white dark:bg-purple-800 p-6 rounded-2xl shadow space-y-4">
                    <input
                        name="name"
                        value={offer.name}
                        onChange={handleChange}
                        placeholder="Nom de l'offre"
                        className="border p-2 w-full rounded
    text-purple-900 dark:text-white
    bg-white dark:bg-purple-900
    focus:border-purple-600
    focus:ring-2 focus:ring-purple-400
    focus:outline-none
    dark:focus:text-white
    dark:focus:border-orange-600
    dark:focus:ring-orange-600"
                        required
                    />
                    <input
                        name="title"
                        value={offer.title}
                        onChange={handleChange}
                        placeholder="Titre de l'offre"
                        className="border p-2 w-full rounded text-purple-900 dark:text-white
    bg-white dark:bg-purple-900
    focus:border-purple-600
    focus:ring-2 focus:ring-purple-400
    focus:outline-none
    dark:focus:text-white
    dark:focus:border-orange-600
    dark:focus:ring-orange-600"
                        required
                    />
                    <textarea
                        name="description"
                        value={offer.description}
                        onChange={handleChange}
                        placeholder="Description de l'offre"
                        className="border p-2 w-full rounded text-purple-900 dark:text-white
    bg-white dark:bg-purple-900
    focus:border-purple-600
    focus:ring-2 focus:ring-purple-400
    focus:outline-none
    dark:focus:text-white
    dark:focus:border-orange-600
    dark:focus:ring-orange-600"
                        required
                    />
                    <button
                        type="submit"
                        disabled={loading}
                        className="bg-purple-800 dark:bg-orange-500 hover:bg-purple-700 dark:hover:bg-orange-600 text-white px-4 py-2 w-full rounded"
                    >
                        {loading ? "Création..." : "Créer l'offre"}
                    </button>
                </form>
            </div>
        </div>
    );
};

export default CreateOffer;
