package oop.labs.lab4.data.configs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("classMapConfig")
public class TypeMappingConfiguration
{
    @JsonProperty("class") private String className;

    public Class<?> getClazz() throws ClassNotFoundException { return Class.forName(className); }
    public String getClassName() { return className; }
}
