package com.np.teva.persistence.mybatis.store;

import com.np.teva.core.bean.PermisoTransitoBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermisoTransitoStore {
    @Insert("INSERT INTO validacion.t_permiso_transito " +
            "(cod_transito, cod_permiso, cod_tipo_permiso, cod_condicion_autorizadora, cod_condicion_subautorizadora ) " +
            "VALUES( #{idTransito, jdbcType=VARCHAR, typeHandler=com.np.teva.persistence.mybatis.typehandler.UUIDTypeHandler}::uuid, " +
            "#{permiso}, #{tipoPermiso}, #{condicionAutorizadora}, #{condicionSubautorizadora}) " +
            "ON CONFLICT (cod_transito, cod_permiso) DO UPDATE SET " +
            "cod_tipo_permiso = #{tipoPermiso}, cod_condicion_autorizadora = #{condicionAutorizadora}")
    void createPermisoTransito(PermisoTransitoBean permisoTransitoBean);
}
