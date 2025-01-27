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

}
