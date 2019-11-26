package com.codegym.service.impl;

import com.codegym.model.Supplier;
import com.codegym.repository.SupplierRepository;
import com.codegym.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;

public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;
    @Override
    public Iterable<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier findById(Long id) {
        return supplierRepository.findOne(id);
    }

    @Override
    public void save(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    @Override
    public void remove(Long id) {
        supplierRepository.delete(id);
    }
}
