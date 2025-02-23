import {User} from "./User.ts";

export interface Reimbursement{
    reimbursementId: number;
    description: string;
    amount: number;
    pending: boolean;
    approved: boolean;
    user: User;
}