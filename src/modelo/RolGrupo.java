package modelo;
// Generated 10/02/2012 04:53:40 PM by Hibernate Tools 3.4.0.CR1


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * RolGrupo generated by hbm2java
 */
@Entity
@Table(name="rol_grupo"
    ,schema="public"
)
public class RolGrupo  implements java.io.Serializable {


     private RolGrupoId id;
     private Grupo grupo;
     private Rol rol;
     private String estatus;

    public RolGrupo() {
    }

    public RolGrupo(RolGrupoId id, Grupo grupo, Rol rol, String estatus) {
       this.id = id;
       this.grupo = grupo;
       this.rol = rol;
       this.estatus = estatus;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="codigoRol", column=@Column(name="codigo_rol", nullable=false) ), 
        @AttributeOverride(name="codigoGrupo", column=@Column(name="codigo_grupo", nullable=false) ) } )
    public RolGrupoId getId() {
        return this.id;
    }
    
    public void setId(RolGrupoId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="codigo_grupo", nullable=false, insertable=false, updatable=false)
    public Grupo getGrupo() {
        return this.grupo;
    }
    
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="codigo_rol", nullable=false, insertable=false, updatable=false)
    public Rol getRol() {
        return this.rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    
    @Column(name="estatus", nullable=false)
    public String getEstatus() {
        return this.estatus;
    }
    
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }




}


