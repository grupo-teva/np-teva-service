package com.np.teva.service;

import com.np.teva.core.exception.AccesoDatosException;

public interface ReglaNegocioService {

    Integer getReglaNegocioId(String reglaNegocio) throws AccesoDatosException;

    Integer insertReglaNegocio(String reglaNegocio) throws AccesoDatosException;
}
