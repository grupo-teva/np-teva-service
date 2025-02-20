package com.np.teva.persistence.mybatis.store;

import com.np.teva.core.bean.EstadoRemesaBean;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface EstadoRemesaStore {

    @Select("select es.cod_zona, es.fec_sancion, es.cod_estado_remesa, es.tms_update, es.cod_usuario " +
            "from validacion.t_estado_remesa es " +
            "where es.cod_zona = #{cod_zona} " +
            "and es.fec_sancion = #{fec_sancion}")
    @Results(value = {
            @Result(property = "codigoZona", column = "cod_zona"),
            @Result(property = "fechaSancion", column = "fec_sancion"),
            @Result(property = "codigoEstadoRemesa", column = "cod_estado_remesa"),
            @Result(property = "fechaActualizacion", column = "tms_update"),
            @Result(property = "codigoUsuario", column = "cod_usuario")
    })
    EstadoRemesaBean findRemesa(@Param("cod_zona") int cod_zona, @Param("fec_sancion") Date fec_sancion);

    @Select("select es.cod_zona, es.fec_sancion, es.cod_estado_remesa, es.tms_update, es.cod_usuario " +
            "from validacion.t_estado_remesa es " +
            "where es.cod_estado_remesa = #{cod_estado} " +
            "and es.cod_zona = #{cod_zona}")
    @Results(value = {
            @Result(property = "codigoZona", column = "cod_zona"),
            @Result(property = "fechaSancion", column = "fec_sancion"),
            @Result(property = "codigoEstadoRemesa", column = "cod_estado_remesa"),
            @Result(property = "fechaActualizacion", column = "tms_update"),
            @Result(property = "codigoUsuario", column = "cod_usuario")
    })
    List<EstadoRemesaBean> findRemesasByEstado(@Param("cod_estado") int cod_estado, @Param("cod_zona") int cod_zona);

    @Select("select es.cod_zona, es.fec_sancion, es.cod_estado_remesa, es.tms_update, es.cod_usuario " +
            "from validacion.t_estado_remesa es " +
            "where es.cod_estado_remesa = #{cod_estado} " +
            "and es.cod_zona = #{cod_zona} " +
            "and es.fec_sancion = #{fec_sancion}")
    @Results(value = {
            @Result(property = "codigoZona", column = "cod_zona"),
            @Result(property = "fechaSancion", column = "fec_sancion"),
            @Result(property = "codigoEstadoRemesa", column = "cod_estado_remesa"),
            @Result(property = "fechaActualizacion", column = "tms_update"),
            @Result(property = "codigoUsuario", column = "cod_usuario")
    })
    List<EstadoRemesaBean> findRemesasByEstadoFecha(@Param("cod_estado") int cod_estado, @Param("cod_zona") int cod_zona, @Param("fec_sancion") Date fec_sancion);

    @Insert("INSERT into validacion.t_estado_remesa(cod_zona, fec_sancion, cod_estado_remesa, tms_update, cod_usuario) " +
            "VALUES (#{codigoZona}, #{fechaSancion}, #{codigoEstadoRemesa}, #{fechaActualizacion}, #{codigoUsuario})")
    void insertEstadoRemesa(EstadoRemesaBean estadoRemesa);

    @Update("UPDATE validacion.t_estado_remesa SET " +
            "cod_estado_remesa = #{cod_estado_remesa} " +
            "WHERE cod_zona = #{cod_zona} and fec_sancion = #{fec_sancion}")
    void updateEstadoRemesa(@Param("fec_sancion") Date fec_sancion,@Param("cod_zona") int cod_zona,@Param("cod_estado_remesa") int cod_estado_remesa);
}
