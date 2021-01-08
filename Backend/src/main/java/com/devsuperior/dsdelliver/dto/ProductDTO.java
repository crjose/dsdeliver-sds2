package com.devsuperior.dsdelliver.dto;

import java.io.Serializable;

import com.devsuperior.dsdelliver.entities.Product;

public class ProductDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Double price;
	private String description;
	private String ImageUri;
	
	//construto sem argumentos , padrão java
	ProductDTO(){}

	public ProductDTO(Long id, String name, Double price, String description, String imageUri) {
		
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		ImageUri = imageUri;
	}
	
	public ProductDTO(Product entity) {
			
			id = entity.getId();
			name = entity.getName();
			price = entity.getPrice();
			description = entity.getDescription();
			ImageUri = entity.getImageUri();
		}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUri() {
		return ImageUri;
	}

	public void setImageUri(String imageUri) {
		ImageUri = imageUri;
	}

	
}
