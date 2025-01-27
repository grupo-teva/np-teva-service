package com.np.teva.service;

import com.np.teva.core.bean.PuntoCapturaBean;
import com.np.teva.core.exception.AccesoDatosException;

public interface PuntoCapturaService {

    PuntoCapturaBean findPDC(Integer codPDC) throws AccesoDatosException;

    boolean isGrupoPDCByCodColectivo(int codColectivo, Integer codGrupoPDC) throws AccesoDatosException;
}
