package com.devsuperior.dsdelliver.services;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdelliver.dto.ProductDTO;
import com.devsuperior.dsdelliver.entities.Product;
import com.devsuperior.dsdelliver.repositories.ProductRepository;



/*Registrando o componente, pode ser de duas formas @Component ou @Service , 
a opção pelo @Component @Service foi apenas por questões semânticas
@Component*/

@Service

public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public List<ProductDTO> findAll(){
		
		List<Product> list = repository.findAllByOrderByNameAsc();
		return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
	}

}
