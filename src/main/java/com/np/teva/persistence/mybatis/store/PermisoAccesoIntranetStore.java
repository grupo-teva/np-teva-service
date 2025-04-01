package com.np.teva.persistence.mybatis.store;

import com.np.teva.core.bean.AccesoIntranetBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Mapper
public interface PermisoAccesoIntranetStore {
    @Select("select ftp.txt_matricula as plate "
            + " , ftp.cod_tipo_perm as tipoPermiso "
            + " , ftp.cod_subcond_aut as condicionSubautorizadora "
            + " , ftp.cod_permiso as permiso  "
            + " from validacion.ft_permiso_acceso_intranet ftp  "
            + " where ftp.txt_matricula = #{matricula} "
            + "      AND (ftp.cod_tipo_perm = 1 or  ftp.cod_tipo_perm = 8) "
            + "      AND ftp.fec_inicio = #{dia} "
            + "      AND ftp.cod_estado_perm in (1,2,4,6)")
    List<AccesoIntranetBean> getPermisosPuntualesAcessoByPlate(@Param("matricula") String matricula, @Param("dia") Timestamp dia);

    @Select("select ftp.txt_matricula as plate "
            + " , ftp.cod_tipo_perm as tipoPermiso "
            + " , ftp.cod_subcond_aut as condicionSubautorizadora "
            + " , ftp.cod_permiso as permiso  "
            + " , ftp.cod_colectivo as codColectivo "
            + " from validacion.ft_permiso_acceso_intranet ftp  "
            + " where ftp.txt_matricula = #{matricula} "
            + "     AND ftp.cod_tipo_perm in (2, 3, 4, 5, 6, 7) "
            + "     AND ftp.cod_estado_perm in (1, 2, 4, 6) "
            + "     and ftp.fec_inicio <=  #{dia} "
            + "     and (COALESCE(ftp.fec_fin, #{dia}::date) >= #{dia} )")
    List<AccesoIntranetBean> getPermisosAcessoByPlate(@Param("matricula") String matricula, @Param("dia") LocalDate dia);

    @Select("select ftp.txt_matricula as plate "
            + " , ftp.cod_tipo_perm as tipoPermiso "
            + " , ftp.cod_subcond_aut as condicionSubautorizadora "
            + " , ftp.cod_permiso as permiso "
            + " , ftp.cod_colectivo as codColectivo "
            + " , true as parkingColectivo "
            + " from validacion.ft_permiso_acceso_intranet ftp "
            + " inner join validacion.ft_colectivo as ftc on ftc.cod_colectivo = ftp.cod_colectivo "
            + " inner join validacion.ft_colectivo_grupo_pdc as ftcgp on ftcgp.cod_colectivo = ftc.cod_colectivo "
            + " inner join validacion.t_punto_captura as tpc on tpc.cod_grupo_pdc = ftcgp.cod_grupo_pdc  "
            + " where ftp.txt_matricula = #{matricula} "
            + "     AND ftp.cod_tipo_perm = 7 "
            + "     AND ftp.cod_estado_perm in (1, 2, 4, 6) "
            + "     AND ftp.cod_colectivo is not null "
            + "     AND tpc.cod_pdc = #{pdc} "
            + "     AND ftp.fec_inicio - interval '1 minute' * ftc.tiempo_cortesia <= #{dia} "
            + "     AND ftp.fec_fin + interval '1 minute' * ftc.tiempo_cortesia >= #{dia} ")
    List<AccesoIntranetBean> getPermisosPuntualesColectivoAcessoByPlate(@Param("matricula") String matricula, @Param("dia") Timestamp dia, @Param("pdc") Integer pdc);

    @Select("select ftp.txt_matricula as plate "
            + " , ftp.cod_tipo_perm as tipoPermiso "
            + " , ftp.cod_subcond_aut as condicionSubautorizadora "
            + " , ftp.cod_permiso as permiso "
            + " , ftp.cod_colectivo as codColectivo "
            + " , true as parkingColectivo "
            + " from validacion.ft_permiso_acceso_intranet ftp "
            + " where ftp.txt_matricula = #{matricula} "
            + "     AND (ftp.cod_tipo_perm = 1 or ftp.cod_tipo_perm = 8) "
            + "     AND ftp.cod_parking is not null "
            + "     AND ftp.cod_estado_perm in (1, 2, 4, 6) "
            + "     AND ftp.fec_inicio <= #{dia} AND ftp.fec_fin >= #{dia} ")
    List<AccesoIntranetBean> getPermisosPuntualesParkingAcessoByPlate(@Param("matricula") String matricula, @Param("dia") Timestamp dia);
}
