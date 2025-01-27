package com.np.teva.service;

import com.np.teva.core.bean.PeriodoBean;
import com.np.teva.core.exception.AccesoDatosException;

import java.util.List;

public interface PeriodoService {
    List<PeriodoBean> findPeriodoByCodPerfil(Integer codPerfil) throws AccesoDatosException;
}
