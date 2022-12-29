package oop.labs.lab4.data.configs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Collections;
import java.util.List;

@JsonRootName("mapConfig")
public class MappingConfiguration
{
    @JsonProperty("classes")
    private List<ClassMappingConfiguration> classes;

    public List<ClassMappingConfiguration> getClasses() { return Collections.unmodifiableList(classes); }
}
