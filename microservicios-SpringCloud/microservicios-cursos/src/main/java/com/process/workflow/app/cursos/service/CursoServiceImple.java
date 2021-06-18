package com.process.workflow.app.cursos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.process.workflow.app.commons.alumnos.models.entity.Alumno;
import com.process.workflow.app.commons.service.CommonServiceImple;
import com.process.workflow.app.cursos.clients.AlumnoFeignClient;
import com.process.workflow.app.cursos.clients.RespuestaFeignClient;
import com.process.workflow.app.cursos.models.entity.Curso;
import com.process.workflow.app.cursos.models.repository.CursoRepository;

@Service
public class CursoServiceImple extends CommonServiceImple<Curso, CursoRepository> implements CursoService {

	@Autowired
	private RespuestaFeignClient client;
	
	@Autowired
	private AlumnoFeignClient alumnoClient;

	@Override
	@Transactional(readOnly = true)
	public Curso findCursoByAlumnoId(Long id) {
		return repository.findCursoByAlumnoId(id);
	}

	@Override
	public Iterable<Long> obtenerExamenesIdsConRespuestaAlumno(Long alumnoId) {

		return client.obtenerExamenesIdsConRespuestaAlumno(alumnoId);
	}

	@Override
	public List<Alumno> obtenerAlumnosPorCurso(List<Long> ids) {
		return alumnoClient.obtenerAlumnosPorCurso(ids);
	}

	@Override
	@Transactional
	public void eliminarCursoAlumnoPorId(Long id) {
		
		repository.eliminarCursoAlumnoPorId(id);
	}

}
