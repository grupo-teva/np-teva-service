package com.np.teva.service.impl;

import com.np.teva.core.bean.CorteBean;
import com.np.teva.core.bean.SancionTransitoBean;
import com.np.teva.core.bean.VehiculoBean;
import com.np.teva.core.exception.AccesoDatosException;
import com.np.teva.persistence.mybatis.store.CorteStore;
import com.np.teva.persistence.mybatis.store.VehiculoStore;
import com.np.teva.service.CorteService;
import com.np.teva.service.VehiculoService;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class CorteServiceImpl implements CorteService {

    @Autowired
    private CorteStore corteStore;

    @Override
    public List<CorteBean> getCortesByDay(LocalDate day) throws AccesoDatosException {
        List<CorteBean> cortes = Collections.EMPTY_LIST;
        try {
            Timestamp tmsInit =Timestamp.valueOf(day.atStartOfDay());
            Timestamp tmsFinish =Timestamp.valueOf(day.atTime(LocalTime.MAX));
            cortes = corteStore.getAllCortesByDay(tmsInit, tmsFinish);
        } catch (MyBatisSystemException e) {
            throw  new AccesoDatosException("MyBatisSystemException getting cortes", e);
        } catch (DataAccessException e) {
            throw new AccesoDatosException("DataAccessException getting cortes", e);
        }
        return cortes;
    }

    @Override
    public List<SancionTransitoBean> getSancionesCorte (Integer pdc, LocalDate fecha, Timestamp inicio, Timestamp fin) throws AccesoDatosException {
        List<SancionTransitoBean> sanciones = Collections.EMPTY_LIST;
        try {
            sanciones = corteStore.getSancionesCorte(pdc, fecha, inicio, fin);
        } catch (MyBatisSystemException e) {
            throw  new AccesoDatosException("MyBatisSystemException getting cortes", e);
        } catch (DataAccessException e) {
            throw new AccesoDatosException("DataAccessException getting cortes", e);
        }
        return sanciones;
    }

    @Override
    public List<Integer> getAllpdcsByCorte(int corte) throws AccesoDatosException {
        List<Integer> pdcs = Collections.EMPTY_LIST;
        try {
            pdcs = corteStore.getAllPdcsByCorte(corte);
        } catch (MyBatisSystemException e) {
            throw  new AccesoDatosException("MyBatisSystemException getting cortes", e);
        } catch (DataAccessException e) {
            throw new AccesoDatosException("DataAccessException getting cortes", e);
        }
        return pdcs;
    }

    @Override
    public void updateSancionCorte(UUID codigoTransito, int codigoEstado, int codigoSistema) throws AccesoDatosException{
        try{
            corteStore.updateSancionCorte(codigoTransito, codigoEstado, codigoSistema);
        } catch (MyBatisSystemException e) {
            throw  new AccesoDatosException("MyBatisSystemException getting cortes", e);
        } catch (DataAccessException e) {
            throw new AccesoDatosException("DataAccessException getting cortes", e);
        }
    }

    @Override
    public void updateTransitoCorte(UUID codigoTransito, int codigoEstado, int codigoSistema) throws AccesoDatosException{
        try{
            corteStore.updateTransitoCorte(codigoTransito, codigoEstado, codigoSistema);
        } catch (MyBatisSystemException e) {
            throw  new AccesoDatosException("MyBatisSystemException getting cortes", e);
        } catch (DataAccessException e) {
            throw new AccesoDatosException("DataAccessException getting cortes", e);
        }
    }

    @Override
    public void insertCorteTransito(UUID transitoId, int corte) throws AccesoDatosException {
        try {
            corteStore.insertCorteTransito(transitoId, corte);
        } catch (MyBatisSystemException e) {
            throw  new AccesoDatosException("MyBatisSystemException inserting cortes", e);
        } catch (DataAccessException e) {
            throw new AccesoDatosException("DataAccessException inserting cortes", e);
        }
    }

}
