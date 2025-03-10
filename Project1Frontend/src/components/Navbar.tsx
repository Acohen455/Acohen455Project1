import {store} from "../GlobalData/store.ts";
import Button from "react-bootstrap/Button";
import {useNavigate} from "react-router-dom";

export const Navbar:React.FC = () => {

    const navigate = useNavigate();


    return (
        // https://mdbootstrap.com/docs/standard/components/navbar/
        //bootstrap commands help style
        <nav className="navbar navbar-expand-lg navbar-light bg-body-tertiary fixed-top w-100">

            <div className="container-fluid">
                <div className="collapse navbar-collapse" id="navbarButtonsExample">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                        <li className="nav-item">
                            <span className="nav-link" style={{fontFamily: 'Poppins', userSelect: 'none'}}>Dashboard</span>
                        </li>
                    </ul>
                </div>

                {store.getLoggedInUserRole() == "ADMIN" ? (
                    <div>
                        <Button className={"me-2"} variant="primary" onClick={()=>navigate("/admin/users")}>Users</Button>
                        <Button variant={"primary"} onClick={()=>navigate("/admin/reimbursements")}>Reimbursements</Button>
                        <Button variant="primary" className={"ms-2"} onClick={() => navigate("/register")}>Register User</Button>
                    </div>
                ) : null}

                {store.getLoggedInUserRole() !== "" ? (
                    <div className="ms-2">
                        <Button className={"me-2"} variant = "primary" onClick={() => navigate("/reimbcreate")}>Create Reimbursement</Button>
                        <Button className={"me-2"} variant = "primary" onClick={() => {store.resetStore(); navigate("/")}}>Logout</Button>
                    </div>) : null
                }

            </div>
        </nav>
    )
}