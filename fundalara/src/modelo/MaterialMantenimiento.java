package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the material_mantenimiento database table.
 * 
 */
@Entity
@Table(name="material_mantenimiento")
public class MaterialMantenimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MaterialMantenimientoPK id;

	@Column(name="cantidad_devuelta")
	private Integer cantidadDevuelta;

	@Column(name="cantidad_entregada")
	private Integer cantidadEntregada;

	private String estatus;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_devolucion")
	private Date fechaDevolucion;

	//bi-directional many-to-one association to Mantenimiento
    @ManyToOne
	@JoinColumn(name="codigo_mantenimiento")
	private Mantenimiento mantenimiento;

	//bi-directional many-to-one association to Material
    @ManyToOne
	@JoinColumn(name="codigo_material")
	private Material material;

    public MaterialMantenimiento() {
    }

	public MaterialMantenimientoPK getId() {
		return this.id;
	}

	public void setId(MaterialMantenimientoPK id) {
		this.id = id;
	}
	
	public Integer getCantidadDevuelta() {
		return this.cantidadDevuelta;
	}

	public void setCantidadDevuelta(Integer cantidadDevuelta) {
		this.cantidadDevuelta = cantidadDevuelta;
	}

	public Integer getCantidadEntregada() {
		return this.cantidadEntregada;
	}

	public void setCantidadEntregada(Integer cantidadEntregada) {
		this.cantidadEntregada = cantidadEntregada;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Date getFechaDevolucion() {
		return this.fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public Mantenimiento getMantenimiento() {
		return this.mantenimiento;
	}

	public void setMantenimiento(Mantenimiento mantenimiento) {
		this.mantenimiento = mantenimiento;
	}
	
	public Material getMaterial() {
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
	
}