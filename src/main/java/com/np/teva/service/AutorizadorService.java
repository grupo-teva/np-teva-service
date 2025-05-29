package com.np.teva.service;

import com.np.teva.core.bean.AutorizadorBean;
import com.np.teva.core.exception.AccesoDatosException;

import java.sql.Timestamp;

public interface AutorizadorService {
    AutorizadorBean findAutorizadorEmpadronado(String documento, Timestamp fechaTransito) throws AccesoDatosException;
}
