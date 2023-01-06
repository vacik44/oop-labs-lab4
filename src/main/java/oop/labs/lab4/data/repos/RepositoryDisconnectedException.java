package oop.labs.lab4.data.repos;

@SuppressWarnings("unused")
public class RepositoryDisconnectedException extends RepositoryException
{
    public RepositoryDisconnectedException(String msg) { super(msg); }
    public RepositoryDisconnectedException(Throwable cause) { super(cause); }

    public RepositoryDisconnectedException() { this("Unable to operate repository in disconnected state."); }
}
