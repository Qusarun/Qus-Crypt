package ru.qusarun.quscrypt.cipher;

import lombok.Getter;
import ru.qusarun.flogger.Logger;
import ru.qusarun.quscrypt.util.FileUtil;
import ru.qusarun.quscrypt.util.StringUtil;

public abstract class Cipher {
    public static final int[] ONE_TO_LEN = new int[] { -1 };

    @Getter private final String name = this.getClass().getSimpleName();
    @Getter private final CipherType type;
    @Getter private final KeyType keyType;

    protected long intKeyA, intKeyB, intKeyC, intKeyD;
    protected String strKey;

    @Getter protected int[] keysA = null, keysB = null;

    public Cipher() {
        final CipherInfo info = this.getClass().getAnnotation(CipherInfo.class);
        this.type = info.cipherType();
        this.keyType = info.keyType();
    }

    public boolean setKey(String... args) {
        try {
            switch (keyType) {
                case NONE:
                    return args.length == 0;
                case INT:
                    intKeyA = Long.parseLong(args[0]);
                    return args.length == 1;
                case INT_INT:
                    intKeyA = Long.parseLong(args[0]);
                    intKeyB = Long.parseLong(args[1]);
                    return args.length == 2;
                case INT4:
                    intKeyA = Long.parseLong(args[0]);
                    intKeyB = Long.parseLong(args[1]);
                    intKeyC = Long.parseLong(args[2]);
                    intKeyD = Long.parseLong(args[3]);
                    return args.length == 4;
                case STR:
                    strKey = StringUtil.join(args);
                    return args.length != 0;
                case INT_STR:
                    intKeyA = Long.parseLong(args[0]);
                    strKey = args[1];
                    return args.length == 2;
                default:
                    Logger.log("Unsupported key type %b" + keyType.name());
                    return false;
            }
        } catch (final Exception ignored) { return false; }
    }

    public final String _encrypt(final String message) {
        try {
            return encrypt(message);
        } catch (final Exception e) {
            e.printStackTrace();
            return "Encryption failed: %b" + e.getClass().getSimpleName();
        }
    }

    public final String _decrypt(final String message) {
        try {
            return decrypt(message);
        } catch (final Exception e) {
            e.printStackTrace();
            return "Decryption failed: %b" + e.getClass().getSimpleName();
        }
    }

    public final String _encryptFile(final String file) {
        try {
            encryptFile(file);
            return "Successfully encrypted %b" + file;
        } catch (final Exception e) {
            e.printStackTrace();
            return "Encryption failed: %b" + e.getClass().getSimpleName();
        }
    }

    public final String _decryptFile(final String file) {
        try {
            decryptFile(file);
            return "Successfully decrypted %b" + file;
        } catch (final Exception e) {
            e.printStackTrace();
            return "Decryption failed: %b" + e.getClass().getSimpleName();
        }
    }

    public final void encryptFile(final String file) throws Exception { FileUtil.write(getOutputName(file), encrypt(FileUtil.read(file))); }
    public final void decryptFile(final String file) throws Exception { FileUtil.write(getOutputName(file), decrypt(FileUtil.read(file))); }

    private String getOutputName(final String file) { return file.split("\\.")[0].replace("-out", "") + "-out" + (file.contains(".")? "." + file.split("\\.")[1] : ""); }

    public abstract String encrypt(final String message);
    public abstract String decrypt(final String message);

    public boolean producesNonASCII() { return type == CipherType.BINARY || type == CipherType.MONOALPHABETIC_SEQUENCE; }
}
