export interface ResponseErrors {
    errors?: {
        email?: {
                taken?: string;
                notExists?: string;
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
        login?: {
            invalidCredentials?: string;
        }
    };
}