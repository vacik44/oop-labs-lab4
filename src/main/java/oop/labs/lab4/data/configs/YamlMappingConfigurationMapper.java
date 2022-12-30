package oop.labs.lab4.data.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;

@Repository
public class YamlMappingConfigurationMapper
{
    final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    public MappingConfiguration readMappingConfiguration(String configPath) throws IOException
    {
        var configFile = new File(configPath);
        return objectMapper.readValue(configFile, MappingConfiguration.class);
    }
}
