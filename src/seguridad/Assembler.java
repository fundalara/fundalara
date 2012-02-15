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

	@Transactional(readOnly = true)
	User buildUserFromUserEntity(Usuario usuario) {
		String username = usuario.getNick();
		String password = usuario.getPassword();
		boolean enabled = usuario.getEstatus() == 'A' ? true : false;
		boolean accountNonExpired = enabled;
		boolean credentialsNonExpired = enabled;
		boolean accountNonLocked = enabled;
		Collection<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		for (GrupoUsuario grupoUsuario : usuario.getGrupoUsuarios()) {
			System.out.println(usuario.getGrupoUsuarios().size()+"----------------------");
			for (RolGrupo rolGrupo : grupoUsuario.getGrupo().getRolGrupos()) {
				Rol auxRol = rolGrupo.getRol();
				if (rolGrupo.getEstatus().equals("A")){
				if (rolGrupo.getRol().getNombre().equals("ROLE_USER")) {
					roles.add(new GrantedAuthorityImpl(rolGrupo.getRol()
							.getNombre()));
				} else {
					if (auxRol != null) {
						roles.add(new GrantedAuthorityImpl(auxRol
								.getCodigoRol() + ""));
						System.out.println(auxRol.getCodigoRol());
						auxRol = auxRol.getRol();
						if (auxRol != null) {
							roles.add(new GrantedAuthorityImpl(auxRol
									.getCodigoRol() + ""));
							System.out.println(auxRol.getCodigoRol());
							auxRol = auxRol.getRol();
							if (auxRol != null) {
								roles.add(new GrantedAuthorityImpl(auxRol
										.getCodigoRol() + ""));
								System.out.println(auxRol.getCodigoRol());
								auxRol = auxRol.getRol();
								if (auxRol != null) {
									roles.add(new GrantedAuthorityImpl(auxRol
											.getCodigoRol() + ""));
									System.out.println(auxRol.getCodigoRol());
									auxRol = auxRol.getRol();
									if (auxRol != null) {
										roles.add(new GrantedAuthorityImpl(auxRol
												.getCodigoRol() + ""));
										System.out.println(auxRol.getCodigoRol());
										auxRol = auxRol.getRol();
										if (auxRol != null) {
											roles.add(new GrantedAuthorityImpl(auxRol
													.getCodigoRol() + ""));
											System.out.println(auxRol.getCodigoRol());
										}
									}
								}
							}
						}
						//
					}
				}
				}
			}
		}

		User user = new User(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, roles);
		return user;
	}
}
