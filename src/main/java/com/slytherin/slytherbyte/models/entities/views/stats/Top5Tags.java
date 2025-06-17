package com.slytherin.slytherbyte.models.entities.views.stats;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name="top_5_tags")
public class Top5Tags {
    @Id
    @Column(name="tag_id")
    private int tagId;
    @Column(name="name")
    private String tagName;

    public Top5Tags(){}

    public Top5Tags(int tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

}
