package com.np.teva.service.impl;

import com.np.teva.core.bean.PeriodoBean;
import com.np.teva.core.exception.AccesoDatosException;
import com.np.teva.persistence.mybatis.store.PeriodoStore;
import com.np.teva.service.PeriodoService;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeriodoServiceImpl implements PeriodoService {

    @Autowired
    private PeriodoStore periodoStore;

    @Override
    public List<PeriodoBean> findPeriodoByCodPerfil(Integer codPerfil) throws AccesoDatosException {
        List<PeriodoBean> periodos = new ArrayList<>();

        try {
            periodos = periodoStore.findPeriodoByCodPerfil(codPerfil);
        } catch (MyBatisSystemException e) {
            throw new AccesoDatosException("MyBatisSystemException running findPeriodoByCodPerfil", e);
        }

        return periodos;
    }
}
