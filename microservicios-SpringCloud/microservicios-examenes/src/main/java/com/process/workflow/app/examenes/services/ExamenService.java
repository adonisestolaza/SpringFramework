package com.process.workflow.app.examenes.services;

import java.util.List;

import com.process.workflow.app.commons.service.CommonService;
import com.process.workflow.commons.examenes.models.entity.Asignatura;
import com.process.workflow.commons.examenes.models.entity.Examen;

public interface ExamenService extends CommonService<Examen> {

	public List<Examen> findByNombre(String term);
	
	
	public List<Asignatura> findAllAsignaturas();
	

	public Iterable<Long> findExamenesIdsConRespuestasByPreguntaIds(Iterable<Long> preguntaIds);
}
