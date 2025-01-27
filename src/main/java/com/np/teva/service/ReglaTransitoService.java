package com.np.teva.service;

import com.np.teva.core.exception.AccesoDatosException;

import java.util.UUID;

public interface ReglaTransitoService {

    void createReglaTransito(UUID idTransito, int reglaId, boolean resultado) throws AccesoDatosException;
}
