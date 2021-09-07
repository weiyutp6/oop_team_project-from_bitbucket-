package Crawler;

import java.util.Calendar;

public class Stock {
    protected String n; //公司簡稱
    protected String g; //揭示買量(配合b以_分)
    protected double u; //漲停價
    protected double o; //開盤
    protected String a; //揭示賣價(低到高以_分)
    protected double tlong; //epoch毫秒數
    protected Calendar t; //最近成交時刻
    protected double z; //當盤成交價
    protected int tv; //當盤成交量
    protected int v; //累積成交量
    protected String b; //揭示買價(高到低以_分)
    protected String f; //揭示賣量(配合a以_分)
    protected double h; //最高
    protected double l; //最低
    protected double y; //昨收
    protected double w; //跌停價
    protected Calendar d; //最近交易日期
    protected int c; //股票代碼
    protected String nf; //公司全名
    protected String priceVariation; //漲跌幅
    public Stock(){};
    public Stock(String n, String g, double u, double o, String a, double tlong, Calendar t,
                 double z, int tv, int v, String b, String f, double h, double l, double y, double w,
                 Calendar d, int c, String nf, String priceVariation){
        this.n = n;
        this.g = g;
        this.u = u;
        this.o = o;
        this.a = a;
        this.tlong = tlong;
        this.t = t;
        this.z = z;
        this.tv = tv;
        this.v = v;
        this.b = b;
        this.f = f;
        this.h = h;
        this.l = l;
        this.y = y;
        this.w = w;
        this.d = d;
        this.c = c;
        this.nf = nf;
        this.priceVariation = priceVariation;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getA() {
        return a;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getB() {
        return b;
    }

    public void setC(int c) { this.c = c; }

    public int getC() {
        return c;
    }

    public void setD(Calendar.Builder d) { this.d = d.build(); }

    public Calendar getD() {
        return d;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getF() {
        return f;
    }

    public Calendar getT() {
        return t;
    }

    public void setT(Calendar.Builder t) {
        this.t = t.build();
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getG() {
        return g;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getH() {
        return h;
    }

    public void setL(double l) {
        this.l = l;
    }

    public double getL() {
        return l;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getN() {
        return n;
    }

    public void setNf(String nf) {
        this.nf = nf;
    }

    public String getNf() {
        return nf;
    }

    public void setO(double o) {
        this.o = o;
    }

    public double getO() {
        return o;
    }

    public void setTlong(double tlong) {
        this.tlong = tlong;
    }

    public double getTlong() {
        return tlong;
    }

    public void setTv(int tv) {
        this.tv = tv;
    }

    public int getTv() {
        return tv;
    }

    public void setU(double u) {
        this.u = u;
    }

    public double getU() {
        return u;
    }

    public void setV(int v) {
        this.v = v;
    }

    public int getV() {
        return v;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getW() {
        return w;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getZ() {
        return z;
    }

    public void setPriceVariation(String priceVariation){this.priceVariation = priceVariation;}

    public String getPriceVariation(){return priceVariation;}
}