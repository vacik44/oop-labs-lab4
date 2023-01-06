package oop.labs.lab4.service.exceptions;

import oop.labs.lab4.ApplicationException;

@SuppressWarnings("unused")
public class ApplicationClassifiedException extends ApplicationException
{
    public ApplicationClassifiedException() {}
    public ApplicationClassifiedException(String msg) { super(msg); }
    public ApplicationClassifiedException(Throwable cause) { super(cause); }
}
