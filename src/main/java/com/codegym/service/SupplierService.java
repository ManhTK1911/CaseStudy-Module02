package com.codegym.service;

import com.codegym.model.Supplier;

public interface SupplierService {
    Iterable<Supplier> findAll();
    Supplier findById(Long id);
    void save(Supplier supplier);
    void remove(Long id);
}
