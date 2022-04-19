package gob.pe.senamhi.sia.Service;

import java.util.List;

import gob.pe.senamhi.sia.Beans.Historial;
import gob.pe.senamhi.sia.Beans.Producto;

public interface ProductoService {
	
	public Producto ObtenerProducto(String esquema, String tabla)throws Exception;
	
	public List<Producto> ObtenerProductos()throws Exception;
	
	public String guardarFicha(Producto request)throws Exception;
	
	public List<String> ObtenerAnios(String esquema,String tabla)throws Exception;
	
	public List<String> ObtenerMeses(String esquema,String tabla,String anio)throws Exception;
	
	public Historial ObtenerHistorial(String esquema,String tabla,String anio,String mes)throws Exception;
	
	public void ObtenerUbigeo(String esquema, String tabla)throws Exception;
	
	public void IngresarImagen(String esquema, String tabla)throws Exception;
	
}
