export interface Game{
    gameId: number;
    title: string;
    coverImageUrl: string;
    releaseDate: string;
    summary: string;
    franchiseId: number;
    publishers: string[],
    platforms: string[],
    developers: string[],
    stores: string[],
    tags: string[],
    languages: string[]
}