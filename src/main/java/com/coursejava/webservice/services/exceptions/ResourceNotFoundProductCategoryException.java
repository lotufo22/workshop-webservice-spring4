package com.coursejava.webservice.services.exceptions;

public class ResourceNotFoundProductCategoryException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundProductCategoryException(Long productId, Long categoryId) {
		super("Product id or category id not found: "
				+ "Product id: " + productId
				+ ", Category id: " + categoryId);
	}
}
