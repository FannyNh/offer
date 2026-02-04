import { createContext, ReactNode } from "react";
import { useFirebaseAuth } from "@/hooks/useFirebaseAuth";

export const AuthContext = createContext<ReturnType<typeof useFirebaseAuth> | null>(null);

export const AuthProvider = ({ children }: { children: ReactNode }) => {
    const auth = useFirebaseAuth();
    return <AuthContext.Provider value={auth}>{children}</AuthContext.Provider>;
};
