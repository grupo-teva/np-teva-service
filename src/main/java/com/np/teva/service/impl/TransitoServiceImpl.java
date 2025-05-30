package com.np.teva.service.impl;

import com.np.teva.core.bean.TransitoBean;
import com.np.teva.core.bean.TransitoExonerableBean;
import com.np.teva.core.exception.AccesoDatosException;
import com.np.teva.persistence.mybatis.store.TransitoStore;
import com.np.teva.service.TransitoService;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransitoServiceImpl implements TransitoService {

    @Autowired
    public TransitoStore transitoStore;

    @Override
    public List<TransitoBean> findTransitosReprocesado(Date fechaSancion) throws AccesoDatosException {
        List<TransitoBean> transitos = new ArrayList<>();

        try{
            transitos = transitoStore.findTransitosReprocesado(fechaSancion);
        }catch (MyBatisSystemException mex) {
            throw new AccesoDatosException("MyBatisSystemException running findTransitosReprocesado", mex);
        } catch (DataAccessException dex) {
            throw new AccesoDatosException("DataAccessException running findTransitosReprocesado", dex);
        }

        return transitos;
    }

    @Override
    public List<TransitoBean> findTransitosReprocesadoRemesadas(Date fechaSancion, int codigoZona) throws AccesoDatosException {
        List<TransitoBean> transitos = new ArrayList<>();

        try{
            transitos = transitoStore.findTransitosReprocesadoRemesadas(fechaSancion, codigoZona);
        }catch (MyBatisSystemException mex) {
            throw new AccesoDatosException("MyBatisSystemException running findTransitosReprocesadoRemesadas", mex);
        } catch (DataAccessException dex) {
            throw new AccesoDatosException("DataAccessException running findTransitosReprocesadoRemesadas", dex);
        }

        return transitos;
    }

    @Override
    public List<TransitoBean> findTransitosPendientesValidados(Date fechaSancion, int codigoZona) throws AccesoDatosException {
        List<TransitoBean> transitos = new ArrayList<>();

        try{
            transitos = transitoStore.findTransitosPendientesValidacion(fechaSancion, codigoZona);
        }catch (MyBatisSystemException mex) {
            throw new AccesoDatosException("MyBatisSystemException running findTransitosPendientesValidados", mex);
        } catch (DataAccessException dex) {
            throw new AccesoDatosException("DataAccessException running findTransitosPendientesValidados", dex);
        }

        return transitos;
    }

    @Override
    public List<TransitoBean> findTransitosPendientesRemesar(Date fechaSancion, int codigoZona) throws AccesoDatosException {
        List<TransitoBean> transitos = new ArrayList<>();

        try{
            transitos = transitoStore.findTransitosPendientesRemesar(fechaSancion, codigoZona);
        }catch (MyBatisSystemException mex) {
            throw new AccesoDatosException("MyBatisSystemException running findTransitosPendientesRemesar", mex);
        } catch (DataAccessException dex) {
            throw new AccesoDatosException("DataAccessException running findTransitosPendientesRemesar", dex);
        }

        return transitos;
    }

    @Override
    public List<TransitoBean> findTransitosPendientesQC(Date fechaSancion, int codigoZona) throws AccesoDatosException {
        List<TransitoBean> transitos = new ArrayList<>();

        try{
            transitos = transitoStore.findTransitosPendientesQC(fechaSancion, codigoZona);
        }catch (MyBatisSystemException mex) {
            throw new AccesoDatosException("MyBatisSystemException running findTransitosPendientesValidados", mex);
        } catch (DataAccessException dex) {
            throw new AccesoDatosException("DataAccessException running findTransitosPendientesValidados", dex);
        }

        return transitos;
    }

    @Override
    public List<TransitoExonerableBean> findTransitosExonerables() throws AccesoDatosException {
        List<TransitoExonerableBean> transitos = new ArrayList<>();

        try {
            transitos = transitoStore.findTransitosExonerables();

        } catch (MyBatisSystemException mex) {
            throw new AccesoDatosException("MyBatisSystemException running findTransitosExonerables", mex);
        } catch (DataAccessException dex) {
            throw new AccesoDatosException("DataAccessException running findTransitosExonerables", dex);
        }

        return transitos;
    }

    @Override
    public List<TransitoExonerableBean> findTransitosSalidaSiguientes(String matricula, Timestamp fechaTransito) throws AccesoDatosException {
        List<TransitoExonerableBean> transitos = new ArrayList<>();

        try {
            transitos = transitoStore.findTransitosSalidaSiguientes(matricula, fechaTransito);

        } catch (MyBatisSystemException mex) {
            throw new AccesoDatosException("MyBatisSystemException running findTransitosSalidaSiguientes", mex);
        } catch (DataAccessException dex) {
            throw new AccesoDatosException("DataAccessException running findTransitosSalidaSiguientes", dex);
        }

        return transitos;
    }

    @Override
    public int marcarTransitoImporter(String codTransito) throws AccesoDatosException {
        int result = 0;

        try{
            result = transitoStore.marcarTransitoImporter(codTransito);
        } catch (MyBatisSystemException e) {
            throw new AccesoDatosException("MyBatisSystemException running marcarTransitoImporter", e);
        }

        return result;
    }

    @Override
    public int updateTransito(TransitoBean transitoBean, int transitoType, int importStateType, boolean isPteImporter, int codSistema) throws AccesoDatosException {
        int result = 0;

        try{
            result = transitoStore.updateTransito(transitoBean.getId(), transitoType, importStateType, isPteImporter, codSistema);
        } catch (MyBatisSystemException e) {
            throw new AccesoDatosException("MyBatisSystemException running updateTransito", e);
        }

        return result;
    }
}
