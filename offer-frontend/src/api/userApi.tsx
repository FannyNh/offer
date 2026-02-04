import {ApiUser, RegisterPayload} from "@/types/auth";
const OFFER_API_URL = import.meta.env.VITE_API_OFFER_URL;

export async function createUserInBackend(token: string, uid: string, data: RegisterPayload) {
    // 3. Appelle ton backend pour créer aussi l'user
    await fetch(`${OFFER_API_URL}/api/users`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`, // 🔑 très important
        },
        body: JSON.stringify({
            email: data.email,
            firstName: "", // tu peux rajouter des champs dans ton form
            lastName: "",
            groupId: 1,
            idpId: uid,
        }),
    });
}


export async function getMe(idToken: string) {
    const response = await fetch(`${OFFER_API_URL}/api/users/me`, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${idToken}`,
            "Content-Type": "application/json",
        },
    });

    if (!response.ok) {
        const err: any = new Error("Failed to fetch /me");
        err.status = response.status;
        throw err;
    }
    const me:ApiUser = await response.json();
    return me;
}
