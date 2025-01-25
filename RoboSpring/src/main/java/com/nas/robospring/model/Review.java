package com.nas.robospring.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("reviews")
public class Review {
    @Id
    private Integer id;
    private Integer restaurantDetailsId;
    private String userName;
    private Integer rating;
    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRestaurantDetailsId() {
        return restaurantDetailsId;
    }

    public void setRestaurantDetailsId(Integer restaurantDetailsId) {
        this.restaurantDetailsId = restaurantDetailsId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
// Getters and setters...
}
