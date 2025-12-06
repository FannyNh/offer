import { getAuth } from "firebase/auth";
import {NewOffer} from "@/types/offer";

export const getOffers = async () => {
    const auth = getAuth();
    const token = await auth.currentUser?.getIdToken();

    const res = await fetch("http://localhost:8080/api/offers", {
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
        },
    });

    if (!res.ok) throw new Error("Failed to fetch offers");
    return res.json();
};

export const createOffer = async (offer: NewOffer) => {
    const auth = getAuth();
    const token = await auth.currentUser?.getIdToken();
    const res = await fetch("http://localhost:8080/api/offers", {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(offer),
    });
    if (!res.ok) throw new Error("Failed to create offer");
    return res.json();
}