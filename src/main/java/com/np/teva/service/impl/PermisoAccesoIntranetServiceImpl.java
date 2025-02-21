package com.np.teva.service.impl;

import com.np.teva.core.bean.AccesoIntranetBean;
import com.np.teva.core.exception.AccesoDatosException;
import com.np.teva.persistence.mybatis.store.PermisoAccesoIntranetStore;
import com.np.teva.service.PermisoAccesoIntranetService;
import org.apache.commons.collections4.ListUtils;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Service
public class PermisoAccesoIntranetServiceImpl implements PermisoAccesoIntranetService {

    @Autowired
    private PermisoAccesoIntranetStore permisoAccesoIntranetStore;

    @Override
    public List<AccesoIntranetBean> getPermisosAcessoByPlate(String plate, Timestamp day, Integer pdc) throws AccesoDatosException {
        List<AccesoIntranetBean> accesos = Collections.emptyList();

        try {
            List<AccesoIntranetBean> accesosEstandar = permisoAccesoIntranetStore.getPermisosAcessoByPlate(plate, day);
            List<AccesoIntranetBean> accesosPuntuales = permisoAccesoIntranetStore.getPermisosPuntualesAcessoByPlate(plate, day);
            List<AccesoIntranetBean> accesosColectivos = permisoAccesoIntranetStore.getPermisosPuntualesColectivoAcessoByPlate(plate, day, pdc);
            List<AccesoIntranetBean> accesosParking = permisoAccesoIntranetStore.getPermisosPuntualesParkingAcessoByPlate(plate, day);

            accesos = ListUtils.union(accesosEstandar, accesosPuntuales);
            accesos = ListUtils.union(accesos, accesosColectivos);
            accesos = ListUtils.union(accesos, accesosParking);

        } catch (MyBatisSystemException e) {
            throw  new AccesoDatosException("MyBatisSystemException running getPermisosAccesoByPlate.", e);
        }

        return accesos;
    }
}
