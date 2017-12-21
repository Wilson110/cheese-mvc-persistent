package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

// Step 1: Create a CategoryController, add RequestMapping("category") annotations

@Controller
@RequestMapping("category")
public class CategoryController {

    // Step 2: Add the Autowired annotation required for the CrudRepository
    @Autowired
    private CategoryDao categoryDao;

    /* Step 3: Create an index handler that works with index template that will retrieve
     a list of all categories
    Remember: the root path is /cheese */
    @RequestMapping(value = "")
    public String index(Model model) {

        /*Step 4: Use .findAll() method to return a list of categories
         add the 'title' attribute */
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "Categories");

        return "category/index";
    }

    /*Step 5: Create an add handler with input Model model that will create
     a new Category object. This handler will accept GET requests*/
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addCategory(Model model) {

        model.addAttribute("title", "Add Category");
        model.addAttribute(new Category());

        return "category/add"; //should return add.html
    }

    /*Step 6: Create an add handler that will accept POST request
     Return an an error if no category chosen on the add template
     Use .save() method to save the new Category */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model,
                      @ModelAttribute @Valid Category category, Errors errors) {

        if (errors.hasErrors()) {
            return "category/add";
        }
        categoryDao.save(category);
        return "redirect:";
    }
}
