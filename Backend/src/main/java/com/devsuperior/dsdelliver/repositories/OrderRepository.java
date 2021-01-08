package com.devsuperior.dsdelliver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsdelliver.entities.Order;

//criando uma interface de acesso aos objetos do tipo abaixo e fazer as operações sql
public interface  OrderRepository extends JpaRepository<Order, Long>{
	//(alteração atual)
	//as linhas abaixo são para obter 
	//os pedidos em ordem de chegada cujo status for pendente
	 @Query("SELECT DISTINCT obj FROM Order obj JOIN FETCH obj.products "
	            + " WHERE obj.status = 0 ORDER BY obj.moment ASC")
	    List<Order> findOrdersWithProducts();


}
