package com.devsuperior.dsdelliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdelliver.dto.OrderDTO;
import com.devsuperior.dsdelliver.dto.ProductDTO;
import com.devsuperior.dsdelliver.entities.Order;
import com.devsuperior.dsdelliver.entities.OrderStatus;
import com.devsuperior.dsdelliver.entities.Product;
import com.devsuperior.dsdelliver.repositories.OrderRepository;
import com.devsuperior.dsdelliver.repositories.ProductRepository;

/*Registrando o componente, pode ser de duas formas @Component ou @Service , 
a opção pelo @Component @Service foi apenas por questões semânticas
@Component*/

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	// alteração atual
	@Autowired
	private ProductRepository productRepository;
	// fim dessa alteração

	// buscando todos os pedidos do banco
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {

		List<Order> list = repository.findOrdersWithProducts();
		/// List<Order> list = repository.findAll();
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}

	// (alteração atual)
	// INSERINDO os pedidos do banco
	
	@Transactional
	public OrderDTO insert(OrderDTO dto){
		//instanciando um objeto do tipo Order e atribuindo o instante
		//atual Instant.now() e o status do novo pedido 
		//que será sempre PENDING(PENDENTE) OrderStatus.PENDING
		
		Order order = new Order(null, dto.getAddress() , dto.getLatitude() , dto.getLongitude(), 
				Instant.now(), OrderStatus.PENDING);
				
				//Percorrendo o pedido  e buscando os produtos(ProductDTO)
				//que vieram do dto
				for(ProductDTO p : dto.getProducts()) {				
//Associando o pedido(order) com os produtos que virão do dto adicionando-os 
//pegando a lista de produtos(que nesse momento está vazia) e adicionando através do método add
//como não se pode adicionar dto, adiciona-se a entidade product 
//ao order(pedido), para isso usamos o método 
						
				Product product =  productRepository.getOne(p.getId());
				order.getProducts().add(product);
				}
				//salvando no banco o objeto order, 
				//o método save faz referência ao objeto salvo
				order = repository.save(order);
				//pegando a referência ao objeto order e passando como argumento de retorno
				return new OrderDTO(order);
				//fim da alteração atual
	}
}
