package oop.labs.lab4.service.math.parse;

import java.text.ParseException;

public interface ParsingSourceIterator
{
    void move();

    boolean hasCurrent();
    Character current();
    int currentPosition();

    ParseException createException();
    String extractRemaining();
}
