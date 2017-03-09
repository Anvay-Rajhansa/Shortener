package org.infobip.Exception;

public class ShortenerBusinessException extends RuntimeException {
    public ShortenerBusinessException(String message) {
        super(message);
    }

    public ShortenerBusinessException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ShortenerBusinessException(Throwable throwable) {
        super(throwable);
    }
}
