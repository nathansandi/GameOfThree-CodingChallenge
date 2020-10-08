package com.gameofthree.validator;

import javax.xml.bind.ValidationException;

public class isDivisibleOfThree<T> implements Validator<T> {
	
	private boolean isDividableByThree(int inputNumber) {
	    return inputNumber % 3 == 0;
	}

	@Override
	public boolean validate(T obj) {
		// TODO Auto-generated method stub
		int teste = Integer.parseInt(obj.toString());
		
		return isDividableByThree(teste);
	}
	@Override
	public void validateOrThrow(T obj) throws ValidationException {
		// TODO Auto-generated method stub	
		throw new ValidationException("Input invalid - Number not divisible by 3");
		
	}

}


