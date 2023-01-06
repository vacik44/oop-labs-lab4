package oop.labs.lab4.service.mapping;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
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


    @Override public Class<?> getClassForName(String name)
    {
        try { return context.getType(name); }
        catch (NoSuchBeanDefinitionException e) { throw new MappingManagementException(e); }
    }

    @Override public Object getInstanceForName(String name)
    {
        try { return context.getBean(name); }
        catch (NoSuchBeanDefinitionException e) { throw new MappingManagementException(e); }
    }

    @Override public Collection<String> getRegisteredNames()
    {
        return Arrays.stream(context.getBeanDefinitionNames()).toList();
    }
}
