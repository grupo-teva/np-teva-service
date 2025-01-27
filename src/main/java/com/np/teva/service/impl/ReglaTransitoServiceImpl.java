package com.np.teva.service.impl;

import com.np.teva.core.exception.AccesoDatosException;
import com.np.teva.persistence.mybatis.store.ReglaTransitoStore;
import com.np.teva.service.ReglaTransitoService;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReglaTransitoServiceImpl implements ReglaTransitoService {

    @Autowired
    private ReglaTransitoStore reglaTransitoStore;

    @Override
    public void createReglaTransito(UUID idTransito, int reglaId, boolean resultado) throws AccesoDatosException {
        try {
            reglaTransitoStore.createReglaTransito(idTransito, reglaId, resultado);
        } catch (MyBatisSystemException e) {
            throw new AccesoDatosException("MyBatisSystemException running createReglaTransito.", e);
        }
    }
}
