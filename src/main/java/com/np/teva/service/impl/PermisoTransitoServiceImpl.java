package com.np.teva.service.impl;

import com.np.teva.core.bean.AccesosBean;
import com.np.teva.core.bean.PermisoTransitoBean;
import com.np.teva.core.exception.AccesoDatosException;
import com.np.teva.persistence.mybatis.store.PermisoTransitoStore;
import com.np.teva.service.PermisoTransitoService;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PermisoTransitoServiceImpl implements PermisoTransitoService {

    @Autowired
    private PermisoTransitoStore permisoTransitoStore;

    @Override
    public void createPermisoTransito(UUID idTransito, List<AccesosBean> accesosBeanList) throws AccesoDatosException {
        try {
            for (AccesosBean accesosBean : accesosBeanList) {
                PermisoTransitoBean permisoTransitoBean = getPermisoTransitoBean(idTransito, accesosBean);
                permisoTransitoStore.createPermisoTransito(permisoTransitoBean);
            }
        } catch (MyBatisSystemException e) {
            throw new AccesoDatosException("MyBatisSystemException running createPermisoTransito", e);
        }
    }

    private PermisoTransitoBean getPermisoTransitoBean(UUID idTransito, AccesosBean accesosBean) {
        PermisoTransitoBean permisoTransitoBean = new PermisoTransitoBean();
        permisoTransitoBean.setIdTransito(idTransito);
        permisoTransitoBean.setPermiso(accesosBean.getPermiso());
        permisoTransitoBean.setTipoPermiso(accesosBean.getTipoPermiso());
        permisoTransitoBean.setCondicionAutorizadora(accesosBean.getCondicionAutorizadora());
        permisoTransitoBean.setCondicionSubautorizadora(accesosBean.getCondicionSubautorizadora());
        return permisoTransitoBean;
    }
}
