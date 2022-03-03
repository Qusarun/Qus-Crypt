package ru.qusarun.quscrypt.cipher;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CipherInfo {
    CipherType cipherType();
    KeyType keyType();
}
