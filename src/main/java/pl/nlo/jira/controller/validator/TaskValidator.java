package pl.nlo.jira.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.nlo.jira.entity.Task;

import java.util.Locale;

public class TaskValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Task.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Task task = (Task) target;


    }
}
