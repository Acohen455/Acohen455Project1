export const AdminViewReimbTable:React.FC = () => {
    return (
        <div className={"container-fluid vh-100 vw-80 mx-auto align-items-center justify-content-center"}>
            <table className="table table-striped table-hover w-100">
                <thead>
                <tr>
                    <th scope="col">Reimbursement ID</th>
                    <th scope="col">Description</th>
                    <th scope="col">Amount</th>
                    <th scope="col">Pending</th>
                    <th scope={"col"}>Approved</th>
                    <th scope={"col"}>User ID</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row">1</th>
                    <td>Mark</td>
                    <td>Otto</td>
                    <td>@mdo</td>
                </tr>
                <tr>
                    <th scope="row">2</th>
                    <td>Jacob</td>
                    <td>Thornton</td>
                    <td>@fat</td>
                </tr>
                <tr>
                    <th scope="row">3</th>
                    <td colSpan={2}>Larry the Bird</td>
                    <td>@twitter</td>
                </tr>
                </tbody>
            </table>
        </div>
    )
}