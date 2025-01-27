package com.np.teva.persistence.mybatis.store;

import com.np.teva.core.bean.AccesosBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface AccesoPermitidoStore {

    @Select({"SELECT txt_matricula as plate, fec_acceso_permitido as day, "
            + "cod_permiso as permiso, cod_tipo_permiso as tipoPermiso, "
            + "tm_inicio_vigencia as inicioVigencia, "
            + "tm_fin_vigencia as finVigencia, "
            + "cod_condicion_autorizadora as condicionAutorizadora, "
            + "cod_condicion_subautorizadora as condicionSubautorizadora "
            + "FROM validacion.t_acceso_permitido_expli "
            + "WHERE txt_matricula = #{matricula} "
            + "and fec_acceso_permitido = #{dia}"})
    List<AccesosBean> findAccesosByPlate(@Param("dia") LocalDate dia, @Param("matricula") String matricula);
}
