package oop.labs.lab4.math.model.simplets;

import oop.labs.lab4.math.model.MathObject;
import oop.labs.lab4.math.parse.MathParser;
import oop.labs.lab4.math.parse.ParsingSourceIterator;
import oop.labs.lab4.math.parse.ParsingSource;

import java.text.ParseException;
import java.util.Objects;


@SuppressWarnings("unused")
public final class VariableDfn implements VariableDefinition
{
    @Override public boolean isImmutable() { return true; }


    private final String name;
    private final Integer index;
    private final String subName;


    @Override
    public boolean equivalent(MathObject o)
    {
        if (this == o) return true;
        if (!(o instanceof VariableDefinition other)) return false;

        if (name().equals(other.name())) return false;
        if (!Objects.equals(index(), other.index())) return false;
        return Objects.equals(subName(), other.subName());
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VariableDfn that = (VariableDfn) o;

        if (!name.equals(that.name)) return false;
        if (!Objects.equals(index, that.index)) return false;
        return Objects.equals(subName, that.subName);
    }

    @Override
    public int hashCode()
    {
        int result = name.hashCode();
        result = 31 * result + (index != null ? index.hashCode() : 0);
        result = 31 * result + (subName != null ? subName.hashCode() : 0);
        return result;
    }


    public VariableDfn(String name, String subName, Integer index)
    {
        this.name = name;
        this.index = index;
        this.subName = subName;
    }

    public VariableDfn(String name, String subName) { this(name, subName, null); }
    public VariableDfn(String name, Integer index) { this(name, null, index); }
    public VariableDfn(String name) { this(name, null, null); }


    public static VariableDfn parse(String source) throws ParseException { return parse(new ParsingSource(source)); }
    public static VariableDfn parse(ParsingSourceIterator source) throws ParseException
    {
        String name = MathParser.parseAlpha(source);
        if (name == null) return null;


        Integer index = null;
        var hasIndex = false;

        String subName = null;
        var hasSubName = false;


        if (source.hasCurrent())
        {
            if (source.current() == '#') hasSubName = true;
            else if (Character.isDigit(source.current())) hasIndex = true;

            if (hasSubName)
            {
                source.move();
                subName = MathParser.parseAlpha(source);
                if (subName == null) throw source.createException();
                if (source.hasCurrent() && Character.isDigit(source.current())) hasIndex = true;
            }

            if (hasIndex) index = MathParser.parseUInt(source);
        }

        return new VariableDfn(name, subName, index);
    }


    @Override
    public String toExpression()
    {
        var builder = new StringBuilder(name);
        if (subName != null) builder.append('#').append(subName);
        if (index != null) builder.append(index);
        return builder.toString();
    }
    @Override public String toString() { return toExpression(); }


    @Override public String name() { return name; }
    @Override public String subName() { return subName; }
    @Override public Integer index() { return index; }
}
