package gob.pe.senamhi.sia.Beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="LEYENDA")
@Data
public class Nivel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "LEYENDA_NOMBRE")
	private String descripcion;
	
	@Column(name = "FECHA")
	private String fecha;
	
	@Column(name = "PRIORIDAD")
	private String prioridad;
	
	@Column(name = "DEPPROV")
	private String depProv;
	
}
