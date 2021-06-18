package com.process.workflow.app.examenes.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.process.workflow.app.commons.controllers.CommonController;
import com.process.workflow.app.examenes.services.ExamenService;
import com.process.workflow.commons.examenes.models.entity.Examen;
import com.process.workflow.commons.examenes.models.entity.Pregunta;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService>{
	
	
	@GetMapping("/examenes-respondidos-por-preguntas")
	public ResponseEntity<?>  obtenerExamenesIdsPorPreguntasIdRespondidas(@RequestParam List<Long> preguntaIds){
		
		return ResponseEntity.ok(service.findExamenesIdsConRespuestasByPreguntaIds(preguntaIds));
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Examen examen,BindingResult result, @PathVariable Long id){
		

		if (result.hasErrors()) {

			return this.validar(result);
		}
		
		Optional<Examen> o  = service.findById(id);
		
		if(!o.isPresent()) {
			
			
			return ResponseEntity.notFound().build();
		}
		
		Examen examenDb = o.get();
		
		examenDb.setNombre(examen.getNombre());
		
		List<Pregunta> eliminadas= examenDb.getPreguntas()
		.stream()
		.filter(pdb -> !examen.getPreguntas().contains(pdb)).collect(Collectors.toList());
		
		
		eliminadas.forEach(examenDb::removePregunta);
		
		
		
		
		examenDb.setPreguntas(examen.getPreguntas());
		examenDb.setAsignaturaHija(examen.getAsignaturaHija());
		examenDb.setAsignaturaPadre(examen.getAsignaturaPadre());
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
	}
	
	
	@GetMapping("/buscar/{term}")
	public ResponseEntity<?> buscarPorNombre(@PathVariable String term){
		
		return ResponseEntity.ok(service.findByNombre(term));
	}
	
	
	@GetMapping("/asignaturas")
	public ResponseEntity<?> listarAsignaturas(){
		
		return ResponseEntity.ok(service.findAllAsignaturas());
	}
	
	

}
