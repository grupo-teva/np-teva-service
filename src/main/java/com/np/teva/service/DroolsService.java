package com.np.teva.service;

import com.np.teva.core.bean.*;
import com.np.teva.core.exception.AccesoDatosException;
import com.np.teva.core.exception.DroolsException;

import java.util.List;

public interface DroolsService {

    DroolsResponseBean isValidVehicle(TransitoBean transitoBean, VehiculoBean vehiculoBean, List<AccesosBean> accesosBeanList, boolean isLaborable, List<ReglaAccesoBean> reglas, Boolean isDomiciliado, Boolean cumpleItinerario, Boolean historico, Integer codZona) throws DroolsException;

    void createReglaTransitoByName(TransitoBean transitoBean, DroolsResponseBean droolsResultExport) throws AccesoDatosException;
}
