package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the familiar_comision_equipo database table.
 * 
 */
@Entity
@Table(name="familiar_comision_equipo")
public class FamiliarComisionEquipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FamiliarComisionEquipoPK id;

	private String estatus;

	//bi-directional many-to-one association to ComisionEquipo
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="codigo_comision", referencedColumnName="codigo_comision"),
		@JoinColumn(name="codigo_equipo", referencedColumnName="codigo_equipo")
		})
	private ComisionEquipo comisionEquipo;

	//bi-directional many-to-one association to FamiliarJugador
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cedula_familiar", referencedColumnName="cedula_familiar"),
		@JoinColumn(name="cedula_jugador", referencedColumnName="cedula_jugador")
		})
	private FamiliarJugador familiarJugador;

    public FamiliarComisionEquipo() {
    }

	public FamiliarComisionEquipoPK getId() {
		return this.id;
	}

	public void setId(FamiliarComisionEquipoPK id) {
		this.id = id;
	}
	
	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public ComisionEquipo getComisionEquipo() {
		return this.comisionEquipo;
	}

	public void setComisionEquipo(ComisionEquipo comisionEquipo) {
		this.comisionEquipo = comisionEquipo;
	}
	
	public FamiliarJugador getFamiliarJugador() {
		return this.familiarJugador;
	}

	public void setFamiliarJugador(FamiliarJugador familiarJugador) {
		this.familiarJugador = familiarJugador;
	}
	
}