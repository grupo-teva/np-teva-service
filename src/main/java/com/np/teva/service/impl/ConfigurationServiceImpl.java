package com.np.teva.service.impl;

import com.np.teva.service.ConfigurationService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    @Override
    public String getProperty(String name) {
        try {
            Properties properties = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream fileStream = loader.getResourceAsStream("/application.properties");
            properties.load(fileStream);

            return  properties.getProperty(name);
        }catch (IOException e) {
            return  "";
        }
    }

}
