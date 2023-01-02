package oop.labs.lab4;

import oop.labs.lab4.data.configs.TypesMappingConfiguration;
import oop.labs.lab4.data.configs.TypesMappingConfigurationYamlReader;
import oop.labs.lab4.data.repos.RecordsRepository;
import oop.labs.lab4.data.repos.RepositoryInternalException;
import oop.labs.lab4.data.repos.RepositoryReconnectionAttemptException;
import oop.labs.lab4.service.mapping.ApplicationMapper;
import oop.labs.lab4.service.mapping.BeansXmlMapper;
import oop.labs.lab4.service.mapping.JsonTypesMapper;
import oop.labs.lab4.service.mapping.MappingException;
import oop.labs.lab4.service.providers.SerializationProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties({ApplicationProperties.class})
public class ApplicationConfig
{
    @Bean(name="DeserializableTypesMappingConfig")
    public TypesMappingConfiguration getDeserializableObjectsMappingConfig(TypesMappingConfigurationYamlReader configurationMapper, @Value("${app.service.mapping.deser-types-map-file-path}") String configPath) throws IOException
    {
        return configurationMapper.readMappingConfiguration(configPath);
    }

    @Bean(name="DeserializableTypesMapper")
    public ApplicationMapper getDeserializableObjectsMapper(@Qualifier("DeserializableTypesMappingConfig") TypesMappingConfiguration mappingConfig) throws MappingException
    {
        return new JsonTypesMapper(mappingConfig);
    }

    @Bean(name="MathSolversMapper")
    public ApplicationMapper getMathSolversMapper(@Value("${app.service.mapping.math-solvers-beans-xml-path}") String xmlMapPath)
    {
        return new BeansXmlMapper(xmlMapPath);
    }

    @Bean("PrimaryCalculationsRepository")
    public RecordsRepository getPrimaryCalculationsRepository(@Value("${app.data.repos.primary-repository-path}") String repositoryPath, SerializationProvider serializationProvider) throws RepositoryReconnectionAttemptException, RepositoryInternalException
    {
        return new RecordsRepository(repositoryPath, serializationProvider.mapper()).initIfNotExistsAndConnect();
    }
}
