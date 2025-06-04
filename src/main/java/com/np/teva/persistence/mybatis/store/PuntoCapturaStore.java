package com.np.teva.persistence.mybatis.store;

import com.np.teva.core.bean.PuntoCapturaBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PuntoCapturaStore {
    @Select({"SELECT cod_pdc as codPDC, tpc.txt_nombre as nombre, tpc.cod_grupo_pdc as codGrupoPDC, ind_entrada as indEntrada, " +
            "num_carril as numCarril, ind_sancionador as indSancionador, cod_barrio as codBarrio, txt_ubicacion as txtUbicacion, " +
            "cod_puerta_ayto as codPuertaAyto, txt_nombre_puerta_ayto as txtNomPuertaAyto, txt_tipo_via as txtTipoVia, txt_nombre_via as txtNombreVia, " +
            "num_numero_via as numVia, txt_calificador as txtCalificador, ind_baja_ocupacion as indBajaOcupacion, tgp.cod_zona AS codZona, " +
            "tpc.fec_baja as fecBaja, tpc.ind_activo as indActivo " +
            "FROM validacion.t_punto_captura tpc " +
            "INNER JOIN validacion.t_grupo_pdc tgp on tgp.cod_grupo_pdc = tpc.cod_grupo_pdc " +
            "WHERE cod_pdc = #{codPDC}" })
    PuntoCapturaBean findPDC(@Param("codPDC") int codPDC);

    @Select({"SELECT CASE " +
            "WHEN EXISTS (SELECT ind_activo FROM validacion.ft_colectivo_grupo_pdc " +
            "WHERE cod_grupo_pdc = #{codGrupoPDC} " +
            "AND cod_colectivo = #{codColectivo}) THEN true " +
            "ELSE false " +
            "END " })
    boolean isGrupoPDCByCodColectivo(@Param("codColectivo") int codColectivo, @Param("codGrupoPDC") Integer codGrupoPDC);
}
