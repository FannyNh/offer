import { useForm } from "react-hook-form";
import type { LoginPayload } from "../types/auth";
import { useFirebaseAuth } from "@/hooks/useFirebaseAuth";
import { useNavigate, Link } from "react-router-dom";

const Login = () => {
    const { register, handleSubmit } = useForm<LoginPayload>();
    const { login } = useFirebaseAuth();
    const navigate = useNavigate();

    const onSubmit = async (data: LoginPayload) => {
        try {
            await login(data.email, data.password);
            navigate("/dashboard");
        } catch (error) {
            console.error("Erreur login Firebase:", error);
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
                    className="bg-blue-600 text-white px-4 py-2 w-full rounded mb-3"
                >
                    Se connecter
                </button>
                <p className="text-center text-sm">
                    Pas de compte ?{" "}
                    <Link to="/register" className="text-blue-600 underline">
                        Créer un compte
                    </Link>
                </p>
            </form>
        </div>
    );
};

export default Login;
