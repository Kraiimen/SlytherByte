export interface Review{
    reviewId: number,
    title: string,
    description: string, 
    date: Date,
    userProfileId: number
    gameId: number;
    userGameId: number;
}