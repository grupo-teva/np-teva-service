package com.np.teva.service.impl;

import com.np.teva.core.bean.AuditoriaBatchBean;
import com.np.teva.core.exception.AccesoDatosException;
import com.np.teva.service.AuditoriaBatchDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class AuditoriaBatchDAOImpl implements AuditoriaBatchDAO {

    @Autowired
    private DatabaseHandler databaseHandler;

    @Override
    public boolean insert(AuditoriaBatchBean auditoria) throws AccesoDatosException {
        int id = 0;
        String query = "{? = call \"mc_intranet\".\"IAuditoriaBatch\"(?,?,?,?,?)}";

        try (Connection con = databaseHandler.getConnectionIntranet();
             CallableStatement stmt = con.prepareCall(query)) {

            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setString(2, auditoria.getNombreBatch());
            stmt.setTimestamp(3, Timestamp.valueOf(auditoria.getFechaInicio()));
            stmt.setTimestamp(4, Timestamp.valueOf(auditoria.getFechaFin()));
            stmt.setInt(5, auditoria.getEstadoBatch().getCodigo());
            stmt.setString(6, auditoria.getDescripcionError());

            stmt.execute();
            id = stmt.getInt(1);

            return id > 0;
        } catch (SQLException e) {
            throw new AccesoDatosException("SQLExcepcion en insert.", e);
        }
    }
}
