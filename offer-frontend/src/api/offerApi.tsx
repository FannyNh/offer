import { getAuth } from "firebase/auth";
import {NewOffer} from "@/types/offer";
const OFFER_API_URL = import.meta.env.VITE_API_OFFER_URL;

export const getOffers = async () => {
    const auth = getAuth();
    const token = await auth.currentUser?.getIdToken();

    const res = await fetch(`${OFFER_API_URL}/api/offers`, {
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
        },
    });

    if (!res.ok) throw new Error("Failed to fetch offers");
    return res.json();
};

export const getOfferById = async (id: number) => {
    const auth = getAuth();
    const token = await auth.currentUser?.getIdToken();

    const res = await fetch(`${OFFER_API_URL}/api/offers/${id}`, {
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
        },
    });

    if (!res.ok) throw new Error("Failed to fetch offer by id");
    return res.json();
};

export const createOffer = async (offer: NewOffer) => {
    const auth = getAuth();
    const token = await auth.currentUser?.getIdToken();
    const res = await fetch("${OFFER_API_URL}/api/offers", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(offer),
    });
    if (!res.ok) throw new Error("Failed to create offer");
    return res.json();
}