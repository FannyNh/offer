import { ReactNode } from "react";
import {ThemeSwitch} from "@/components/ThemeSwitch";

interface LayoutProps {
    children: ReactNode;
}

export const Layout = ({ children }: LayoutProps) => {
    return (
        <div className="min-h-screen bg-white text-purple-900 dark:bg-purple-900 dark:text-white transition-colors duration-300">
            {/* Header simple avec ThemeSwitch */}
            <header className="flex justify-end p-4">
                <ThemeSwitch />
            </header>

            {/* Contenu principal */}
            <main>{children}</main>
        </div>
    );
};
