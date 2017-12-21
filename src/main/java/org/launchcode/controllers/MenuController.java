package org.launchcode.controllers;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

/*Step 1: Create a MenuController
 set the RequestMapping value to = "menu" */

@Controller
@RequestMapping(value= "menu")
public class MenuController {

    // Step 2: Use Autowired annotation to import MenuDao and CheeseDao
    @Autowired
    private MenuDao menuDao;

    @Autowired
    private CheeseDao cheeseDao;

    /* Step 3: Create an index handler that uses MenuDao to
     retreive all menus and diplay them in a list using the
     .findall() method. Should handle GET requests */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("menus", menuDao.findAll());
        model.addAttribute("title", "Menus");
        return "menu/index";
    }

    /* Step 4: Create an add handler that handles GET requests
    Pass in the new Menu object */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add Menu");
        model.addAttribute(new Menu());
        return "menu/add";  //should render menu/add.html template

    }

    /* Step 5: Create an add handler that handles POST requests
    Include error handling using conditional statement. If the form
    has errors, return to the menu/add.html template.
    If no errors, save the menu object and redirect to a view template*/
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Menu menu, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Menu");
            return "menu/add";
        }

        menuDao.save(menu);
        return "redirect:view/" + menu.getId();
    }

    /* Step 6: Create a handler called viewMenu() that handles GET requests
    Use RequestMapping to set value to the view template. Add @PathVariable
    to the method argument.*/
    @RequestMapping(value = "view/{menuId}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int menuId) {
        Menu menu = menuDao.findOne(menuId);

        model.addAttribute("menu", menu);
        model.addAttribute("title", menu.getName());

        return "menu/view";
    }

    /* Step 7: Create a handler called addItem() that handles GET requests
    *Add @PathVariable to the method argument. Retrieve items from menuDao
    when requested and add items to cheeseDao to be listed*/
    @RequestMapping(value = "add-item/{menuId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int menuId) {
        Menu menu = menuDao.findOne(menuId);
        AddMenuItemForm form = new AddMenuItemForm(menu, cheeseDao.findAll());
        model.addAttribute("title", "Add item to menu: " + menu.getName());
        model.addAttribute("form", form);
        return "menu/add-item";
    }

    /* Step 8: Create another addItem() handler that handles POST requests
    Include error-handling conditional. If there is an error, return the
    add-item template. If no error, find Cheese and Menu by id. Use the
    .findOne() method with the getter methods as arguments from the form.
    If found, save theMenu items to the menuDao. Return view template*/
    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddMenuItemForm form, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "menu/add-item";
        }

        Cheese theCheese = cheeseDao.findOne(form.getCheeseId());
        Menu theMenu = menuDao.findOne(form.getMenuId());
        theMenu.addItem(theCheese);
        menuDao.save(theMenu);
        return "redirect:view/" + theMenu.getId();

    }

}
