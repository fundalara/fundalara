package seguridad;

import java.util.ArrayList;

import java.util.Collection;

import modelo.Grupo;
import modelo.GrupoUsuario;
import modelo.Rol;
import modelo.RolGrupo;
import modelo.Usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("assembler")
public class Assembler {
    
	
	@Transactional(readOnly=true)
	User buildUserFromUserEntity(Usuario usuario){
		String username = usuario.getNick();
		String password = usuario.getPassword();
	    boolean enabled = usuario.getEstatus()=='A' ? true : false;
	    boolean accountNonExpired = enabled;
	    boolean credentialsNonExpired = enabled;
	    boolean accountNonLocked = enabled;
	    Collection<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
	    for (GrupoUsuario grupoUsuario : usuario.getGrupoUsuarios()) {	  
	    	for (RolGrupo rolGrupo : grupoUsuario.getGrupo().getRolGrupos()) {
	    		System.out.println(rolGrupo.getRol().getNombre());
	    		roles.add(new GrantedAuthorityImpl(rolGrupo.getRol().getNombre()));
			}
		}
	    
	    User user = new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, roles);
	    return user;
	}
}
