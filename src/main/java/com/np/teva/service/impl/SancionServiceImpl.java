package com.np.teva.service.impl;

import com.np.teva.core.exception.AccesoDatosException;
import com.np.teva.persistence.mybatis.store.SancionStore;
import com.np.teva.service.SancionService;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class SancionServiceImpl implements SancionService {

    @Autowired
    public SancionStore sancionStore;

    @Override
    public int contarSancionesEstado(Date fechaSancion, int codigoEstadoSancion) throws AccesoDatosException {
        int total = 0;
        try {
            total = sancionStore.contarSancionesEstado(fechaSancion, codigoEstadoSancion);
        } catch (MyBatisSystemException mex) {
            throw new AccesoDatosException("MyBatisSystemException running contarSancionesEstado", mex);
        } catch (DataAccessException dex) {
            throw new AccesoDatosException("DataAccessException running contarSancionesEstado", dex);
        }

        return total;
    }

    @Override
    public int updateSancion(UUID codigoTransito, int codigoEstado, int codigoSistema) throws AccesoDatosException {
        int total = 0;
        try {
            total = sancionStore.updateEstado(codigoTransito, codigoEstado, codigoSistema);
        } catch (MyBatisSystemException mex) {
            throw new AccesoDatosException("MyBatisSystemException running updateSancion", mex);
        } catch (DataAccessException dex) {
            throw new AccesoDatosException("DataAccessException running updateSancion", dex);
        }

        return total;
    }

    @Override
    public int contarSancionesPendientesValidar(Date fec_sancion) throws AccesoDatosException {
        int total = 0;
        try {
            total = sancionStore.contarSancionesPendientesValidar(fec_sancion);
        } catch (MyBatisSystemException mex) {
            throw new AccesoDatosException("MyBatisSystemException running contarSancionesEstado", mex);
        } catch (DataAccessException dex) {
            throw new AccesoDatosException("DataAccessException running contarSancionesEstado", dex);
        }

        return total;
    }

    @Override
    public int contarSancionesPendientesQC(Date fec_sancion, int cod_zona) throws AccesoDatosException {
        int total = 0;
        try {
            total = sancionStore.contarSancionesPendientesQC(fec_sancion, cod_zona);
        } catch (MyBatisSystemException mex) {
            throw new AccesoDatosException("MyBatisSystemException running contarSancionesEstado", mex);
        } catch (DataAccessException dex) {
            throw new AccesoDatosException("DataAccessException running contarSancionesEstado", dex);
        }

        return total;
    }
}
