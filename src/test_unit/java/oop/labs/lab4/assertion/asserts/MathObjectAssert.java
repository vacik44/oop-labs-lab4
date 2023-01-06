package oop.labs.lab4.assertion.asserts;

import oop.labs.lab4.math.model.MathObject;
import org.assertj.core.api.ObjectAssert;

import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("UnusedReturnValue")
public class MathObjectAssert<TObject extends MathObject> extends ObjectAssert<TObject>
{
    public MathObjectAssert(TObject tObject) { super(tObject); }
    public MathObjectAssert(AtomicReference<TObject> actual) { super(actual); }


    public MathObjectAssert<TObject> isEquivalentOf(MathObject other)
    {
        assertThat(actual.equivalent(other)).isTrue();
        return this;
    }

    public MathObjectAssert<TObject> isNotEquivalentOf(MathObject other)
    {
        assertThat(actual.equivalent(other)).isFalse();
        return this;
    }


    public MathObjectAssert<TObject> isDefinedAsImmutable()
    {
        assertThat(actual.isImmutable()).isTrue();
        return this;
    }

    public MathObjectAssert<TObject> isNotDefinedAsImmutable()
    {
        assertThat(actual.isImmutable()).isFalse();
        return this;
    }
}
