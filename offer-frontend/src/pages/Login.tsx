import { useForm } from "react-hook-form";
import { useAuth } from "@/hooks/useAuth";
import { useNavigate, Link } from "react-router-dom";

interface LoginPayload {
    email: string;
    password: string;
}

const Login = () => {
    const { register, handleSubmit } = useForm<LoginPayload>();
    const { login } = useAuth();
    const navigate = useNavigate();

    const onSubmit = async (data: LoginPayload) => {
        try {
            await login(data.email, data.password);
            navigate("/dashboard");
        } catch (error: any) {
            alert(error.message || "Erreur de connexion");
        }
    };

    return (
        <div className="flex items-center justify-center h-screen bg-gray-100">
            <form
                onSubmit={handleSubmit(onSubmit)}
                className="bg-white p-6 rounded-2xl shadow w-96"
            >
                <h2 className="text-2xl font-bold mb-4">Connexion</h2>
                <input
                    {...register("email", { required: true })}
                    placeholder="Email"
                    className="border p-2 w-full mb-3 rounded"
                />
                <input
                    {...register("password", { required: true })}
                    type="password"
                    placeholder="Mot de passe"
                    className="border p-2 w-full mb-3 rounded"
                />
                <button
                    type="submit"
                    className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 w-full rounded mb-3"
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
