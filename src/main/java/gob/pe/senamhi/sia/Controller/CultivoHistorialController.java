package gob.pe.senamhi.sia.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gob.pe.senamhi.sia.Beans.Historial;
import gob.pe.senamhi.sia.Beans.Prioridad;
import gob.pe.senamhi.sia.Service.CultivoHistorialService;

@CrossOrigin(origins= {"*"})
@RestController
@RequestMapping(value="/cultivo")
public class CultivoHistorialController {

	@Autowired
	private CultivoHistorialService historialService;
	
	@GetMapping(value="/historial/anio/{cultivoId}")
	public ResponseEntity<List<String>> obtenerAnio(@PathVariable String cultivoId){
		try {
			List<String> response = historialService.ObtenerAnios(cultivoId);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/historial/mes/{cultivoId}/{anio}")
	public ResponseEntity<List<String>> obtenerMes(
			@PathVariable String cultivoId,
			@PathVariable String anio
			){
		try {
			List<String> response = historialService.ObtenerMeses(cultivoId,anio);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/historial")
	public ResponseEntity<Historial> obtenerHistorial(
			@RequestParam(value = "cultivoId", required = true) String cultivoId,
			@RequestParam(value = "mes", required = true) String mes,
			@RequestParam(value = "anio", required = true) String anio
			){
		try {
			Historial response = historialService.ObtenerHistorial(cultivoId, mes, anio);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/prioridad/{cultivoId}")
	public ResponseEntity<Prioridad> obtenerPrioridad(
			@PathVariable String cultivoId
			){
		try {
			Prioridad response = historialService.ObtenerPrioridad(cultivoId);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
