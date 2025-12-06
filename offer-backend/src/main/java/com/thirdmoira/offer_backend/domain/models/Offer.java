package com.thirdmoira.offer_backend.domain.models;



public record  Offer (
        String name,
        String description,
        Long userId,
        Long id
//TODO: à ajouter
//        long version
){

}


