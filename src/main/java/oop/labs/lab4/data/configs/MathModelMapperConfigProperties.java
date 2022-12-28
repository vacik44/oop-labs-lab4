package oop.labs.lab4.data.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.data.cfg.math.model")
public class MathModelMapperConfigProperties
{
    private String mapperCfgPath;

    public String getMapperCfgPath() { return mapperCfgPath; }
    public void setMapperCfgPath(String mapperCfgPath) { this.mapperCfgPath = mapperCfgPath; }
}
