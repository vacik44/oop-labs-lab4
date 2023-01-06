package oop.labs.lab4.service.providers;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;
import oop.labs.lab4.service.mapping.ApplicationMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SerializationProvider
{
    private final ObjectMapper jsonObjMapper;


    public SerializationProvider(@Qualifier("DeserializableTypesMapper") ApplicationMapper deserObjMapper)
    {
        this.jsonObjMapper = configureMapper(new ObjectMapper().registerModule(buildConfig(deserObjMapper)));
    }

    protected Module buildConfig(ApplicationMapper deserObjMapper)
    {
        var module = new SimpleModule();
        for (var name: deserObjMapper.getRegisteredNames()) module.registerSubtypes(new NamedType(deserObjMapper.getClassForName(name), name));
        return module;
    }

    protected ObjectMapper configureMapper(ObjectMapper jsonObjMapper)
    {
        return jsonObjMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    }


    public ObjectMapper mapper() { return jsonObjMapper; }
}
