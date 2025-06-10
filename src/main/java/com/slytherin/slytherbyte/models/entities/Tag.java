package com.slytherin.slytherbyte.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private int tagId;

    private String name;

    public Tag() {
    }

    public Tag(int tagId, String name) {
        this.tagId = tagId;
        this.name = name;
    }

    public int getTagId() {
        return tagId;
    }

    public String getName() {
        return name;
    }
}
