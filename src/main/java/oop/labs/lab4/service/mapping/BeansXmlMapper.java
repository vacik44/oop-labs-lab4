package oop.labs.lab4.service.mapping;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Arrays;
import java.util.Collection;

public class BeansXmlMapper implements ApplicationMapper
{
    private final ApplicationContext context;


    public BeansXmlMapper(String... xmlMapPaths)
    {
        context = new FileSystemXmlApplicationContext(xmlMapPaths);
    }


    @Override public Class<?> getClassForName(String name) { return context.getType(name); }
    @Override public Object getInstanceForName(String name) { return context.getBean(name); }
    @Override public Collection<String> getRegisteredNames() { return Arrays.stream(context.getBeanDefinitionNames()).toList(); }
}
