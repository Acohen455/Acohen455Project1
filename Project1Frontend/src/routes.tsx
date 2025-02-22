import {createBrowserRouter} from "react-router-dom";

import {Login} from "./pages/Login.tsx";
import {Register} from "./pages/Register.tsx";
import {NameInput} from "./pages/NameInput.tsx";
import {AdminViewUserTable} from "./pages/AdminViewUserTable.tsx";
import {Layout} from "./components/Layout.tsx";
import {AdminViewReimbTable} from "./pages/AdminViewReimbTable.tsx";


export const router = createBrowserRouter([
    {
        path: "/",
        element: <Layout />,
        children: [
            {path: "/", element: <div>Home</div>},
            {path: "/login", element: <Login></Login>},
            {path: "/register", element: <Register />},
            {path: "/nameinput", element: <NameInput />},
            {path: "/admin/users", element: <AdminViewUserTable />},
            {path: "/admin/reimbursements", element: <AdminViewReimbTable />}
        ]
    }



])