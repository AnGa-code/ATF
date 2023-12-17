package exceptions;

import java.io.IOException;

public class BookSharingException extends Exception {

    public BookSharingException(String message) {
        super((message));
    }
}

