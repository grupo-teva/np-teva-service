package com.np.teva.service.impl;

import com.np.teva.core.bean.ReglaNegocioBean;
import com.np.teva.core.exception.AccesoDatosException;
import com.np.teva.persistence.mybatis.store.ReglaNegocioStore;
import com.np.teva.service.ReglaNegocioService;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReglaNegocioServiceImpl implements ReglaNegocioService {

    @Autowired
    private ReglaNegocioStore reglaNegocioStore;

    @Override
    public Integer getReglaNegocioId(String reglaNegocio) throws AccesoDatosException {
        Integer regla = null;

        try {
            regla = reglaNegocioStore.getReglaNegocioId(reglaNegocio);
        } catch (MyBatisSystemException e) {
            throw new AccesoDatosException("MyBatisSystemException running getReglaNegocioId", e);
        }

        return regla;
    }

    @Override
    public Integer insertReglaNegocio(String reglaNegocio) throws AccesoDatosException {
        Integer reglaNegocioId = null;

        try {
            ReglaNegocioBean reglaNegocioBean = new ReglaNegocioBean();
            reglaNegocioBean.setDescripcion(reglaNegocio);
            reglaNegocioStore.insertReglaNegocio(reglaNegocioBean);
            reglaNegocioId = reglaNegocioBean.getReglaId();
        } catch (MyBatisSystemException e) {
            throw new AccesoDatosException("MyBatisSystemException running insertReglaNegocio.", e);
        }

        return reglaNegocioId;
    }
}
