package com.slytherin.slytherbyte.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private int storeId;

    private String name;

    @Column(name = "icon_url")
    private String iconUrl;

    @Column(name = "site_url")
    private String siteUrl;

    public Store() {
    }

    public Store(int storeId, String name, String iconUrl, String siteUrl) {
        this.storeId = storeId;
        this.name = name;
        this.iconUrl = iconUrl;
        this.siteUrl = siteUrl;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getName() {
        return name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getSiteUrl() {
        return siteUrl;
    }
}
