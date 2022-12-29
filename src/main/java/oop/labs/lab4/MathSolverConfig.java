package oop.labs.lab4;

import oop.labs.lab4.data.configs.MappingConfiguration;
import oop.labs.lab4.data.configs.YamlMappingConfigurationMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties({MathSolverProperties.class})
public class MathSolverConfig
{
    @Bean(name="MathExternModelMappingConfiguration")
    public MappingConfiguration getMathExternModelMapConfiguration(YamlMappingConfigurationMapper configurationMapper,
            @Value("${app.service.math.model.extern.map-file-path}") String configPath) throws IOException
    {
        return configurationMapper.readMappingConfiguration(configPath);
    }

    @Bean(name="MathSolversMappingConfiguration")
    public MappingConfiguration getMathSolversMapConfiguration(YamlMappingConfigurationMapper configurationMapper,
            @Value("${app.service.math.eval.solvers.map-file-path}") String configPath) throws IOException
    {
        return configurationMapper.readMappingConfiguration(configPath);
    }
}
