import {useNavigate} from "react-router-dom";
import {useState} from "react";
import "mdb-react-ui-kit/dist/css/mdb.min.css";
import "@fortawesome/fontawesome-free/css/all.min.css";
import "bootstrap/dist/css/bootstrap.min.css";
import axios from 'axios';
import {store} from "../GlobalData/store.ts";


export const Login:React.FC = () => {

    const navigate = useNavigate();

    const[loginCreds, setLoginCreds] = useState({
        username: "",
        password: ""
    })

    //store user inputs
    const storeValues = (e:React.ChangeEvent<HTMLInputElement>) => {
        //store the name of the box and the value of the inputs
        const name = e.target.name;
        const value = e.target.value;

        //set login credentials
        setLoginCreds((loginCreds) => ({...loginCreds, [name]:value}));
    }

    const login = async () => {
        //use try catch blocks for !!!SAFETY!!!
        try {
            const response = await axios.post("http://localhost:8080/auth/login", loginCreds, {withCredentials: true});

            store.loggedInUser = response.data;




            if (store.getLoggedInUserRole() == "ADMIN") {
                navigate("/admin/users");
            } else {
                navigate("/reimbursements", { state: { userId: store.loggedInUser.userId } });
            }


        } catch {
            alert("Login Failed");
        }
    }


    return (
        <form>
            <div className={"card p-5 shadow-lg"} style={{width: "25rem"}}>
            <h3>Sign In</h3>
            {/*<!-- Email input -->*/}
                <div className="form-floating mb-1 pb-2">
                {/* styling for bootstrap goes in the classnames */}
                {/* here we're indicating a border of thickness 1 and color grey */}
                <input type="text" id="usernameInput" className="form-control border border-1 border-grey" style={{fontSize:".95rem"}}
                       onChange={storeValues} name={"username"}/>
                {/* TODO: get label to float to the right spot */}
                <label className="form-label form-control-sm" htmlFor="usernameInput" style={{}} >Username</label>
            </div>

            {/*<!-- Password input -->*/}
            <div className="form-floating mb-1">
                <input type="password" id="passwordInput" className="form-control border border-1 border-grey" style={{fontSize:".95rem"}}
                       onChange={storeValues} name={"password"}/>
                <label className="form-label form-control-sm" htmlFor="passwordInput">Password</label>
            </div>

            {/*<!-- Forgot password WOULD go here, but it's not in the mockup -->*/}

            {/*}<!-- Submit button -->*/}
            <button type="button" data-mdb-button-init data-mdb-ripple-init
                    className="btn btn-primary btn-block mb-4" onClick={login}>Sign in
            </button>

            {/* Register buttons */}
            <div className="text-center">
                <p>Not a user? <a href="#!" onClick={()=>navigate("/register")}>Register</a></p>

            </div>
            </div>
        </form>
    )
}