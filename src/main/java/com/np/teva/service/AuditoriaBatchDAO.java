package com.np.teva.service;

import com.np.teva.core.bean.AuditoriaBatchBean;
import com.np.teva.core.exception.AccesoDatosException;

public interface AuditoriaBatchDAO {

    boolean insert(AuditoriaBatchBean auditoria) throws AccesoDatosException;
}
