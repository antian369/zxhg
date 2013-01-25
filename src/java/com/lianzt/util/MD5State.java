// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2004-4-16 11:12:28
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   MD5.java
package com.lianzt.util;

// Referenced classes of package com.aspire.cmppApi:
//            MD5
class MD5State {

    public MD5State() {
        buffer = new byte[64];
        count = new int[2];
        state = new int[4];
        state[0] = 0x67452301;
        state[1] = 0xefcdab89;
        state[2] = 0x98badcfe;
        state[3] = 0x10325476;
        count[0] = count[1] = 0;
    }

    public MD5State(MD5State md5state) {
        this();
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = md5state.buffer[i];
        }

        for (int j = 0; j < state.length; j++) {
            state[j] = md5state.state[j];
        }

        for (int k = 0; k < count.length; k++) {
            count[k] = md5state.count[k];
        }

    }
    int state[];
    int count[];
    byte buffer[];
}