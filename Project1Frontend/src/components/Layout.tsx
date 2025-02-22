import { Outlet } from "react-router-dom";
import { Navbar } from "./Navbar";  // Import your Navbar component

export const Layout: React.FC = () => {
    return (
        <>
            <Navbar /> {/* Navbar appears on all pages */}
            <div className="container mt-4">
                <Outlet /> {/* Renders the current route's component */}
            </div>
        </>
    );
};