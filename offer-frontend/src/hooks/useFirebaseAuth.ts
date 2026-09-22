import {useEffect, useState} from "react";
import {
    createUserWithEmailAndPassword,
    onAuthStateChanged,
    signInWithEmailAndPassword,
    signOut,
    User,
} from "firebase/auth";
import {auth} from "@/firebase/config";
import {createUserInBackend, getMe} from "@/api/userApi";
import {ApiUser} from "@/types/auth";

export const useFirebaseAuth = () => {
    const [user, setUser] = useState<User | null>(null);
    const [backendUser, setBackendUser] = useState<ApiUser | null>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        return onAuthStateChanged(auth, (currentUser) => {
            setUser(currentUser);
        });
    }, []);
    useEffect(() => {
        (async () => {
            if(user){
                try {
                    const idToken = await user.getIdToken();
                    const me = await getMe(idToken);
                    setBackendUser(me);
                } catch (err: any) {
                    const idToken = await user.getIdToken();
                    if (err instanceof Error && (err as any).status === 404) {
                        await createUserInBackend(idToken, user.uid, {
                            firstName: user.displayName || "",
                            lastName: user.displayName || "",
                            email: user.email || "",
                            password: ""
                        });
                        const me = await getMe(idToken);
                        setBackendUser(me);
                    } else {
                        console.error("Failed to fetch /me", err);
                        setBackendUser(null);
                    }
                } finally {
                    setLoading(false);
                }
            }
        })();
    }, [user]);





    const login = (email: string, password: string) =>
        signInWithEmailAndPassword(auth, email, password);

    const register = (email: string, password: string) =>
        createUserWithEmailAndPassword(auth, email, password);

    const logout = () => signOut(auth);

    return {user, loading, login, register, logout,backendUser};
};
