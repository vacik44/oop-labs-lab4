package oop.labs.lab4.math.parse;

import java.math.BigDecimal;

public class MathParser
{
    public static Integer parseUInt(ParsingSourceIterator source)
    {
        var parsed = 0;
        var startPosition = source.currentPosition();

        while (source.hasCurrent())
        {
            var c = source.current();
            if (!Character.isDigit(c)) break;

            parsed = parsed * 10 + Character.getNumericValue(c);
            source.move();
        }

        return source.currentPosition() == startPosition ? null : parsed;
    }

    public static Integer parseInt(ParsingSourceIterator source)
    {
        var c = source.current();

        if (c == '+' || c =='-') source.move();
        var buffer = parseUInt(source);

        return c != '-' || buffer == null ? buffer : Integer.valueOf(-buffer);
    }

    public static BigDecimal parseUNum(ParsingSourceIterator source)
    {
        var builder = new StringBuilder();

        while (source.hasCurrent())
        {
            var c = source.current();

            if (Character.isDigit(c)) { builder.append(c); source.move(); continue; }
            if (c == '.' || c == ',') { builder.append('.'); source.move(); }
            break;
        }

        return builder.isEmpty() ? null : new BigDecimal(builder.toString());
    }

    public static BigDecimal parseNum(ParsingSourceIterator source)
    {
        var c = source.current();

        if (c == '+' || c =='-') source.move();
        var buffer = parseUNum(source);

        return c != '-' || buffer == null ? buffer : buffer.multiply(BigDecimal.valueOf(-1));
    }

    public static String parseAlpha(ParsingSourceIterator source)
    {
        var builder = new StringBuilder();

        while (source.hasCurrent() && Character.isAlphabetic(source.current()))
        {
            builder.append(source.current());
            source.move();
        }

        return builder.isEmpty() ? null : builder.toString();
    }
}
