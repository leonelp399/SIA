package gob.pe.senamhi.sia.Beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)
public class Leyenda implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String descripcion;
	
	private String area;
	
	private String perimetro;

}
