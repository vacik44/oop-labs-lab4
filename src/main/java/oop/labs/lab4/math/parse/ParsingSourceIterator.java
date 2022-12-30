package oop.labs.lab4.math.parse;

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
