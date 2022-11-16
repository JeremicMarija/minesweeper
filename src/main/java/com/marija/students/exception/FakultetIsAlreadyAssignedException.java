package com.marija.students.exception;

import java.text.MessageFormat;

public class FakultetIsAlreadyAssignedException  extends RuntimeException{
    public FakultetIsAlreadyAssignedException(final String fakultetId, final Long mestoId){
        super(MessageFormat.format("Fakultet: {0} je vec dodeljen mestu: {1}", fakultetId, mestoId));
    }
}
