package com.np.teva.persistence.mybatis.store;

import com.np.teva.core.bean.ReglaNegocioBean;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ReglaNegocioStore {
    @Select({"Select cod_regla from validacion.tt_regla_negocio where txt_descripcion = #{reglaNegocio} limit 1"})
    Integer getReglaNegocioId(@Param("reglaNegocio") String reglaNegocio);

    @Insert({"insert into validacion.tt_regla_negocio (cod_regla, txt_descripcion) values (#{reglaId}, #{descripcion})"})
    @SelectKey(before = true, keyColumn = "cod_regla", keyProperty = "reglaId", resultType = Integer.class, statement = "select nextval('validacion.tt_regla_negocio_cod_regla_seq')" )
    void insertReglaNegocio(ReglaNegocioBean reglaNegocioBean);

}
