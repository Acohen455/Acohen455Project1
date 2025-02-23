import {createBrowserRouter} from "react-router-dom";

import {Login} from "./pages/Login.tsx";
import {Register} from "./pages/Register.tsx";
import {NameInput} from "./pages/NameInput.tsx";
import {AdminViewUserTable} from "./pages/AdminViewUserTable.tsx";
import {Layout} from "./components/Layout.tsx";
import {AdminViewReimbTable} from "./pages/AdminViewReimbTable.tsx";
import {ReimbCreate} from "./pages/ReimbCreate.tsx";
import {UserReimbTable} from "./pages/UserViewReimbTable.tsx";


export const router = createBrowserRouter([
    {
        path: "/",
        element: <Layout />,
        children: [
            {path: "/", element: <Login />},
            {path: "/login", element: <Login></Login>},
            {path: "/register", element: <Register />},
            {path: "/nameinput", element: <NameInput />},
            {path: "/admin/users", element: <AdminViewUserTable />},
            {path: "/admin/reimbursements", element: <AdminViewReimbTable />},
            {path: "/reimbcreate", element: <ReimbCreate />},
            {path: "/user/reimbursements", element: <UserReimbTable />},
        ]
    }



])