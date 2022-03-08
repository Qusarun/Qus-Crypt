package ru.qusarun.quscrypt.cipher;

import lombok.Getter;
import ru.qusarun.quscrypt.cipher.impl.english.*;
import ru.qusarun.quscrypt.cipher.impl.german.*;
import ru.qusarun.quscrypt.cipher.impl.russian.*;
import ru.qusarun.quscrypt.cipher.impl.toki_pona.*;
import ru.qusarun.quscrypt.util.ClassUtil;

import java.util.List;

public class CipherManager {
    @Getter private final List<Cipher> ciphers = ClassUtil.createInstances(List.of(
            A1A33.class,
            A1J14.class,
            A1Z26.class,
            A1Z30.class,
            A01A33.class,
            A01J14.class,
            A01Z26.class,
            A01Z30.class,
            Affine.class,
            ASCII.class,
            Atbash.class,
            AtbashDe.class,
            AtbashRu.class,
            AtbashTp.class,
            Baconian.class,
            BaconianBin.class,
            Base16.class,
            Base32.class,
            Base36.class,
            Base58.class,
            Base64.class,
            Binary.class,
            BitShift.class,
            Caesar.class,
            CaesarDe.class,
            CaesarRu.class,
            CaesarTp.class,
            Cross.class,
            Decabit.class,
            Dvorak.class,
            Hex.class,
            Leet.class,
            Lower.class,
            Modulo.class,
            ModuloNS.class,
            Morse.class,
            Note.class,
            NoteDe.class,
            NoteRu.class,
            NoteTp.class,
            Octal.class,
            Phone.class,
            QusCrypt1.class,
            QusCrypt2.class,
            QusCrypt4.class,
            QWERTY.class,
            RailFence.class,
            Skip.class,
            TomTom.class,
            Upper.class,
            Vigenere.class,
            VigenereDe.class,
            VigenereRu.class,
            VigenereTp.class,
            XOR.class
    ));

    @Getter private Cipher cipher;

    public boolean setCipher(final Cipher cipher) { this.cipher = cipher; return true; }

    public final Cipher getCipherByName(final String name) { return ciphers.stream().filter(cipher -> cipher.getName().equalsIgnoreCase(name)).findAny().orElse(null); }
}
