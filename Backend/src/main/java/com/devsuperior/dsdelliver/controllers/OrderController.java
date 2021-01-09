package com.devsuperior.dsdelliver.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dsdelliver.dto.OrderDTO;
import com.devsuperior.dsdelliver.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
	
	@Autowired OrderService service;
	
	//criando um endpoint para listar
	@GetMapping
	public ResponseEntity<List<OrderDTO>> findAll(){
		List<OrderDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	//alteração atual

	//criando um endpoint para salvar
	
	//a anotation @RequestBody servirá para o Spring transformar um Json em um objeto dto
	@PostMapping
	public ResponseEntity<OrderDTO> insert(@RequestBody OrderDTO dto){
		//chamando o insert que acabamos de criar no service
		dto = service.insert(dto);
		//opção que funciona retornando o código 200
		//return ResponseEntity.ok().body(dto);
		
		//opção adotada retornando o código 201 que significa: Recurso Criado,
		//que passa o endereço da resposta usando um objeto do tipo URI de nome uri
		//chamada que cria a URI corresponde ao recurso criado
		//ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				//.buildAndExpand(dto.getId()).toUri();
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		
		//criando a resposta 201
		return ResponseEntity.created(uri).body(dto);
		//fim da alteração atual
	}
	
}
