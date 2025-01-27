package com.np.teva.service.impl;

import com.np.teva.core.bean.AccesosBean;
import com.np.teva.core.bean.TransitoBean;
import com.np.teva.core.exception.AccesoDatosException;
import com.np.teva.persistence.mybatis.store.AccesoPermitidoStore;
import com.np.teva.service.AccesoPermitidoService;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccesoPermitidoServiceImpl implements AccesoPermitidoService {

    @Autowired
    private AccesoPermitidoStore accesoPermitidoStore;

    @Override
    public List<AccesosBean> findAccesosByPlate(TransitoBean transito) throws AccesoDatosException {
        List<AccesosBean> accesos = new ArrayList<>();

        try{
            accesos = accesoPermitidoStore.findAccesosByPlate(transito.getTmsTransito().toLocalDateTime().toLocalDate(), transito.getPlate());
        } catch (MyBatisSystemException mex) {
            throw new AccesoDatosException("MyBatisSystemException running getAccesos.", mex);
        }

        return accesos;
    }
}
