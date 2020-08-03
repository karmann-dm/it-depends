package com.karmanno.itdepends.core.examples.plain_examples;

import com.karmanno.itdepends.core.scan.ContextScoped;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

@ContextScoped
public class PlainClass {
    public PlainClass(BigInteger bigInteger, BigDecimal bigDecimal) {
    }

    public PlainClass(BigDecimal bigDecimal, InputStream inputStream) {
        
    }
}
