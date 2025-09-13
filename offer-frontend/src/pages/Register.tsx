import {useForm} from "react-hook-form";
import {useAuth} from "@/hooks/useAuth";
import {Link, useNavigate} from "react-router-dom";
import {createUserInBackend} from "@/api/userApi";
import {RegisterPayload} from "@/types/auth";


const Register = () => {
    const {register, handleSubmit} = useForm<RegisterPayload>();
    const {register: firebaseRegister} = useAuth();
    const navigate = useNavigate();

    const onSubmit = async (data: RegisterPayload) => {
        try {
            // 1. Crée l'utilisateur dans Firebase
            const cred = await firebaseRegister(data.email, data.password);

            // 2. Récupère le token Firebase
            const token = await cred.user.getIdToken();
            await createUserInBackend(token, cred.user.uid,data);

            // 4. Redirige vers le dashboard
            navigate("/dashboard");
        } catch (error: any) {
            alert(error.message || "Erreur lors de l'inscription");
        }
    };


    return (
        <div className="flex items-center justify-center h-screen bg-gray-100">
            <form
                onSubmit={handleSubmit(onSubmit)}
                className="bg-white p-6 rounded-2xl shadow w-96"
            >
                <h2 className="text-2xl font-bold mb-4">Inscription</h2>
                <input
                    {...register("email", {required: true})}
                    type="email"
                    placeholder="Email"
                    className="border p-2 w-full mb-3 rounded"
                />
                <input
                    {...register("password", {required: true, minLength: 6})}
                    type="password"
                    placeholder="Mot de passe (min 6 caractères)"
                    className="border p-2 w-full mb-3 rounded"
                />
                <button
                    type="submit"
                    className="bg-green-600 hover:bg-green-700 text-white px-4 py-2 w-full rounded mb-3"
                >
                    S'inscrire
                </button>
                <p className="text-center text-sm">
                    Déjà un compte ?{" "}
                    <Link to="/login" className="text-blue-600 underline">
                        Se connecter
                    </Link>
                </p>
            </form>
        </div>
    );
};

export default Register;
