import { Review } from "./review";

export interface UserGame{
    userGameId: number;
    status: string | null;
    isOwned: boolean;
    reviewId: number;
    completionDate: string;
    gameId: number;
}