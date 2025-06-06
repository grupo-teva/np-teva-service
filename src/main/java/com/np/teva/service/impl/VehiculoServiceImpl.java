package com.np.teva.service.impl;

import com.np.teva.core.bean.VehiculoBean;
import com.np.teva.core.exception.AccesoDatosException;
import com.np.teva.persistence.mybatis.store.VehiculoStore;
import com.np.teva.service.VehiculoService;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoStore vehiculoStore;

    @Override
    public VehiculoBean findVehiculo(String matricula) throws AccesoDatosException {
        VehiculoBean vehiculo = null;

        try {
            vehiculo = vehiculoStore.findVehiculo(matricula);
            if (vehiculo == null){
                vehiculo = new VehiculoBean();
                vehiculo.setMatricula(matricula);
                vehiculo.setMunicipio("");
            }
        } catch (MyBatisSystemException e) {
            throw new AccesoDatosException("MyBatisSystemException running findVehiculo.", e);
        }

        return vehiculo;
    }

    @Override
    public List<String> findTitularesVehiculo(String matricula) throws AccesoDatosException {
        List<String> titulares = new ArrayList<>();
        try {
            titulares = vehiculoStore.findTitulares(matricula);
        } catch (MyBatisSystemException e) {
            throw new AccesoDatosException("MyBatisSystemException running findTitulares.", e);
        }

        return titulares;
    }
}
