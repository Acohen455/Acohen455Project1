import {handleMoneyInput} from "../util/Formatters.tsx";
import {useState} from "react";
import {store} from "../GlobalData/store.ts";

export const ReimbCreate:React.FC = () => {


    const [amount, setAmount] = useState("");


    return (
        <form>
            <div className={"card p-5 shadow-lg"} style={{width: "25rem"}}>
                <h3 className={"mb-4"}>Create Reimbursement</h3>
                {/*<!-- Email input -->*/}
                <div className="form-floating mb-1 pb-2">
                    {/* styling for bootstrap goes in the classnames */}
                    {/* here we're indicating a border of thickness 1 and color grey */}
                    <textarea id="reimbursementDescription" className="form-control border border-1 border-grey" style={{fontSize:".95rem",
                        resize: "none", overflow:"hidden"}}
                    rows={1}
                      onInput={(e) => {
                          const target = e.target as HTMLTextAreaElement;
                          target.style.height = "auto"; // Reset height to prevent jumping
                          target.style.height = `${target.scrollHeight}px`; // Adjust dynamically
                      }
                    }/>
                    {/* TODO: get label to float to the right spot */}
                    <label className="form-label form-control-sm" htmlFor="reimbursementDescription">Reimbursement Description</label>
                </div>

                {/*this allows us to input the reimbursement amount*/}
                <div className="input-group">
                    <p className="input-group-text">Amount</p>
                    <span className="input-group-text">$</span>
                    <input
                        type="text"  // Using text instead of "number" for better control
                        className="form-control"
                        placeholder="0.00"
                        value={amount}
                        onChange={(e) => handleMoneyInput(e.target.value, setAmount)}
                    />
                </div>

                    {/*this allows us to manually set user id  if we're an admin*/}
                {store.getLoggedInUser()  == "ADMIN"  ? (
                    <div className={"input-group"}>
                        <span className="input-group-text mb-3">User ID</span>
                        <input type={"number"} className={"form-control"} placeholder={"User ID"}/>
                    </div>
                ) : null}

                {/*}<!-- Submit button -->*/}
                <button type="button" data-mdb-button-init data-mdb-ripple-init
                        className="btn btn-primary btn-block mb-4">Submit Reimbursement
                </button>

            </div>
        </form>
    )


}