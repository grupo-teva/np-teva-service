package com.np.teva.persistence.mybatis.store;

import com.np.teva.core.bean.ReglaAccesoBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReglaAccesoStore {
    //TODO: crear tabla para reglas y recogerlas con mybatis
    @Select({"SELECT des_regla_acceso AS desRegla, " +
            "replace(replace(cod_distintivo_ambiental::varchar, '{', ''), '}', '') AS distintivo, " +
            "replace(replace(cod_tipo_industria::varchar, '{', ''), '}', '') AS tipoVehiculo, " +
            "replace(replace(cod_subconds_auts::varchar, '{', ''), '}', '') AS subcondicion, " +
            "case when ind_comprueba_permiso is false then null else ind_comprueba_permiso end AS indTienePermiso, " +
            "case when ind_comprueba_domiciliacion is false then null else ind_comprueba_domiciliacion end AS indDomiciliacion, " +
            "fec_inicio AS fecIni, " +
            "fec_fin AS fecFin, " +
            "hora_inicio AS horaInicio, " +
            "hora_fin AS horaFin, " +
            "replace(replace(dias_aplicacion::varchar, '{', ''), '}', '') AS dayOfWeek, " +
            "replace(replace(cod_construccion::varchar, '{', ''), '}', '') AS tipoConstruccion, " +
            "replace(replace(cod_uso::varchar, '{', ''), '}', '') AS tipoUso, " +
            "ind_comprueba_domiciliacion AS indDomiciliacion " +
            "FROM validacion.ft_rn_restriccion_permiso_acceso "})
    List<ReglaAccesoBean> findReglas();

    //TODO: verificar y adjudicar valor a cod_zona
    @Select({"SELECT des_regla_acceso AS desRegla, " +
            "replace(replace(cod_distintivo_ambiental::varchar, '{', ''), '}', '') AS distintivo, " +
            "replace(replace(cod_tipo_industria::varchar, '{', ''), '}', '') AS tipoVehiculo, " +
            "replace(replace(cod_subconds_auts::varchar, '{', ''), '}', '') AS subcondicion, " +
            "case when ind_comprueba_permiso is false then null else ind_comprueba_permiso end AS indTienePermiso, " +
            "case when ind_comprueba_domiciliacion is false then null else ind_comprueba_domiciliacion end AS indDomiciliacion, " +
            "fec_inicio AS fecIni, " +
            "fec_fin AS fecFin, " +
            "hora_inicio AS horaInicio, " +
            "hora_fin AS horaFin, " +
            "replace(replace(dias_aplicacion::varchar, '{', ''), '}', '') AS dayOfWeek, " +
            "replace(replace(cod_construccion::varchar, '{', ''), '}', '') AS tipoConstruccion, " +
            "replace(replace(cod_uso::varchar, '{', ''), '}', '') AS tipoUso, " +
            "'PROHIBIDO' AS estadoPerm " +
            "FROM validacion.ft_rn_prohibicion_permiso_acceso " +
            "WHERE cod_zona = 2"})
    List<ReglaAccesoBean> findReglasCascoHistorico();

    //TODO: verificar y adjudicar valor a cod_zona
    @Select({"SELECT des_regla_acceso AS desRegla, " +
            "replace(replace(cod_distintivo_ambiental::varchar, '{', ''), '}', '') AS distintivo, " +
            "replace(replace(cod_tipo_industria::varchar, '{', ''), '}', '') AS tipoVehiculo, " +
            "replace(replace(cod_subconds_auts::varchar, '{', ''), '}', '') AS subcondicion, " +
            "case when ind_comprueba_permiso is false then null else ind_comprueba_permiso end AS indTienePermiso, " +
            "case when ind_comprueba_domiciliacion is false then null else ind_comprueba_domiciliacion end AS indDomiciliacion, " +
            "fec_inicio AS fecIni, " +
            "fec_fin AS fecFin, " +
            "hora_inicio AS horaInicio, " +
            "hora_fin AS horaFin, " +
            "replace(replace(dias_aplicacion::varchar, '{', ''), '}', '') AS dayOfWeek, " +
            "replace(replace(cod_construccion::varchar, '{', ''), '}', '') AS tipoConstruccion, " +
            "replace(replace(cod_uso::varchar, '{', ''), '}', '') AS tipoUso, " +
            "'PROHIBIDO' AS estadoPerm " +
            "FROM validacion.ft_rn_prohibicion_permiso_acceso " +
            "WHERE cod_zona = 1"})
    List<ReglaAccesoBean> findReglasZBE();

    @Select({"SELECT des_regla_acceso AS desRegla, " +
            "replace(replace(cod_distintivo_ambiental::varchar, '{', ''), '}', '') AS distintivo, " +
            "replace(replace(cod_tipo_industria::varchar, '{', ''), '}', '') AS tipoVehiculo, " +
            "replace(replace(cod_subconds_auts::varchar, '{', ''), '}', '') AS subcondicion, " +
            "case when ind_comprueba_permiso is false then null else ind_comprueba_permiso end AS indTienePermiso, " +
            "case when ind_comprueba_domiciliacion is false then null else ind_comprueba_domiciliacion end AS indDomiciliacion, " +
            "case when ind_historico is false then null else ind_historico end AS indHistorico, " +
            "fec_inicio AS fecIni, " +
            "fec_fin AS fecFin, " +
            "hora_inicio AS horaInicio, " +
            "hora_fin AS horaFin, " +
            "replace(replace(dias_aplicacion::varchar, '{', ''), '}', '') AS dayOfWeek, " +
            "replace(replace(cod_construccion::varchar, '{', ''), '}', '') AS tipoConstruccion, " +
            "replace(replace(cod_uso::varchar, '{', ''), '}', '') AS tipoUso, " +
            "replace(replace(cod_tipo_servicio::varchar, '{', ''), '}', '') AS tipoServicio, " +
            "'PERMITIDO' AS estadoPerm " +
            "FROM validacion.ft_rn_excepcion_permiso_acceso"})
    List<ReglaAccesoBean> findReglasExcepcion();

    @Select({"SELECT des_regla_acceso AS desRegla, " +
            "replace(replace(cod_distintivo_ambiental::varchar, '{', ''), '}', '') AS distintivo, " +
            "replace(replace(cod_tipo_industria::varchar, '{', ''), '}', '') AS tipoVehiculo, " +
            "replace(replace(cod_subconds_auts::varchar, '{', ''), '}', '') AS subcondicion, " +
            "case when ind_comprueba_permiso is false then null else ind_comprueba_permiso end AS indTienePermiso, " +
            "case when ind_comprueba_domiciliacion is false then null else ind_comprueba_domiciliacion end AS indDomiciliacion, " +
            "case when ind_historico is false then null else ind_historico end AS indHistorico, " +
            "fec_inicio AS fecIni, " +
            "fec_fin AS fecFin, " +
            "hora_inicio AS horaInicio, " +
            "hora_fin AS horaFin, " +
            "replace(replace(dias_aplicacion::varchar, '{', ''), '}', '') AS dayOfWeek, " +
            "replace(replace(cod_construccion::varchar, '{', ''), '}', '') AS tipoConstruccion, " +
            "replace(replace(cod_uso::varchar, '{', ''), '}', '') AS tipoUso, " +
            "replace(replace(cod_tipo_servicio::varchar, '{', ''), '}', '') AS tipoServicio, " +
            "'PROHIBIDO' AS estadoPerm " +
            "FROM validacion.ft_rn_prohibicion_permiso_acceso "})
    List<ReglaAccesoBean> findReglasProhibicion();
}
