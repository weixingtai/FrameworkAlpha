package com.alpha.module_common.utils.encrypt.AES;

public enum AESType {

    ECB("ECB", "0"), CBC("CBC", "1"), CFB("CFB", "2"), OFB("OFB", "3");
    private String k;
    private String v;

    AESType(String k, String v) {
        this.k = k;
        this.v = v;
    }

    public String key() {
        return this.k;
    }

    public String value() {
        return this.v;
    }

    public static AESType get(int id) {
        AESType[] vs = AESType.values();
        for (AESType d : vs) {
            if (Integer.parseInt(d.key())==id)
                return d;
        }
        return AESType.CBC;
    }

}
