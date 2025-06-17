import { Review } from "./review";

export interface UserGame{
    userGameId: number;
    status: string;
    isOwned: boolean;
    review: Review;
    completionDate: Date;
    gameId: number;
}