import {User} from "./User.ts";

export interface Reimbursement{
    reimbursementId: number;
    description: string;
    amount: number;
    status: string;
    user: User;
}