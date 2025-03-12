package com.np.teva.persistence.mybatis.store;

import com.np.teva.core.bean.TransitoBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Mapper
public interface TransitoStore {

    @Select({"SELECT t.cod_transito, t.cod_captura, t.tms_transito, t.cod_pdc, t.txt_matricula, t.txt_matricula_leida, t.cod_tipo_transito, " +
            "t.cod_estado_importacion, t.ind_entrada, t.txt_ruta_imagenes, t.ind_pte_importer " +
            "from validacion.t_transito t " +
            "inner join validacion.t_sancion s on s.cod_transito = t.cod_transito " +
            "inner join validacion.t_estado_remesa es on es.fec_sancion = s.fec_sancion and es.cod_zona = #{cod_zona} " +
            "where t.txt_matricula != t.txt_matricula_leida " +
            "and s.cod_estado_sancion in (5) " +
            "and es.fec_sancion = #{fec_sancion} " +
            "and es.cod_zona = #{cod_zona} " +
            "order by s.fec_sancion;"})
    @Results(value = {
            @Result(property = "id", column = "cod_transito", javaType = UUID.class, jdbcType = JdbcType.OTHER, typeHandler = com.np.teva.persistence.mybatis.typehandler.UUIDTypeHandler.class),
            @Result(property = "idCaptura", column = "cod_captura", javaType = UUID.class, jdbcType = JdbcType.OTHER, typeHandler = com.np.teva.persistence.mybatis.typehandler.UUIDTypeHandler.class),
            @Result(property = "tmsTransito", column = "tms_transito"),
            @Result(property = "pdc", column = "cod_pdc"),
            @Result(property = "plate", column = "txt_matricula"),
            @Result(property = "readPlate", column = "txt_matricula_leida"),
            @Result(property = "transitoType", column = "cod_tipo_transito"),
            @Result(property = "estadoImportacion", column = "cod_estado_importacion"),
            @Result(property = "Into", column = "ind_entrada"),
            @Result(property = "imagesPath", column = "txt_ruta_imagenes"),
            @Result(property = "pteImporter", column = "ind_pte_importer")
    })
    List<TransitoBean> findTransitosPendientesValidacion(@Param("fec_sancion") Date fec_sancion, @Param("cod_zona") int cod_zona);

    @Select({"SELECT t.cod_transito, t.cod_captura, t.tms_transito, t.cod_pdc, t.txt_matricula, t.txt_matricula_leida, t.cod_tipo_transito, " +
            "t.cod_estado_importacion, t.ind_entrada, t.txt_ruta_imagenes, t.ind_pte_importer " +
            "from validacion.t_transito t " +
            "inner join validacion.t_sancion s on s.cod_transito = t.cod_transito " +
            "inner join validacion.t_estado_remesa es on es.fec_sancion = s.fec_sancion and es.cod_zona = #{cod_zona} " +
            "where t.txt_matricula != t.txt_matricula_leida " +
            "and s.cod_estado_sancion in (5, 12, 17) " +
            "and es.fec_sancion = #{fec_sancion} " +
            "and es.cod_zona = #{cod_zona} " +
            "order by s.fec_sancion;"})
    @Results(value = {
            @Result(property = "id", column = "cod_transito", javaType = UUID.class, jdbcType = JdbcType.OTHER, typeHandler = com.np.teva.persistence.mybatis.typehandler.UUIDTypeHandler.class),
            @Result(property = "idCaptura", column = "cod_captura", javaType = UUID.class, jdbcType = JdbcType.OTHER, typeHandler = com.np.teva.persistence.mybatis.typehandler.UUIDTypeHandler.class),
            @Result(property = "tmsTransito", column = "tms_transito"),
            @Result(property = "pdc", column = "cod_pdc"),
            @Result(property = "plate", column = "txt_matricula"),
            @Result(property = "readPlate", column = "txt_matricula_leida"),
            @Result(property = "transitoType", column = "cod_tipo_transito"),
            @Result(property = "estadoImportacion", column = "cod_estado_importacion"),
            @Result(property = "Into", column = "ind_entrada"),
            @Result(property = "imagesPath", column = "txt_ruta_imagenes"),
            @Result(property = "pteImporter", column = "ind_pte_importer")
    })
    List<TransitoBean> findTransitosPendientesQC(@Param("fec_sancion") Date fec_sancion,@Param("cod_zona") int cod_zona);

    @Select({"(SELECT cod_transito AS id, tms_transito AS tmsTransito, cod_pdc AS pdc, txt_matricula AS plate, " +
            "cod_captura AS idCaptura, cod_tipo_transito AS transitoType, cod_estado_importacion AS estadoImportacion, " +
            "ind_pte_importer AS pteImporter, txt_matricula_leida AS readPlate, txt_ruta_imagenes AS imagesPath " +
            "FROM validacion.t_transito " +
            "WHERE tms_transito > ( SELECT validacion.fcn_immutable_ultima_remesa()) " +
            "AND cod_tipo_transito = 1 " +
            "AND cod_estado_importacion = 3 " +
            "AND ind_pte_importer IS TRUE " +
            "ORDER BY tms_transito " +
            "LIMIT #{numRows}) " +
            "UNION " +
            "(SELECT cod_transito AS id, tms_transito AS tmsTransito, cod_pdc AS pdc, txt_matricula AS plate, " +
            "cod_captura AS idCaptura, cod_tipo_transito AS transitoType, cod_estado_importacion AS estadoImportacion, " +
            "ind_pte_importer AS pteImporter, txt_matricula_leida AS readPlate, txt_ruta_imagenes AS imagesPath " +
            "FROM validacion.t_transito " +
            "WHERE tms_transito > ( SELECT validacion.fcn_immutable_ultima_remesa()) " +
            "AND cod_tipo_transito = 2 " +
            "AND cod_estado_importacion = 9 " +
            "AND ind_pte_importer IS TRUE " +
            "ORDER BY tms_transito " +
            "LIMIT #{numRows} )"
    })
    @Results(value = {
            @Result(property = "id", column = "id", javaType= UUID.class, jdbcType= JdbcType.OTHER, typeHandler=com.np.teva.persistence.mybatis.typehandler.UUIDTypeHandler.class),
            @Result(property = "idCaptura", column = "idCaptura", javaType= UUID.class, jdbcType= JdbcType.OTHER, typeHandler=com.np.teva.persistence.mybatis.typehandler.UUIDTypeHandler.class)
    })
    List<TransitoBean> findTransitosToCheckImporter(@Param("numRows") int numRows);

    @Select({"SELECT t.cod_transito, t.cod_captura, t.tms_transito, t.cod_pdc, t.txt_matricula, t.txt_matricula_leida, t.cod_tipo_transito, " +
            "t.cod_estado_importacion, t.ind_entrada, t.txt_ruta_imagenes, t.ind_pte_importer " +
            "from validacion.t_transito t " +
            "inner join validacion.t_sancion s on s.cod_transito = t.cod_transito " +
            "inner join validacion.t_punto_captura pc on pc.cod_pdc = t.cod_pdc " +
            "where s.cod_estado_sancion in (0) " +
            "and s.fec_sancion = #{fechaSancion} " +
            "order by s.fec_sancion;"})
    @Results(value = {
            @Result(property = "id", column = "cod_transito", javaType = UUID.class, jdbcType = JdbcType.OTHER, typeHandler = com.np.teva.persistence.mybatis.typehandler.UUIDTypeHandler.class),
            @Result(property = "idCaptura", column = "cod_captura", javaType = UUID.class, jdbcType = JdbcType.OTHER, typeHandler = com.np.teva.persistence.mybatis.typehandler.UUIDTypeHandler.class),
            @Result(property = "tmsTransito", column = "tms_transito"),
            @Result(property = "pdc", column = "cod_pdc"),
            @Result(property = "plate", column = "txt_matricula"),
            @Result(property = "readPlate", column = "txt_matricula_leida"),
            @Result(property = "transitoType", column = "cod_tipo_transito"),
            @Result(property = "estadoImportacion", column = "cod_estado_importacion"),
            @Result(property = "Into", column = "ind_entrada"),
            @Result(property = "imagesPath", column = "txt_ruta_imagenes"),
            @Result(property = "pteImporter", column = "ind_pte_importer")
    })
    List<TransitoBean> findTransitosReprocesado(@Param("fechaSancion") Date fechaSancion);

    @Select({"SELECT t.cod_transito, t.cod_captura, t.tms_transito, t.cod_pdc, t.txt_matricula, t.txt_matricula_leida, t.cod_tipo_transito, " +
            "t.cod_estado_importacion, t.ind_entrada, t.txt_ruta_imagenes, t.ind_pte_importer " +
            "from validacion.t_transito t " +
            "inner join validacion.t_sancion s on s.cod_transito = t.cod_transito " +
            "inner join validacion.t_punto_captura pc on pc.cod_pdc = t.cod_pdc " +
            "where s.cod_estado_sancion in (5, 12, 17) " +
            "and s.fec_sancion = #{fechaSancion} " +
            "and pc.cod_zona = #{codigoZona} " +
            "order by s.fec_sancion;"})
    @Results(value = {
            @Result(property = "id", column = "cod_transito", javaType = UUID.class, jdbcType = JdbcType.OTHER, typeHandler = com.np.teva.persistence.mybatis.typehandler.UUIDTypeHandler.class),
            @Result(property = "idCaptura", column = "cod_captura", javaType = UUID.class, jdbcType = JdbcType.OTHER, typeHandler = com.np.teva.persistence.mybatis.typehandler.UUIDTypeHandler.class),
            @Result(property = "tmsTransito", column = "tms_transito"),
            @Result(property = "pdc", column = "cod_pdc"),
            @Result(property = "plate", column = "txt_matricula"),
            @Result(property = "readPlate", column = "txt_matricula_leida"),
            @Result(property = "transitoType", column = "cod_tipo_transito"),
            @Result(property = "estadoImportacion", column = "cod_estado_importacion"),
            @Result(property = "Into", column = "ind_entrada"),
            @Result(property = "imagesPath", column = "txt_ruta_imagenes"),
            @Result(property = "pteImporter", column = "ind_pte_importer")
    })
    List<TransitoBean> findTransitosReprocesadoRemesadas(@Param("fechaSancion") Date fechaSancion, @Param("codigoZona") int codigoZona);

    @Select({"SELECT t.cod_transito, t.cod_captura, t.tms_transito, t.cod_pdc, t.txt_matricula, t.txt_matricula_leida, t.cod_tipo_transito, " +
            "t.cod_estado_importacion, t.ind_entrada, t.txt_ruta_imagenes, t.ind_pte_importer " +
            "from validacion.t_transito t " +
            "inner join validacion.t_sancion s on s.cod_transito = t.cod_transito " +
            "inner join validacion.t_punto_captura pc on pc.cod_pdc = t.cod_pdc " +
            "where s.cod_estado_sancion in (0, 5, 12) " +
            "and s.fec_sancion = #{fechaSancion} " +
            "and pc.cod_zona = #{codigoZona} " +
            "order by s.fec_sancion;"})
    @Results(value = {
            @Result(property = "id", column = "cod_transito", javaType = UUID.class, jdbcType = JdbcType.OTHER, typeHandler = com.np.teva.persistence.mybatis.typehandler.UUIDTypeHandler.class),
            @Result(property = "idCaptura", column = "cod_captura", javaType = UUID.class, jdbcType = JdbcType.OTHER, typeHandler = com.np.teva.persistence.mybatis.typehandler.UUIDTypeHandler.class),
            @Result(property = "tmsTransito", column = "tms_transito"),
            @Result(property = "pdc", column = "cod_pdc"),
            @Result(property = "plate", column = "txt_matricula"),
            @Result(property = "readPlate", column = "txt_matricula_leida"),
            @Result(property = "transitoType", column = "cod_tipo_transito"),
            @Result(property = "estadoImportacion", column = "cod_estado_importacion"),
            @Result(property = "Into", column = "ind_entrada"),
            @Result(property = "imagesPath", column = "txt_ruta_imagenes"),
            @Result(property = "pteImporter", column = "ind_pte_importer")
    })
    List<TransitoBean> findTransitosPendientesRemesar(@Param("fechaSancion") Date fechaSancion, @Param("codigoZona") int codigoZona);


    @Update({"UPDATE validacion.t_transito SET cod_tipo_transito = #{tipoTransito}, "
            + "cod_estado_importacion = #{importStateType}, "
            + "ind_pte_importer = #{pteImporter}, "
            + "cod_sistema = #{codSistema} "
            + "WHERE cod_transito =  #{idTransito}::uuid"})
    int updateTransito(@Param("idTransito") UUID idTransito, @Param("tipoTransito") int tipoTransito, @Param("importStateType") int importStateType ,@Param("pteImporter") boolean pteImporter, @Param("codSistema") int codSistema);
}
