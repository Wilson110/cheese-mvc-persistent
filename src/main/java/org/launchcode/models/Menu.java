package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Menu {

    @NotNull
    @Size(min=3, max=15)

    private String name;

    @Id
    @GeneratedValue

    private Integer id;

    // Step 1: Create a ManyToMany annotation for a private List Cheese
    @ManyToMany
    private List<Cheese> cheeses = new ArrayList<>();

    // Step 2: Create a method that will add items to the Menu
    public void addItem(Cheese item) {
        cheeses.add(item);
    }

    public Menu(){}

    public Menu(String name){
        this.name = name;
    }

    // Step 3: Create getter and setter methods for name, Integer, and Cheese list
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }

    public void setCheeses(List<Cheese> cheeses) {
        this.cheeses = cheeses;
    }
}
