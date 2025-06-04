package com.np.teva.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.np.teva.core.bean.*;
import com.np.teva.core.exception.AccesoDatosException;
import com.np.teva.core.exception.DroolsException;
import com.np.teva.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Service
public class DroolsServiceImpl implements DroolsService {

    private static Logger LOG = LoggerFactory.getLogger(DroolsServiceImpl.class);

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private ReglaTransitoService reglaTransitoService;

    @Autowired
    private ReglaNegocioService reglaNegocioService;

    @Autowired
    private ApplicationPropertiesService applicationPropertiesService;

    @Override
    public DroolsResponseBean isValidVehicle(TransitoBean transitoBean, VehiculoBean vehiculoBean, List<AccesosBean> accesosBeanList, boolean isLaborable, List<ReglaAccesoBean> reglas, Boolean isDomiciliado, Boolean cumpleItinerario, Boolean historico, Integer codZona) throws DroolsException {

        DroolsBean droolsBean = new DroolsBean(transitoBean, vehiculoBean, accesosBeanList, reglas, isLaborable, isDomiciliado, cumpleItinerario, historico, codZona);
        DroolsResponseBean response = new DroolsResponseBean();
        String url = applicationPropertiesService.getDroolsUrl();
        try {
            URI uri = new URI(url);
            ResponseEntity<String> routeResponse = restTemplate.postForEntity(uri, droolsBean, String.class);
            if (HttpStatus.OK.equals(routeResponse.getStatusCode())) {
                String responseData = routeResponse.getBody();
                ObjectMapper mapper = new ObjectMapper();
                response = mapper.readValue(responseData, DroolsResponseBean.class);

                if (StringUtils.isNotBlank(response.getErrorMessage())) {
                    LOG.info("Drools responde KO. Error: " + response.getErrorMessage());
                    throw new DroolsException(response.getErrorMessage());
                }
            } else {
                String errorStatus = "Response status is not OK, is " + routeResponse.getStatusCode().value() + " getting rest drools request.";
                throw new DroolsException(errorStatus);
            }
        } catch (URISyntaxException e) {
            throw new DroolsException("URISyntaxException running isValidVehicle.", e);
        } catch (Exception e) {
            throw new DroolsException("Exception running isValidVehicle", e);
        }

        return response;
    }

    @Override
    public void createReglaTransitoByName(TransitoBean transitoBean, DroolsResponseBean droolsResponse) throws AccesoDatosException {
        String finalRule = droolsResponse.getResult().getFinalRule();
        Integer reglaDrools = getReglaNegocio(finalRule);

        reglaTransitoService.createReglaTransito(transitoBean.getId(), reglaDrools, droolsResponse.getResult().isResult());
    }

    private Integer getReglaNegocio(String finalRule) throws AccesoDatosException {
        Integer reglaNegocio = reglaNegocioService.getReglaNegocioId(finalRule);
        if (reglaNegocio == null) {
            reglaNegocio = reglaNegocioService.insertReglaNegocio(finalRule);
        }
        return reglaNegocio;
    }
}
