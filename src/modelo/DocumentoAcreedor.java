package modelo;
// Generated 24/01/2012 04:28:30 AM by Hibernate Tools 3.4.0.CR1


import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * DocumentoAcreedor generated by hbm2java
 */
@Entity
@Table(name="documento_acreedor"
    ,schema="public"
)
public class DocumentoAcreedor  implements java.io.Serializable {


     private int codigoDocumentoAcreedor;
     private TipoIngreso tipoIngreso;
     private Persona personaByCedulaRif;
     private Persona personaByCedulaAtleta;
     private Date fechaEmision;
     private Date fechaVencimiento;
     private double monto;
     private String concepto;
     private char estado;
     private char estatus;
     private double saldo;
     private Set<IngresoDocumentoAcreedor> ingresoDocumentoAcreedors = new HashSet<IngresoDocumentoAcreedor>(0);
     private Set<DocumentoAcreedorMaterial> documentoAcreedorMaterials = new HashSet<DocumentoAcreedorMaterial>(0);
     private Set<DocumentoIndumentaria> documentoIndumentarias = new HashSet<DocumentoIndumentaria>(0);
     private Set<NotaEntrega> notaEntregas = new HashSet<NotaEntrega>(0);

    public DocumentoAcreedor() {
    }

	
    public DocumentoAcreedor(int codigoDocumentoAcreedor, TipoIngreso tipoIngreso, Date fechaEmision, Date fechaVencimiento, double monto, char estado, char estatus, double saldo) {
        this.codigoDocumentoAcreedor = codigoDocumentoAcreedor;
        this.tipoIngreso = tipoIngreso;
        this.fechaEmision = fechaEmision;
        this.fechaVencimiento = fechaVencimiento;
        this.monto = monto;
        this.estado = estado;
        this.estatus = estatus;
        this.saldo = saldo;
    }
    public DocumentoAcreedor(int codigoDocumentoAcreedor, TipoIngreso tipoIngreso, Persona personaByCedulaRif, Persona personaByCedulaAtleta, Date fechaEmision, Date fechaVencimiento, double monto, String concepto, char estado, char estatus, double saldo, Set<IngresoDocumentoAcreedor> ingresoDocumentoAcreedors, Set<DocumentoAcreedorMaterial> documentoAcreedorMaterials, Set<DocumentoIndumentaria> documentoIndumentarias, Set<NotaEntrega> notaEntregas) {
       this.codigoDocumentoAcreedor = codigoDocumentoAcreedor;
       this.tipoIngreso = tipoIngreso;
       this.personaByCedulaRif = personaByCedulaRif;
       this.personaByCedulaAtleta = personaByCedulaAtleta;
       this.fechaEmision = fechaEmision;
       this.fechaVencimiento = fechaVencimiento;
       this.monto = monto;
       this.concepto = concepto;
       this.estado = estado;
       this.estatus = estatus;
       this.saldo = saldo;
       this.ingresoDocumentoAcreedors = ingresoDocumentoAcreedors;
       this.documentoAcreedorMaterials = documentoAcreedorMaterials;
       this.documentoIndumentarias = documentoIndumentarias;
       this.notaEntregas = notaEntregas;
    }
   
     @Id 

    
    @Column(name="codigo_documento_acreedor", unique=true, nullable=false)
    public int getCodigoDocumentoAcreedor() {
        return this.codigoDocumentoAcreedor;
    }
    
    public void setCodigoDocumentoAcreedor(int codigoDocumentoAcreedor) {
        this.codigoDocumentoAcreedor = codigoDocumentoAcreedor;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="codigo_tipo_ingreso", nullable=false)
    public TipoIngreso getTipoIngreso() {
        return this.tipoIngreso;
    }
    
    public void setTipoIngreso(TipoIngreso tipoIngreso) {
        this.tipoIngreso = tipoIngreso;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cedula_rif")
    public Persona getPersonaByCedulaRif() {
        return this.personaByCedulaRif;
    }
    
    public void setPersonaByCedulaRif(Persona personaByCedulaRif) {
        this.personaByCedulaRif = personaByCedulaRif;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cedula_atleta")
    public Persona getPersonaByCedulaAtleta() {
        return this.personaByCedulaAtleta;
    }
    
    public void setPersonaByCedulaAtleta(Persona personaByCedulaAtleta) {
        this.personaByCedulaAtleta = personaByCedulaAtleta;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha_emision", nullable=false, length=13)
    public Date getFechaEmision() {
        return this.fechaEmision;
    }
    
    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha_vencimiento", nullable=false, length=13)
    public Date getFechaVencimiento() {
        return this.fechaVencimiento;
    }
    
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    
    @Column(name="monto", nullable=false, precision=17, scale=17)
    public double getMonto() {
        return this.monto;
    }
    
    public void setMonto(double monto) {
        this.monto = monto;
    }

    
    @Column(name="concepto")
    public String getConcepto() {
        return this.concepto;
    }
    
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    
    @Column(name="estado", nullable=false, length=1)
    public char getEstado() {
        return this.estado;
    }
    
    public void setEstado(char estado) {
        this.estado = estado;
    }

    
    @Column(name="estatus", nullable=false, length=1)
    public char getEstatus() {
        return this.estatus;
    }
    
    public void setEstatus(char estatus) {
        this.estatus = estatus;
    }

    
    @Column(name="saldo", nullable=false, precision=17, scale=17)
    public double getSaldo() {
        return this.saldo;
    }
    
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="documentoAcreedor")
    public Set<IngresoDocumentoAcreedor> getIngresoDocumentoAcreedors() {
        return this.ingresoDocumentoAcreedors;
    }
    
    public void setIngresoDocumentoAcreedors(Set<IngresoDocumentoAcreedor> ingresoDocumentoAcreedors) {
        this.ingresoDocumentoAcreedors = ingresoDocumentoAcreedors;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="documentoAcreedor")
    public Set<DocumentoAcreedorMaterial> getDocumentoAcreedorMaterials() {
        return this.documentoAcreedorMaterials;
    }
    
    public void setDocumentoAcreedorMaterials(Set<DocumentoAcreedorMaterial> documentoAcreedorMaterials) {
        this.documentoAcreedorMaterials = documentoAcreedorMaterials;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="documentoAcreedor")
    public Set<DocumentoIndumentaria> getDocumentoIndumentarias() {
        return this.documentoIndumentarias;
    }
    
    public void setDocumentoIndumentarias(Set<DocumentoIndumentaria> documentoIndumentarias) {
        this.documentoIndumentarias = documentoIndumentarias;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="documentoAcreedor")
    public Set<NotaEntrega> getNotaEntregas() {
        return this.notaEntregas;
    }
    
    public void setNotaEntregas(Set<NotaEntrega> notaEntregas) {
        this.notaEntregas = notaEntregas;
    }




}


