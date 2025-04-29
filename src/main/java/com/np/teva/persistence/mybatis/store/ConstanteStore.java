package com.np.teva.persistence.mybatis.store;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ConstanteStore {
    @Select("select des_valor_constante::int from validacion.t_constante where des_cod_constante = #{nombre};")
    int getConstanteAsInt(@Param("nombre") String nombre);

    @Select("select des_valor_constante from validacion.t_constante where des_cod_constante = #{nombre};")
    String getConstanteAsString(@Param("nombre") String nombre);

}
