package modelo;
// Generated 10/02/2012 04:53:40 PM by Hibernate Tools 3.4.0.CR1


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

/**
 * EquipoJuego generated by hbm2java
 */
@Entity
@Table(name="equipo_juego"
    ,schema="public"
)
public class EquipoJuego  implements java.io.Serializable {


     private int codigoEquipoJuego;
     private Juego juego;
     private EquipoCompetencia equipoCompetencia;
     private boolean homeClub;
     private Integer carrera;
     private Integer hit;
     private Integer error;
     private boolean ganado;
     private char estatus;
     private Set<DesempennoColectivo> desempennoColectivos = new HashSet<DesempennoColectivo>(0);
     private Set<PersonalEquipoJuego> personalEquipoJuegos = new HashSet<PersonalEquipoJuego>(0);

    public EquipoJuego() {
    }

	
    public EquipoJuego(int codigoEquipoJuego, Juego juego, EquipoCompetencia equipoCompetencia, boolean homeClub, boolean ganado, char estatus) {
        this.codigoEquipoJuego = codigoEquipoJuego;
        this.juego = juego;
        this.equipoCompetencia = equipoCompetencia;
        this.homeClub = homeClub;
        this.ganado = ganado;
        this.estatus = estatus;
    }
    public EquipoJuego(int codigoEquipoJuego, Juego juego, EquipoCompetencia equipoCompetencia, boolean homeClub, Integer carrera, Integer hit, Integer error, boolean ganado, char estatus, Set<DesempennoColectivo> desempennoColectivos, Set<PersonalEquipoJuego> personalEquipoJuegos) {
       this.codigoEquipoJuego = codigoEquipoJuego;
       this.juego = juego;
       this.equipoCompetencia = equipoCompetencia;
       this.homeClub = homeClub;
       this.carrera = carrera;
       this.hit = hit;
       this.error = error;
       this.ganado = ganado;
       this.estatus = estatus;
       this.desempennoColectivos = desempennoColectivos;
       this.personalEquipoJuegos = personalEquipoJuegos;
    }
   
     @Id 

    
    @Column(name="codigo_equipo_juego", unique=true, nullable=false)
    public int getCodigoEquipoJuego() {
        return this.codigoEquipoJuego;
    }
    
    public void setCodigoEquipoJuego(int codigoEquipoJuego) {
        this.codigoEquipoJuego = codigoEquipoJuego;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="codigo_juego", nullable=false)
    public Juego getJuego() {
        return this.juego;
    }
    
    public void setJuego(Juego juego) {
        this.juego = juego;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="codigo_equipo_competencia", nullable=false)
    public EquipoCompetencia getEquipoCompetencia() {
        return this.equipoCompetencia;
    }
    
    public void setEquipoCompetencia(EquipoCompetencia equipoCompetencia) {
        this.equipoCompetencia = equipoCompetencia;
    }

    
    @Column(name="home_club", nullable=false)
    public boolean isHomeClub() {
        return this.homeClub;
    }
    
    public void setHomeClub(boolean homeClub) {
        this.homeClub = homeClub;
    }

    
    @Column(name="carrera")
    public Integer getCarrera() {
        return this.carrera;
    }
    
    public void setCarrera(Integer carrera) {
        this.carrera = carrera;
    }

    
    @Column(name="hit")
    public Integer getHit() {
        return this.hit;
    }
    
    public void setHit(Integer hit) {
        this.hit = hit;
    }

    
    @Column(name="error")
    public Integer getError() {
        return this.error;
    }
    
    public void setError(Integer error) {
        this.error = error;
    }

    
    @Column(name="ganado", nullable=false)
    public boolean isGanado() {
        return this.ganado;
    }
    
    public void setGanado(boolean ganado) {
        this.ganado = ganado;
    }

    
    @Column(name="estatus", nullable=false, length=1)
    public char getEstatus() {
        return this.estatus;
    }
    
    public void setEstatus(char estatus) {
        this.estatus = estatus;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="equipoJuego")
    public Set<DesempennoColectivo> getDesempennoColectivos() {
        return this.desempennoColectivos;
    }
    
    public void setDesempennoColectivos(Set<DesempennoColectivo> desempennoColectivos) {
        this.desempennoColectivos = desempennoColectivos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="equipoJuego")
    public Set<PersonalEquipoJuego> getPersonalEquipoJuegos() {
        return this.personalEquipoJuegos;
    }
    
    public void setPersonalEquipoJuegos(Set<PersonalEquipoJuego> personalEquipoJuegos) {
        this.personalEquipoJuegos = personalEquipoJuegos;
    }




}


