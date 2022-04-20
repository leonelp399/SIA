package gob.pe.senamhi.sia.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gob.pe.senamhi.sia.Beans.Mes;

@Repository
public interface MesDao extends JpaRepository<Mes,String>{
	
	
	@Query(nativeQuery = true, value = "SELECT ROWNUM LINEA,\r\n" + 
										"       ANIO,\r\n" + 
										"       '' MES\r\n" + 
										" FROM (\r\n" + 
										"     SELECT EXTRACT(YEAR FROM RV.FECHA) ANIO\r\n" + 
										"       FROM RIESGO_VALORES RV\r\n" + 
										"            INNER JOIN LEYENDA L ON L.ID=RV.LEYENDA_ID\r\n" + 
										"            INNER JOIN PRODUCTO P ON P.ID=L.PRODUCTO_ID\r\n" + 
										"      WHERE P.ESQUEMA = ?1\r\n" + 
										"        AND P.TABLA = ?2\r\n" + 
										"      GROUP BY EXTRACT(YEAR FROM RV.FECHA)\r\n" + 
										"      )")
	List<Mes> findYearsRiesgo(String esquema, String tabla);
	
	@Query(nativeQuery = true, value = "SELECT ROWNUM LINEA,\r\n" + 
										"       '' ANIO,\r\n" + 
										"       LPAD(MES,2,'0') MES\r\n" + 
										"  FROM (\r\n" + 
										"    SELECT EXTRACT(MONTH FROM RV.FECHA) MES\r\n" + 
										"       FROM RIESGO_VALORES RV\r\n" + 
										"            INNER JOIN LEYENDA L ON L.ID=RV.LEYENDA_ID\r\n" + 
										"            INNER JOIN PRODUCTO P ON P.ID=L.PRODUCTO_ID\r\n" + 
										"      WHERE P.ESQUEMA = ?1\r\n" + 
										"        AND P.TABLA = ?2\r\n" + 
										"        AND EXTRACT(YEAR FROM RV.FECHA) = ?3\r\n" + 
										"      GROUP BY EXTRACT(MONTH FROM RV.FECHA)\r\n" + 
										"     )")
	List<Mes> findMonthsRiesgo(String esquema, String tabla, Integer anio);
	
	@Query(nativeQuery = true, value = "SELECT ROWNUM LINEA,\r\n" + 
										"       ANIO,\r\n" + 
										"       '' MES\r\n" + 
										" FROM (\r\n" + 
										"     SELECT EXTRACT(YEAR FROM MV.FECHA) ANIO\r\n" + 
										"       FROM PRONOSTICO_VALORES MV\r\n" + 
										"            INNER JOIN LEYENDA L ON L.ID=MV.LEYENDA_ID\r\n" + 
										"            INNER JOIN PRODUCTO P ON P.ID=L.PRODUCTO_ID\r\n" + 
										"      WHERE P.ESQUEMA = ?1\r\n" + 
										"        AND P.TABLA = ?2\r\n" + 
										"      GROUP BY EXTRACT(YEAR FROM MV.FECHA)\r\n" + 
										"      )")
	List<Mes> findYearsPronostico(String esquema, String tabla);
	
	@Query(nativeQuery = true, value = "SELECT ROWNUM LINEA,\r\n" + 
										"       '' ANIO,\r\n" + 
										"       LPAD(MES,2,'0') MES\r\n" + 
										"  FROM (\r\n" + 
										"    SELECT EXTRACT(MONTH FROM MV.FECHA) MES\r\n" + 
										"       FROM PRONOSTICO_VALORES MV\r\n" + 
										"            INNER JOIN LEYENDA L ON L.ID=MV.LEYENDA_ID\r\n" + 
										"            INNER JOIN PRODUCTO P ON P.ID=L.PRODUCTO_ID\r\n" + 
										"      WHERE P.ESQUEMA = ?1\r\n" + 
										"        AND P.TABLA = ?2\r\n" + 
										"        AND EXTRACT(YEAR FROM MV.FECHA) = ?3\r\n" + 
										"      GROUP BY EXTRACT(MONTH FROM MV.FECHA)\r\n" + 
										"     )")
	List<Mes> findMonthsPronostico(String esquema, String tabla, Integer anio);
	
	
	@Query(nativeQuery = true, value = "SELECT ROWNUM LINEA,\r\n" + 
										"       ANIO,\r\n" + 
										"       '' MES\r\n" + 
										" FROM (\r\n" + 
										"     SELECT EXTRACT(YEAR FROM PV.FECHA) ANIO\r\n" + 
										"       FROM MONITOREO_VALORES PV\r\n" + 
										"            INNER JOIN LEYENDA L ON L.ID=PV.LEYENDA_ID\r\n" + 
										"            INNER JOIN PRODUCTO P ON P.ID=L.PRODUCTO_ID\r\n" + 
										"      WHERE P.ESQUEMA = ?1\r\n" + 
										"        AND P.TABLA = ?2\r\n" + 
										"      GROUP BY EXTRACT(YEAR FROM PV.FECHA)\r\n" + 
										"      )")
	List<Mes> findYearsMonitoreo(String esquema, String tabla);
	
	@Query(nativeQuery = true, value = "SELECT ROWNUM LINEA,\r\n" + 
										"       '' ANIO,\r\n" + 
										"       LPAD(MES,2,'0') MES\r\n" + 
										"  FROM (\r\n" + 
										"    SELECT EXTRACT(MONTH FROM PV.FECHA) MES\r\n" + 
										"       FROM MONITOREO_VALORES PV\r\n" + 
										"            INNER JOIN LEYENDA L ON L.ID=PV.LEYENDA_ID\r\n" + 
										"            INNER JOIN PRODUCTO P ON P.ID=L.PRODUCTO_ID\r\n" + 
										"      WHERE P.ESQUEMA = ?1\r\n" + 
										"        AND P.TABLA = ?2\r\n" + 
										"        AND EXTRACT(YEAR FROM PV.FECHA) = ?3\r\n" + 
										"      GROUP BY EXTRACT(MONTH FROM PV.FECHA)\r\n" + 
										"     )")
	List<Mes> findMonthsMonitoreo(String esquema, String tabla, Integer anio);
}
