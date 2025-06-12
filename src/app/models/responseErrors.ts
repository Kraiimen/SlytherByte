export interface ResponseErrors {
    errors?: {
        email?: {
                taken?: string;
        };
        username?: {
            length?: string;
            taken?: string;
        };
        password?: {
            length?: string;
            uppercase?: string;
            digit?: string;
            specialChar?: string;
        };
        repeteadPassword?: {
            mismatch?: string;
        };
    };
}