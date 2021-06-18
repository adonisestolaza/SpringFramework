package com.process.workflow.app.cursos.service;


import java.util.List;


import com.process.workflow.app.commons.alumnos.models.entity.Alumno;
import com.process.workflow.app.commons.service.CommonService;
import com.process.workflow.app.cursos.models.entity.Curso;

public interface CursoService extends CommonService<Curso> {

	
	public Curso findCursoByAlumnoId(Long id);
	
	public Iterable<Long> obtenerExamenesIdsConRespuestaAlumno(Long alumnoId);
	
	public List<Alumno> obtenerAlumnosPorCurso(List<Long> ids);
	
	
	public void eliminarCursoAlumnoPorId(Long id);
	
	
}
