package com.wwj.authority.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author 阿涛
 * @date 2021-06-01
 */
@Data
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoredUrlsConfig {

    private List<String> urls;

}
