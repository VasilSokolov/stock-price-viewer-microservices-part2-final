package com.techprimers.stock.dbservice.util;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Ignore annotated fields and classes in the GSON serialization.
 * @author Renato R. R. de Oliveira
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RUNTIME)
public @interface SkipSerialization {

}
