package com.np.teva.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.np.teva.core.bean.*;
import com.np.teva.core.enumeration.SubCondicionAutorizadora;
import com.np.teva.core.enumeration.TipoVehiculosConstruccion;
import com.np.teva.core.enumeration.ZonaType;
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
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DroolsServiceImpl implements DroolsService {

    private static String nombreMunicipio = "Fuenlabrada";

    private static Logger LOG = LoggerFactory.getLogger(DroolsServiceImpl.class);

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private ReglaTransitoService reglaTransitoService;

    @Autowired
    private ReglaNegocioService reglaNegocioService;

    @Autowired
    private ApplicationPropertiesService applicationPropertiesService;

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private PuntoCapturaService puntoCapturaService;

    @Autowired
    private PeriodoService periodoService;

    @Autowired
    private AccesoPermitidoService accesoPermitidoService;

    @Autowired
    private PermisoAccesoIntranetService permisoAccesoIntranetService;

    @Autowired
    private ReglaAccesoService reglaAccesoService;

    @Override
    public DroolsResponseBean isValidVehicle(TransitoBean transitoBean, VehiculoBean vehiculoBean, List<AccesosBean> accesosBeanList, boolean isLaborable, List<ReglaAccesoBean> reglas, Boolean isDomiciliado, Boolean cumpleItinerario, Boolean historico) throws DroolsException {

        DroolsBean droolsBean = new DroolsBean(transitoBean, vehiculoBean, accesosBeanList, reglas, isLaborable, isDomiciliado, historico);
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
    public DroolsResponseBean getResponse(TransitoBean transitoBean, List<AccesosBean> accesos, Boolean isLaborable) throws DroolsException, AccesoDatosException {
        DroolsResponseBean response = new DroolsResponseBean();
        String url = applicationPropertiesService.getDroolsUrl();
        try {
            VehiculoBean vehiculoBean = vehiculoService.findVehiculo(transitoBean.getPlate());
            Boolean isDomiciliado = vehiculoBean.isDomiciliado(nombreMunicipio);
            Boolean isHistorico = vehiculoBean.getHistorico();
            List<ReglaAccesoBean> reglas = getReglas(transitoBean);

            DroolsBean droolsBean = new DroolsBean(transitoBean, vehiculoBean, accesos, reglas, isLaborable, isDomiciliado, isHistorico);
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
            throw new DroolsException("URISyntaxException running getResponse.", e);
        } catch (AccesoDatosException e) {
            LOG.error("AccesoDatosException running getResponse", e);
            throw new DroolsException("AccesoDatosException running getResponse", e);
        } catch (Exception e) {
            LOG.error("Exception running getResponse", e);
            throw new DroolsException("Exception running getResponse", e);
        }

        // Guardamos la respuesta de drools
        createReglaTransitoByName(transitoBean, response);

        return response;
    }

    @Override
    public DroolsResponseBean getResponseSimulado(TransitoBean transitoBean, List<AccesosBean> accesos, Boolean isLaborable) throws DroolsException, AccesoDatosException {
        DroolsResponseBean response = new DroolsResponseBean();
        String url = applicationPropertiesService.getDroolsUrl();
        try {
            VehiculoBean vehiculoBean = vehiculoService.findVehiculo(transitoBean.getPlate());
            Boolean isDomiciliado = vehiculoBean.isDomiciliado(nombreMunicipio);
            Boolean isHistorico = vehiculoBean.getHistorico();
            List<ReglaAccesoBean> reglas = getReglas(transitoBean);

            DroolsBean droolsBean = new DroolsBean(transitoBean, vehiculoBean, accesos, reglas, isLaborable, isDomiciliado, isHistorico);
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
            throw new DroolsException("URISyntaxException running getResponse.", e);
        } catch (AccesoDatosException e) {
            LOG.error("AccesoDatosException running getResponse", e);
            throw new DroolsException("AccesoDatosException running getResponse", e);
        } catch (Exception e) {
            LOG.error("Exception running getResponse", e);
            throw new DroolsException("Exception running getResponse", e);
        }

        // No guardamos registro en t_regla_transito

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

    /* TODO: Ver si es conveniente que este método esté aquí. La lista de accesos se usa en el servicio llamador para asociar los permisos al tránsito
    private List<AccesosBean> getAccesos(TransitoBean transito) throws AccesoDatosException {
        List<AccesosBean> accesos = new ArrayList<>();

        List<AccesosBean> accesosPermitidos = accesoPermitidoService.findAccesosByPlate(transito);
        List<AccesosBean> accesosPermitidosFilter = filterByTime(accesosPermitidos, transito.getTmsTransito().toLocalDateTime().toLocalTime());

        List<AccesoIntranetBean> accesosIntranet = permisoAccesoIntranetService.getPermisosAcessoByPlate(transito.getPlate(), transito.getTmsTransito(), transito.getPdc());
        List<AccesosBean> accesosBeanIntranet = createbyAccesoIntranetBean(accesosIntranet, transito.getTmsTransito().toLocalDateTime().toLocalDate());

        accesos = addOnlyNewAccesosBean(accesosPermitidosFilter, accesosBeanIntranet);

        return accesos;
    }
    */

    private List<AccesosBean> createbyAccesoIntranetBean(
            List<AccesoIntranetBean> accesoIntranetBeanList, LocalDate day) {

        List<AccesosBean> accesosBeanList = Collections.emptyList();
        if (!accesoIntranetBeanList.isEmpty()) {
            accesosBeanList = new ArrayList<AccesosBean>();
            for (AccesoIntranetBean accesoIntranetBean : accesoIntranetBeanList) {
                AccesosBean accesosBean = createAccesoBean(accesoIntranetBean, day);
                accesosBeanList.add(accesosBean);
            }
        }
        return accesosBeanList;
    }

    private AccesosBean createAccesoBean(AccesoIntranetBean accesoIntranetBean, LocalDate day) {
        LocalTime initTime = LocalTime.of(0, 0, 0);
        LocalTime finishTime = LocalTime.of(23, 59, 59);
        AccesosBean accesosBean = new AccesosBean();
        accesosBean.setTipoPermiso(accesoIntranetBean.getTipoPermiso());
        accesosBean.setCondicionSubautorizadora(accesoIntranetBean.getCondicionSubautorizadora());
        accesosBean.setCondicionAutorizadora(
                SubCondicionAutorizadora.value(accesoIntranetBean.getCondicionSubautorizadora())
                        .getCondicion());
        accesosBean.setInicioVigencia(initTime);
        accesosBean.setFinVigencia(finishTime);
        accesosBean.setDay(day);
        accesosBean.setPermiso(accesoIntranetBean.getPermiso());
        accesosBean.setCodPerfil(accesoIntranetBean.getCodPerfil());
        accesosBean.setCodColectivo(accesoIntranetBean.getCodColectivo());
        accesosBean.setParkingColectivo(accesoIntranetBean.isParkingColectivo());
        return accesosBean;
    }

    private List<AccesosBean> addOnlyNewAccesosBean(
            List<AccesosBean> accesosBeanList, List<AccesosBean> accesoIntranetBeanList) {

        if (accesoIntranetBeanList.isEmpty()) {
            return accesosBeanList;
        } else {
            if (accesosBeanList.isEmpty()) {
                return accesoIntranetBeanList;
            } else {
                List<AccesosBean> unionAccesoBean = new ArrayList<AccesosBean>();
                Set<Long> permisos = new HashSet<Long>();
                for (AccesosBean accesosBean : accesosBeanList) {
                    unionAccesoBean.add(accesosBean);
                    permisos.add(accesosBean.getPermiso());
                }
                for (AccesosBean accesosBeanToFilter : accesoIntranetBeanList) {
                    if (!permisos.contains(accesosBeanToFilter.getPermiso())) {
                        unionAccesoBean.add(accesosBeanToFilter);
                    }
                }
                return unionAccesoBean;
            }
        }
    }

    private List<AccesosBean> filterByTime(List<AccesosBean> accesosBeanList, LocalTime transitoTime) {

        List<AccesosBean> validAccesoParking = filterByTimeParking(accesosBeanList, transitoTime);
        List<AccesosBean> validAccesoNotParking = filterNotParking(accesosBeanList, transitoTime);
        validAccesoParking.addAll(validAccesoNotParking);

        return validAccesoParking;
    }

    private List<AccesosBean> filterByTimeParking(List<AccesosBean> accesosBeanList, LocalTime transitoTime) {
        List<AccesosBean> filterAccesoBean = Collections.emptyList();
        if (!accesosBeanList.isEmpty()) {
            filterAccesoBean =
                    accesosBeanList.stream()
                            .filter(accesosBean -> accesosBean.getCondicionSubautorizadora() == 1)
                            .filter(
                                    accesosBean ->
                                            accesosBean.getInicioVigencia().isBefore(transitoTime)
                                                    || accesosBean.getInicioVigencia().equals(transitoTime))
                            .filter(
                                    accesosBean ->
                                            accesosBean.getFinVigencia().isAfter(transitoTime)
                                                    || accesosBean.getFinVigencia().equals(transitoTime))
                            .collect(Collectors.toList());
        }
        return filterAccesoBean;
    }

    private List<AccesosBean> filterNotParking(
            List<AccesosBean> accesosBeanList, LocalTime transitoTime) {
        List<AccesosBean> filterAccesoBean = Collections.emptyList();
        if (!accesosBeanList.isEmpty()) {
            filterAccesoBean =
                    accesosBeanList.stream()
                            .filter(accesosBean -> accesosBean.getCondicionSubautorizadora() != 1)
                            .collect(Collectors.toList());
        }
        return filterAccesoBean;
    }

    private List<ReglaAccesoBean> getReglas(TransitoBean transito) throws AccesoDatosException {
        List<ReglaAccesoBean> reglas = new ArrayList<>();

        List<ReglaAccesoBean> reglasProhibicion = reglaAccesoService.findReglasProhibicion();
        List<ReglaAccesoBean> reglasExcepcion = reglaAccesoService.findReglasExcepcion();
        reglas.addAll(reglasExcepcion);
        reglas.addAll(reglasProhibicion);

        return reglas;
    }

}
