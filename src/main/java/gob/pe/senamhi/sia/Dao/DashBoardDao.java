package gob.pe.senamhi.sia.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gob.pe.senamhi.sia.Beans.DashBoard;

@Repository
public interface DashBoardDao extends JpaRepository<DashBoard,String>{
	
	@Query(nativeQuery = true, value = "SELECT *\r\n" + 
										"  FROM (SELECT L.NOMBRE LEYENDA,\r\n" + 
										"               L.PRIORIDAD,\r\n" + 
										"               EXTRACT(MONTH FROM RV.FECHA) FECHA\r\n" + 
										"          FROM RIESGO_VALORES RV\r\n" + 
										"               INNER JOIN LEYENDA L ON RV.LEYENDA_ID=L.ID\r\n" + 
										"               INNER JOIN PRODUCTO P ON P.ID=L.PRODUCTO_ID\r\n" + 
										"         WHERE P.ESQUEMA = ?1 \r\n" + 
										"           AND P.TABLA= ?2\r\n" + 
										"           AND EXTRACT(YEAR FROM RV.FECHA)=?3\r\n" + 
										"           AND RV.DEP = ?4) \r\n" + 
										"  PIVOT (COUNT(FECHA) FOR FECHA IN (1,2,3,4,5,6,7,8,9,10,11,12)) \r\n" + 
										"  ORDER BY PRIORIDAD")
	List<DashBoard> findRiesgoLeyendaByDep(String esquema, String tabla, Integer anio, String dep);
	
	@Query(nativeQuery = true, value = "SELECT *\r\n" + 
										"  FROM (SELECT L.NOMBRE LEYENDA,\r\n" + 
										"               L.PRIORIDAD,\r\n" + 
										"               EXTRACT(MONTH FROM RV.FECHA) FECHA\r\n" + 
										"          FROM RIESGO_VALORES RV\r\n" + 
										"               INNER JOIN LEYENDA L ON RV.LEYENDA_ID=L.ID\r\n" + 
										"               INNER JOIN PRODUCTO P ON P.ID=L.PRODUCTO_ID\r\n" + 
										"         WHERE P.ESQUEMA = ?1 \r\n" + 
										"           AND P.TABLA= ?2\r\n" + 
										"           AND EXTRACT(YEAR FROM RV.FECHA)=?3\r\n" + 
										"           AND RV.DEP = ?4\r\n" + 
										"           AND RV.PROV = ?5) \r\n" + 
										"  PIVOT (COUNT(FECHA) FOR FECHA IN (1,2,3,4,5,6,7,8,9,10,11,12))  \r\n" + 
										"  ORDER BY PRIORIDAD")
	List<DashBoard> findRiesgoLeyendaByDepProv(String esquema, String tabla, Integer anio, String dep, String prov);
	
	@Query(nativeQuery = true, value = "SELECT *\r\n" + 
										"  FROM (SELECT L.NOMBRE LEYENDA,\r\n" + 
										"               L.PRIORIDAD,\r\n" + 
										"               EXTRACT(MONTH FROM MV.FECHA) FECHA\r\n" + 
										"          FROM MONITOREO_VALORES MV\r\n" + 
										"               INNER JOIN LEYENDA L ON MV.LEYENDA_ID=L.ID\r\n" + 
										"               INNER JOIN PRODUCTO P ON P.ID=L.PRODUCTO_ID\r\n" + 
										"         WHERE P.ESQUEMA = ?1 \r\n" + 
										"           AND P.TABLA= ?2\r\n" + 
										"           AND EXTRACT(YEAR FROM MV.FECHA)=?3\r\n" + 
										"           AND MV.DEP = ?4) \r\n" + 
										"  PIVOT (COUNT(FECHA) FOR FECHA IN (1,2,3,4,5,6,7,8,9,10,11,12))\r\n" + 
										"  ORDER BY PRIORIDAD ")
	List<DashBoard> findMonitoreoLeyendaByDep(String esquema, String tabla, Integer anio, String dep);

}
