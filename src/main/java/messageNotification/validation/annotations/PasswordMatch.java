package messageNotification.validation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import messageNotification.validation.validators.PasswordMatchValidator;

@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Constraint(validatedBy = PasswordMatchValidator.class)
public @interface PasswordMatch 
{
	String message() default "Passwords do not match";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
