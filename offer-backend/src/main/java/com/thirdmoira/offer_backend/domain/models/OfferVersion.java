package com.thirdmoira.offer_backend.domain.models;

import lombok.Data;

@Data
public class OfferVersion {
    Long id;
    Long offerId;
    String title;
    Long versionNumber;


}
