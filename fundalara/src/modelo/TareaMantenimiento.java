package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tarea_mantenimiento database table.
 * 
 */
@Entity
@Table(name="tarea_mantenimiento")
public class TareaMantenimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TareaMantenimientoPK id;

	private String estatus;

	//bi-directional many-to-one association to EmpleadoMantenimiento
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cedula", referencedColumnName="cedula"),
		@JoinColumn(name="codigo_mantenimiento", referencedColumnName="codigo_mantenimiento")
		})
	private EmpleadoMantenimiento empleadoMantenimiento;

	//bi-directional many-to-one association to Mantenimiento
    @ManyToOne
	@JoinColumn(name="codigo_mantenimiento")
	private Mantenimiento mantenimiento;

	//bi-directional many-to-one association to Tarea
    @ManyToOne
	@JoinColumn(name="codigo_tarea")
	private Tarea tarea;

    public TareaMantenimiento() {
    }

	public TareaMantenimientoPK getId() {
		return this.id;
	}

	public void setId(TareaMantenimientoPK id) {
		this.id = id;
	}
	
	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public EmpleadoMantenimiento getEmpleadoMantenimiento() {
		return this.empleadoMantenimiento;
	}

	public void setEmpleadoMantenimiento(EmpleadoMantenimiento empleadoMantenimiento) {
		this.empleadoMantenimiento = empleadoMantenimiento;
	}
	
	public Mantenimiento getMantenimiento() {
		return this.mantenimiento;
	}

	public void setMantenimiento(Mantenimiento mantenimiento) {
		this.mantenimiento = mantenimiento;
	}
	
	public Tarea getTarea() {
		return this.tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}
	
}