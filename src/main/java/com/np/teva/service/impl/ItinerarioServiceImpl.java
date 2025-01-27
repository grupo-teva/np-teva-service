package com.np.teva.service.impl;

import com.np.teva.core.bean.AccesosBean;
import com.np.teva.core.bean.ItinerarioBean;
import com.np.teva.core.exception.AccesoDatosException;
import com.np.teva.persistence.mybatis.store.ItinerarioStore;
import com.np.teva.service.ItinerarioService;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItinerarioServiceImpl implements ItinerarioService {

    @Autowired
    private ItinerarioStore itinerarioStore;

    @Override
    public List<ItinerarioBean> findItinerarioByGrupoPDC(Integer codGrupoPDC) throws AccesoDatosException {
        List<ItinerarioBean> itinerarios = new ArrayList<>();

        itinerarios = itinerarioStore.findItinerarioByGrupoPDC(codGrupoPDC);

        try {

        } catch (MyBatisSystemException e){
            throw new AccesoDatosException("MyBatisSystemException running findItinerarioByGrupoPDC.", e);
        }

        return itinerarios;
    }

    @Override
    public List<ItinerarioBean> findItinerarioBySCA(List<AccesosBean> filterAccesoBean) throws AccesoDatosException {
        List<ItinerarioBean> itinerarios = new ArrayList<>();

        try {
            for (AccesosBean acceso : filterAccesoBean) {
                itinerarios.addAll(itinerarioStore.findItinerarioBySCA(acceso.getCondicionSubautorizadora()));
            }
        } catch (MyBatisSystemException e) {
            throw new AccesoDatosException("MyBatisSystemException running findItinerarioByGrupoPDC.", e);
        }

        return itinerarios;
    }

    @Override
    public List<ItinerarioBean> findItinerarioByPerfilAndPDC(List<AccesosBean> filterAccesoBean, Integer codGrupoPDC) throws AccesoDatosException {
        List<ItinerarioBean> itinerarios = new ArrayList<>();

        try {
            for (AccesosBean acceso : filterAccesoBean) {
                itinerarios.addAll(itinerarioStore.findItinerarioByPerfilAndPDC(acceso.getCodPerfil(), codGrupoPDC));
            }
        } catch (MyBatisSystemException e) {
            throw new AccesoDatosException("MyBatisSystemException running findItinerarioByGrupoPDC.", e);
        }

        return itinerarios;
    }
}
