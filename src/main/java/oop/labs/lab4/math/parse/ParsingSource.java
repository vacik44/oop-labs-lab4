package oop.labs.lab4.math.parse;

import java.text.ParseException;
import java.util.*;

@SuppressWarnings("unused")
public final class ParsingSource implements ParsingSourceIterator
{
    private String origin;
    private final Iterator<Character> iterator;

    private Character current;
    private int position;


    public ParsingSource(String source)
    {
        this(source.chars().mapToObj(c -> (char) c).iterator());
        origin = source;
    }

    public ParsingSource(Iterator<Character> source)
    {
        this(source, 0);
    }

    public ParsingSource(Iterator<Character> source, int startPosition)
    {
        position = startPosition;
        iterator = source;
        move();
    }


    @Override public void move()
    {
        position ++;
        current = iterator.hasNext() ? iterator.next() : null;
    }

    @Override public boolean hasCurrent() { return current != null; }
    @Override public Character current() { return current; }
    @Override public int currentPosition() { return position; }

    @Override public ParseException createException() { return new ParseException(origin, position); }
    @Override public String extractRemaining() { return origin == null ? null : origin.substring(position); }
}
