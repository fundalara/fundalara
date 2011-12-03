package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the estado_mantenimiento database table.
 * 
 */
@Entity
@Table(name="estado_mantenimiento")
public class EstadoMantenimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EstadoMantenimientoPK id;

	private String estatus;

	//bi-directional many-to-one association to Estado
    @ManyToOne
	@JoinColumn(name="codigo_estado")
	private Estado estado;

	//bi-directional many-to-one association to Mantenimiento
    @ManyToOne
	@JoinColumn(name="codigo_mantenimiento")
	private Mantenimiento mantenimiento;

	//bi-directional many-to-one association to ResultadoMantenimiento
	@OneToMany(mappedBy="estadoMantenimiento")
	private Set<ResultadoMantenimiento> resultadoMantenimientos;

    public EstadoMantenimiento() {
    }

	public EstadoMantenimientoPK getId() {
		return this.id;
	}

	public void setId(EstadoMantenimientoPK id) {
		this.id = id;
	}
	
	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Mantenimiento getMantenimiento() {
		return this.mantenimiento;
	}

	public void setMantenimiento(Mantenimiento mantenimiento) {
		this.mantenimiento = mantenimiento;
	}
	
	public Set<ResultadoMantenimiento> getResultadoMantenimientos() {
		return this.resultadoMantenimientos;
	}

	public void setResultadoMantenimientos(Set<ResultadoMantenimiento> resultadoMantenimientos) {
		this.resultadoMantenimientos = resultadoMantenimientos;
	}
	
}