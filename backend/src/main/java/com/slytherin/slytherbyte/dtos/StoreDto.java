package com.slytherin.slytherbyte.dtos;

import com.slytherin.slytherbyte.models.entities.Store;

public record StoreDto(int storeId, String name, String iconUrl, String siteUrl) {

    public static StoreDto toDto(Store store){
        return new StoreDto(store.getStoreId(), store.getName(), store.getIconUrl(), store.getSiteUrl());
    }

    public Store toEntity(){
        return new Store(this.storeId, this.name, this.iconUrl, this.siteUrl);
    }
}
