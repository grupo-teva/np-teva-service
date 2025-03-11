package com.np.teva.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracion {

    @Value("${spring.datasource.url}")
    private String validacionUrl;

    @Value("${spring.datasource.username}")
    private String validacionUser;

    @Value("${spring.datasource.password}")
    private String validacionPassword;

    @Value("${spring.datasource.intranet.url}")
    private String intranetUrl;

    @Value("${spring.datasource.intranet.username}")
    private String intranetUser;

    @Value("${spring.datasource.intranet.password}")
    private String intranetPassword;


    public String getValidacionUrl() {return validacionUrl; }

    public String getValidacionUser() {return validacionUser;}

    public String getValidacionPassword() {return validacionPassword;}

    public String getIntranetUrl() {
        return intranetUrl;
    }

    public String getIntranetUser() {
        return intranetUser;
    }

    public String getIntranetPassword() {
        return intranetPassword;
    }
}
