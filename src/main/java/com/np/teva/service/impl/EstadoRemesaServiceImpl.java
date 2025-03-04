package com.np.teva.service.impl;

import com.np.teva.core.bean.EstadoRemesaBean;
import com.np.teva.core.exception.AccesoDatosException;
import com.np.teva.persistence.mybatis.store.EstadoRemesaStore;
import com.np.teva.service.EstadoRemesaService;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EstadoRemesaServiceImpl implements EstadoRemesaService {

    @Autowired
    private EstadoRemesaStore estadoRemesaStore;

    @Override
    public EstadoRemesaBean findEstadoRemesa(int codigoZona, Date fechaSancion) throws AccesoDatosException {
        EstadoRemesaBean estado = null;
        try {
            estado = estadoRemesaStore.findRemesa(codigoZona, fechaSancion);
        } catch (MyBatisSystemException mex) {
            throw new AccesoDatosException("MyBatisSystemException running findRemesasByEstado.", mex);
        } catch (DataAccessException dex) {
            throw new AccesoDatosException("DataAccessException running findRemesasByEstado.", dex);
        }
        return estado;
    }

    @Override
    public List<EstadoRemesaBean> findRemesasByEstadoZona(int codigoEstado, int codigoZona) throws AccesoDatosException {
        List<EstadoRemesaBean> estados = new ArrayList<>();

        try {
            estados = estadoRemesaStore.findRemesasByEstadoZona(codigoEstado, codigoZona);
        } catch (MyBatisSystemException mex) {
            throw new AccesoDatosException("MyBatisSystemException running findRemesasByEstadoZona.", mex);
        } catch (DataAccessException dex) {
            throw new AccesoDatosException("DataAccessException running findRemesasByEstadoZona.", dex);
        }

        return estados;
    }

    @Override
    public List<EstadoRemesaBean> findRemesasByEstado(int codigoEstado) throws AccesoDatosException {
        List<EstadoRemesaBean> estados = new ArrayList<>();

        try {
            estados = estadoRemesaStore.findRemesasByEstado(codigoEstado);
        } catch (MyBatisSystemException mex) {
            throw new AccesoDatosException("MyBatisSystemException running findRemesasByEstado.", mex);
        } catch (DataAccessException dex) {
            throw new AccesoDatosException("DataAccessException running findRemesasByEstado.", dex);
        }
        return estados;
    }

    @Override
    public List<EstadoRemesaBean> findRemesasByEstadoFecha(int codigoEstado, int codigoZona, Date fec_sancion) throws AccesoDatosException {
        List<EstadoRemesaBean> estados = new ArrayList<>();

        try {
            estados = estadoRemesaStore.findRemesasByEstadoFecha(codigoEstado, codigoZona, fec_sancion);
        } catch (MyBatisSystemException mex) {
            throw new AccesoDatosException("MyBatisSystemException running findRemesasByEstadoFecha.", mex);
        } catch (DataAccessException dex) {
            throw new AccesoDatosException("DataAccessException running findRemesasByEstadoFecha.", dex);
        }

        return estados;
    }

    @Override
    public boolean updateEstadoRemesa(Date fechaSancion, int codigoZona, int estado) throws AccesoDatosException {
        try {
            estadoRemesaStore.updateEstadoRemesa(fechaSancion, codigoZona, estado);
            return true;
        } catch (MyBatisSystemException mex){
            throw new AccesoDatosException("MyBatisSystemException running updateEstadoRemesa.", mex);
        } catch (DataAccessException dex) {
            throw new AccesoDatosException("DataAccessException running updateEstadoRemesa.", dex);
        }
    }

    @Override
    public boolean insertEstadoRemesa(EstadoRemesaBean estadoRemesa) throws AccesoDatosException {
        try {
            estadoRemesaStore.insertEstadoRemesa(estadoRemesa);
            return true;
        } catch (MyBatisSystemException mex){
            throw new AccesoDatosException("MyBatisSystemException running insertEstadoRemesa.", mex);
        } catch (DataAccessException dex) {
            throw new AccesoDatosException("DataAccessException running insertEstadoRemesa.", dex);
        }
    }
}
