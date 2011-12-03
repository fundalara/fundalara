package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the resultado_mantenimiento database table.
 * 
 */
@Entity
@Table(name="resultado_mantenimiento")
public class ResultadoMantenimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ResultadoMantenimientoPK id;

	private String estatus;

	//bi-directional many-to-one association to EstadoMantenimiento
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="codigo_estado", referencedColumnName="codigo_mantenimiento"),
		@JoinColumn(name="codigo_mantenimiento", referencedColumnName="codigo_estado")
		})
	private EstadoMantenimiento estadoMantenimiento;

	//bi-directional many-to-one association to Resultado
    @ManyToOne
	@JoinColumn(name="codigo_resultado")
	private Resultado resultado;

    public ResultadoMantenimiento() {
    }

	public ResultadoMantenimientoPK getId() {
		return this.id;
	}

	public void setId(ResultadoMantenimientoPK id) {
		this.id = id;
	}
	
	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public EstadoMantenimiento getEstadoMantenimiento() {
		return this.estadoMantenimiento;
	}

	public void setEstadoMantenimiento(EstadoMantenimiento estadoMantenimiento) {
		this.estadoMantenimiento = estadoMantenimiento;
	}
	
	public Resultado getResultado() {
		return this.resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}
	
}