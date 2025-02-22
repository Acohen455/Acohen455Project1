import globals from "globals";
import pluginJs from "@eslint/js";
import tseslint from "typescript-eslint";
import pluginReact from "eslint-plugin-react";
import importPlugin from "eslint-plugin-import";

/** @type {import('eslint').Linter.FlatConfig[]} */
export default [
  {
    files: ["**/*.{js,mjs,cjs,ts,jsx,tsx}"],
    languageOptions: {
      globals: globals.browser,
    },
    settings: {
      react: {
        version: "detect",
      },
      "import/resolver": {
        typescript: {
          project: "./tsconfig.app.json", // Point to your tsconfig file
        },
      },
    },
    plugins: {
      import: importPlugin,
      react: pluginReact, // Add the React plugin here
    },
    rules: {
      ...importPlugin.configs.recommended.rules,
      "react/react-in-jsx-scope": "off", // Ensure this rule is disabled
      "import/prefer-default-export": "warn",
      "@typescript-eslint/ban-ts-comment": "off",
      "react/display-name": "off",
      "@typescript-eslint/no-empty-interface": "warn",
    },
  },
  pluginJs.configs.recommended,
  ...tseslint.configs.recommended,
  {
    // Override the recommended rules from eslint-plugin-react
    ...pluginReact.configs.flat.recommended,
    rules: {
      ...pluginReact.configs.flat.recommended.rules,
      "react/react-in-jsx-scope": "off",
      'react/display-name': 'off',
      '@typescript-eslint/ban-ts-comment': 'off',// Explicitly disable the rule
    },
  },
];