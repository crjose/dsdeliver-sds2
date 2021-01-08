package com.devsuperior.dsdelliver.services;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdelliver.dto.OrderDTO;
import com.devsuperior.dsdelliver.entities.Order;
import com.devsuperior.dsdelliver.repositories.OrderRepository;



/*Registrando o componente, pode ser de duas formas @Component ou @Service , 
a opção pelo @Component @Service foi apenas por questões semânticas
@Component*/

@Service
public class OrderService {
	 
	@Autowired
	private OrderRepository repository;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		//(alteração atual)
		List<Order> list = repository.findOrdersWithProducts();
		///List<Order> list = repository.findAll();
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}

}




