package gob.pe.senamhi.sia.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gob.pe.senamhi.sia.Beans.Producto;

@Repository
public interface ProductoDao extends JpaRepository<Producto, String> {
	
	@Query(nativeQuery = true, value = "SELECT ID,\r\n" + 
										"       NOMBRE,\r\n" + 
										"       ESQUEMA,\r\n" + 
										"       TABLA,\r\n" + 
										"       ENLACE_FICHA\r\n" + 
										"  FROM PRODUCTO\r\n" + 
										" WHERE ESQUEMA = ?1\r\n" + 
										"   AND TABLA = ?2")
	Producto findById(String esquema, String tabla);
	
	
	@Transactional
    @Modifying
    @Query(value = "{CALL SP_FICHA_INFORMATIVA(:esquema, :tabla, :enlace)}", nativeQuery = true)
    void sp_ficha_informativa(
            @Param("esquema") String esquema,
            @Param("tabla") String tabla,
            @Param("enlace") String enlace);
	
}
