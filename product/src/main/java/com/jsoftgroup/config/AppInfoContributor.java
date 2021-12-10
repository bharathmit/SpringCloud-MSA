package com.jsoftgroup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AppInfoContributor implements InfoContributor {

    @Autowired
    private ServletWebServerApplicationContext server;

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Integer> serverinfo = new HashMap<>();

        serverinfo.put("port",server.getWebServer().getPort());

        builder.withDetail("serverInfo", serverinfo);
    }
}
