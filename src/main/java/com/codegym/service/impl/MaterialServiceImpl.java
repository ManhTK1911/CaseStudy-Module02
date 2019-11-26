package com.codegym.service.impl;

import com.codegym.model.Material;
import com.codegym.model.Supplier;
import com.codegym.repository.MaterialRepository;
import com.codegym.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public Page<Material> findAll(Pageable pageable) {
        return materialRepository.findAll(pageable);
    }

    @Override
    public Material findById(Long id) {
        return materialRepository.findOne(id);
    }

    @Override
    public void save(Material material) {
        materialRepository.save(material);
    }

    @Override
    public void remove(Long id) {
        materialRepository.delete(id);
    }

    @Override
    public Iterable<Material> findAllBySupplier(Supplier supplier) {
        return materialRepository.findAllBySupplier(supplier);
    }

    @Override
    public Page<Material> findAllBySupplier(Supplier supplier, Pageable pageable) {
        return materialRepository.findAllBySupplier(supplier, pageable);
    }

    @Override
    public Page<Material> findAllByOrderByPriceAsc(Pageable pageable) {
        return materialRepository.findAllByOrderByPriceAsc(pageable);
    }

    @Override
    public Page<Material> findAllByOrderByPriceDesc(Pageable pageable) {
        return materialRepository.findAllByOrderByPriceDesc(pageable);
    }
}
