package com.np.teva.service.impl;

import com.np.teva.core.bean.AutorizadorBean;
import com.np.teva.core.bean.VehiculoBean;
import com.np.teva.core.exception.AccesoDatosException;
import com.np.teva.persistence.mybatis.store.AutorizadorStore;
import com.np.teva.service.AutorizadorService;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AutorizadorServiceImpl implements AutorizadorService {

    @Autowired
    private AutorizadorStore autorizadorStore;

    @Override
    public AutorizadorBean findAutorizadorEmpadronado(String documento, Timestamp fechaTransito) throws AccesoDatosException {
        AutorizadorBean autorizador = null;

        try {
            autorizador = autorizadorStore.findAutorizadorEmpadronado(documento, fechaTransito);
        } catch (MyBatisSystemException e) {
            throw new AccesoDatosException("MyBatisSystemException running findAutorizadorEmpadronado.", e);
        }

        return autorizador;
    }
}
