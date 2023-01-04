package oop.labs.lab4.data.repos;

@SuppressWarnings("unused")
public class RepositoryInternalException extends RepositoryException
{
    public RepositoryInternalException() {}
    public RepositoryInternalException(String msg) { super(msg); }
    public RepositoryInternalException(Throwable cause) { super(cause); }
}
