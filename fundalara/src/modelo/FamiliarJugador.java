package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the familiar_jugador database table.
 * 
 */
@Entity
@Table(name="familiar_jugador")
public class FamiliarJugador implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FamiliarJugadorPK id;

	private String estatus;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_ingreso")
	private Date fechaIngreso;

	private String parentesco;

	private Boolean representante;

	//bi-directional many-to-one association to FamiliarComisionEquipo
	@OneToMany(mappedBy="familiarJugador")
	private Set<FamiliarComisionEquipo> familiarComisionEquipos;

	//bi-directional many-to-one association to Familiar
    @ManyToOne
	@JoinColumn(name="cedula_familiar")
	private Familiar familiar;

	//bi-directional many-to-one association to Jugador
    @ManyToOne
	@JoinColumn(name="cedula_jugador")
	private Jugador jugador;

    public FamiliarJugador() {
    }

	public FamiliarJugadorPK getId() {
		return this.id;
	}

	public void setId(FamiliarJugadorPK id) {
		this.id = id;
	}
	
	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getParentesco() {
		return this.parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	public Boolean getRepresentante() {
		return this.representante;
	}

	public void setRepresentante(Boolean representante) {
		this.representante = representante;
	}

	public Set<FamiliarComisionEquipo> getFamiliarComisionEquipos() {
		return this.familiarComisionEquipos;
	}

	public void setFamiliarComisionEquipos(Set<FamiliarComisionEquipo> familiarComisionEquipos) {
		this.familiarComisionEquipos = familiarComisionEquipos;
	}
	
	public Familiar getFamiliar() {
		return this.familiar;
	}

	public void setFamiliar(Familiar familiar) {
		this.familiar = familiar;
	}
	
	public Jugador getJugador() {
		return this.jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
}