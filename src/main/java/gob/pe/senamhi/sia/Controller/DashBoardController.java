package gob.pe.senamhi.sia.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gob.pe.senamhi.sia.Beans.DashBoardResponse;
import gob.pe.senamhi.sia.Beans.Producto;
import gob.pe.senamhi.sia.Service.DashBoardService;

@CrossOrigin(origins= {"*"})
@RestController
@RequestMapping(value="/dashboard")
public class DashBoardController {
	
	
	@Autowired
	private DashBoardService dashBoardService;
	
	@GetMapping(value="/riesgo/{esquema}/{tabla}/{anio}/{dep}")
	public ResponseEntity<DashBoardResponse> obtenerDashBoardByDepRiesgo(
			@PathVariable String esquema,
			@PathVariable String tabla,
			@PathVariable String anio,
			@PathVariable String dep
			){
		try {
			DashBoardResponse response = dashBoardService.obtenerDashBoardByDepRiesgo(esquema, tabla.replace("_", "."),anio,dep);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/riesgo/{esquema}/{tabla}/{anio}/{dep}/{prov}")
	public ResponseEntity<DashBoardResponse> obtenerDashBoardByDepProvRiesgo(
			@PathVariable String esquema,
			@PathVariable String tabla,
			@PathVariable String anio,
			@PathVariable String dep,
			@PathVariable String prov
			){
		try {
			DashBoardResponse response = dashBoardService.obtenerDashBoardByDepProvRiesgo(esquema, tabla.replace("_", "."),anio,dep,prov);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/monitoreo/{esquema}/{tabla}/{anio}/{dep}")
	public ResponseEntity<DashBoardResponse> obtenerDashBoardByDepMonitoreo(
			@PathVariable String esquema,
			@PathVariable String tabla,
			@PathVariable String anio,
			@PathVariable String dep
			){
		try {
			DashBoardResponse response = dashBoardService.obtenerDashBoardByDepMonitoreo(esquema, tabla.replace("_", "."),anio,dep);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
