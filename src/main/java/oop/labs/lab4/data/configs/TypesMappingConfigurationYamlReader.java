package oop.labs.lab4.data.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;

@Component
public class TypesMappingConfigurationYamlReader
{
    final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    public TypesMappingConfiguration readMappingConfiguration(String configPath) throws IOException
    {
        var configFile = new File(configPath);
        return objectMapper.readValue(configFile, TypesMappingConfiguration.class);
    }
}
