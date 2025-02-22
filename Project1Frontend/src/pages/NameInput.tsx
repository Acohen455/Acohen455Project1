export const NameInput:React.FC = () => {
    return (
        <form>
            <div className={"card p-5 shadow-lg"} style={{width: "25rem"}}>
                <h3>Please enter your name</h3>
                {/*<!-- Email input -->*/}
                <div className="form-floating mb-1 mt-3 pb-2">
                    {/* styling for bootstrap goes in the classnames */}
                    {/* here we're indicating a border of thickness 1 and color grey */}
                    <input type="text" id="firstNameInput" className="form-control border border-1 border-grey" style={{fontSize:".95rem"}}/>
                    {/* TODO: get label to float to the right spot */}
                    <label className="form-label form-control-sm" htmlFor="firstNameInput">First Name</label>
                </div>

                {/*<!-- Password input -->*/}
                <div className="form-floating mb-1 pb-2">
                    <input type="text" id="lastNameInput" className="form-control border border-1 border-grey" style={{fontSize:".95rem"}}/>
                    <label className="form-label form-control-sm" htmlFor="lastNameInput">Last Name</label>
                </div>

                {/*<!-- div for spacing -->*/}
                <div className="row mb-2">
                </div>

                {/*}<!-- Submit button -->*/}
                <button type="button" data-mdb-button-init data-mdb-ripple-init
                        className="btn btn-primary btn-block mb-4">Confirm
                </button>


            </div>
        </form>
    )
}