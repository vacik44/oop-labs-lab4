package oop.labs.lab4.service.math;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import oop.labs.lab4.data.configs.MathExternModelMapperConfig;
import oop.labs.lab4.service.math.exceptions.MathExternModelRecognitionException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MathExternModelMapper
{
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ModelEntities modelEntities;
    public ModelEntities getModelEntities() { return modelEntities; }


    MathExternModelMapper(MathExternModelMapperConfig config)
    {
        try
        {
            modelEntities = new ModelEntities(config);
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }


    public Object json2Entity(JsonNode jsonEntity, String headId) throws MathExternModelRecognitionException
    {
        try
        {
            var names = jsonEntity.get(headId).fields();
            if (!names.hasNext()) throw new MathExternModelRecognitionException("Model entity not found.");
            var entry = names.next();

            return objectMapper.treeToValue(entry.getValue(), modelEntities.getEntityForName(entry.getKey()).getClazz());
        }
        catch (MathExternModelRecognitionException | JsonProcessingException e)
        {
            throw new MathExternModelRecognitionException(e);
        }
    }


    public static class ModelEntities
    {
        private final Map<String, ModelEntity> entities;

        public ModelEntities(MathExternModelMapperConfig config) throws ClassNotFoundException
        {
            entities = new HashMap<>();

            for (var entityConfig : config.getEntitiesConfig().getEntitiesList())
            {
                var modelEntity = new ModelEntity(entityConfig);
                var jsonName = modelEntity.clazz.getAnnotationsByType(JsonRootName.class)[0].value();

                entities.put(jsonName, modelEntity);
            }
        }

        public ModelEntity getEntityForName(String name) { return entities.get(name); }
    }

    public static class ModelEntity
    {
        private final Class<?> clazz;

        public ModelEntity(MathExternModelMapperConfig.EntityConfig config) throws ClassNotFoundException
        {
            clazz = Class.forName(config.getClassPath());
        }

        public Class<?> getClazz() { return clazz; }
    }
}
