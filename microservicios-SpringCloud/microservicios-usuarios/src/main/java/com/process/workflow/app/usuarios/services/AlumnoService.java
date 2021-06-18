package com.process.workflow.app.usuarios.services;

import java.util.List;

import com.process.workflow.app.commons.alumnos.models.entity.Alumno;
import com.process.workflow.app.commons.service.CommonService;



public interface AlumnoService extends CommonService<Alumno>{
	
	public List<Alumno> findByNombreOrApellido(String term);
	
	public Iterable<Alumno> findAllById(Iterable<Long> ids);
	
	public void eliminarCursoAlumnoPorId(Long id);
}
