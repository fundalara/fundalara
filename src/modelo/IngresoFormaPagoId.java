package modelo;
// Generated 10/02/2012 04:53:40 PM by Hibernate Tools 3.4.0.CR1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * IngresoFormaPagoId generated by hbm2java
 */
@Embeddable
public class IngresoFormaPagoId  implements java.io.Serializable {


     private int codigoIngresoFormaPago;
     private int codigoIngreso;

    public IngresoFormaPagoId() {
    }

    public IngresoFormaPagoId(int codigoIngresoFormaPago, int codigoIngreso) {
       this.codigoIngresoFormaPago = codigoIngresoFormaPago;
       this.codigoIngreso = codigoIngreso;
    }
   


    @Column(name="codigo_ingreso_forma_pago", nullable=false)
    public int getCodigoIngresoFormaPago() {
        return this.codigoIngresoFormaPago;
    }
    
    public void setCodigoIngresoFormaPago(int codigoIngresoFormaPago) {
        this.codigoIngresoFormaPago = codigoIngresoFormaPago;
    }


    @Column(name="codigo_ingreso", nullable=false)
    public int getCodigoIngreso() {
        return this.codigoIngreso;
    }
    
    public void setCodigoIngreso(int codigoIngreso) {
        this.codigoIngreso = codigoIngreso;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof IngresoFormaPagoId) ) return false;
		 IngresoFormaPagoId castOther = ( IngresoFormaPagoId ) other; 
         
		 return (this.getCodigoIngresoFormaPago()==castOther.getCodigoIngresoFormaPago())
 && (this.getCodigoIngreso()==castOther.getCodigoIngreso());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getCodigoIngresoFormaPago();
         result = 37 * result + this.getCodigoIngreso();
         return result;
   }   


}


