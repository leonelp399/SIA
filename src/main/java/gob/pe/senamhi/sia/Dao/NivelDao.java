package gob.pe.senamhi.sia.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gob.pe.senamhi.sia.Beans.Nivel;

@Repository
public interface NivelDao extends JpaRepository<Nivel, String>{
	
	@Query(nativeQuery = true, value = "SELECT L.ID,\r\n" + 
									"         L.NOMBRE LEYENDA_NOMBRE,\r\n" + 
									"         L.PRIORIDAD ,\r\n" + 
									"         LPAD(EXTRACT(MONTH FROM RV.FECHA),2,'0')||'-'||EXTRACT(YEAR FROM RV.FECHA) FECHA,\r\n" + 
									"         LISTAGG(RV.DEP||','||RV.PROV, '#') WITHIN GROUP (ORDER BY RV.DEP,RV.PROV) DEPPROV\r\n" + 
									"    FROM RIESGO_VALORES RV\r\n" + 
									"         INNER JOIN LEYENDA L ON L.ID=RV.LEYENDA_ID\r\n" + 
									"         INNER JOIN (\r\n" + 
									"                  SELECT RV.LEYENDA_ID,\r\n" + 
									"                         MAX(RV.FECHA) FECHA\r\n" + 
									"                    FROM RIESGO_VALORES RV\r\n" + 
									"                         INNER JOIN LEYENDA L ON L.ID=RV.LEYENDA_ID\r\n" + 
									"                         INNER JOIN PRODUCTO P ON P.ID=L.PRODUCTO_ID\r\n" + 
									"                   WHERE P.ESQUEMA = ?1\r\n" + 
									"                     AND P.TABLA = ?2\r\n" + 
									"                   GROUP BY RV.LEYENDA_ID       \r\n" + 
									"                    )LE ON LE.LEYENDA_ID = RV.LEYENDA_ID\r\n" + 
									"    WHERE EXTRACT(MONTH FROM RV.FECHA) = EXTRACT(MONTH FROM LE.FECHA)\r\n" + 
									"      AND EXTRACT(YEAR FROM RV.FECHA) = EXTRACT(YEAR FROM LE.FECHA)\r\n" + 
									"    GROUP BY L.ID,L.NOMBRE,L.PRIORIDAD,LPAD(EXTRACT(MONTH FROM RV.FECHA),2,'0')||'-'||EXTRACT(YEAR FROM RV.FECHA)\r\n" + 
									"    ORDER BY L.PRIORIDAD DESC")
	List<Nivel> findUbigeoByRiesgo(String esquema, String tabla);
	
	@Query(nativeQuery = true, value = "  SELECT L.ID,\r\n" + 
										"         L.NOMBRE LEYENDA_NOMBRE,\r\n" + 
										"         L.PRIORIDAD ,\r\n" + 
										"         LPAD(EXTRACT(MONTH FROM MV.FECHA),2,'0')||'-'||EXTRACT(YEAR FROM MV.FECHA) FECHA,\r\n" + 
										"         LISTAGG(MV.DEP||','||MV.PROV, '#') WITHIN GROUP (ORDER BY MV.DEP,MV.PROV) DEPPROV\r\n" + 
										"    FROM MONITOREO_VALORES MV\r\n" + 
										"         INNER JOIN LEYENDA L ON L.ID=MV.LEYENDA_ID\r\n" + 
										"         INNER JOIN (\r\n" + 
										"                  SELECT MV.LEYENDA_ID,\r\n" + 
										"                         MAX(MV.FECHA) FECHA\r\n" + 
										"                    FROM MONITOREO_VALORES MV\r\n" + 
										"                         INNER JOIN LEYENDA L ON L.ID=MV.LEYENDA_ID\r\n" + 
										"                         INNER JOIN PRODUCTO P ON P.ID=L.PRODUCTO_ID\r\n" + 
										"                   WHERE P.ESQUEMA = ?1\r\n" + 
										"                     AND P.TABLA = ?2\r\n" + 
										"                   GROUP BY MV.LEYENDA_ID       \r\n" + 
										"                    )LE ON LE.LEYENDA_ID = MV.LEYENDA_ID\r\n" + 
										"    WHERE EXTRACT(MONTH FROM MV.FECHA) = EXTRACT(MONTH FROM LE.FECHA)\r\n" + 
										"      AND EXTRACT(YEAR FROM MV.FECHA) = EXTRACT(YEAR FROM LE.FECHA)\r\n" + 
										"    GROUP BY L.ID,L.NOMBRE,L.PRIORIDAD,LPAD(EXTRACT(MONTH FROM MV.FECHA),2,'0')||'-'||EXTRACT(YEAR FROM MV.FECHA)\r\n" + 
										"    ORDER BY L.PRIORIDAD DESC")
	List<Nivel> findUbigeoByMonitoreo(String esquema, String tabla);
	
	@Query(nativeQuery = true, value = "SELECT L.ID,\r\n" + 
										"         L.NOMBRE LEYENDA_NOMBRE,\r\n" + 
										"         L.PRIORIDAD ,\r\n" + 
										"         LPAD(EXTRACT(MONTH FROM PV.FECHA),2,'0')||'-'||EXTRACT(YEAR FROM PV.FECHA) FECHA,\r\n" + 
										"         LISTAGG(PV.DEP||','||PV.PROV, '#') WITHIN GROUP (ORDER BY PV.DEP,PV.PROV) DEPPROV\r\n" + 
										"    FROM PRONOSTICO_VALORES PV\r\n" + 
										"         INNER JOIN LEYENDA L ON L.ID=PV.LEYENDA_ID\r\n" + 
										"         INNER JOIN (\r\n" + 
										"                  SELECT PV.LEYENDA_ID,\r\n" + 
										"                         MAX(PV.FECHA) FECHA\r\n" + 
										"                    FROM PRONOSTICO_VALORES PV\r\n" + 
										"                         INNER JOIN LEYENDA L ON L.ID=PV.LEYENDA_ID\r\n" + 
										"                         INNER JOIN PRODUCTO P ON P.ID=L.PRODUCTO_ID\r\n" + 
										"                   WHERE P.ESQUEMA = ?1\r\n" + 
										"                     AND P.TABLA = ?2\r\n" + 
										"                   GROUP BY PV.LEYENDA_ID       \r\n" + 
										"                    )LE ON LE.LEYENDA_ID = PV.LEYENDA_ID\r\n" + 
										"    WHERE EXTRACT(MONTH FROM PV.FECHA) = EXTRACT(MONTH FROM LE.FECHA)\r\n" + 
										"      AND EXTRACT(YEAR FROM PV.FECHA) = EXTRACT(YEAR FROM LE.FECHA)\r\n" + 
										"    GROUP BY L.ID,L.NOMBRE,L.PRIORIDAD,LPAD(EXTRACT(MONTH FROM PV.FECHA),2,'0')||'-'||EXTRACT(YEAR FROM PV.FECHA)\r\n" + 
										"    ORDER BY L.PRIORIDAD DESC")
	List<Nivel> findUbigeoByPronostico(String esquema, String tabla);
	
	@Query(nativeQuery = true, value = "SELECT L.ID,\r\n" + 
										"       L.NOMBRE LEYENDA_NOMBRE,\r\n" + 
										"       L.PRIORIDAD,\r\n" + 
										"       '' FECHA,\r\n" + 
										"       '' DEPPROV\r\n" + 
										"  FROM LEYENDA L\r\n" + 
										"       INNER JOIN PRODUCTO P ON P.ID=L.PRODUCTO_ID\r\n" + 
										" WHERE P.ESQUEMA = ?1 \r\n" + 
										"   AND P.TABLA = ?2\r\n" + 
										" ORDER BY L.PRIORIDAD")
	List<Nivel> findLeyendaByProducto(String esquema, String tabla);
	
}
