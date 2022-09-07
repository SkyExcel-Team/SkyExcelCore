package annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD,ElementType.PACKAGE})
public @interface Adjust {
    public String command() default "";

    String args() default "";

    boolean console() default false;

    int parameter() default 0;

    boolean tab() default false;

    public boolean label() default false;
}
