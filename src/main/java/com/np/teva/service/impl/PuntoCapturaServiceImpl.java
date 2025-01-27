package com.np.teva.service.impl;

import com.np.teva.core.bean.PuntoCapturaBean;
import com.np.teva.core.exception.AccesoDatosException;
import com.np.teva.persistence.mybatis.store.PuntoCapturaStore;
import com.np.teva.service.PuntoCapturaService;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PuntoCapturaServiceImpl implements PuntoCapturaService {

    @Autowired
    private PuntoCapturaStore puntoCapturaStore;

    @Override
    public PuntoCapturaBean findPDC(Integer codPDC) throws AccesoDatosException {
        PuntoCapturaBean pdc = null;
        try {
            pdc = puntoCapturaStore.findPDC(codPDC);
        } catch (MyBatisSystemException e) {
            throw new AccesoDatosException("MyBatisException running findPDC.", e);
        }

        return pdc;
    }

    @Override
    public boolean isGrupoPDCByCodColectivo(int codColectivo, Integer codGrupoPDC) throws AccesoDatosException {
        Boolean isGrupoPDC = false;
        try {
            isGrupoPDC = puntoCapturaStore.isGrupoPDCByCodColectivo(codColectivo, codGrupoPDC);
        } catch (MyBatisSystemException e) {
            throw new AccesoDatosException("MyBatisException running findPDC.", e);
        }

        return isGrupoPDC;
    }
}
