package com.nexthero.demo.storage;

/**
 * StorageFileNotFoundException
 *
 * @version 1.0
 */
public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
