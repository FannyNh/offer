import { getAuth } from "firebase/auth";

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
