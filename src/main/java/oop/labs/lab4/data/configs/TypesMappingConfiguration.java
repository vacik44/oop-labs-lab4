package oop.labs.lab4.data.configs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@JsonRootName("mapConfig")
public class TypesMappingConfiguration
{
    @JsonProperty("classes") private List<TypeMappingConfiguration> classes;

    @JsonSetter("classes")
    private void setClasses(List<TypeMappingConfiguration> classes) { this.classes = Optional.ofNullable(classes).orElse(new ArrayList<>(0)); }
    public List<TypeMappingConfiguration> getClasses() { return Collections.unmodifiableList(classes); }
}
