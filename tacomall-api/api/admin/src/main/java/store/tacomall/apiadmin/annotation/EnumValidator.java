package store.tacomall.apiadmin.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Constraint(validatedBy = EnumValidator.EnumValidatorHandle.class)
public @interface EnumValidator {
    Class<?> value();

    String message() default "入参值不在正确枚举中";

    String method() default "getCode";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Slf4j
    class EnumValidatorHandle implements ConstraintValidator<EnumValidator, Object>, Annotation {
        private List<Object> values = new ArrayList<>();

        @Override
        public void initialize(EnumValidator enumValidator) {
            Class<?> clz = enumValidator.value();
            Object[] objects = clz.getEnumConstants();
            try {
                Method method = clz.getMethod(enumValidator.method());
                if (Objects.isNull(method)) {
                    return;
                }
                Object value;
                for (Object obj : objects) {
                    value = method.invoke(obj);
                    values.add(value);
                }
            } catch (Exception e) {
                log.error("处理枚举校验异常:{}", e);
            }
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return null;
        }

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
            if (CollectionUtils.isEmpty(values)) {
                return false;
            }
            if (value instanceof String) {
                String valueStr = (String) value;
                return StringUtils.isEmpty(valueStr) || values.contains(value);
            }
            return Objects.isNull(value) || values.contains(value);
        }

    }

}