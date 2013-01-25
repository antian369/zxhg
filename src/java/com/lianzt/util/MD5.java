// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2004-4-16 11:12:28
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   MD5.java
package com.lianzt.util;

// Referenced classes of package com.aspire.cmppApi:
//            MD5State
public class MD5 {

    public MD5() {
        Init();
    }

    public MD5(Object obj) {
        this();
        Update(obj.toString());
    }

    private int[] Decode(byte abyte0[], int i, int j) {
        int ai[] = new int[16];
        int l;
        int k = l = 0;
        for (; l < i; l += 4) {
            ai[k] = abyte0[l + j] & 0xff | (abyte0[l + 1 + j] & 0xff) << 8 | (abyte0[l + 2 + j] & 0xff) << 16 | (abyte0[l + 3 + j] & 0xff) << 24;
            k++;
        }

        return ai;
    }

    private byte[] Encode(int ai[], int i) {
        byte abyte0[] = new byte[i];
        int k;
        int j = k = 0;
        for (; k < i; k += 4) {
            abyte0[k] = (byte) (ai[j] & 0xff);
            abyte0[k + 1] = (byte) (ai[j] >>> 8 & 0xff);
            abyte0[k + 2] = (byte) (ai[j] >>> 16 & 0xff);
            abyte0[k + 3] = (byte) (ai[j] >>> 24 & 0xff);
            j++;
        }

        return abyte0;
    }

    private int FF(int i, int j, int k, int l, int i1, int j1, int k1) {
        i = uadd(i, j & k | ~j & l, i1, k1);
        return uadd(rotate_left(i, j1), j);
    }

    public synchronized byte[] Final() {
        if (finals == null) {
            MD5State md5state = new MD5State(state);
            byte abyte0[] = Encode(md5state.count, 8);
            int i = md5state.count[0] >>> 3 & 0x3f;
            int j = i >= 56 ? 120 - i : 56 - i;
            Update(md5state, padding, 0, j);
            Update(md5state, abyte0, 0, 8);
            finals = md5state;
        }
        return Encode(finals.state, 16);
    }

    private int GG(int i, int j, int k, int l, int i1, int j1, int k1) {
        i = uadd(i, j & l | k & ~l, i1, k1);
        return uadd(rotate_left(i, j1), j);
    }

    private int HH(int i, int j, int k, int l, int i1, int j1, int k1) {
        i = uadd(i, j ^ k ^ l, i1, k1);
        return uadd(rotate_left(i, j1), j);
    }

    private int II(int i, int j, int k, int l, int i1, int j1, int k1) {
        i = uadd(i, k ^ (j | ~l), i1, k1);
        return uadd(rotate_left(i, j1), j);
    }

    public synchronized void Init() {
        state = new MD5State();
        finals = null;
    }

    private void Transform(MD5State md5state, byte abyte0[], int i) {
        int j = md5state.state[0];
        int k = md5state.state[1];
        int l = md5state.state[2];
        int i1 = md5state.state[3];
        int ai[] = Decode(abyte0, 64, i);
        j = FF(j, k, l, i1, ai[0], 7, 0xd76aa478);
        i1 = FF(i1, j, k, l, ai[1], 12, 0xe8c7b756);
        l = FF(l, i1, j, k, ai[2], 17, 0x242070db);
        k = FF(k, l, i1, j, ai[3], 22, 0xc1bdceee);
        j = FF(j, k, l, i1, ai[4], 7, 0xf57c0faf);
        i1 = FF(i1, j, k, l, ai[5], 12, 0x4787c62a);
        l = FF(l, i1, j, k, ai[6], 17, 0xa8304613);
        k = FF(k, l, i1, j, ai[7], 22, 0xfd469501);
        j = FF(j, k, l, i1, ai[8], 7, 0x698098d8);
        i1 = FF(i1, j, k, l, ai[9], 12, 0x8b44f7af);
        l = FF(l, i1, j, k, ai[10], 17, -42063);
        k = FF(k, l, i1, j, ai[11], 22, 0x895cd7be);
        j = FF(j, k, l, i1, ai[12], 7, 0x6b901122);
        i1 = FF(i1, j, k, l, ai[13], 12, 0xfd987193);
        l = FF(l, i1, j, k, ai[14], 17, 0xa679438e);
        k = FF(k, l, i1, j, ai[15], 22, 0x49b40821);
        j = GG(j, k, l, i1, ai[1], 5, 0xf61e2562);
        i1 = GG(i1, j, k, l, ai[6], 9, 0xc040b340);
        l = GG(l, i1, j, k, ai[11], 14, 0x265e5a51);
        k = GG(k, l, i1, j, ai[0], 20, 0xe9b6c7aa);
        j = GG(j, k, l, i1, ai[5], 5, 0xd62f105d);
        i1 = GG(i1, j, k, l, ai[10], 9, 0x2441453);
        l = GG(l, i1, j, k, ai[15], 14, 0xd8a1e681);
        k = GG(k, l, i1, j, ai[4], 20, 0xe7d3fbc8);
        j = GG(j, k, l, i1, ai[9], 5, 0x21e1cde6);
        i1 = GG(i1, j, k, l, ai[14], 9, 0xc33707d6);
        l = GG(l, i1, j, k, ai[3], 14, 0xf4d50d87);
        k = GG(k, l, i1, j, ai[8], 20, 0x455a14ed);
        j = GG(j, k, l, i1, ai[13], 5, 0xa9e3e905);
        i1 = GG(i1, j, k, l, ai[2], 9, 0xfcefa3f8);
        l = GG(l, i1, j, k, ai[7], 14, 0x676f02d9);
        k = GG(k, l, i1, j, ai[12], 20, 0x8d2a4c8a);
        j = HH(j, k, l, i1, ai[5], 4, 0xfffa3942);
        i1 = HH(i1, j, k, l, ai[8], 11, 0x8771f681);
        l = HH(l, i1, j, k, ai[11], 16, 0x6d9d6122);
        k = HH(k, l, i1, j, ai[14], 23, 0xfde5380c);
        j = HH(j, k, l, i1, ai[1], 4, 0xa4beea44);
        i1 = HH(i1, j, k, l, ai[4], 11, 0x4bdecfa9);
        l = HH(l, i1, j, k, ai[7], 16, 0xf6bb4b60);
        k = HH(k, l, i1, j, ai[10], 23, 0xbebfbc70);
        j = HH(j, k, l, i1, ai[13], 4, 0x289b7ec6);
        i1 = HH(i1, j, k, l, ai[0], 11, 0xeaa127fa);
        l = HH(l, i1, j, k, ai[3], 16, 0xd4ef3085);
        k = HH(k, l, i1, j, ai[6], 23, 0x4881d05);
        j = HH(j, k, l, i1, ai[9], 4, 0xd9d4d039);
        i1 = HH(i1, j, k, l, ai[12], 11, 0xe6db99e5);
        l = HH(l, i1, j, k, ai[15], 16, 0x1fa27cf8);
        k = HH(k, l, i1, j, ai[2], 23, 0xc4ac5665);
        j = II(j, k, l, i1, ai[0], 6, 0xf4292244);
        i1 = II(i1, j, k, l, ai[7], 10, 0x432aff97);
        l = II(l, i1, j, k, ai[14], 15, 0xab9423a7);
        k = II(k, l, i1, j, ai[5], 21, 0xfc93a039);
        j = II(j, k, l, i1, ai[12], 6, 0x655b59c3);
        i1 = II(i1, j, k, l, ai[3], 10, 0x8f0ccc92);
        l = II(l, i1, j, k, ai[10], 15, 0xffeff47d);
        k = II(k, l, i1, j, ai[1], 21, 0x85845dd1);
        j = II(j, k, l, i1, ai[8], 6, 0x6fa87e4f);
        i1 = II(i1, j, k, l, ai[15], 10, 0xfe2ce6e0);
        l = II(l, i1, j, k, ai[6], 15, 0xa3014314);
        k = II(k, l, i1, j, ai[13], 21, 0x4e0811a1);
        j = II(j, k, l, i1, ai[4], 6, 0xf7537e82);
        i1 = II(i1, j, k, l, ai[11], 10, 0xbd3af235);
        l = II(l, i1, j, k, ai[2], 15, 0x2ad7d2bb);
        k = II(k, l, i1, j, ai[9], 21, 0xeb86d391);
        md5state.state[0] += j;
        md5state.state[1] += k;
        md5state.state[2] += l;
        md5state.state[3] += i1;
    }

    public void Update(byte byte0) {
        byte abyte0[] = new byte[1];
        abyte0[0] = byte0;
        Update(abyte0, 1);
    }

    public void Update(int i) {
        Update((byte) (i & 0xff));
    }

    public void Update(MD5State md5state, byte abyte0[], int i, int j) {
        finals = null;
        if (j - i > abyte0.length) {
            j = abyte0.length - i;
        }
        int k = md5state.count[0] >>> 3 & 0x3f;
        if ((md5state.count[0] += j << 3) < j << 3) {
            md5state.count[1]++;
        }
        md5state.count[1] += j >>> 29;
        int l = 64 - k;
        int j1;
        if (j >= l) {
            for (int i1 = 0; i1 < l; i1++) {
                md5state.buffer[i1 + k] = abyte0[i1 + i];
            }

            Transform(md5state, md5state.buffer, 0);
            for (j1 = l; j1 + 63 < j; j1 += 64) {
                Transform(md5state, abyte0, j1);
            }

            k = 0;
        } else {
            j1 = 0;
        }
        if (j1 < j) {
            int k1 = j1;
            for (; j1 < j; j1++) {
                md5state.buffer[(k + j1) - k1] = abyte0[j1 + i];
            }

        }
    }

    public void Update(String s) {
        byte abyte0[] = new byte[s.length()];
        s.getBytes(0, s.length(), abyte0, 0);
        Update(abyte0, abyte0.length);
    }

    public void Update(byte abyte0[]) {
        Update(abyte0, 0, abyte0.length);
    }

    public void Update(byte abyte0[], int i) {
        Update(state, abyte0, 0, i);
    }

    public void Update(byte abyte0[], int i, int j) {
        Update(state, abyte0, i, j);
    }

    public String asHex() {
        return asHex(Final());
    }

    public static String asHex(byte abyte0[]) {
        StringBuffer stringbuffer = new StringBuffer(abyte0.length * 2);
        for (int i = 0; i < abyte0.length; i++) {
            if ((abyte0[i] & 0xff) < 16) {
                stringbuffer.append("0");
            }
            stringbuffer.append(Long.toString(abyte0[i] & 0xff, 16));
        }

        return stringbuffer.toString();
    }

    private int rotate_left(int i, int j) {
        return i << j | i >>> 32 - j;
    }

    private int uadd(int i, int j) {
        long l = (long) i & 0xffffffffL;
        long l1 = (long) j & 0xffffffffL;
        l += l1;
        return (int) (l & 0xffffffffL);
    }

    private int uadd(int i, int j, int k) {
        return uadd(uadd(i, j), k);
    }

    private int uadd(int i, int j, int k, int l) {
        return uadd(uadd(i, j, k), l);
    }
    MD5State state;
    MD5State finals;
    static byte padding[] = {
        -128, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0
    };
}