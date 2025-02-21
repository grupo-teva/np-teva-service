package com.np.teva.service;

import com.np.teva.core.exception.AccesoDatosException;

import java.util.Date;
import java.util.UUID;

public interface SancionService {

    /**
     * Obtiene el número de sanciones en el estado indicado y establece el sistema que ha realizado el cambio de estado.
     * @param fechaSancion Fecha de la remesa.
     * @param codigoEstadoSancion Código del estado de la sanción. Ver tabla tt_estado_sancion.
     * @return Entero con el número de sanciones en el estado indicado.
     * @throws AccesoDatosException
     */
    int contarSancionesEstado(Date fechaSancion, int codigoEstadoSancion) throws AccesoDatosException;

    /**
     * Actualiza el estado de la sanción identificada.
     * @param codigoTransito Código del tránsito para identificar la sanción a modificar.
     * @param codigoEstado Código del estado a cambiar en la sanción. Ver tabla tt_estado_sancion.
     * @param codigoSistema Código del sistema que ha cambiado el estado. Ver tabla tt_sistema.
     * @return Entero con el número de filas afectadas.
     * @throws AccesoDatosException
     */
    int updateSancion(UUID codigoTransito, int codigoEstado, int codigoSistema) throws AccesoDatosException;

    /**
     * Actualiza el estado de la sanción identificada. Pone el campo num_boletin a NULL y establece el sistema que ha realizado el cambio de estado.
     * @param codigoTransito Código del tránsito para identificar la sanción a modificar.
     * @param codigoEstado Código del estado a cambiar en la sanción. Ver tabla tt_estado_sancion.
     * @param codigoSistema Código del sistema que ha cambiado el estado. Ver tabla tt_sistema.
     * @return Entero con el número de filas afectadas.
     * @throws AccesoDatosException
     */
    int updateSancionRemesada(UUID codigoTransito, int codigoEstado, int codigoSistema) throws AccesoDatosException;

    /**
     * Obtiene el número de sanciones que quedan pendientes de validar. Usa la vista validacion.v_sancion_nueva_validador_v2.
     * @param fec_sancion Fecha de la remesa para obtener las sanciones pendientes.
     * @param cod_zona Código de la zona del PDC para obtener las sanciones.
     * @return Entero con el número de sanciones pendientes de validar.
     * @throws AccesoDatosException
     */
    int contarSancionesPendientesValidar(Date fec_sancion, int cod_zona) throws AccesoDatosException;

    /**
     * Obtiene el número de sanciones que quedan pendientes de pasar el QC.
     * @param fec_sancion Fecha de la remesa para obtener las sanciones pendientes.
     * @param cod_zona Código de zona. Ver tabla tt_zona.
     * @return Entero con el número de sanciones en QC pendientes de contestar.
     * @throws AccesoDatosException
     */
    int contarSancionesPendientesQC(Date fec_sancion, int cod_zona) throws AccesoDatosException;

    /**
     * Elimina las sanciones reincidentes de la remesa.
     * @param fec_sancion Fecha de la remesa para obtener las sanciones reincidentes.
     * @param cod_zona Código de zona. Ver tabla tt_zona.
     * @return Entero con el número de sanciones en QC pendientes de eliminar.
     * @throws AccesoDatosException
     */
    int rechazarSancionesDuplicadas(Date fec_sancion, int cod_zona) throws AccesoDatosException;
}
