package com.process.workflow.app.models.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.process.workflow.app.models.dao.IUsuarioDao;
import com.process.workflow.app.models.entity.Rol;
import com.process.workflow.app.models.entity.Usuario;


@Service("jpaUserDetailsService")
public class JpaUserDetailsService  implements UserDetailsService {

	@Autowired
	private IUsuarioDao usuarioDao;
	
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		
		Usuario usuario = usuarioDao.findByUsername(username);
		
		if(usuario == null) {
			
			logger.error("Error no existe el usuario:: '" + username +"'");
			
			throw new UsernameNotFoundException("Error no existe el usuario:: '" + username +"'");
			
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(Rol rol: usuario.getRoles()) {
			
			authorities.add(new SimpleGrantedAuthority(rol.getAuthority()));
			
		}
		
		if(authorities.isEmpty()) {
			
			logger.error("Error login el  usuario:: '" + username +" no tiene roles asignados");
			
			throw new UsernameNotFoundException("Error login el  usuario:: '" + username +" no tiene roles asignados");
			
		}
		
		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, authorities);
	}

	
	
}
