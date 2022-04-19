package gob.pe.senamhi.sia.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gob.pe.senamhi.sia.Beans.Historial;
import gob.pe.senamhi.sia.Beans.Producto;
import gob.pe.senamhi.sia.Service.ProductoService;

@CrossOrigin(origins= {"*"})
@RestController
@RequestMapping(value="/producto")
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;
	
	@GetMapping(value="/{esquema}/{tabla}")
	public ResponseEntity<Producto> obtenerProducto(
			@PathVariable String esquema,
			@PathVariable String tabla
			){
		try {
			Producto response = productoService.ObtenerProducto(esquema, tabla.replace("_", "."));
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="")
	public ResponseEntity<List<Producto>> obtenerProductos(
			){
		try {
			List<Producto> response = productoService.ObtenerProductos();
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="/ficha")
	public ResponseEntity<String> GuardarFicha(@RequestBody Producto request){
		try {
			String response = productoService.guardarFicha(request);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/anio/{esquema}/{tabla}")
	public ResponseEntity<List<String>> obtenerAnio(
			@PathVariable String esquema,
			@PathVariable String tabla
			){
		try {
			List<String> response = productoService.ObtenerAnios(esquema,tabla.replace("_", "."));
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/mes/{esquema}/{tabla}/{anio}")
	public ResponseEntity<List<String>> obtenerMes(
			@PathVariable String esquema,
			@PathVariable String tabla,
			@PathVariable String anio
			){
		try {
			List<String> response = productoService.ObtenerMeses(esquema,tabla.replace("_", "."),anio);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/historial/{esquema}/{tabla}/{anio}/{mes}")
	public ResponseEntity<Historial> obtenerHistorial(
			@PathVariable String esquema,
			@PathVariable String tabla,
			@PathVariable String anio,
			@PathVariable String mes
			){
		try {
			Historial response = productoService.ObtenerHistorial(esquema, tabla.replace("_", "."), anio, mes);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/ubigeo/{esquema}/{tabla}")
	public ResponseEntity<String> obtenerUbigeo(
			@PathVariable String esquema,
			@PathVariable String tabla
			){
		try {
			productoService.ObtenerUbigeo(esquema, tabla.replace("_", "."));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/imagen/{esquema}/{tabla}")
	public ResponseEntity<String> ingresarImagen(
			@PathVariable String esquema,
			@PathVariable String tabla
			){
		try {
			productoService.IngresarImagen(esquema, tabla.replace(".", "_"));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
