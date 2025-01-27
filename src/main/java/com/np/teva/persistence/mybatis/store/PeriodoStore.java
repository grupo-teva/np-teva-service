package com.np.teva.persistence.mybatis.store;

import com.np.teva.core.bean.PeriodoBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PeriodoStore {

    @Select({"SELECT tp.cod_periodo as codPeriodo, tp.cod_horario as codHorario, tp.dia_inicio as diaInicio, tp.dia_fin as diaFin, " +
            "tp.fec_inicio as fecInicio, tp.fec_fin as fecFin, tp.hora_inicio as horaInicio, tp.hora_fin as horaFin, " +
            "tp.fec_baja as fecBaja, tp.ind_activo as indActivo " +
            "FROM validacion.t_periodo as tp " +
            "inner join validacion.t_horario th on th.cod_horario = tp.cod_horario " +
            "inner join validacion.t_perfil tp2 on tp2.cod_horario = th.cod_horario " +
            "WHERE cod_perfil = #{codPerfil}" })
    List<PeriodoBean> findPeriodoByCodPerfil(@Param("codPerfil") int codPerfil);
}
