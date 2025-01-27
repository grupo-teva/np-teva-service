package com.np.teva.service;

import com.np.teva.core.bean.AccesosBean;
import com.np.teva.core.exception.AccesoDatosException;

import java.util.List;
import java.util.UUID;

public interface PermisoTransitoService {
    void createPermisoTransito(UUID idTransito, List<AccesosBean> accesosBeanList) throws AccesoDatosException;
}
