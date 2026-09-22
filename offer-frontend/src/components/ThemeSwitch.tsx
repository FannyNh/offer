import {useTheme} from "@/context/ThemeContext";


export const ThemeSwitch = () => {
    const { dark, toggleDark } = useTheme();

    return (
        <button
            className="px-4 py-2 rounded-xl bg-purple-500 text-white dark:bg-orange-500"
            onClick={toggleDark}
        >
            {dark ? "Light Mode" : "Dark Mode"}
        </button>
    );
};
