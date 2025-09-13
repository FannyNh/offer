import {RegisterPayload} from "@/types/auth";

export async function createUserInBackend(token: string, uid: string, data: RegisterPayload) {
    // 3. Appelle ton backend pour créer aussi l'user
    await fetch("http://localhost:8080/api/users", {
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