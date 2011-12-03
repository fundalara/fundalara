package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the material database table.
 * 
 */
@Entity
public class Material implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_material")
	private String codigoMaterial;

	@Column(name="cantidad_disponible")
	private Integer cantidadDisponible;

	@Column(name="cantidad_existencia")
	private Integer cantidadExistencia;

	@Column(name="cantidad_presentacion")
	private Integer cantidadPresentacion;

	private String descripcion;

	@Column(name="estado_material")
	private String estadoMaterial;

	private String estatus;

	private Boolean reutilizable;

	@Column(name="stock_maximo")
	private Integer stockMaximo;

	@Column(name="stock_minimo")
	private Integer stockMinimo;

	//bi-directional many-to-one association to TipoMaterial
    @ManyToOne
	@JoinColumn(name="codigo_tipo_material")
	private TipoMaterial tipoMaterial;

	//bi-directional many-to-one association to UnidadMedida
    @ManyToOne
	@JoinColumn(name="codigo_unidad_medida")
	private UnidadMedida unidadMedida;

	//bi-directional many-to-one association to MaterialMantenimiento
	@OneToMany(mappedBy="material")
	private Set<MaterialMantenimiento> materialMantenimientos;

	//bi-directional many-to-one association to MaterialMantenimientoPlanificado
	@OneToMany(mappedBy="material")
	private Set<MaterialMantenimientoPlanificado> materialMantenimientoPlanificados;

    public Material() {
    }

	public String getCodigoMaterial() {
		return this.codigoMaterial;
	}

	public void setCodigoMaterial(String codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}

	public Integer getCantidadDisponible() {
		return this.cantidadDisponible;
	}

	public void setCantidadDisponible(Integer cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	public Integer getCantidadExistencia() {
		return this.cantidadExistencia;
	}

	public void setCantidadExistencia(Integer cantidadExistencia) {
		this.cantidadExistencia = cantidadExistencia;
	}

	public Integer getCantidadPresentacion() {
		return this.cantidadPresentacion;
	}

	public void setCantidadPresentacion(Integer cantidadPresentacion) {
		this.cantidadPresentacion = cantidadPresentacion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstadoMaterial() {
		return this.estadoMaterial;
	}

	public void setEstadoMaterial(String estadoMaterial) {
		this.estadoMaterial = estadoMaterial;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Boolean getReutilizable() {
		return this.reutilizable;
	}

	public void setReutilizable(Boolean reutilizable) {
		this.reutilizable = reutilizable;
	}

	public Integer getStockMaximo() {
		return this.stockMaximo;
	}

	public void setStockMaximo(Integer stockMaximo) {
		this.stockMaximo = stockMaximo;
	}

	public Integer getStockMinimo() {
		return this.stockMinimo;
	}

	public void setStockMinimo(Integer stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public TipoMaterial getTipoMaterial() {
		return this.tipoMaterial;
	}

	public void setTipoMaterial(TipoMaterial tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}
	
	public UnidadMedida getUnidadMedida() {
		return this.unidadMedida;
	}

	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	
	public Set<MaterialMantenimiento> getMaterialMantenimientos() {
		return this.materialMantenimientos;
	}

	public void setMaterialMantenimientos(Set<MaterialMantenimiento> materialMantenimientos) {
		this.materialMantenimientos = materialMantenimientos;
	}
	
	public Set<MaterialMantenimientoPlanificado> getMaterialMantenimientoPlanificados() {
		return this.materialMantenimientoPlanificados;
	}

	public void setMaterialMantenimientoPlanificados(Set<MaterialMantenimientoPlanificado> materialMantenimientoPlanificados) {
		this.materialMantenimientoPlanificados = materialMantenimientoPlanificados;
	}
	
}