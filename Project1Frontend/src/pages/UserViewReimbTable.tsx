

export const UserReimbTable: React.FC = () => {

    const reimbursements = [
        { id: 1, description: "Test Description", amount: 10000.35, pending: true, approved: false, user_id: 1 },
        { id: 2, description: "Test Description", amount: 100, pending: false, approved: false, user_id: 2 },
        { id: 3, description: "Test Description", amount: 100, pending: true, approved: true, user_id: 1 },
    ];

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
                    <tr key={reimbursement.id}>
                        <th scope="row">{reimbursement.id}</th>
                        <td>{reimbursement.description}</td>
                        <td>{`$${reimbursement.amount.toLocaleString()}`}</td>
                        <td>
                            {reimbursement.pending ? (
                                <span className={"text-success"}>Pending</span>
                            ) : (
                                <span className={"text-secondary"}>Closed</span>
                            )}
                        </td>
                        <td>
                            {reimbursement.approved ? (
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
