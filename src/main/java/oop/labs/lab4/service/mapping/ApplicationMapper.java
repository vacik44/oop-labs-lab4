package oop.labs.lab4.service.mapping;

import java.util.Collection;

public interface ApplicationMapper
{
    default Class<?> getClassForName(String name) { throw new UnsupportedOperationException(); }
    default Object getInstanceForName(String name) { throw new UnsupportedOperationException(); }
    default Collection<String> getRegisteredNames() { throw new UnsupportedOperationException(); }
}
