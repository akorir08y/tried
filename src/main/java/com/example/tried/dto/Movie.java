package com.example.tried.dto;



public class Movie {
    private String title;
    private String actor;
    private Double price;
    private Integer id;


    public Movie() {

    }

    public Movie(String title, String actor, Double price, Integer id) {
        this.title = title;
        this.actor = actor;
        this.price = price;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
