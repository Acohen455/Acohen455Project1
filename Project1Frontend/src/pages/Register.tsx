import {useNavigate} from "react-router-dom";
import {useState} from "react";
import "mdb-react-ui-kit/dist/css/mdb.min.css";
import "@fortawesome/fontawesome-free/css/all.min.css";
import "bootstrap/dist/css/bootstrap.min.css";
import axios from 'axios';
import {store} from "../GlobalData/store.ts";

export const Register:React.FC = () => {


    const navigate = useNavigate();

    const[regCreds, setRegCreds] = useState({
        username: "",
        password: "",
        firstName: "",
        lastName: ""
    })

    const storeValues = (e:React.ChangeEvent<HTMLInputElement>) => {
        //store the name of the box and the value of the inputs
        const name = e.target.name;
        const value = e.target.value;

        //set register credentials
        setRegCreds((regCreds) => ({...regCreds, [name]:value}));
    }


    const register = async () => {

        if (regCreds.username == "" || regCreds.password == "" || regCreds.firstName == "" || regCreds.lastName == "") {
            alert("Please fill out all fields");
            return;
        }
            //use try catch blocks for !!!SAFETY!!!
        /*
            try {
                const response = await axios.post("http://localhost:8080/auth/register", regCreds, {withCredentials: true});

                store.loggedInUser = response.data;

                if (store.getLoggedInUserRole() == "ADMIN") {
                    navigate("/admin/users");
                } else {
                    console.log(store.loggedInUser.userId)
                    navigate("/reimbursements", { state: { userId: store.loggedInUser.userId } });
                }

            } catch {
                alert("Registration Failed");
            }
            */

        try {
            const response = await axios.post("http://localhost:8080/auth/register", regCreds, {withCredentials: true});

            if (store.getLoggedInUserRole() !== "ADMIN"){
                store.loggedInUser = response.data;
            }






            if (store.getLoggedInUserRole() == "ADMIN") {
                navigate("/admin/users");
            } else {
                console.log(store.loggedInUser.userId)
                navigate("/reimbursements", { state: { userId: store.loggedInUser.userId } });
            }

        } catch {
            alert("Registration Failed");
        }

    }



    return (
        <form>
            <div className={"card p-5 shadow-lg"} style={{width: "25rem"}}>
                <h3>Register</h3>
                {/*<!-- Email input -->*/}
                <div className="form-floating mb-1 mt-3 pb-2">
                    {/* styling for bootstrap goes in the classnames */}
                    {/* here we're indicating a border of thickness 1 and color grey */}
                    <input type="text" id="firstNameInput" className="form-control border border-1 border-grey"
                           style={{fontSize:".95rem"}} name="firstName" onChange={storeValues}/>
                    {/* TODO: get label to float to the right spot */}
                    <label className="form-label form-control-sm" htmlFor="firstNameInput">First Name</label>
                </div>

                {/*<!-- Password input -->*/}
                <div className="form-floating mb-1 pb-2">
                    <input type="text" id="lastNameInput" className="form-control border border-1 border-grey"
                           style={{fontSize:".95rem"}} name="lastName" onChange={storeValues}/>
                    <label className="form-label form-control-sm" htmlFor="lastNameInput">Last Name</label>
                </div>



                <div className="form-floating mb-1 pb-2">
                    {/* styling for bootstrap goes in the classnames */}
                    {/* here we're indicating a border of thickness 1 and color grey */}
                    <input type="text" id="usernameInput" className="form-control border border-1 border-grey" style={{fontSize:".95rem"}}
                           name={"username"} onChange={storeValues}/>
                    {/* TODO: get label to float to the right spot */}
                    <label className="form-label form-control-sm" htmlFor="usernameInput">Username</label>
                </div>

                {/*<!-- Password input -->*/}
                <div className="form-floating mb-1 pb-2">
                    <input type="password" id="passwordInput" className="form-control border border-1 border-grey"
                           style={{fontSize:".95rem"}} name={"password"} onChange={storeValues}/>
                    <label className="form-label form-control-sm" htmlFor="passwordInput">Password</label>
                </div>



                {/*<!-- div for spacing -->*/}
                <div className="row mb-2">
                </div>

                {/*}<!-- Submit button -->*/}
                <button type="button" data-mdb-button-init data-mdb-ripple-init
                        className="btn btn-primary btn-block mb-4" onClick={register}>Register
                </button>

                {/* Register buttons */}
                <div className="text-center">
                    <p>Already a user? <a href="#!">Sign In</a></p>

                </div>
            </div>
        </form>
    )
}
