package com.thirdmoira.offer_backend.domain.models;

import lombok.Data;

import java.util.List;

@Data
public class OfferEditView {
    Offer offer;
    List<Service> services;
}
