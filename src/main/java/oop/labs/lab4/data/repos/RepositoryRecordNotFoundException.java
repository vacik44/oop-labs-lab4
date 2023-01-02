package oop.labs.lab4.data.repos;

public class RepositoryRecordNotFoundException extends RepositoryException
{
    public RepositoryRecordNotFoundException() {}
    public RepositoryRecordNotFoundException(String msg) { super(msg); }
    public RepositoryRecordNotFoundException(Throwable cause) { super(cause); }
}
