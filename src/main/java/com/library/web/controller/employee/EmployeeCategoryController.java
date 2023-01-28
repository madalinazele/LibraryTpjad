package com.library.web.controller.employee;

import com.library.facade.CategoryFacade;
import com.library.facade.dto.CategoryDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
@Controller
@RequestMapping("/admin/categories")
public class EmployeeCategoryController {
    private final CategoryFacade categoryFacade;

    @GetMapping("/update/{id}")
    public String getEditCategoryView(@PathVariable String id, Model model) {
        final Optional<CategoryDto> categoryDto = categoryFacade.getById(Integer.parseInt(id));
        if (categoryDto.isEmpty()) {
            return "redirect:/admin/categories";
        }
        model.addAttribute("category", categoryDto.get());
        return "employee/updateCategory";
    }

    @PostMapping("/update")
    public String updateCategory(@ModelAttribute("category") CategoryDto categoryDto) {
        categoryFacade.update(categoryDto);
        return "redirect:/admin/categories";
    }

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView model = new ModelAndView("employee/categoriesEmployee");
        List<CategoryDto> categories = categoryFacade.getAll();
        model.addObject("categories", categories);
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showForm(Model model) {
        model.addAttribute("category", new CategoryDto());
        return "employee/addCategory";
    }

    @PostMapping("/add")
    public String submit(@ModelAttribute("category") CategoryDto categoryDto) {
        categoryFacade.save(categoryDto);
        return "redirect:/admin/categories";
    }
    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable(name = "id") Integer id) {
        categoryFacade.delete(id);
        return "redirect:/admin/categories";
    }
}
