package com.np.teva.service;

import com.np.teva.core.bean.CorteBean;
import com.np.teva.core.bean.SancionTransitoBean;
import com.np.teva.core.bean.VehiculoBean;
import com.np.teva.core.exception.AccesoDatosException;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CorteService {

    List<CorteBean> getCortesByDay(LocalDate day) throws AccesoDatosException;

    List<Integer> getAllpdcsByCorte(int corte) throws AccesoDatosException;

    List<SancionTransitoBean> getSancionesCorte (Integer pdc, LocalDate fecha, Timestamp inicio, Timestamp fin) throws AccesoDatosException;

    void updateSancionCorte(UUID codigoTransito, int codigoEstado, int codigoSistema) throws AccesoDatosException;

    void updateTransitoCorte(UUID codigoTransito, int codigoEstado, int codigoSistema) throws AccesoDatosException;

    void insertCorteTransito(UUID transitoId, int corte) throws AccesoDatosException;
}
