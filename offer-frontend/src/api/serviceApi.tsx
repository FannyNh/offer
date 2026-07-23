import { getAuth } from "firebase/auth";
import { ServiceCategory } from "@/types/offer";

const OFFER_API_URL = import.meta.env.VITE_API_OFFER_URL;

export const getServiceCategories = async (): Promise<ServiceCategory[]> => {
    const auth = getAuth();
    const token = await auth.currentUser?.getIdToken();

    const res = await fetch(`${OFFER_API_URL}/api/service-categories`, {
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
        },
    });

    if (!res.ok) throw new Error("Failed to fetch service categories");
    const data = await res.json();
    return data.categories || [];
};

export const updateServiceCategory = async (serviceId: number, categoryId: number | null) => {
    const auth = getAuth();
    const token = await auth.currentUser?.getIdToken();

    const res = await fetch(`${OFFER_API_URL}/api/services/${serviceId}/category`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({ categoryId }),
    });

    if (!res.ok) throw new Error("Failed to update service category");
    return res.json();
};
