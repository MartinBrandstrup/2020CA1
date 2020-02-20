package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import static javax.persistence.TemporalType.DATE;

/**
 * 
 * @author Brandstrup
 */
@Entity
@NamedQuery(name = "Car.deleteAllRows", query = "DELETE from Car")
public class Car implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String make;
    private String model;
    @Temporal(TemporalType.DATE)
    private Date date;
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
    
    private Date convertToDateViaInstant(LocalDate dateToConvert)
    {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant().plusSeconds(86400));
    }
    
    public Car()
    {
    }

    /**
     * Creates a Car entity with a LocalDate. Used when persisting to DB.
     * 
     * @param date LocalDate, use LocalDate.now() for the current time and use
     * LocalDate.of(2001, Month.MARCH, 13) for instance for specific dates.
     */
    public Car(String make, String model, LocalDate date, double price, String owner, String color, FuelType fuel)
    {
        this.make = make;
        this.model = model;
        this.date = convertToDateViaInstant(date);
        this.price = price;
        this.owner = owner;
        this.color = color;
        this.fuel = fuel;
    }

    /**
     * Creates a Car entity with a Date. Mostly used when retrieving from the DB.
     * 
     * @param date A regular java.util.Date, which is the SQL's preferred format.
     */
    public Car(String make, String model, Date date, double price, String owner, String color, FuelType fuel)
    {
        this.make = make;
        this.model = model;
        this.date = date;
        this.price = price;
        this.owner = owner;
        this.color = color;
        this.fuel = fuel;
    }

    

}
