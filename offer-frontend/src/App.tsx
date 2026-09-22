import {RouterProvider} from "react-router-dom";
import {AuthProvider} from "./context/AuthContext";
import {router} from "./router";
import {Layout} from "./components/Layout";
import {ThemeProvider} from "@/context/ThemeContext";

function App() {
    return (
        <ThemeProvider>
            <AuthProvider>
                <Layout>
                    <RouterProvider router={router}/>
                </Layout>
            </AuthProvider>
        </ThemeProvider>
    );
}

export default App;
