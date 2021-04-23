package com.process.workflow.webflux.app.models.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.process.workflow.webflux.app.models.documents.Producto;

public interface ProductoDao extends ReactiveMongoRepository<Producto, Long> {

	
	
}
