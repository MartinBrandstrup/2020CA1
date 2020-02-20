package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
//import static javax.persistence.TemporalType.DATE;

/**
 * 
 * @author Brandstrup
 */
@Entity
@NamedQuery(name = "Car.deleteAllRows", query = "DELETE from Joke")
public class Car implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String make;
    private String model;
    private Date year;
    private double price;
    private String owner;
    private String color;
    private FuelType fuel;
    
    public enum FuelType
    {
        Diesel,
        Electric,
        Hybrid
    }

    public Car()
    {
    }

    public Car(String make, String model, double price, String owner, String color, FuelType fuel)
    {
        this.make = make;
        this.model = model;
        this.price = price;
        this.owner = owner;
        this.color = color;
        this.fuel = fuel;
    }

    

}
