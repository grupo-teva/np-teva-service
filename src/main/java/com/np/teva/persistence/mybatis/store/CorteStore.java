package com.np.teva.persistence.mybatis.store;

import com.np.teva.core.bean.CorteBean;
import com.np.teva.core.bean.SancionTransitoBean;
import com.np.teva.core.bean.VehiculoBean;
import com.np.teva.core.exception.AccesoDatosException;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Mapper
public interface CorteStore {

    @Select("select id_corte as id, fec_ini as tmsInicio, fec_fin as tmsFin "
            + " from t_corte where fec_ini <= #{fin} "
            + " and fec_fin >= #{ini} ")
    List<CorteBean> getAllCortesByDay(@Param("ini") Timestamp ini, @Param("fin") Timestamp fin);

    @Select("select cod_pdc "
            + "from t_corte_camara where id_corte = #{corte} ")
    List<Integer> getAllPdcsByCorte(@Param("corte") int corte);

    @Select("select ts.cod_sancion, ts.cod_transito " +
            "from t_sancion ts " +
            "inner join t_transito tt on tt.cod_transito = ts.cod_transito " +
            "where ts.fec_sancion = #{fecha} " +
            "and tt.cod_pdc = #{pdc} " +
            "and tt.tms_transito >= #{inicio} " +
            "and tt.tms_transito <= #{fin} " +
            "and ts.cod_estado_sancion in (0, 1, 5, 12) ")
    @Results(value = {
            @Result(property = "idSanction", column = "cod_sancion", javaType = UUID.class, jdbcType = JdbcType.OTHER, typeHandler = com.np.teva.persistence.mybatis.typehandler.UUIDTypeHandler.class),
            @Result(property = "idTransito", column = "cod_transito", javaType = UUID.class, jdbcType = JdbcType.OTHER, typeHandler = com.np.teva.persistence.mybatis.typehandler.UUIDTypeHandler.class)
    })
    List<SancionTransitoBean> getSancionesCorte(@Param("pdc") Integer pdc, @Param("fecha") LocalDate fecha, @Param("inicio") Timestamp inicio, @Param("fin") Timestamp fin);

    @Update("update validacion.t_sancion set " +
            "cod_estado_sancion = #{cod_estado}, " +
            "cod_sistema = #{cod_sistema} " +
            "where cod_transito = #{cod_transito}")
    void updateSancionCorte(@Param("cod_transito") UUID cod_transito, @Param("cod_estado") Integer cod_estado, @Param("cod_sistema") Integer cod_sistema);

    @Update("update validacion.t_transito set " +
            "cod_estado_importacion = #{cod_estado}, " +
            "cod_sistema = #{cod_sistema} " +
            "where cod_transito = #{cod_transito}")
    void updateTransitoCorte(@Param("cod_transito") UUID cod_transito, @Param("cod_estado") Integer cod_estado, @Param("cod_sistema") Integer cod_sistema);

    @Insert("insert into t_corte_transito (cod_transito, cod_corte) values (#{transitoId}::uuid, #{corte})")
    void insertCorteTransito(@Param("transitoId") UUID transitoId, @Param("corte") int corte);

}
