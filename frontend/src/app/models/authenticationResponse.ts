import { UserAccount } from "./userAccount";

export interface AuthenticationResponse {
    userAccountId: number;
    token: string;
}