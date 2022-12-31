package oop.labs.lab4.data.configs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("classMapConfig")
@SuppressWarnings("unused")
public class ClassMappingConfiguration
{
    @JsonProperty("class") private String className;

    public Class<?> getClazz() throws ClassNotFoundException { return Class.forName(className); }
    public String getClassName() { return className; }
}
