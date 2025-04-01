package com.np.teva.service.impl;

import com.np.teva.core.exception.AccesoDatosException;
import com.np.teva.service.ConstanteIntranetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class ConstanteIntranetServiceImpl implements ConstanteIntranetService {

    @Autowired
    private DatabaseHandler databaseHandler;

    @Override
    public int getConstanteAsInt(String descripcionConstante) throws AccesoDatosException {
        int value = 0;
        String query = "select * from mc_intranet.\"SConstante\"(?)";
        ResultSet rs = null;

        try (Connection conn = databaseHandler.getConnectionIntranet();
             PreparedStatement stmt = conn.prepareStatement(query);) {

            stmt.setString(1, descripcionConstante);
            rs = stmt.executeQuery();
            while (rs.next()) {
                value = Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error in getConstanteAsInt", e);
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new AccesoDatosException("Error in getConstanteAsInt", e);
                }
        }

        return value;
    }

    @Override
    public String getConstanteAsString(String descripcionConstante) throws AccesoDatosException {
        String value = "";
        String query = "select * from mc_intranet.\"SConstante\"(?)";
        ResultSet rs = null;

        try (Connection conn = databaseHandler.getConnectionIntranet();
             PreparedStatement stmt = conn.prepareStatement(query);) {

            stmt.setString(1, descripcionConstante);
            rs = stmt.executeQuery();
            while (rs.next()) {
                value = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error in getConstanteAsInt", e);
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new AccesoDatosException("Error in getConstanteAsInt", e);
                }
        }

        return value;    }
}
