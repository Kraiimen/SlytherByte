package com.slytherin.slytherbyte.dtos;

import com.slytherin.slytherbyte.models.entities.Franchise;

public record FranchiseDto(int franchiseId, String name) {

    public static FranchiseDto toDto(Franchise franchise){
        return new FranchiseDto(franchise.getFranchiseId(), franchise.getName());
    }

    public Franchise toEntity(){
        return new Franchise(this.franchiseId, this.name);
    }
}
