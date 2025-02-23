import Button from 'react-bootstrap/Button';

export const AdminViewUserTable:React.FC = () => {

    const users = [
        { id: 1, firstName: "Mark", lastName: "Otto", username: "@mdo", role: "Admin" },
        { id: 2, firstName: "Jacob", lastName: "Thornton", username: "@fat", role: "User" },
        { id: 3, firstName: "Larry", lastName: "Bird", username: "@twitter", role: "User" },
    ];



    return (
        <div className={"container-fluid vh-100 vw-80 mx-auto align-items-center justify-content-center"}>
        <h2 className={"fw-bold mt-5"}>User Table</h2>
        <table className="table table-striped table-hover w-100">
            <thead>
            <tr>
                <th scope="col">UserId</th>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Username</th>
                <th scope={"col"}>Role</th>
                <th scope={"col"}></th>
                <th scope={"col"}></th>
            </tr>
            </thead>
            <tbody>
            {users.map((user) => (
                <tr key={user.id}>

                    <th scope="row">{user.id}</th>
                    <td>{user.firstName}</td>
                    <td>{user.lastName}</td>
                    <td>{user.username}</td>
                    <td>{user.role}</td>
                    <td><Button variant="primary" size={"sm"}>Reimbursements</Button></td>
                    <td><Button variant="danger" size={"sm"}>Fire</Button></td>
                </tr>
            ))}
            </tbody>
        </table>
        </div>
    )
}