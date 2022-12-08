package com.marija.students.exception;

import java.text.MessageFormat;

public class FakultetVecDodeljenException extends RuntimeException {
    public FakultetVecDodeljenException(final String fakultetId, final String studentId){
        super(MessageFormat.format("Fakulteet: {0} je vec dodeljen studentu: {1}", fakultetId, studentId));
    }
}
