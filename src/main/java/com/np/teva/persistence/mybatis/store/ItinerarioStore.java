package com.np.teva.persistence.mybatis.store;

import com.np.teva.core.bean.ItinerarioBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ItinerarioStore {
    @Select({"SELECT tigp.cod_itinerario as codItinerario, tigp.cod_grupo_pdc as codGrupoPDC, tisa.cod_subcond_aut as codSubcondAut, " +
            "tisa.cod_perfil as codPerfil, tisa.cod_tipo_usuario as codTipoUsuario, tigp.fec_baja as fecBaja, tigp.ind_activo as indActivo " +
            "FROM validacion.t_itinerario_grupo_pdc as tigp " +
            "inner join validacion.t_itinerario_subcond_aut tisa on tisa.cod_itinerario = tigp.cod_itinerario " +
            "WHERE cod_grupo_pdc = #{codGrupoPDC}" })
    List<ItinerarioBean> findItinerarioByGrupoPDC(@Param("codGrupoPDC") int codPDC);

    @Select({"SELECT tigp.cod_itinerario as codItinerario, tigp.cod_grupo_pdc as codGrupoPDC, tisa.cod_subcond_aut as codSubcondAut, " +
            "tisa.cod_perfil as codPerfil, tisa.cod_tipo_usuario as codTipoUsuario, tigp.fec_baja as fecBaja, tigp.ind_activo as indActivo " +
            "FROM validacion.t_itinerario_grupo_pdc as tigp " +
            "inner join validacion.t_itinerario_subcond_aut tisa on tisa.cod_itinerario = tigp.cod_itinerario " +
            "WHERE cod_subcond_aut = #{codSubcondAut}" })
    List<ItinerarioBean> findItinerarioBySCA(@Param("codSubcondAut") int codSubcondAut);

    @Select({"SELECT tigp.cod_itinerario as codItinerario, tigp.cod_grupo_pdc as codGrupoPDC, " +
            "tp.cod_perfil as codPerfil, tigp.fec_baja as fecBaja, tigp.ind_activo as indActivo " +
            "FROM validacion.t_perfil tp  " +
            "inner join validacion.t_itinerario_grupo_pdc tigp on tigp.cod_itinerario = tp.cod_itinerario  " +
            "WHERE tp.cod_perfil = #{codPerfil} and tigp.cod_grupo_pdc = #{codGrupoPDC}" })
    List<ItinerarioBean> findItinerarioByPerfilAndPDC(@Param("codPerfil") int codPerfil, @Param("codGrupoPDC") int codGrupoPDC);
}
