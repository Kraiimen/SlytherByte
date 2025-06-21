export interface Review{
    reviewId: number,
    title: string,
    description: string, 
    date: string,
    userProfileId: number
    gameId: number;
    userGameId: number;
    rating: number;
}