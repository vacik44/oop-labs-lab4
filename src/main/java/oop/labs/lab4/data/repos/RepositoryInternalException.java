package oop.labs.lab4.data.repos;

public class RepositoryInternalException extends RepositoryException
{
    public RepositoryInternalException() {}
    public RepositoryInternalException(String msg) { super(msg); }
    public RepositoryInternalException(Throwable cause) { super(cause); }
}
