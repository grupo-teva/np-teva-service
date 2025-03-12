package com.np.teva.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class DatabaseHandler {

    @Autowired
    private Configuracion configuracion;

    public DatabaseHandler(){
    }

    public Connection getConnectionValidacion() throws SQLException {
        Connection con = null;
        con = DriverManager.getConnection(configuracion.getValidacionUrl(), configuracion.getValidacionUser(), configuracion.getValidacionPassword());

        return con;
    }

    public Connection getConnectionIntranet() throws SQLException {
        Connection con = null;
        con = DriverManager.getConnection(configuracion.getIntranetUrl(), configuracion.getIntranetUser(), configuracion.getIntranetPassword());

        return con;
    }
}
