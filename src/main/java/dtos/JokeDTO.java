/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Joke;
import java.util.Date;

/**
 *
 * @author Claus
 */

public class JokeDTO {
    
    private Long id;
    private String type;
    private String joke;
    private int funnyness;
    private String description;
    
public JokeDTO(Joke j) {
    

        this.id = j.getId();
        this.type = j.getType();
        this.joke = j.getJoke();
        this.funnyness = j.getFunnyness();
        this.description = j.getDescription();
    }
 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public int getFunnyness() {
        return funnyness;
    }

    public void setFunnyness(int funnyness) {
        this.funnyness = funnyness;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return "Joke{" + "id=" + id + ", type=" + type + ", joke=" + joke + ", funnyness=" + funnyness + ", description=" + description + '}';
    }
  
}
