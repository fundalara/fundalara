package modelo;
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514

// Generated 25/01/2012 12:32:42 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
=======
// Generated 24/01/2012 04:28:30 AM by Hibernate Tools 3.4.0.CR1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514

/**
 * JugadorPlan generated by hbm2java
 */
@Entity
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
@Table(name = "jugador_plan", schema = "public")
public class JugadorPlan implements java.io.Serializable {

	private String cedulaRif;
	private TallaPorIndumentaria tallaPorIndumentaria;
	private DatoBasico datoBasico;
	private Jugador jugador;
	private String apellido;
	private String nombre;
	private char estatus;
	private Date fechaNacimiento;
	private Set<RepresentanteJugadorPlan> representanteJugadorPlans = new HashSet<RepresentanteJugadorPlan>(
			0);
	private Set<RosterPlan> rosterPlans = new HashSet<RosterPlan>(0);

	public JugadorPlan() {
	}

	public JugadorPlan(TallaPorIndumentaria tallaPorIndumentaria,
			DatoBasico datoBasico, Jugador jugador, String apellido,
			String nombre, char estatus, Date fechaNacimiento) {
		this.tallaPorIndumentaria = tallaPorIndumentaria;
		this.datoBasico = datoBasico;
		this.jugador = jugador;
		this.apellido = apellido;
		this.nombre = nombre;
		this.estatus = estatus;
		this.fechaNacimiento = fechaNacimiento;
	}

	public JugadorPlan(TallaPorIndumentaria tallaPorIndumentaria,
			DatoBasico datoBasico, Jugador jugador, String apellido,
			String nombre, char estatus, Date fechaNacimiento,
			Set<RepresentanteJugadorPlan> representanteJugadorPlans,
			Set<RosterPlan> rosterPlans) {
		this.tallaPorIndumentaria = tallaPorIndumentaria;
		this.datoBasico = datoBasico;
		this.jugador = jugador;
		this.apellido = apellido;
		this.nombre = nombre;
		this.estatus = estatus;
		this.fechaNacimiento = fechaNacimiento;
		this.representanteJugadorPlans = representanteJugadorPlans;
		this.rosterPlans = rosterPlans;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "jugador"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "cedula_rif", unique = true, nullable = false)
	public String getCedulaRif() {
		return this.cedulaRif;
	}

	public void setCedulaRif(String cedulaRif) {
		this.cedulaRif = cedulaRif;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_talla_indumentaria", nullable = false)
	public TallaPorIndumentaria getTallaPorIndumentaria() {
		return this.tallaPorIndumentaria;
	}

	public void setTallaPorIndumentaria(
			TallaPorIndumentaria tallaPorIndumentaria) {
		this.tallaPorIndumentaria = tallaPorIndumentaria;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_tipo_jugador", nullable = false)
	public DatoBasico getDatoBasico() {
		return this.datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Jugador getJugador() {
		return this.jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	@Column(name = "apellido", nullable = false)
	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_nacimiento", nullable = false, length = 13)
	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jugadorPlan")
	public Set<RepresentanteJugadorPlan> getRepresentanteJugadorPlans() {
		return this.representanteJugadorPlans;
	}

	public void setRepresentanteJugadorPlans(
			Set<RepresentanteJugadorPlan> representanteJugadorPlans) {
		this.representanteJugadorPlans = representanteJugadorPlans;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jugadorPlan")
	public Set<RosterPlan> getRosterPlans() {
		return this.rosterPlans;
	}

	public void setRosterPlans(Set<RosterPlan> rosterPlans) {
		this.rosterPlans = rosterPlans;
	}

}
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
=======
@Table(name="jugador_plan"
    ,schema="public"
)
public class JugadorPlan  implements java.io.Serializable {


     private String cedulaRif;
     private TallaPorIndumentaria tallaPorIndumentaria;
     private DatoBasico datoBasico;
     private Jugador jugador;
     private String apellido;
     private String nombre;
     private char estatus;
     private Date fechaNacimiento;
     private Set<RepresentanteJugadorPlan> representanteJugadorPlans = new HashSet<RepresentanteJugadorPlan>(0);
     private Set<RosterPlan> rosterPlans = new HashSet<RosterPlan>(0);

    public JugadorPlan() {
    }

	
    public JugadorPlan(TallaPorIndumentaria tallaPorIndumentaria, DatoBasico datoBasico, Jugador jugador, String apellido, String nombre, char estatus, Date fechaNacimiento) {
        this.tallaPorIndumentaria = tallaPorIndumentaria;
        this.datoBasico = datoBasico;
        this.jugador = jugador;
        this.apellido = apellido;
        this.nombre = nombre;
        this.estatus = estatus;
        this.fechaNacimiento = fechaNacimiento;
    }
    public JugadorPlan(TallaPorIndumentaria tallaPorIndumentaria, DatoBasico datoBasico, Jugador jugador, String apellido, String nombre, char estatus, Date fechaNacimiento, Set<RepresentanteJugadorPlan> representanteJugadorPlans, Set<RosterPlan> rosterPlans) {
       this.tallaPorIndumentaria = tallaPorIndumentaria;
       this.datoBasico = datoBasico;
       this.jugador = jugador;
       this.apellido = apellido;
       this.nombre = nombre;
       this.estatus = estatus;
       this.fechaNacimiento = fechaNacimiento;
       this.representanteJugadorPlans = representanteJugadorPlans;
       this.rosterPlans = rosterPlans;
    }
   
     @GenericGenerator(name="generator", strategy="foreign", parameters=@Parameter(name="property", value="jugador"))@Id @GeneratedValue(generator="generator")

    
    @Column(name="cedula_rif", unique=true, nullable=false)
    public String getCedulaRif() {
        return this.cedulaRif;
    }
    
    public void setCedulaRif(String cedulaRif) {
        this.cedulaRif = cedulaRif;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="codigo_talla_indumentaria", nullable=false)
    public TallaPorIndumentaria getTallaPorIndumentaria() {
        return this.tallaPorIndumentaria;
    }
    
    public void setTallaPorIndumentaria(TallaPorIndumentaria tallaPorIndumentaria) {
        this.tallaPorIndumentaria = tallaPorIndumentaria;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="codigo_tipo_jugador", nullable=false)
    public DatoBasico getDatoBasico() {
        return this.datoBasico;
    }
    
    public void setDatoBasico(DatoBasico datoBasico) {
        this.datoBasico = datoBasico;
    }

@OneToOne(fetch=FetchType.LAZY)@PrimaryKeyJoinColumn
    public Jugador getJugador() {
        return this.jugador;
    }
    
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    
    @Column(name="apellido", nullable=false)
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    @Temporal(TemporalType.DATE)
    @Column(name="fecha_nacimiento", nullable=false, length=13)
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }
    
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="jugadorPlan")
    public Set<RepresentanteJugadorPlan> getRepresentanteJugadorPlans() {
        return this.representanteJugadorPlans;
    }
    
    public void setRepresentanteJugadorPlans(Set<RepresentanteJugadorPlan> representanteJugadorPlans) {
        this.representanteJugadorPlans = representanteJugadorPlans;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="jugadorPlan")
    public Set<RosterPlan> getRosterPlans() {
        return this.rosterPlans;
    }
    
    public void setRosterPlans(Set<RosterPlan> rosterPlans) {
        this.rosterPlans = rosterPlans;
    }




}


>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
