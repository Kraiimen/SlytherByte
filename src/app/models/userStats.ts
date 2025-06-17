import { Top5Tags } from "./top5Tags";

export interface UserStats{
    top5Tags: Top5Tags[];
    gamesOwned: number;
    gamesPlaying: number;
    gamesBeaten: number;
    userReviews: number;
}