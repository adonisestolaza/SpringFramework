package com.process.workflow.app.examenes.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.process.workflow.app.commons.service.CommonServiceImple;
import com.process.workflow.app.examenes.models.repository.AsignaturaRepository;
import com.process.workflow.app.examenes.models.repository.ExamenRepository;
import com.process.workflow.commons.examenes.models.entity.Asignatura;
import com.process.workflow.commons.examenes.models.entity.Examen;


@Service
public class ExamenServiceImple extends CommonServiceImple<Examen, ExamenRepository> implements ExamenService {

	@Autowired
	private AsignaturaRepository asignaturaRepository;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Examen> findByNombre(String term) {
		return repository.findByNombre(term);
	}

	@Override
	public List<Asignatura> findAllAsignaturas() {
		// TODO Auto-generated method stub
		return (List<Asignatura>) asignaturaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Long> findExamenesIdsConRespuestasByPreguntaIds(Iterable<Long> preguntaIds) {
		
		
		return repository.findExamenesIdsConRespuestasByPreguntaIds(preguntaIds);
	}

	
	
	

}
