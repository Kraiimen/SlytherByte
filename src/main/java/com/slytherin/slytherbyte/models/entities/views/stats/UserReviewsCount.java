package com.slytherin.slytherbyte.models.entities.views.stats;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name="user_review_count")
public class UserReviewsCount {
    @Id
    @Column(name="id")
    private int reviewsCountId;

    @Column(name="review_count")
    private int reviewsCount;

    public UserReviewsCount(){}

    public UserReviewsCount(int reviewsCountId, int reviewsCount){
        this.reviewsCountId=reviewsCountId;
        this.reviewsCount=reviewsCount;
    }

    public int getReviewsCountId() {
        return reviewsCountId;
    }

    public int getReviewsCount(){
        return reviewsCount;
    }
}
