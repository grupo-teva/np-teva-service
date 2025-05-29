package com.np.teva.service;

import com.np.teva.core.bean.VehiculoBean;
import com.np.teva.core.exception.AccesoDatosException;

import java.util.List;

public interface VehiculoService {

    VehiculoBean findVehiculo(String matricula) throws AccesoDatosException;

    List<String> findTitularesVehiculo(String matricula) throws AccesoDatosException;
}
