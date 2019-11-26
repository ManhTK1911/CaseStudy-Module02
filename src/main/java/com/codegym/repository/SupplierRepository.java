package com.codegym.repository;

import com.codegym.model.Supplier;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SupplierRepository extends PagingAndSortingRepository<Supplier, Long> {
}
