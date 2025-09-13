import { useState, useEffect } from "react";
import { onAuthStateChanged, signInWithEmailAndPassword, createUserWithEmailAndPassword, User } from "firebase/auth";
import { auth } from "@/firebase/config";

export const useFirebaseAuth = () => {
    const [user, setUser] = useState<User | null>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const unsubscribe = onAuthStateChanged(auth, (currentUser) => {
            setUser(currentUser);
            setLoading(false);
        });
        return unsubscribe;
    }, []);

    const login = (email: string, password: string) =>
        signInWithEmailAndPassword(auth, email, password);

    const register = (email: string, password: string) =>
        createUserWithEmailAndPassword(auth, email, password);

    const logout = () => auth.signOut();

    return { user, loading, login, register, logout };
};
