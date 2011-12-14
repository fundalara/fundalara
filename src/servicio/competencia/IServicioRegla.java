package servicio.competencia;


import java.util.List;


import modelo.Regla;



public interface IServicioRegla {

	
	public abstract void eliminar(Regla r);

    public abstract void agregar(Regla r);
		
    public abstract void actualizar(Regla r);
	
    public abstract  Regla buscarPorCodigo (Regla r);

    
    public abstract  List<Regla> listar ();
	
	


}
