package seguridad;

import modelo.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zkoss.zkplus.spring.SpringUtil;

import dao.general.DaoUsuario;

import servicio.implementacion.ServicioUsuario;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    
	
	
	//@Autowired private Usuario usuario;
	
	private Assembler assembler;
	private DaoUsuario daoUsuario;
	
	public UserDetails loadUserByUsername(String username)
			
	    throws UsernameNotFoundException, DataAccessException {
		UserDetails userDetails = null;
		Usuario usuario = daoUsuario.buscarPorNombre(username);
		if (usuario == null)
			throw new UsernameNotFoundException("Usuario no encontrado");
		assembler = new Assembler();
		return assembler.buildUserFromUserEntity(usuario);
	}
	
	public DaoUsuario getDaoUsuario() {
		return daoUsuario;
	}
	public void setDaoUsuario(DaoUsuario daoUsuario) {
		this.daoUsuario = daoUsuario;
	}

}
