package com.np.teva.service;

import com.np.teva.core.bean.VehiculoBean;
import com.np.teva.core.exception.AccesoDatosException;

public interface VehiculoService {

    VehiculoBean findVehiculo(String matricula) throws AccesoDatosException;
}
