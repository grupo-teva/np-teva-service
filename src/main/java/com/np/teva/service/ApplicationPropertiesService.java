package com.np.teva.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@Getter
@Setter
public class ApplicationPropertiesService {

    @Value("${drools.url}")
    private String droolsUrl;

    // TODO: Arreglar esto
    //@Value("${drools.nombremunicipio}")
    //private String nombreMunicipio;

    //@Value("${spring.datasource.intranet.url}")
    //private String intranetDataSourceUrl;

    //@Value("${spring.datasource.intranet.username}")
    //private String intranetDataSourceUser;

    //@Value("${spring.datasource.intranet.password}")
    //private String intranetDataSourcePwd;




}
