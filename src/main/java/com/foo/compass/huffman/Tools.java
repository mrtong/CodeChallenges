package com.foo.compass.huffman;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class Tools {

    /**
     * Writes 32 bit integer from any shorter or equal length integer.
     *
     * @param bos Where to write
     * @throws IOException
     */
    public static void writeFullInt(BufferedOutputStream bos, int integer) throws IOException {
        String hexRepresentation = Integer.toHexString(integer);
        String hex = "";
        for (int i = 0; i < 8 - hexRepresentation.length(); ++i) {
            hex = hex + "0";
        }
        hex = hex + hexRepresentation;
        for (int i = 0; i < 4; ++i) {
            String byteToWrite = hex.substring(0, 2);
            bos.write(Integer.parseInt(byteToWrite, 16));
            hex = hex.substring(2);
        }
    }

    /**
     * Reads four byte long integer from BufferedInputStream
     *
     * @param bis path to file
     * @return integer read from file
     * @throws IOException
     */
    public static int readFullLengthInt(BufferedInputStream bis) throws IOException {
        int integer = 0;
        for (int i = 0; i < 4; ++i) {
            int byteRead = bis.read();
            for (int j = 0; j < 8; ++j) {
                int bit = (byteRead & 0x80);
                if (bit == 0x80) {
                    integer <<= 1;
                    integer += 1;
                } else {
                    integer <<= 1;
                }
                byteRead <<= 1;
            }
        }
        return integer;
    }

    public static int bitsToByte(boolean[] bits) {
        if (bits == null || bits.length != 8) {
            throw new IllegalArgumentException();
        }
        int data = 0;
        for (int i = 0; i < 8; i++) {
            if (bits[i]) data += (1 << (7 - i));
        }
        return data;
    }

}