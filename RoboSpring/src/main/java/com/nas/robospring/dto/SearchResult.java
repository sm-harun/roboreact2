package com.nas.robospring.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResult {
    private String type; // "Product", "Course", "Post"
    private String title;
    private Long id;

    public SearchResult(String type, String title, Long id) {
        this.type = type;
        this.title = title;
        this.id = id;
    }

}
