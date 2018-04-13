package org.mockdata.fields;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DependentField {

    Class<?> dependentOn();

}
