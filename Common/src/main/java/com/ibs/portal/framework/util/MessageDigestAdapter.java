package com.ibs.portal.framework.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO jvm
//import sun.misc.BASE64Encoder;

public class MessageDigestAdapter {

    private final String arithmetic;

    private static final Logger logger = LoggerFactory.getLogger(MessageDigestAdapter.class);

    public MessageDigestAdapter(String arithmetic) {
        this.arithmetic = arithmetic;
    }

    public char[] encode(byte[] src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance(arithmetic);
            log(src);
            byte[] d = md5.digest(src);
            // BASE64Encoder base64en = new BASE64Encoder();
            String result = new String(Base64.encodeBase64(d)); // base64en.encode(d);
            return result.toCharArray();
        } catch (NoSuchAlgorithmException e) {
            throw new java.lang.RuntimeException(e);
        }
    }

    private void log(byte[] src) {

        String str = "";
        for (byte b : src) {
            str += String.format("%4s", (char) b);
        }
        logger.info(str);

        str = "";
        for (byte b : src) {
            str += String.format("%4d", b);
        }

        logger.info(str);
    }

    public String encode(String src) {
        return new String(encode(src.getBytes()));
    }

}
