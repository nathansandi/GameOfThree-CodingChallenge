package com.gameofthree.validator;

import java.util.List;

import javax.xml.bind.ValidationException;

public interface Validator<T>  {
 
    boolean validate(T obj);
    
    void validateOrThrow(T obj) throws ValidationException;

}
