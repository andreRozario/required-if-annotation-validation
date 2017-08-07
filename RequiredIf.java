package br.gov.caraguatatuba.ocorrencias.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = RequiredIfConstraintValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredIf {

	String message() default "{RequiredIf}";
    
    Class<?>[] groups() default {};
     
    Class<? extends Payload>[] payload() default {};
    
    String attribute();
    
    String value();
    
    String target();
    
    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
    	
    	RequiredIf[] value();
    }
}
