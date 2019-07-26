package com.nexthero.demo.storage;

/**
 * StorageException
 *
 * @version 1.0
 */
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}