package com.np.teva.service;


import com.np.teva.core.exception.AccesoDatosException;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.UUID;

public interface SancionService {

    int contarSancionesEstado(Date fechaSancion, int codigoEstadoSancion) throws AccesoDatosException;

    int updateSancion(UUID codigoTransito, int codigoEstado, int codigoSistema) throws AccesoDatosException;

    int contarSancionesPendientesValidar(Date fec_sancion) throws AccesoDatosException;

    int contarSancionesPendientesQC(Date fec_sancion, int cod_zona) throws AccesoDatosException;
}
