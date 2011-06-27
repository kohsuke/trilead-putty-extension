/*
 * The MIT License
 *
 * Copyright (c) 2009-, Kohsuke Kawaguchi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.kohsuke.putty;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Parses the putty key bit vector, which is an encoded sequence
 * of {@link BigInteger}s.
 *
 * @author Kohsuke Kawaguchi
 */
class KeyReader {
    private final DataInput di;

    KeyReader(byte[] key) {
        this.di = new DataInputStream(new ByteArrayInputStream(key));
    }

    /**
     * Skips an integer without reading it.
     */
    public void skip() {
        try {
            di.skipBytes(di.readInt());
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    private byte[] read() {
        try {
            int len = di.readInt();
            byte[] r = new byte[len];
            di.readFully(r);
            return r;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Reads the next integer.
     */
    public BigInteger readInt() {
        return new BigInteger(read());
    }
}
