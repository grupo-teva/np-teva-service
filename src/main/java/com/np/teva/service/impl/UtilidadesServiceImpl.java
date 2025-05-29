package com.np.teva.service.impl;

import com.np.teva.service.UtilidadesService;
import org.springframework.stereotype.Service;

@Service
public class UtilidadesServiceImpl implements UtilidadesService {
    @Override
    public String humanizarSegundos(int segundosTotales) {
        int horas = 0;
        int minutos = 0;
        int segundos = 0;

        horas = segundosTotales / 3600;
        if (horas > 0)
            segundosTotales = segundosTotales - (horas * 3600);
        minutos = segundosTotales / 60;
        if (minutos > 0)
            segundosTotales = segundosTotales - (minutos * 60);
        segundos = segundosTotales;

        String tiempoHumanize = "";
        if (horas > 0)
            tiempoHumanize += horas + "h ";
        if (minutos > 0)
            tiempoHumanize += minutos + "m ";
        if (segundos > 0)
            tiempoHumanize += segundos + "s ";

        return tiempoHumanize.trim();
    }
}
