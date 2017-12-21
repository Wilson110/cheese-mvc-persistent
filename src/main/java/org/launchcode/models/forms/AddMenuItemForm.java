package org.launchcode.models.forms;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import javax.validation.constraints.NotNull;

// This class is not persistent
public class AddMenuItemForm {

    // Step 1: Create 2 fields to render the form for private Menu menu and Cheese cheeses
    private Menu menu;
    private Iterable<Cheese> cheeses;

    // Step 2: Create private menuId and cheeseId fields, both are integers
    @NotNull
    private int menuId;

    @NotNull
    private int cheeseId;

    // Step 3: create no argument constructor and AddMenuItemForm() method
    public AddMenuItemForm() { }

    public AddMenuItemForm(Menu menu, Iterable<Cheese> cheeses) {
        this.menu = menu;
        this.cheeses = cheeses;
    }

    // Step 4: Autogenerate getter and setter methods for all the above
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Iterable<Cheese> getCheeses() {
        return cheeses;
    }

    public void setCheeses(Iterable<Cheese> cheeses) {
        this.cheeses = cheeses;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }
}
