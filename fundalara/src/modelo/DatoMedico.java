package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the dato_medico database table.
 * 
 */
@Entity
@Table(name="dato_medico")
public class DatoMedico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_registro")
	private String codigoRegistro;

	private String estatus;

	@Column(name="factor_sanguineo")
	private String factorSanguineo;

	private String observacion;

	//bi-directional many-to-one association to AfeccionJugador
	@OneToMany(mappedBy="datoMedico")
	private Set<AfeccionJugador> afeccionJugadors;

	//bi-directional many-to-one association to Jugador
    @ManyToOne
	@JoinColumn(name="cedula_jugador")
	private Jugador jugador;

	//bi-directional many-to-one association to Medico
    @ManyToOne
	@JoinColumn(name="matricula")
	private Medico medico;

    public DatoMedico() {
    }

	public String getCodigoRegistro() {
		return this.codigoRegistro;
	}

	public void setCodigoRegistro(String codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getFactorSanguineo() {
		return this.factorSanguineo;
	}

	public void setFactorSanguineo(String factorSanguineo) {
		this.factorSanguineo = factorSanguineo;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Set<AfeccionJugador> getAfeccionJugadors() {
		return this.afeccionJugadors;
	}

	public void setAfeccionJugadors(Set<AfeccionJugador> afeccionJugadors) {
		this.afeccionJugadors = afeccionJugadors;
	}
	
	public Jugador getJugador() {
		return this.jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
	public Medico getMedico() {
		return this.medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
}