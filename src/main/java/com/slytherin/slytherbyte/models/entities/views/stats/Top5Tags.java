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
    private int top5TagsId;
    @Column(name="name")
    private String tagName;

    public Top5Tags(){}

    public Top5Tags(int top5TagsId, String tagName) {
        this.top5TagsId = top5TagsId;
        this.tagName = tagName;
    }

    public int getTop5TagsId() {
        return top5TagsId;
    }

    public void setTop5TagsId(int top5TagsId) {
        this.top5TagsId = top5TagsId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
