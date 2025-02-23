import {useEffect, useState} from "react";
import {Reimbursement} from "../interfaces/Reimbursement.ts";
import axios from "axios";
import {useLocation} from "react-router-dom";


export const UserReimbTable: React.FC = () => {


    const location = useLocation();
    const userId = location.state?.userId


    //TODO: REMEMBER TO PASS PROPS!!!!
    //TODO: Figure out why passing null values for the status column breaks the database calls
    const [reimbursements, setReimbursements] = useState<Reimbursement[]>([]);

    useEffect(() => {
            if (userId) {
                console.log("Fetching reimbursements for userId:", userId);
                getUserReimbursements(userId);
            } else {
                console.error("No userId found in location state");
            }
    }, [userId]);


    const getUserReimbursements = async (userId: number) => {
        try{
            console.log(userId);
            const response = await axios.get(`http://localhost:8080/reimbursements`, {withCredentials: true,
                params: {userId}});
            setReimbursements(response.data);
        } catch {
            alert("Failed to get reimbursements");
        }
    }



    return (
        <div className={"container-fluid vh-100 vw-95 mx-auto align-items-center justify-content-center"}>
            <h2 className={"fw-bold mt-5"}>Reimbursements Table</h2>
            <table className="table table-striped table-hover w-100">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Description</th>
                    <th scope="col">Amount</th>
                    <th scope="col">Pending</th>
                    <th scope="col">Status</th>
                </tr>
                </thead>
                <tbody>
                {reimbursements.map((reimbursement) => (
                    <tr key={reimbursement.reimbursementId}>
                        <th scope="row">{reimbursement.reimbursementId}</th>
                        <td>{reimbursement.description}</td>
                        <td>{`$${reimbursement.amount.toLocaleString()}`}</td>
                        <td>
                            {reimbursement.status === "PENDING" ? (
                                <span className={"text-success"}>Pending</span>
                            ) : (
                                <span className={"text-secondary"}>Closed</span>
                            )}
                        </td>
                        <td>
                            {reimbursement.status === "PENDING" ? (
                                <span></span>
                            ) : reimbursement.status === "APPROVED" ? (
                                <span className={"text-success"}>Approved</span>
                            ) : (
                                <span className={"text-danger"}>Denied</span>
                            )}
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};
