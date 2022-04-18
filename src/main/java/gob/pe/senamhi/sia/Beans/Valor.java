package gob.pe.senamhi.sia.Beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Entity
@Table(name="VALORES")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)
public class Valor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	private String leyendaId;
	
	@Column(name = "NOMBRE")
	private String descripcion;
	
	@Column(name = "AREA")
	private String area;
	
	@Column(name = "PERIMETRO")
	private String perimetro;
	
	@Column(name = "PERIODO")
	private String periodo;
	
	@Column(name = "IMAGEN")
	private String imagen;
}
