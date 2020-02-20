/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Car;
import java.util.Date;

/**
 *
 * @author Brandstrup
 */
public class CarDTO
{

    private int id;
    private String make;
    private String model;
    private String year;
    private double price;
    private String color;
    private String fuel;

    public CarDTO(Car c)
    {
        this.id = c.getId();
        this.make = c.getMake();
        this.model = c.getModel();
        this.year = String.valueOf(c.getLocalDate().getYear());
        this.price = c.getPrice();
        this.color = c.getColor();
        this.fuel = c.getFuel().toString();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getMake()
    {
        return make;
    }

    public void setMake(String make)
    {
        this.make = make;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public String getFuel()
    {
        return fuel;
    }

    public void setFuel(String fuel)
    {
        this.fuel = fuel;
    }

    @Override
    public String toString()
    {
        return "CarDTO{" + "id=" + id + ", make=" + make + ", model=" + model
                + ", year=" + year + ", price=" + price + ", color=" + color
                + ", fuel=" + fuel + '}';
    }

}
