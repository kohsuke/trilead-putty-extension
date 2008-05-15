package org.kohsuke.putty;

import junit.framework.TestCase;
import org.apache.commons.io.IOUtils;
import org.kohsuke.putty.PuTTYKey;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

/**
 * @author Kohsuke Kawaguchi
 */
public class PuTTYTest extends TestCase {
    public void test1enc() throws IOException {
        PuTTYKey key = new PuTTYKey(getClass().getResourceAsStream("test1-encrypted.ppk"), "test");
        verifyWith(key,"test1-openssh.txt");
    }

    public void test1() throws IOException {
        PuTTYKey key = new PuTTYKey(getClass().getResourceAsStream("test1.ppk"),null);
        verifyWith(key,"test1-openssh.txt");
    }

    public void test2enc() throws IOException {
        PuTTYKey key = new PuTTYKey(getClass().getResourceAsStream("test2-encrypted.ppk"), "test");
        verifyWith(key,"test2-openssh.txt");
    }

    public void test2() throws IOException {
        PuTTYKey key = new PuTTYKey(getClass().getResourceAsStream("test2.ppk"),null);
        verifyWith(key,"test2-openssh.txt");
    }

    private void verifyWith(PuTTYKey key, String res) throws IOException {
        List answer = IOUtils.readLines(getClass().getResourceAsStream(res));
        List test = IOUtils.readLines(new StringReader(key.toOpenSSH()));

        assertEquals(answer,test);
    }
}
