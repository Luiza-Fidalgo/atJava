package com.moster.AT.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Monster {
    private int id;
    private String name;
    private List elements;

    public Monster(int id, String name, List elements) {
        this.id = id;
        this.name = name;
        this.elements = elements;
    }

    public Monster() {
    }
}
