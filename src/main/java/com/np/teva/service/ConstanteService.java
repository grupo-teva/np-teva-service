package com.np.teva.service;

import com.np.teva.core.exception.AccesoDatosException;

public interface ConstanteService {

    int getConstanteAsInt(String descripcionConstante) throws AccesoDatosException;

    String getConstanteAsString(String descripcionConstante) throws AccesoDatosException;
}
