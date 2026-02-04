import {useAuth} from "@/hooks/useAuth";
import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {getOfferById} from "@/api/offerApi";

interface OfferVersion {
    name: string | null;
    title: string | null;
    description: string | null;
    versionNumber: number;
}

interface Offer {
    id: number;
    name: string;
    userId: number;
    version: OfferVersion;
}


const OfferDetail = () => {
    const {user} = useAuth();
    const {idOffer} = useParams();
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
    const updateOfferField = (field: string, value: string) => {
        setOffer(prev => {
            if (!prev) return prev;

            const updated = {...prev};

            if (field.includes(".")) {
                // Champ imbriqué, ex: "version.name"
                const keys = field.split(".");
                let obj: any = updated;
                for (let i = 0; i < keys.length - 1; i++) {
                    obj = obj[keys[i]] = {...obj[keys[i]]}; // clone pour l'immutabilité
                }
                obj[keys[keys.length - 1]] = value;
            } else {
                // @ts-ignore
                updated[field as keyof Offer] = value as any;
            }

            return updated;
        });
    };
    return (
        <div className="min-h-screen w-full p-6 bg-purple-50 dark:bg-purple-950">

            {/* HEADER STYLE MID-FI */}
            <div className="w-full bg-white dark:bg-purple-900 border border-purple-300 dark:border-purple-700
                            rounded-xl shadow-md px-4 py-3 flex items-center justify-between mb-6">

                <div className="flex items-center gap-3">
                    <button
                        onClick={() => navigate("/dashboard")}
                        className="px-4 py-2 rounded-lg font-semibold
                                   bg-orange-500 hover:bg-orange-600 text-white"
                    >
                        ← Retour
                    </button>
                    {JSON.stringify(offer)}

                </div>

                <button className="px-4 py-2 rounded-lg bg-orange-500 hover:bg-orange-600 text-white font-semibold">
                    Enregistrer
                </button>
            </div>


            {/* LAYOUT A DEUX COLONNES - comme ton midfidelity */}
            <div className="grid grid-cols-1 lg:grid-cols-[1fr_320px] gap-6">

                {/* ==== MAIN ZONE GAUCHE ==== */}
                <div className="flex flex-col gap-6">

                    {/* Bloc principal */}
                    <div className="bg-white dark:bg-purple-900 border border-purple-300 dark:border-purple-700
                                    shadow-md rounded-xl p-5">

                        {loading ? (
                            <p className="text-purple-700 dark:text-purple-200">Chargement...</p>
                        ) : !offer ? (
                            <p className="text-orange-600 font-semibold">Aucune offre trouvée.</p>
                        ) : (
                            <>
                                editor
                            </>
                        )}
                    </div>

                    {/* NOTES / CONDITIONS - style mid-fi */}
                    <div className="bg-white dark:bg-purple-900 border border-purple-300 dark:border-purple-700
                                    shadow-md rounded-xl p-5">
                        <h3 className="font-bold text-purple-700 dark:text-purple-200 mb-3">
                            Notes / Conditions
                        </h3>
                        <p className="text-purple-700 dark:text-purple-300 text-sm">
                            (zone placeholder similaire à la maquette)
                        </p>
                    </div>

                </div>


                {/* ==== SIDEBAR STYLE MID-FI ==== */}
                <aside className="bg-white dark:bg-purple-950 border rounded-xl border-0 h-fit sticky top-6">
                    <div
                        className=" dark:bg-purple-900 border border-purple-300 dark:border-purple-700 shadow-md rounded-xl p-5 h-fit ">
                        <h3 className="text-lg font-bold text-purple-800 dark:text-purple-200 mb-4">
                            Informations <span className="px-2 py-1 rounded bg-purple-200 dark:bg-purple-700
                                     text-purple-800 dark:text-purple-100 text-xs font-bold">
                                  v{offer?.version?.versionNumber}
                        </span>
                        </h3>
                        <div className="text-sm text-purple-700 dark:text-purple-300">
                            <span className="font-semibold">Auteur({offer?.userId}) :</span> {user?.email}
                        </div>
                        <div className="flex flex-col gap-1">
                            <label className="text-xs font-semibold text-purple-700 dark:text-purple-200">
                                Name
                            </label>
                            <input
                                type="text"
                                value={offer?.version?.name || ""}
                                onChange={(e) => updateOfferField("version.name", e.target.value)}
                                className="text-sm font-bold text-purple-700 dark:text-purple-200
               bg-purple-100 dark:bg-purple-800 px-2 py-1 rounded-lg
               focus:outline-none focus:ring-2 focus:ring-orange-400"
                            />
                        </div>
                        <div className="flex flex-col gap-1">
                            <label className="text-xs font-semibold text-purple-700 dark:text-purple-200">
                                Title
                            </label>
                            <input
                                type="text"
                                value={offer?.version?.title || ""}
                                onChange={(e) => updateOfferField("version.title", e.target.value)}
                                className="text-sm font-bold text-purple-700 dark:text-purple-200
               bg-purple-100 dark:bg-purple-800 px-2 py-1 rounded-lg
               focus:outline-none focus:ring-2 focus:ring-orange-400"
                            />
                        </div>

                        <div className="flex flex-col gap-1">
                            <label className="text-xs font-semibold text-purple-700 dark:text-purple-200">
                                Description
                            </label>
                            <textarea
                                value={offer?.version?.description || ""}
                                onChange={(e) => updateOfferField("version.description", e.target.value)}
                                className="text-sm font-bold text-purple-700 dark:text-purple-200
               bg-purple-100 dark:bg-purple-800 px-2 py-1 rounded-lg
               focus:outline-none focus:ring-2 focus:ring-orange-400
               resize-none min-h-[80px]"
                            />
                        </div>


                    </div>
                    <div
                        className=" dark:bg-purple-900 border border-purple-300 dark:border-purple-700 shadow-md rounded-xl p-5 h-fit ">
                        <h3 className="text-lg font-bold text-purple-800 dark:text-purple-200 mb-4">
                            Récapitulatif
                        </h3>
                        <div className="flex justify-between py-2 border-b border-purple-200 dark:border-purple-700">
                            <span className="text-purple-700 dark:text-purple-300">Sous-total</span>
                            <span className="font-semibold text-purple-900 dark:text-white">
                            —
                        </span>
                        </div>

                        <div className="flex justify-between py-2 border-b border-purple-200 dark:border-purple-700">
                            <span className="text-purple-700 dark:text-purple-300">Remises</span>
                            <span className="font-semibold text-purple-900 dark:text-white">
                            —
                        </span>
                        </div>

                        <div className="flex justify-between py-2 border-b border-purple-200 dark:border-purple-700">
                            <span className="text-purple-700 dark:text-purple-300">Taxes</span>
                            <span className="font-semibold text-purple-900 dark:text-white">
                            —
                        </span>
                        </div>

                        <div
                            className="flex justify-between pt-3 mt-2 border-t border-purple-300 dark:border-purple-700 text-lg font-bold">
                            <span>Total</span>
                            <span>—</span>
                        </div>
                    </div>

                </aside>

            </div>
        </div>
    );
};

export default OfferDetail;
