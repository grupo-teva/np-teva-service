package com.np.teva.service;

import com.np.teva.core.bean.AccesosBean;
import com.np.teva.core.bean.ItinerarioBean;
import com.np.teva.core.exception.AccesoDatosException;

import java.util.List;

public interface ItinerarioService {
    List<ItinerarioBean> findItinerarioByGrupoPDC(Integer codGrupoPDC) throws AccesoDatosException;

    List<ItinerarioBean> findItinerarioBySCA(List<AccesosBean> filterAccesoBean) throws AccesoDatosException;

    List<ItinerarioBean> findItinerarioByPerfilAndPDC(List<AccesosBean> filterAccesoBean, Integer codGrupoPDC) throws AccesoDatosException;
}
