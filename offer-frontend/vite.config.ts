import { defineConfig, type PluginOption } from 'vite';
import react from '@vitejs/plugin-react';
import tsconfigPaths from 'vite-tsconfig-paths';

export default defineConfig({
    plugins: [
        react(),
        tsconfigPaths()
    ] as PluginOption[],
    server: {
        port: 5173
    }
});
