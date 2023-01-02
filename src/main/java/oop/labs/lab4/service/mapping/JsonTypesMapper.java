package oop.labs.lab4.service.mapping;

import com.fasterxml.jackson.annotation.JsonTypeName;
import oop.labs.lab4.data.configs.TypesMappingConfiguration;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class JsonTypesMapper implements ApplicationMapper
{
    private final Map<String, Class<?>> typesMap;


    public JsonTypesMapper(TypesMappingConfiguration configuration) throws MappingException
    {
        typesMap = new HashMap<>();

        try
        {
            for (var clazz : configuration.getClasses())
            {
                var entity = clazz.getClazz();
                var annotations = entity.getAnnotationsByType(JsonTypeName.class);
                var jsonName = annotations.length == 0 ? entity.getSimpleName() : annotations[0].value();

                typesMap.put(jsonName, entity);
            }
        }
        catch (ClassNotFoundException e)
        {
            throw new MappingInitiationException(e);
        }

    }


    public Class<?> getClassForName(String name)
    {
        var type = typesMap.get(name);
        if (type == null) throw new MappingManagementException(String.format("Name '%s' was not mapped by current mapper.", name));
        return type;
    }

    public Collection<String> getRegisteredNames() { return typesMap.keySet(); }
}
