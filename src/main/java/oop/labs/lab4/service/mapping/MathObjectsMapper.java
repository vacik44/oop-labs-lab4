package oop.labs.lab4.service.mapping;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import oop.labs.lab4.data.configs.MappingConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class MathObjectsMapper
{
    private final Map<String, Class<?>> entities;
    private final ObjectMapper objectMapper = new ObjectMapper();


    MathObjectsMapper(@Qualifier("MathObjectsMappingConfiguration") MappingConfiguration configuration) throws ClassNotFoundException
    {
        entities = new HashMap<>();

        for (var clazz : configuration.getClasses())
        {
            var entity = clazz.getClazz();
            var jsonName = entity.getAnnotationsByType(JsonRootName.class)[0].value();

            entities.put(jsonName, entity);
        }
    }


    public Class<?> getEntityForName(String name)
    {
        return entities.get(name);
    }

    public Object json2Entity(JsonNode jsonEntity, String headId) throws MappingException
    {
        try
        {
            var names = jsonEntity.get(headId).fields();
            if (!names.hasNext()) throw new MappingException("Model entity not found.");
            var entry = names.next();

            return objectMapper.treeToValue(entry.getValue(), getEntityForName(entry.getKey()));
        }
        catch (MappingException | JsonProcessingException e)
        {
            throw new MappingException(e);
        }
    }
}
