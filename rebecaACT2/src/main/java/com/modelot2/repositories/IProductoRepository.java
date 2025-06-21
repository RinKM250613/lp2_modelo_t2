package com.modelot2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modelot2.models.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Integer>{

	List<Producto> findAllByOrderByNombre();
}
