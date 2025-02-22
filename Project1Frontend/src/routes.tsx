import {createBrowserRouter} from "react-router-dom";

import {Login} from "./pages/Login.tsx";
import {Register} from "./pages/Register.tsx";
import {NameInput} from "./pages/NameInput.tsx";

export const router = createBrowserRouter([
    {path: "/", element: <div>Home</div>},
    {path: "/login", element: <Login></Login>},
    {path: "/register", element: <Register />},
    {path: "/nameinput", element: <NameInput />}
])