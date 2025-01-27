package com.np.teva.service;

import com.np.teva.core.bean.ReglaAccesoBean;
import com.np.teva.core.exception.AccesoDatosException;

import java.util.List;

public interface ReglaAccesoService {
    List<ReglaAccesoBean> findReglas() throws AccesoDatosException;

    List<ReglaAccesoBean> findReglasCascoHistorico() throws AccesoDatosException;

    List<ReglaAccesoBean> findReglasZBE() throws AccesoDatosException;

    List<ReglaAccesoBean> findReglasExcepcionByZona(int codZona) throws AccesoDatosException;

    List<ReglaAccesoBean> findReglasProhibicionByZona(int codZona) throws AccesoDatosException;
}
