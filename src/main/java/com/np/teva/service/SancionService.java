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
     * @param codigoZona Codigo de la zona para obtener las sanciones pendientes.
     * @return Entero con el número de sanciones pendientes de validar.
     * @throws AccesoDatosException
     */
    int contarSancionesPendientesValidar(Date fec_sancion, int codigoZona) throws AccesoDatosException;

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

    /**
     * Cambia el estado de las sanciones nuevas al estado castigado.
     * @param fec_sancion Fecha de la sanción.
     * @param cod_zona Código de la zona. Ver tabla tt_zona.
     * @param cod_estado_nuevo Codigo de estado de las sanciones nuevas.
     * @param cod_estado_castigado Código de estado de las sanciones castigadas.
     * @return Entero con el número de sanciones modificadas.
     * @throws AccesoDatosException
     */
    int castigarSancionesNuevas(Date fec_sancion, int cod_zona, int cod_estado_nuevo, int cod_estado_castigado) throws AccesoDatosException;

    /**
     * Descarta las sanciones castigadas de un vehículo una vez que se ha librado para ese día por el tiempo de acceso.
     * @param fec_sancion Fecha de la sanción.
     * @param txt_matricula Matrícula del vehículo a descartar.
     * @param cod_estado_duplicidad Código del estado con el que se marcarán las sanciones duplicadas.
     * @return Entero con el número de sanciones descartadas.
     * @throws AccesoDatosException
     */
    int descartarDuplicadosTiempoCruce(Date fec_sancion, String txt_matricula, int cod_estado_duplicidad) throws AccesoDatosException;
}
