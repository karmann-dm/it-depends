package com.karmanno.itdepends.core.examples.annotated_examples;

import com.karmanno.itdepends.core.scan.ContextTarget;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SomeClass {
    @ContextTarget
    public SomeClass(BigDecimal bigDecimal, BigInteger bigInteger) {

    }

    @ContextTarget
    public SomeClass(BigInteger bigInteger, BigDecimal bigDecimal) {

    }
}
