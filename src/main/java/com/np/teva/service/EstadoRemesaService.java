package com.np.teva.service;

import com.np.teva.core.bean.EstadoRemesaBean;
import com.np.teva.core.exception.AccesoDatosException;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface EstadoRemesaService {

    List<EstadoRemesaBean> findRemesasByEstado(int codigoEstado, int codigoZona) throws AccesoDatosException;

    List<EstadoRemesaBean> findRemesasByEstado(int codigoEstado) throws AccesoDatosException;

    List<EstadoRemesaBean> findRemesasByEstadoFecha(int cod_estado, int cod_zona, Date fec_sancion) throws AccesoDatosException;

    boolean updateEstadoRemesa(Date fechaSancion, int codigoZona, int estado) throws AccesoDatosException;
}
