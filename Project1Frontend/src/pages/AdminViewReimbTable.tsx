import Dropdown from 'react-bootstrap/Dropdown';
import {Reimbursement} from "../interfaces/Reimbursement.ts";
import {useEffect, useState} from "react";
import axios from "axios";


export const AdminViewReimbTable:React.FC = () => {

    const [reimbursements, setReimbursements] = useState<Reimbursement[]>([]);

    useEffect(() => {
        //Call API to get all reimbursements on component load
        getAllReimbursements();
    }, []);

    const getAllReimbursements = async () => {
        try{
            const response = await axios.get("http://localhost:8080/admin/reimbursements", {withCredentials: true});
            setReimbursements(response.data);
        } catch {
            alert("Failed to get reimbursements");
        }
    }

    const approveReimbursement = async (reimbursementId: number, statusString: string) => {
        try{
            await axios.put(`http://localhost:8080/admin/updatereimbursement/${reimbursementId}`, null, {withCredentials: true,
                params: {reimbursementId: reimbursementId, status: statusString}});
            getAllReimbursements();
        } catch {
            alert("Failed to approve reimbursement");
        }
    }



    return (
        <div className={"container-fluid vh-100 vw-95 mx-auto align-items-center justify-content-center"}>
            <h2 className={"fw-bold mt-5"}>Reimbursement Table</h2>
            <table className="table table-striped table-hover w-100">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Description</th>
                    <th scope="col">Amount</th>
                    <th scope="col">Pending</th>
                    <th scope={"col"}>Approval Status</th>
                    <th scope={"col"}>User ID</th>
                </tr>
                </thead>
                <tbody>
                {/* TODO: map over users and display them programmatically*/}
                {reimbursements
                    .sort((a, b) => a.reimbursementId - b.reimbursementId)
                    .map((reimbursement) => (
                    <tr key={reimbursement.reimbursementId}>
                        <th scope="row">{reimbursement.reimbursementId}</th>
                        <td>{reimbursement.description}</td>
                        <td>{`$${reimbursement.amount.toLocaleString()}`}</td>
                        <td> {reimbursement.status === "PENDING" ? (
                                <Dropdown>
                                    <Dropdown.Toggle variant="secondary" id="dropdown-basic" size={"sm"}>
                                        Pending
                                    </Dropdown.Toggle>
                                    <Dropdown.Menu>
                                        <Dropdown.Item onClick={() => approveReimbursement(reimbursement.reimbursementId,
                                                                                                                "APPROVED")}>Approve</Dropdown.Item>
                                        <Dropdown.Item onClick={() => approveReimbursement(reimbursement.reimbursementId,
                                                                                                                        "DENIED")}>Deny</Dropdown.Item>
                                    </Dropdown.Menu>
                                </Dropdown>
                        ):
                            (<span className={"text-secondary"}>Closed</span>)
                        }

                        </td>
                        <td>{reimbursement.status === "APPROVED" ? (
                            <span className={"text-success"}>Approved</span>
                        ) : reimbursement.status === "DENIED" ? (
                            <span className={"text-danger"}>Denied</span>
                        ) : (
                            <span></span>
                        )}</td>
                        <td>{reimbursement.user.userId}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
}