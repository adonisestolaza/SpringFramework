package com.process.workflow.webflux.app;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.process.workflow.webflux.app.models.dao.ProductoDao;
import com.process.workflow.webflux.app.models.documents.Producto;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootWebfluxApplication implements CommandLineRunner {

	@Autowired
	private ProductoDao productoDao;
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(SpringBootWebfluxApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		mongoTemplate.dropCollection("productos").subscribe();
		
		Flux.just(
				new Producto("TV panasonic", 100.000),
				new Producto("Sony Camara", 200.000),
				new Producto("comoda", 150.000),
				new Producto("microhondas", 100.000),
				new Producto("celular samsung", 300.000),
				new Producto("Reloj Casio", 250.000),
				new Producto("Notebook Dell", 500.000),
				new Producto("Tv Sony", 600.000)
				
				).flatMap(producto -> {
					producto.setCreateAt(new Date());
					return productoDao.save(producto);
					})
		.subscribe(producto -> log.info("Insert "+ producto.getId()+" "+ producto.getNombre()));
		
	}

}
