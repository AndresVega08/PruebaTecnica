package com.pruebatecnica.demo.repository;

import com.pruebatecnica.demo.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}