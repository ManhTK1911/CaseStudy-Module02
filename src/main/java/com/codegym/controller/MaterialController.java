package com.codegym.controller;

import com.codegym.model.Material;
import com.codegym.model.MaterialForm;
import com.codegym.model.Supplier;
import com.codegym.service.MaterialService;
import com.codegym.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
@PropertySource("classpath:upload_file.properties")
public class MaterialController {

    @GetMapping("")
    public String getIndex() {
        return "/material/index";
    }

    @Autowired
    Environment env;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private SupplierService supplierService;

    @ModelAttribute("suppliers")
    public Iterable<Supplier> suppliers() {
        return supplierService.findAll();
    }

    @GetMapping("/materials")
    public ModelAndView listMaterial(@PageableDefault(size = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/material/list");
        Page<Material> materials = materialService.findAll(pageable);
        modelAndView.addObject("materials", materials);
        return modelAndView;
    }

    @GetMapping("/create-material")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/material/create");
        modelAndView.addObject("materialForm", new MaterialForm());
        return modelAndView;
    }

    @PostMapping("/save-material")
    public ModelAndView saveMaterial(@ModelAttribute("materialForm") MaterialForm materialForm) {
        MultipartFile multipartFile = materialForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();

        try{
            FileCopyUtils.copy(materialForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Material material = new Material(materialForm.getCode(), materialForm.getName(),  materialForm.getImportDate(), materialForm.getDescription(),materialForm.getPrice(),materialForm.getQuantity(), fileName);
        material.setSupplier(materialForm.getSupplier());
        ModelAndView modelAndView = new ModelAndView("/material/create");
        materialService.save(material);
        modelAndView.addObject("materialForm", new MaterialForm());
        modelAndView.addObject("message", "New material created successfully!");
        return modelAndView;
    }

    @GetMapping("/edit-material/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Material material = materialService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/material/edit");
        modelAndView.addObject("material", material);
        return modelAndView;
    }

    @PostMapping("/edit-material")
    public ModelAndView updateMaterial(@ModelAttribute("material") Material material) {
        materialService.save(material);
        ModelAndView modelAndView = new ModelAndView("/material/edit");
        modelAndView.addObject("material", material);
        modelAndView.addObject("message", "Material update successful!");
        return modelAndView;
    }

    @GetMapping("/delete-material/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Material material = materialService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/material/delete");
        modelAndView.addObject("material", material);
        return modelAndView;
    }

    @PostMapping("/delete-material")
    public String deleteMaterial(@ModelAttribute("material") Material material) {
        materialService.remove(material.getId());
        return "redirect:materials";
    }

    @GetMapping("/detail-material/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("material", materialService.findById(id));
        return "/material/detail";
    }

    @GetMapping("/searchBySupplier")
    public ModelAndView getMaterialBySupplier(@RequestParam("searchSupplier") Long searchSupplier, @PageableDefault(size = 5) Pageable pageable) {
        Page<Material> materials;
        if (searchSupplier == -1) {
            materials = materialService.findAll(pageable);
        } else {
            Supplier searchSupplierID = supplierService.findById(searchSupplier);
            materials = materialService.findAllBySupplier(searchSupplierID, pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/material/list");
        modelAndView.addObject("materials", materials);
        modelAndView.addObject("searchSupplier", searchSupplier);
        modelAndView.addObject("suppliers", suppliers());
        return modelAndView;
    }

    @GetMapping("/sortByPrice")
    public ModelAndView getListSortedByPrice(@RequestParam("sortPrice") String sort, @PageableDefault(size = 5) Pageable pageable) {
        Page<Material> materials;
        if (sort.equals("no")) {
            materials = materialService.findAll(pageable);
        } else if (sort.equals("asc")) {
            materials = materialService.findAllByOrderByPriceAsc(pageable);
        } else {
            materials = materialService.findAllByOrderByPriceDesc(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/material/list");
        modelAndView.addObject("materials", materials);
        modelAndView.addObject("sortPrice", sort);
        return modelAndView;
    }

}
