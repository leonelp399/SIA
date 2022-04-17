package gob.pe.senamhi.sia.Service;

import java.util.List;

import gob.pe.senamhi.sia.Beans.Historial;
import gob.pe.senamhi.sia.Beans.Prioridad;

public interface CultivoHistorialService {
	
	
	public List<String> ObtenerAnios(String cultivo)throws Exception;
	
	public List<String> ObtenerMeses(String cultivo,String anio)throws Exception;
	
	public Historial ObtenerHistorial(String cultivo, String mes, String anio)throws Exception;
	
	public Prioridad ObtenerPrioridad(String cultivo)throws Exception;
}
