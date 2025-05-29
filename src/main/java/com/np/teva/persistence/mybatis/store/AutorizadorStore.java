package com.np.teva.persistence.mybatis.store;

import com.np.teva.core.bean.AutorizadorBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface AutorizadorStore {

    @Select({"select a.cod_autorizador, a.cod_representante, a.fec_alta, a.fec_caducidad " +
            "from validacion.ft_persona p " +
            "inner join validacion.ft_autorizador a on a.cod_representante = p.cod_persona " +
            "where p.txt_documento = documento " +
            "and a.cod_condicion_aut = 3 " +
            "and a.fec_alta <= fecha::date " +
            "and (a.fec_caducidad <= fecha::date or a.fec_caducidad is null) " +
            "order by a.fec_alta"})
    AutorizadorBean findAutorizadorEmpadronado(@Param("documento") String documento, @Param("fecha") Timestamp fechaTransito);
}
