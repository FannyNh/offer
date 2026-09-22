export interface NewOffer {
    name: string;
    title: string;
    description: string;
    userId: number | undefined ;
}

export interface OfferVersion {
    name: string | null;
    title: string | null;
    description: string | null;
    versionNumber: number;
}

export interface ServiceCategory {
    id: number;
    name: string;
}

export interface ServiceEditView {
    serviceId: number;
    title: string;
    description: string | null;
    categoryId: number | null;
    categoryName: string | null;
}

export interface OfferEditView {
    id: number;
    userId: number;
    version: OfferVersion;
    services?: ServiceEditView[];
}
