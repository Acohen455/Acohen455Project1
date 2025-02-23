import {useNavigate} from "react-router-dom";
import {useState} from "react";
import "mdb-react-ui-kit/dist/css/mdb.min.css";
import "@fortawesome/fontawesome-free/css/all.min.css";
import "bootstrap/dist/css/bootstrap.min.css";
import axios from 'axios';

export const Register:React.FC = () => {


    const navigate = useNavigate();

    const[regCreds, setRegCreds] = useState({
        username: "",
        password: ""
    })

    const storeValues = (e:React.ChangeEvent<HTMLInputElement>) => {
        //store the name of the box and the value of the inputs
        const name = e.target.name;
        const value = e.target.value;

        //set login credentials
        setRegCreds((regCreds) => ({...regCreds, [name]:value}));
    }





    return (
        <form>
            <div className={"card p-5 shadow-lg"} style={{width: "25rem"}}>
                <h3>Register</h3>
                {/*<!-- Email input -->*/}
                <div className="form-floating mb-1 pb-2">
                    {/* styling for bootstrap goes in the classnames */}
                    {/* here we're indicating a border of thickness 1 and color grey */}
                    <input type="email" id="emailInput" className="form-control border border-1 border-grey" style={{fontSize:".95rem"}}/>
                    {/* TODO: get label to float to the right spot */}
                    <label className="form-label form-control-sm" htmlFor="emailInput" style={{}} >Email address</label>
                </div>

                {/*<!-- Password input -->*/}
                <div className="form-floating mb-1 pb-2">
                    <input type="password" id="passwordInput" className="form-control border border-1 border-grey" style={{fontSize:".95rem"}}/>
                    <label className="form-label form-control-sm" htmlFor="passwordInput">Password</label>
                </div>

                {/*<!-- div for spacing -->*/}
                <div className="row mb-2">
                </div>

                {/*}<!-- Submit button -->*/}
                <button type="button" data-mdb-button-init data-mdb-ripple-init
                        className="btn btn-primary btn-block mb-4">Register
                </button>

                {/* Register buttons */}
                <div className="text-center">
                    <p>Already a user? <a href="#!">Sign In</a></p>

                </div>
            </div>
        </form>
    )
}
