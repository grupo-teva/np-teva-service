package com.np.teva.service.impl;

import com.np.teva.core.bean.ReglaAccesoBean;
import com.np.teva.core.exception.AccesoDatosException;
import com.np.teva.persistence.mybatis.store.ReglaAccesoStore;
import com.np.teva.service.ReglaAccesoService;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReglaAccesoServiceImpl implements ReglaAccesoService {

    @Autowired
    private ReglaAccesoStore reglaAccesoStore;

    @Override
    public List<ReglaAccesoBean> findReglas() throws AccesoDatosException {
        List<ReglaAccesoBean> reglas = null;

        try {
            reglas = reglaAccesoStore.findReglas();
        } catch (MyBatisSystemException e) {
            throw new AccesoDatosException("MyBatisSystemException running findReglas.", e);
        }

        return reglas;
    }

    @Override
    public List<ReglaAccesoBean> findReglasCascoHistorico() throws AccesoDatosException {
        List<ReglaAccesoBean> reglas = null;

        try {
            reglas = reglaAccesoStore.findReglasCascoHistorico();
        } catch (MyBatisSystemException e) {
            throw new AccesoDatosException("MyBatisSystemException running findReglasCascoHistorico.", e);
        }

        return reglas;
    }

    @Override
    public List<ReglaAccesoBean> findReglasZBE() throws AccesoDatosException {
        List<ReglaAccesoBean> reglas = null;

        try {
            reglas = reglaAccesoStore.findReglasZBE();
        } catch (MyBatisSystemException e) {
            throw new AccesoDatosException("MyBatisSystemException running findReglasZBE.", e);
        }

        return reglas;
    }

    @Override
    public List<ReglaAccesoBean> findReglasExcepcion() throws AccesoDatosException {
        List<ReglaAccesoBean> reglas = null;

        try {
            reglas = reglaAccesoStore.findReglasExcepcion();
        } catch (MyBatisSystemException e) {
            throw new AccesoDatosException("MyBatisSystemException running findReglasExcepcionByZona.", e);
        }

        return reglas;
    }

    @Override
    public List<ReglaAccesoBean> findReglasProhibicion() throws AccesoDatosException {
        List<ReglaAccesoBean> reglas = null;

        try {
            reglas = reglaAccesoStore.findReglasProhibicion();
        } catch (MyBatisSystemException e) {
            throw new AccesoDatosException("MyBatisSystemException running findReglasProhibicionByZona.", e);
        }

        return reglas;
    }
}
