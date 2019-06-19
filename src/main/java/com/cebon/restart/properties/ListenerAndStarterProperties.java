package com.cebon.restart.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: LiaoPeng
 * @Date: 2019/5/31
 */
@Component
@ConfigurationProperties(prefix = "listen-server")
@Data
public class ListenerAndStarterProperties {

    private String port;
    private String listenCmd;
    private String start;
    private String close;
    private String floder;

    private Boolean isOpen = false;

}
