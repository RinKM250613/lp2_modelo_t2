package com.modelot2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modelot2.models.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Integer>{

}
