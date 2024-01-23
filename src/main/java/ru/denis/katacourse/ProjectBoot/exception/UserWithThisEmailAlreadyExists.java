package ru.denis.katacourse.ProjectBoot.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class UserWithThisEmailAlreadyExists extends DataIntegrityViolationException {
    public UserWithThisEmailAlreadyExists(String msg){
        super(msg);
    }
}
