package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Joke.deleteAllRows", query = "DELETE from Joke")
public class Joke implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String joke;
    private int funnyness;
    private String description;

    public Joke(){
        
    }

    public Joke(String type, String joke, int funnyness, String description) {
        
        this.type = type;
        this.joke = joke;
        this.funnyness = funnyness;
        this.description = description;
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
