package oop.labs.lab4.service.mapping;

import com.fasterxml.jackson.annotation.JsonTypeName;
import oop.labs.lab4.data.configs.TypesMappingConfiguration;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ConfigTypesMapper implements ApplicationMapper
{
    private final Map<String, Class<?>> entities;


    public ConfigTypesMapper(TypesMappingConfiguration configuration) throws MappingException
    {
        entities = new HashMap<>();

        try
        {
            for (var clazz : configuration.getClasses())
            {
                var entity = clazz.getClazz();
                var jsonName = entity.getAnnotationsByType(JsonTypeName.class)[0].value();

                entities.put(jsonName, entity);
            }
        }
        catch (ClassNotFoundException e)
        {
            throw new MappingException(e);
        }

    }


    public Class<?> getClassForName(String name) { return entities.get(name); }
    public Collection<String> getRegisteredNames() { return entities.keySet(); }
}
