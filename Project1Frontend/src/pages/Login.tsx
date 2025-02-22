import {useNavigate} from "react-router-dom";
import {useState} from "react";
import {Button, Container, Form} from "react-bootstrap";
import "mdb-react-ui-kit/dist/css/mdb.min.css";
import "@fortawesome/fontawesome-free/css/all.min.css";
import "bootstrap/dist/css/bootstrap.min.css";


export const Login:React.FC = () => {

    const navigate = useNavigate();

    const[loginCreds, setLoginCreds] = useState({
        username: "",
        password: ""
    })


    return (
        <form>
            <div className={"card p-5 shadow-lg"} style={{width: "25rem"}}>
            <h3>Sign In</h3>
            {/*<!-- Email input -->*/}
            <div data-mdb-input-init className="form-floating">
                {/* styling for bootstrap goes in the classnames */}
                {/* here we're indicating a border of thickness 1 and color grey */}
                <input type="email" id="emailInput" className="form-control border border-1 border-grey"/>
                {/* TODO: get label to float to the right spot */}
                {/*<label className="form-label" htmlFor="emailInput" style={{}} >Email address</label>*/}
                <label for="emailInput" >Email address</label>
            </div>

            {/*<!-- Password input -->*/}
            <div data-mdb-input-init className="form-outline mb-4">
                <input type="password" id="form2Example2" className="form-control border border-1 border-grey"/>
                <label className="form-label" htmlFor="form2Example2">Password</label>
            </div>

            {/*<!-- 2 column grid layout for inline styling -->*/}
            <div className="row mb-4">
                <div className="col d-flex justify-content-center">
                    {/*<!-- Checkbox -->*/}
                    <div className="form-check">
                        <input className="form-check-input" type="checkbox" value="" id="form2Example31" checked/>
                        <label className="form-check-label" htmlFor="form2Example31"> Remember me </label>
                    </div>
                </div>

                <div className="col">
                    {/*<!-- Simple link -->*/}
                    <a href="#!">Forgot password?</a>
                </div>
            </div>

            {/*}<!-- Submit button -->*/}
            <button type="button" data-mdb-button-init data-mdb-ripple-init
                    className="btn btn-primary btn-block mb-4">Sign in
            </button>

            {/* Register buttons */}
            <div className="text-center">
                <p>Not a member? <a href="#!">Register</a></p>
                <p>or sign up with:</p>
                <button type="button" data-mdb-button-init data-mdb-ripple-init
                        className="btn btn-link btn-floating mx-1">
                    <i className="fab fa-facebook-f"></i>
                </button>

                <button type="button" data-mdb-button-init data-mdb-ripple-init
                        className="btn btn-link btn-floating mx-1">
                    <i className="fab fa-google"></i>
                </button>

                <button type="button" data-mdb-button-init data-mdb-ripple-init
                        className="btn btn-link btn-floating mx-1">
                    <i className="fab fa-twitter"></i>
                </button>

                <button type="button" data-mdb-button-init data-mdb-ripple-init
                        className="btn btn-link btn-floating mx-1">
                    <i className="fab fa-github"></i>
                </button>
            </div>
            </div>
        </form>
    )
}