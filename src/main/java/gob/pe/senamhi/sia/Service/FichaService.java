package gob.pe.senamhi.sia.Service;

import gob.pe.senamhi.sia.Beans.Ficha;

public interface FichaService {
	
	public Ficha ObtenerFicha(String id)throws Exception;
	
	public String guardarFicha(Ficha request)throws Exception;
	
}
