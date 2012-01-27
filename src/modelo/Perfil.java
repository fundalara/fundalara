package modelo;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0

// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
<<<<<<< HEAD
=======
=======
// Generated 24/01/2012 04:28:30 AM by Hibernate Tools 3.4.0.CR1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0

/**
 * Perfil generated by hbm2java
 */
@Entity
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
@Table(name = "perfil", schema = "public")
public class Perfil implements java.io.Serializable {

	private int codigoPerfil;
	private Usuario usuario;
	private String nombre;
	private char estatus;
	private Set<PerfilGrupo> perfilGrupos = new HashSet<PerfilGrupo>(0);

	public Perfil() {
	}

	public Perfil(int codigoPerfil, Usuario usuario, String nombre, char estatus) {
		this.codigoPerfil = codigoPerfil;
		this.usuario = usuario;
		this.nombre = nombre;
		this.estatus = estatus;
	}

	public Perfil(int codigoPerfil, Usuario usuario, String nombre,
			char estatus, Set<PerfilGrupo> perfilGrupos) {
		this.codigoPerfil = codigoPerfil;
		this.usuario = usuario;
		this.nombre = nombre;
		this.estatus = estatus;
		this.perfilGrupos = perfilGrupos;
	}

	@Id
	@Column(name = "codigo_perfil", unique = true, nullable = false)
	public int getCodigoPerfil() {
		return this.codigoPerfil;
	}

	public void setCodigoPerfil(int codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cedula_rif", nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "perfil")
	public Set<PerfilGrupo> getPerfilGrupos() {
		return this.perfilGrupos;
	}

	public void setPerfilGrupos(Set<PerfilGrupo> perfilGrupos) {
		this.perfilGrupos = perfilGrupos;
	}

}
<<<<<<< HEAD
=======
=======
@Table(name="perfil"
    ,schema="public"
)
public class Perfil  implements java.io.Serializable {


     private int codigoPerfil;
     private Usuario usuario;
     private String nombre;
     private char estatus;
     private Set<PerfilGrupo> perfilGrupos = new HashSet<PerfilGrupo>(0);

    public Perfil() {
    }

	
    public Perfil(int codigoPerfil, Usuario usuario, String nombre, char estatus) {
        this.codigoPerfil = codigoPerfil;
        this.usuario = usuario;
        this.nombre = nombre;
        this.estatus = estatus;
    }
    public Perfil(int codigoPerfil, Usuario usuario, String nombre, char estatus, Set<PerfilGrupo> perfilGrupos) {
       this.codigoPerfil = codigoPerfil;
       this.usuario = usuario;
       this.nombre = nombre;
       this.estatus = estatus;
       this.perfilGrupos = perfilGrupos;
    }
   
     @Id 

    
    @Column(name="codigo_perfil", unique=true, nullable=false)
    public int getCodigoPerfil() {
        return this.codigoPerfil;
    }
    
    public void setCodigoPerfil(int codigoPerfil) {
        this.codigoPerfil = codigoPerfil;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cedula_rif", nullable=false)
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    @Column(name="nombre", nullable=false)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="estatus", nullable=false, length=1)
    public char getEstatus() {
        return this.estatus;
    }
    
    public void setEstatus(char estatus) {
        this.estatus = estatus;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="perfil")
    public Set<PerfilGrupo> getPerfilGrupos() {
        return this.perfilGrupos;
    }
    
    public void setPerfilGrupos(Set<PerfilGrupo> perfilGrupos) {
        this.perfilGrupos = perfilGrupos;
    }




}


>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
