package com.np.teva.service;

import com.np.teva.core.bean.TransitoBean;
import com.np.teva.core.bean.TransitoExonerableBean;
import com.np.teva.core.exception.AccesoDatosException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface TransitoService {
    List<TransitoBean> findTransitosReprocesado(Date fechaSancion) throws AccesoDatosException;

    List<TransitoBean> findTransitosReprocesadoRemesadas(Date fechaSancion, int codigoZona) throws AccesoDatosException;

    List<TransitoBean> findTransitosPendientesRemesar(Date fechaSancion, int codigoZona) throws AccesoDatosException;

    List<TransitoBean> findTransitosPendientesValidados(Date fechaSancion, int codigoZona) throws AccesoDatosException;

    List<TransitoBean> findTransitosPendientesQC(Date fechaSancion, int codigoZona) throws AccesoDatosException;

    List<TransitoExonerableBean> findTransitosExonerables() throws AccesoDatosException;

    List<TransitoExonerableBean> findTransitosSalidaSiguientes(String matricula, Timestamp fechaTransito) throws AccesoDatosException;

    int marcarTransitoImporter(String codTransito) throws AccesoDatosException;

    int updateTransito(TransitoBean transitoBean, int transitoType, int importStateType, boolean isPteImporter,  int codSistema) throws AccesoDatosException;
}
