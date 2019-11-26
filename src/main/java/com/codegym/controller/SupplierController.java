package com.codegym.controller;

import com.codegym.model.Supplier;
import com.codegym.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/suppliers")
    public ModelAndView listSupplier() {
        Iterable<Supplier> suppliers = supplierService.findAll();
        ModelAndView modelAndView = new ModelAndView("/supplier/list");
        modelAndView.addObject("suppliers", suppliers);
        return modelAndView;
    }

    @GetMapping("/create-supplier")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/supplier/create", "supplier", new Supplier());
        return modelAndView;
    }

    @PostMapping("/create-supplier")
    public ModelAndView saveSupplier(@ModelAttribute("supplier") Supplier supplier) {
        supplierService.save(supplier);
        ModelAndView modelAndView = new ModelAndView("/supplier/create");
        modelAndView.addObject("supplier", new Supplier());
        modelAndView.addObject("message", "New supplier created successfully!");
        return modelAndView;
    }

    @GetMapping("/edit-supplier/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Supplier supplier = supplierService.findById(id);
            ModelAndView modelAndView = new ModelAndView("/supplier/edit");
            modelAndView.addObject("supplier", supplier);
            return modelAndView;
    }

    @PostMapping("/edit-supplier")
    public ModelAndView updateSupplier(@ModelAttribute("supplier") Supplier supplier) {
        supplierService.save(supplier);
        ModelAndView modelAndView = new ModelAndView("/supplier/edit");
        modelAndView.addObject("supplier", supplier);
        modelAndView.addObject("message", "supplier updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-supplier/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Supplier supplier = supplierService.findById(id);
            ModelAndView modelAndView = new ModelAndView("/supplier/delete");
            modelAndView.addObject("supplier", supplier);
            return modelAndView;
    }

    @PostMapping("/delete-supplier")
    public String deleteSupplier(@ModelAttribute("supplier") Supplier supplier) {
        supplierService.remove(supplier.getId());
        return "redirect:suppliers";
    }
}
