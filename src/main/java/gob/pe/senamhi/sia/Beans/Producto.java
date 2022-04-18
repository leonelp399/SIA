package gob.pe.senamhi.sia.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="PRODUCTO")
@Data
public class Producto {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "ESQUEMA")
	private String esquema;
	
	@Column(name = "TABLA")
	private String tabla;
	
	@Column(name = "ENLACE_FICHA")
	private String enlace;
	
}
