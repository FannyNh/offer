/** @type {import('tailwindcss').Config} */
export default {
    content: [
        "./index.html",
        "./src/**/*.{js,ts,jsx,tsx}",
    ],
    darkMode: 'class',
    theme: {
        extend: {
            colors: {
                white: "#f8fafc",
                purple: {
                    100: "#f3eef9",
                    200: "#e0d9f3",
                    300: "#c6b8eb",
                    400: "#a689e0",
                    500: "#4c1d95", // couleur principale
                    600: "#421978",
                    700: "#36155f",
                    800: "#2a1047",
                    900: "#1e0a2b",
                },
                orange: {
                    100: "#fff1e6",
                    200: "#ffd6b8",
                    300: "#ffb285",
                    400: "#ff8c4d",
                    500: "#ea580c", // couleur principale
                    600: "#c44a0a",
                    700: "#9b3b08",
                    800: "#732e06",
                    900: "#4b1f03",
                },
            },
            spacing: {
                px: '1px',
                0: '0px',
                1: '0.25rem',   // 4px
                2: '0.5rem',    // 8px
                3: '0.75rem',   // 12px
                4: '1rem',      // 16px
                5: '1.25rem',   // 20px
                6: '1.5rem',    // 24px
                8: '2rem',      // 32px
                10: '2.5rem',   // 40px
                12: '3rem',     // 48px
                16: '4rem',     // 64px
                20: '5rem',     // 80px
                24: '6rem',     // 96px
                32: '8rem',     // 128px
                40: '10rem',    // 160px
                48: '12rem',    // 192px
                56: '14rem',    // 224px
                64: '16rem',    // 256px
            },
            fontSize: {
                xs: ['0.75rem', { lineHeight: '1rem' }],   // 12px
                sm: ['0.875rem', { lineHeight: '1.25rem' }], // 14px
                base: ['1rem', { lineHeight: '1.5rem' }], // 16px
                lg: ['1.125rem', { lineHeight: '1.75rem' }], // 18px
                xl: ['1.25rem', { lineHeight: '1.75rem' }], // 20px
                '2xl': ['1.5rem', { lineHeight: '2rem' }], // 24px
                '3xl': ['1.875rem', { lineHeight: '2.25rem' }], // 30px
                '4xl': ['2.25rem', { lineHeight: '2.5rem' }], // 36px
                '5xl': ['3rem', { lineHeight: '1' }],       // 48px
                '6xl': ['3.75rem', { lineHeight: '1' }],    // 60px
                '7xl': ['4.5rem', { lineHeight: '1' }],     // 72px
                '8xl': ['6rem', { lineHeight: '1' }],       // 96px
                '9xl': ['8rem', { lineHeight: '1' }],       // 128px
            },
            borderRadius: {
                'sm': '0.125rem',
                DEFAULT: '0.25rem',
                'md': '0.375rem',
                'lg': '0.5rem',
                'xl': '1rem',
                '2xl': '1.5rem',
                '3xl': '2rem',
                full: '9999px',
            },
        },
    },
    plugins: [
        require('@tailwindcss/forms'),
        require('@tailwindcss/typography'),
        require('@tailwindcss/aspect-ratio'),
        require('@tailwindcss/line-clamp'),
    ],
}
