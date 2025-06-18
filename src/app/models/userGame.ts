import { Review } from "./review";

export interface UserGame{
    userGameId: number;
    status: string | null;
    isOwned: boolean;
    review: Review;
    completionDate: Date;
    gameId: number;
}