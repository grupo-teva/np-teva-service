package com.np.teva.service;

import com.np.teva.core.bean.AccesoIntranetBean;
import com.np.teva.core.exception.AccesoDatosException;

import java.sql.Timestamp;
import java.util.List;

public interface PermisoAccesoIntranetService {
    List<AccesoIntranetBean> getPermisosAcessoByPlate(String plate, Timestamp day, Integer pdc, Integer codZona) throws AccesoDatosException;

}
