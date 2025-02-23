import Dropdown from 'react-bootstrap/Dropdown';
import Table from 'react-bootstrap/Table';
import Form from 'react-bootstrap/Form';


export const AdminViewReimbTable:React.FC = () => {
   const reimbursements = [
       { id: 1, description: "Test Description", amount: 100, pending: true, approved: false, user_id: 1},
       { id: 2, description: "Test Description", amount: 100, pending: false, approved: false, user_id: 2},
       { id: 3, description: "Test Description", amount: 100, pending: true, approved: true, user_id: 1},
   ];



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
                {reimbursements.map((reimbursement) => (
                    <tr key={reimbursement.id}>
                        <th scope="row">{reimbursement.id}</th>
                        <td>{reimbursement.description}</td>
                        <td>{`$${reimbursement.amount.toLocaleString()}`}</td>
                        <td> {reimbursement.pending ? (
                                <Dropdown>
                                    <Dropdown.Toggle variant="secondary" id="dropdown-basic" size={"sm"}>
                                        Pending
                                    </Dropdown.Toggle>
                                    <Dropdown.Menu>
                                        <Dropdown.Item href="#/action-1">Approve</Dropdown.Item>
                                        <Dropdown.Item href="#/action-2">Deny</Dropdown.Item>
                                    </Dropdown.Menu>
                                </Dropdown>
                        ):
                            (<span className={"text-secondary"}>Closed</span>)
                        }

                        </td>
                        <td>{reimbursement.approved ? (
                            <span className={"text-success"}>Approved</span>
                        ): (
                            <span className={"text-danger"}>Denied</span>
                        )}</td>
                        <td>{reimbursement.user_id}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
}