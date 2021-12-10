package com.jsoftgroup.config;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

@Component
public class AppInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> serverinfo = new HashMap<>();

        String hostName="";
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        }
        catch(Exception e) {
            hostName = e.getMessage().split(":")[0];
        }

        serverinfo.put("hostName",hostName);

        builder.withDetail("serverInfo", serverinfo);
    }
}
