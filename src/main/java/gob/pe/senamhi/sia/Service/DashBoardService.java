package gob.pe.senamhi.sia.Service;

import gob.pe.senamhi.sia.Beans.DashBoardResponse;

public interface DashBoardService {
	
	
	public DashBoardResponse obtenerDashBoardByDepRiesgo(String esquema, String tabla, String anio, String dep)throws Exception;
	
	public DashBoardResponse obtenerDashBoardByDepProvRiesgo(String esquema, String tabla, String anio, String dep, String prov)throws Exception;
	
	public DashBoardResponse obtenerDashBoardByDepMonitoreo(String esquema, String tabla, String anio, String dep)throws Exception;
	
	
}
