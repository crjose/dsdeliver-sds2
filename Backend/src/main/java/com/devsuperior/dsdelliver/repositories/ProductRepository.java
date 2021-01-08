package com.devsuperior.dsdelliver.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsdelliver.entities.Product;
//criando uma interface de acesso aos objetos do tipo abaixo e fazer as operações sql
public interface  ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findAllByOrderByNameAsc();

}
