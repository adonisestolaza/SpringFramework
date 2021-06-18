package com.process.workflow.app.respuestas.service;

//import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.process.workflow.app.respuestas.clients.ExamenFeignClient;
import com.process.workflow.app.respuestas.models.entity.Respuesta;
import com.process.workflow.app.respuestas.models.repository.RespuestaRepository;
//import com.process.workflow.commons.examenes.models.entity.Examen;
//import com.process.workflow.commons.examenes.models.entity.Pregunta;

@Service
public class RespuestaServiceImple  implements RespuestaService{
	
	
	@Autowired
	private RespuestaRepository respuestaRepository;
	
	//@Autowired
	//private ExamenFeignClient examenClient;
	
	
	
	@Override
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
		
		
		return respuestaRepository.saveAll(respuestas);
	}


	@Override
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) {
		
		/*
		Examen examen = examenClient.obtenerExamenPorId(examenId);
		
		List<Pregunta> preguntas = examen.getPreguntas();
		List<Long> preguntaids = preguntas.stream().map( p -> p.getId()).collect(Collectors.toList());
		List<Respuesta> respuestas = (List<Respuesta>) respuestaRepository.findRespuestaByAlumnoByPreguntaIds(alumnoId, preguntaids);
		
		respuestas = respuestas.stream().map( r ->{
			
			preguntas.forEach(p ->{
				if(p.getId() == r.getPreguntaId()) {
					
					r.setPregunta(p);
				}
				
			});
			
			return r;
		}).collect(Collectors.toList());*/
		
		List<Respuesta> respuestas = (List<Respuesta>) respuestaRepository.findRespuestaByAlumnoByExamen(alumnoId, examenId);
		
		return respuestas;
	}
	
	


	@Override
	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId) {
		/*
		List<Respuesta> respuestasAlumno = (List<Respuesta>) respuestaRepository.findByAlumnoId(alumnoId);
		List<Long> examenIds = Collections.emptyList();
		
		if(respuestasAlumno.size() > 0) {
		  List<Long> preguntaIds = respuestasAlumno.stream().map(r -> r.getPreguntaId()).collect(Collectors.toList());
		  examenIds = examenClient.obtenerExamenesIdsPorPreguntasIdRespondidas(preguntaIds);
		}*/
		
		List<Respuesta> respuestasAlumno = (List<Respuesta>) respuestaRepository.findExamenesIdsConRespuestasByAlumno(alumnoId);
		
		
		return respuestasAlumno.stream().map(r -> r.getPregunta().getExamen().getId())
				.distinct().collect(Collectors.toList());
	}


	@Override
	public Iterable<Respuesta> findByAlumnoId(Long alumnoId) {
		return respuestaRepository.findByAlumnoId(alumnoId);
	}

	
	
}
