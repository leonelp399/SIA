package gob.pe.senamhi.sia.Service;

import gob.pe.senamhi.sia.Beans.UsuarioRq;
import gob.pe.senamhi.sia.Beans.UsuarioRs;

public interface UsuarioService {
	
	public UsuarioRs login(UsuarioRq request)throws Exception;
	
}
