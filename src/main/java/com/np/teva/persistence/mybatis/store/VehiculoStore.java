package com.np.teva.persistence.mybatis.store;

import com.np.teva.core.bean.VehiculoBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VehiculoStore {

    @Select({"SELECT txt_matricula matricula, fec_matriculacion as fecMatriculacion, txt_provincia provincia, txt_municipio municipio, " +
            "txt_codigo_postal codigoPostal, txt_letra_distintivo letraDistintivo, " +
            "case " +
            "when txt_letra_distintivo = 'S/D' then 6 " +
            "when txt_letra_distintivo = 'B' then 5 " +
            "when txt_letra_distintivo = 'C' then 3 " +
            "when txt_letra_distintivo = '0' then 8 " +
            "when txt_letra_distintivo = 'E' then 9 " +
            "else 7 " +
            "end distintivoAmbiental, txt_codigo_industria tipoIndustria, ind_historica historico, fec_alta creacion, " +
            "txt_codigo_servicio tipoServicio, txt_categoria_europea categoriaEuropea " +
            "from validacion.ft_datos_vehiculo " +
            "WHERE txt_matricula = #{matricula}"})
    VehiculoBean findVehiculo(@Param("matricula") String matricula);

    @Select({"select txt_documento " +
            "from ft_titular_vehiculo " +
            "where txt_matricula = #{matricula} " +
            "and fec_baja_titularidad is null"})
    List<String> findTitulares(@Param("matricula") String matricula);
}
