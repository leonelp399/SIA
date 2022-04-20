package gob.pe.senamhi.sia.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gob.pe.senamhi.sia.Beans.Valor;

@Repository
public interface ValorDao extends JpaRepository<Valor, String>{
	
	
	@Query(nativeQuery = true, value = "SELECT I.IMAGEN,ROWNUM ID,'' NOMBRE, '' AREA, '' PERIMETRO, '' PERIODO\r\n" + 
										"  FROM IMAGEN I\r\n" + 
										"       INNER JOIN PRODUCTO P ON P.ID=I.PRODUCTO_ID\r\n" + 
										" WHERE P.ESQUEMA = ?1\r\n" + 
										"   AND P.TABLA = ?2\r\n" + 
										"   AND EXTRACT(YEAR FROM I.FECHA)= ?3\r\n" + 
										"   AND EXTRACT(MONTH FROM I.FECHA) = ?4")
	Valor findImagen(String esquema,String tabla, Integer anio, Integer mes);
	
	
	
	@Query(nativeQuery = true, value = "SELECT ID,\r\n" + 
			"										       NOMBRE,\r\n" + 
			"										       SUM(AREA) AREA, \r\n" + 
			"										       SUM(PERIMETRO) PERIMETRO,\r\n" + 
			"                                               '' IMAGEN,\r\n" + 
			"										       '' PERIODO \r\n" + 
			"										 FROM( \r\n" + 
			"										        SELECT L.ID ,\r\n" + 
			"										               L.NOMBRE,\r\n" + 
			"										               RV.AREA,\r\n" + 
			"										               RV.PERIMETRO\r\n" + 
			"										          FROM RIESGO_VALORES RV \r\n" + 
			"										               INNER JOIN LEYENDA L ON L.ID=RV.LEYENDA_ID\r\n" + 
			"										               INNER JOIN PRODUCTO P ON P.ID=L.PRODUCTO_ID\r\n" + 
			"										         WHERE P.ESQUEMA = ?1\r\n" + 
			"										           AND P.TABLA = ?2 \r\n" + 
			"										           AND EXTRACT(YEAR FROM RV.FECHA) = ?3\r\n" + 
			"										           AND EXTRACT(MONTH FROM RV.FECHA) = ?4\r\n" + 
			"										         GROUP BY L.ID,L.NOMBRE,RV.AREA,RV.PERIMETRO \r\n" + 
			"										         ORDER BY L.ID)\r\n" + 
			"										 GROUP BY ID,NOMBRE\r\n" + 
			"										 ORDER BY ID")
	List<Valor> findValoresRiesgo(String esquema,String tabla, Integer anio, Integer mes);
	
	@Query(nativeQuery = true, value = "       SELECT ID,\r\n" + 
			"										       NOMBRE, \r\n" + 
			"										       SUM(AREA) AREA, \r\n" + 
			"										       SUM(PERIMETRO) PERIMETRO,\r\n" + 
			"										       PERIODO,\r\n" + 
			"										       '' IMAGEN \r\n" + 
			"										 FROM(\r\n" + 
			"										        SELECT L.ID ,\r\n" + 
			"										               L.NOMBRE,\r\n" + 
			"										               MV.AREA,\r\n" + 
			"										               MV.PERIMETRO,\r\n" + 
			"										               MV.PERIODO\r\n" + 
			"										          FROM MONITOREO_VALORES MV \r\n" + 
			"										               INNER JOIN LEYENDA L ON L.ID=MV.LEYENDA_ID\r\n" + 
			"										               INNER JOIN PRODUCTO P ON P.ID=L.PRODUCTO_ID\r\n" + 
			"										         WHERE P.ESQUEMA = ?1\r\n" + 
			"										           AND P.TABLA = ?2\r\n" + 
			"										           AND EXTRACT(YEAR FROM MV.FECHA) = ?3\r\n" + 
			"										           AND EXTRACT(MONTH FROM MV.FECHA) = ?4\r\n" + 
			"										         GROUP BY L.ID,L.NOMBRE,MV.AREA,MV.PERIMETRO,MV.PERIODO\r\n" + 
			"										         ORDER BY L.ID)\r\n" + 
			"										 GROUP BY ID,NOMBRE,PERIODO\r\n" + 
			"										 ORDER BY ID")
	List<Valor> findValoresMonitoreo(String esquema,String tabla, Integer anio, Integer mes);
	
}
