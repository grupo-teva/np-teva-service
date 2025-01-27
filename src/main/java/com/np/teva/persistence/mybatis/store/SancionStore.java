package com.np.teva.persistence.mybatis.store;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.UUID;

@Mapper
public interface SancionStore {

    @Select("SELECT COUNT(*) " +
            "FROM validacion.t_sancion " +
            "WHERE fec_sancion = #{fec_sancion} " +
            "and cod_estado_sancion = #{cod_estado_sancion}")
    int contarSancionesEstado(@Param("fec_sancion") Date fec_sancion, @Param("cod_estado_sancion") int cod_estado_sancion);

    @Select("SELECT COUNT(*) " +
            "FROM validacion.v_sancion_nueva_validador_v2 " +
            "WHERE fec_sancion = #{fec_sancion}")
    int contarSancionesPendientesValidar(@Param("fec_sancion") Date fec_sancion);

    @Select("select count(*) " +
            "from validacion.t_control_calidad_sancion cc " +
            "inner join validacion.t_sancion s on s.cod_sancion = cc.cod_sancion " +
            "inner join validacion.t_transito t on t.cod_transito = s.cod_transito " +
            "inner join validacion.t_punto_captura pc on pc.cod_pdc = t.cod_pdc " +
            "where cc.fec_sancion = #{fec_sancion} " +
            "and pc.cod_zona = #{cod_zona} " +
            "and cc.cod_resultado = 0 ")
    int contarSancionesPendientesQC(@Param("fec_sancion") Date fec_sancion, @Param("cod_zona") int cod_zona);

    @Update("update validacion.t_sancion set " +
            "cod_estado_sancion = #{cod_estado}, " +
            "cod_sistema = #{cod_sistema} " +
            "where cod_transito = #{cod_transito}")
    int updateEstado(@Param("cod_transito") UUID cod_transito, @Param("cod_estado") Integer cod_estado, @Param("cod_sistema") Integer cod_sistema);
}
