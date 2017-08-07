package br.gov.caraguatatuba.ocorrencias.validators;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

public class RequiredIfConstraintValidator implements ConstraintValidator<RequiredIf, Object> {
	
	private String attribute;
    private String value;
    private String target;

	@Override
	public void initialize(RequiredIf annotation) {
		this.attribute = annotation.attribute();
		this.value = annotation.value();
		this.target = annotation.target();
	}
	
	@Override
	public boolean isValid(final Object object, final ConstraintValidatorContext context) {
		
		if(StringUtils.isEmpty(object)) {
			
            return true;
        }
		
		try {
			
            final Object objFieldValue       = BeanUtils.getProperty(object, attribute);
            final Object objTargetFieldValue = BeanUtils.getProperty(object, target);

            if (value.equals(objFieldValue) && StringUtils.isEmpty(objTargetFieldValue)) {
            	
            	context.disableDefaultConstraintViolation();
            	
            	context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addNode(target)
                    .addConstraintViolation();
            	
                    return false;
            }

        } catch (final NoSuchMethodException ex) {
        	
            throw new RuntimeException(ex);

        } catch (final InvocationTargetException ex) {
        	
            throw new RuntimeException(ex);

        } catch (final IllegalAccessException ex) {
        	
            throw new RuntimeException(ex);
        }
		
		return true;
	}
}
