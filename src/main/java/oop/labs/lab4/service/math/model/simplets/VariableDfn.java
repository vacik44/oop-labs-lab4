package oop.labs.lab4.service.math.model.simplets;

import oop.labs.lab4.service.math.model.MathObject;
import oop.labs.lab4.service.math.parse.MathParser;
import oop.labs.lab4.service.math.parse.MathParserSource;
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

        if (getName().equals(other.getName())) return false;
        if (!Objects.equals(getIndex(), other.getIndex())) return false;
        return Objects.equals(getSubName(), other.getSubName());
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


    public static VariableDfn parse(String source) { return parse(new MathParserSource(source)); }
    public static VariableDfn parse(MathParserSource source)
    {
        var hasIndex = false;
        var hasSubName = false;

        String name;
        Integer index = null;
        String subName = null;

        {
            var nameBuilder = new StringBuilder();

            while(source.getRemindLength() > 0)
            {
                var c = source.getRemindHead();

                if (Character.isAlphabetic(c)) { nameBuilder.append(c); source.moveNext(); continue; }
                else if (c == '#') { hasSubName = true; source.moveNext(); }
                else if (Character.isDigit(c)) hasIndex = true;
                break;
            }

            name = nameBuilder.toString();
        }

        if (hasSubName)
        {
            var subNameBuilder = new StringBuilder();

            while(source.getRemindLength() > 0)
            {
                var c = source.getRemindHead();

                if (Character.isAlphabetic(c)) { subNameBuilder.append(c); source.moveNext(); continue; }
                if (Character.isDigit(c)) hasIndex = true;
                break;
            }

            subName = subNameBuilder.toString();
        }

        if (hasIndex)
        {
            var branch = source.newBranch();
            index = MathParser.parseInt(branch);
            source.pull(branch);
        }

        return new VariableDfn(name, subName, index);
    }


    @Override
    public String getExpression()
    {
        var builder = new StringBuilder(name);
        if (subName != null) builder.append('#').append(subName);
        if (index != null) builder.append(index);
        return builder.toString();
    }
    @Override public String toString() { return getExpression(); }


    @Override public String getName() { return name; }
    @Override public String getSubName() { return subName; }
    @Override public Integer getIndex() { return index; }
}
