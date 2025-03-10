import Button from 'react-bootstrap/Button';
import {useEffect, useState} from "react";
import {User} from "../interfaces/User.ts";
import axios from "axios";
import { useNavigate } from 'react-router-dom';

export const AdminViewUserTable:React.FC = () => {


    const [userData, setUserData] = useState<User[]>([]);

    const navigate = useNavigate();

    useEffect(() => {
        //Call API to get all users on component load
        getAllUsers();
    }, []);


    const getAllUsers = async () => {
        try{
            const response = await axios.get("http://localhost:8080/admin/users", {withCredentials: true});
            setUserData(response.data.sort((a: User, b: User) => a.userId - b.userId));
        } catch {
            alert("Failed to get users");
        }
    }

    const fireUser = async (userId: number) => {
        try{
            await axios.delete(`http://localhost:8080/admin/deleteuser`, {withCredentials: true, params : {userId}});
            getAllUsers();
        } catch (error) {
            // @ts-ignore
            console.error("Failed to fire user:", error.response ? error.response.data : error)
            alert("Failed to fire user");
        }
    }




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
            {userData.map((user) => (
                <tr key={user.userId}>

                    <th scope="row">{user.userId}</th>
                    <td>{user.firstName}</td>
                    <td>{user.lastName}</td>
                    <td>{user.username}</td>
                    <td>{user.role}</td>
                    <td><Button variant="primary" size={"sm"} onClick={() => {
                        console.log("Navigating to /reimbursements with userId:", user.userId);
                        navigate('/reimbursements', { state: { userId: user.userId } });
                    }}>
                        Reimbursements</Button></td>
                    <td>
                        {user.role == "ADMIN" ? null :(<Button variant="danger" size={"sm"} onClick={() => {fireUser(user.userId)}}>Fire</Button>)}
                    </td>

                </tr>
            ))}
            </tbody>
        </table>
        </div>
    )
}