

import type {LoginPayload} from "@/types/auth";
import { useAuth } from "@/hooks/useAuth";
import {loginApi} from "@/api/authApi";
import {useForm} from "react-hook-form";

const Login = () => {
    const { register, handleSubmit } = useForm<LoginPayload>();
    const { login } = useAuth();

    const onSubmit = async (data: LoginPayload) => {
        try {
            const res = await loginApi(data);
            login(res.user, res.token);
        } catch (error) {
            console.error("Erreur login:", error);
        }
    };

    return (
        <div className="flex items-center justify-center h-screen bg-gray-100">
            <form
                onSubmit={handleSubmit(onSubmit)}
                className="bg-white p-6 rounded shadow w-96"
            >
                <h2 className="text-2xl mb-4">Connexion</h2>
                <input
                    {...register("email")}
                    placeholder="Email"
                    className="border p-2 w-full mb-3"
                />
                <input
                    {...register("password")}
                    type="password"
                    placeholder="Mot de passe"
                    className="border p-2 w-full mb-3"
                />
                <button
                    type="submit"
                    className="bg-blue-600 text-white px-4 py-2 w-full rounded"
                >
                    Se connecter
                </button>
            </form>
        </div>
    );
};

export default Login;
