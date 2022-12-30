package oop.labs.lab4.service.math.parse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public final class MathParserSource
{
    private List<Character> origin;
    private List<Character> remind;


    public MathParserSource(String originSource) { this(originSource.chars().mapToObj(c -> (char) c).toList()); }
    public MathParserSource(List<Character> originSource)
    {
        this.origin = originSource;
        this.remind = new ArrayList<>(originSource);
    }


    public Iterable<Character> getOriginCharacters() { return Collections.unmodifiableList(origin); }
    public Iterable<Character> getRemindCharacters() { return Collections.unmodifiableList(remind); }

    public Character getOrigin(int position) { return origin.get(position); }
    public Character getRemind(int position) { return remind.get(position); }

    public boolean hasEmptyOrigin() { return getOriginLength() == 0; }
    public boolean hasEmptyRemind() { return getRemindLength() == 0; }

    public Character getOriginHead() { return getOrigin(0); }
    public Character getRemindHead() { return getRemind(0); }

    public int getOriginLength() { return origin.size(); }
    public int getRemindLength() { return remind.size(); }


    public void pull(MathParserSource branch) { remind = branch.remind; }
    public MathParserSource newBranch() { return new MathParserSource(remind); }


    public void moveNext() { moveNext(1); }
    public void moveNext(int characters) { remind = remind.subList(characters, getOriginLength() - 1); }

    public boolean hasMoved() { return getOriginLength() == getRemindLength(); }

    public void commit() { origin = remind; remind = new ArrayList<>(origin); }
}
