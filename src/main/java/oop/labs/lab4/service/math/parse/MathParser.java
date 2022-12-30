package oop.labs.lab4.service.math.parse;

import java.math.BigDecimal;

@SuppressWarnings("unused")
public class MathParser
{
    public static Integer parseInt(MathParserSource source)
    {
        var parsed = 0;

        while (source.getRemindLength() > 0)
        {
            var c = source.getRemindHead();
            if (!Character.isDigit(c)) break;

            parsed = parsed * 10 + Character.getNumericValue(c);
            source.moveNext();
        }

        return source.hasMoved() ? null : parsed;
    }

    public static BigDecimal parseNum(MathParserSource source)
    {
        var builder = new StringBuilder();

        while (source.getRemindLength() > 0)
        {
            var c = source.getRemindHead();

            if (Character.isDigit(c)) { builder.append(c); source.moveNext(); continue; }
            if (c == '.' || c == ',') { builder.append('.'); source.moveNext(); }
            break;
        }

        return new BigDecimal(builder.toString());
    }
}
