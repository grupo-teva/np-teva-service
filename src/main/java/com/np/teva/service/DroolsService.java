package com.np.teva.service;

import com.np.teva.core.bean.*;
import com.np.teva.core.exception.AccesoDatosException;
import com.np.teva.core.exception.DroolsException;

import java.util.List;

public interface DroolsService {

    /**
     * Llama a Drools para determinar si el tránsito está permitido o no.
     * @param transitoBean Objeto TransitoBean con los datos del tránsito.
     * @param vehiculoBean Objeto VehiculoBean con los datos del vehículo.
     * @param accesosBeanList Lista de objetos AccesoBean con los permisos del vehículo.
     * @param isLaborable Valor booleano para indicar si es día laborable.
     * @param reglas Lista de objetos ReglaAccesoBean con las reglas de acceso.
     * @param isDomiciliado Valor booleano para indicar si el vehículo está domicilidado en el municipio.
     * @param cumpleItinerario Valor booleano para indicar si se cumple el itinerario. Es sólo válido para Málaga.
     * @param historico Valor booleano para indicar si el vehículo está catalogado como histórico.
     * @return Objeto DroolsResponseBean con el resultado de Drools.
     * @throws DroolsException
     */
    DroolsResponseBean isValidVehicle(TransitoBean transitoBean, VehiculoBean vehiculoBean, List<AccesosBean> accesosBeanList, boolean isLaborable, List<ReglaAccesoBean> reglas, Boolean isDomiciliado, Boolean cumpleItinerario, Boolean historico) throws DroolsException;

    /**
     * Llama a Drools para determinar si el tránsito está permitido o no.
     * @param transitoBean Objeto TransitoBean con los datos del tránsito.
     * @param accesos Lista de objetos AccesoBean con los permisos.
     * @param isLaborable Valor booleano para indicar si es día laborable.
     * @return Objeto DroolsResponseBean con el resultado de Drools.
     * @throws DroolsException
     */
    DroolsResponseBean getResponse(TransitoBean transitoBean, List<AccesosBean> accesos, Boolean isLaborable) throws DroolsException, AccesoDatosException;

    /**
     * Llama a Drools para determinar si el tránsito está permitido o no. No crea registro en la tabla t_regla_transito, sólo hace la llamada y devuelve el resultado.
     * @param transitoBean
     * @param transitoBean Objeto TransitoBean con los datos del tránsito.
     * @param accesos Lista de objetos AccesoBean con los permisos.
     * @param isLaborable Valor booleano para indicar si es día laborable.
     * @return Objeto DroolsResponseBean con el resultado de Drools.
     * @throws DroolsException
     * @throws AccesoDatosException
     */
    DroolsResponseBean getResponseSimulado(TransitoBean transitoBean, List<AccesosBean> accesos, Boolean isLaborable) throws  DroolsException, AccesoDatosException;

    void createReglaTransitoByName(TransitoBean transitoBean, DroolsResponseBean droolsResultExport) throws AccesoDatosException;
}
