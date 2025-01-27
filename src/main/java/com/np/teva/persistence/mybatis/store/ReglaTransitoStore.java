package com.np.teva.persistence.mybatis.store;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.UUID;

@Mapper
public interface ReglaTransitoStore {
    @Insert("INSERT INTO validacion.t_regla_transito " +
            "(cod_transito, cod_regla, ind_resultado) " +
            "VALUES( #{idTransito, jdbcType=VARCHAR, typeHandler=com.np.teva.persistence.mybatis.typehandler.UUIDTypeHandler}::uuid, " +
            "#{reglaId}, #{resultado}) " +
            "ON CONFLICT (cod_transito, cod_regla) " +
            "DO UPDATE SET ind_resultado = #{resultado} ")
    void createReglaTransito(@Param("idTransito") UUID idTransito, @Param("reglaId") int reglaId, @Param("resultado") boolean resultado);

}
