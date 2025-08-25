import { useForm } from "react-hook-form";
import type { RegisterPayload } from "../types/auth";
import { registerApi } from "../api/authApi";
import { useAuth } from "../hooks/useAuth";
import { useNavigate } from "react-router-dom";

const Register = () => {
    const { register, handleSubmit } = useForm<RegisterPayload>();
    const { login } = useAuth();
    const navigate = useNavigate();

    const onSubmit = async (data: RegisterPayload) => {
        try {
            const res = await registerApi(data);
            login(res.user, res.token); // connecte l'utilisateur après inscription
            navigate("/dashboard");     // redirige vers dashboard
        } catch (error) {
            console.error("Erreur inscription:", error);
        }
    };

    return (
        <div className="flex items-center justify-center h-screen bg-gray-100">
            <form
                onSubmit={handleSubmit(onSubmit)}
                className="bg-white p-6 rounded shadow w-96"
            >
                <h2 className="text-2xl mb-4">Inscription</h2>

                <input
                    {...register("firstName")}
                    placeholder="Prénom"
                    className="border p-2 w-full mb-3"
                />

                <input
                    {...register("lastName")}
                    placeholder="Nom"
                    className="border p-2 w-full mb-3"
                />

                <input
                    {...register("email")}
                    type="email"
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
                    className="bg-green-600 text-white px-4 py-2 w-full rounded"
                >
                    S'inscrire
                </button>
            </form>
        </div>
    );
};

export default Register;
