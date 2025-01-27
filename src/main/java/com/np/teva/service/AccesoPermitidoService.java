package com.np.teva.service;

import com.np.teva.core.bean.AccesosBean;
import com.np.teva.core.bean.TransitoBean;
import com.np.teva.core.exception.AccesoDatosException;

import java.util.List;

public interface AccesoPermitidoService {
    List<AccesosBean> findAccesosByPlate(TransitoBean transito) throws AccesoDatosException;
}
