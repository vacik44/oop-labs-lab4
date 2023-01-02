package oop.labs.lab4.data.repos;

public class RepositoryReconnectionAttemptException extends RepositoryException
{
    public RepositoryReconnectionAttemptException(String msg) { super(msg); }
    public RepositoryReconnectionAttemptException(Throwable cause) { super(cause); }

    public RepositoryReconnectionAttemptException() { this("Repository already initialized and connected."); }
}
