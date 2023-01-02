package oop.labs.lab4.data.repos;

import oop.labs.lab4.data.DataAccessException;

public class RepositoryException extends DataAccessException
{
    public RepositoryException() {}
    public RepositoryException(String msg) { super(msg); }
    public RepositoryException(Throwable cause) { super(cause); }
}
