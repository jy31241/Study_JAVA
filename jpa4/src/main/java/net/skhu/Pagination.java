package net.skhu;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Pagination {
    int pg = 1;      // 현재 페이지
    int sz = 15;     // 페이지 당 레코드 수
    int sb;          // search by
    String st;       // search text
    int ob;          // order by
    int recordCount; // 총 레코드 수

    public int getPg() {
        return pg;
    }

    public void setPg(int pg) {
        this.pg = pg;
    }

    public int getSz() {
        return sz;
    }

    public void setSz(int sz) {
        this.sz = sz;
    }

    public int getSb() {
        return sb;
    }

    public void setSb(int sb) {
        this.sb = sb;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public int getOb() {
        return ob;
    }

    public void setOb(int ob) {
        this.ob = ob;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public String getQueryString() {
        String url = null;
        try {
            String temp = (st == null) ? "" : URLEncoder.encode(st, "UTF-8");
            url = String.format("pg=%d&sz=%d&ob=%d&sb=%d&st=%s", pg, sz, ob, sb, temp);
        } catch (UnsupportedEncodingException e) { }
        return url;
    }
}