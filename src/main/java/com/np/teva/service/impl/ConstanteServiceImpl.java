package com.np.teva.service.impl;

import com.np.teva.core.exception.AccesoDatosException;
import com.np.teva.persistence.mybatis.store.ConstanteStore;
import com.np.teva.service.ConstanteService;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ConstanteServiceImpl implements ConstanteService {
    @Autowired
    private ConstanteStore constanteStore;

    @Override
    public int getConstanteAsInt(String descripcionConstante) throws AccesoDatosException {
        int valor = 0;

        try {
            valor = constanteStore.getConstanteAsInt(descripcionConstante);
        } catch (MyBatisSystemException mex) {
            throw new AccesoDatosException("MyBatisSystemException running getConstanteAsInt.", mex);
        } catch (DataAccessException dex) {
            throw new AccesoDatosException("DataAccessException running getConstanteAsInt.", dex);
        }

        return valor;
    }

    @Override
    public String getConstanteAsString(String descripcionConstante) throws AccesoDatosException {
        String valor = "";

        try {
            valor = constanteStore.getConstanteAsString(descripcionConstante);
        } catch (MyBatisSystemException mex) {
            throw new AccesoDatosException("MyBatisSystemException running getConstanteAsString.", mex);
        } catch (DataAccessException dex) {
            throw new AccesoDatosException("DataAccessException running getConstanteAsString.", dex);
        }

        return valor;
    }
}
