package oop.labs.lab4.data.configs;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class MathExternModelMapperConfig
{
    private final EntitiesConfig entitiesConfig;
    public EntitiesConfig getEntitiesConfig() { return entitiesConfig; }


    @Autowired
    MathExternModelMapperConfig(@Value("${app.service.math.model.extern-mapper-cfg-path}") String path)
    {
        try
        {
            XmlMapper mapper = new XmlMapper();
            XMLStreamReader reader = XMLInputFactory.newFactory().createXMLStreamReader(new FileInputStream(path));
            entitiesConfig = mapper.readValue(reader, EntitiesConfig.class);
        }
        catch (XMLStreamException | IOException e)
        {
            throw new RuntimeException(e);
        }

    }


    @JacksonXmlRootElement(localName = "entities")
    public static final class EntitiesConfig
    {
        @JacksonXmlElementWrapper(localName = "entity", useWrapping = false)
        private List<EntityConfig> entity;

        public List<EntityConfig> getEntitiesList() { return Collections.unmodifiableList(entity); }
    }

    @JacksonXmlRootElement(localName = "entity")
    public static final class EntityConfig
    {
        @JacksonXmlProperty(isAttribute = true, localName = "class-path")
        private String classPath;

        public String getClassPath() { return classPath; }
    }
}
