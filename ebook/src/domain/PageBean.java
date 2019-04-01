package domain;

import java.util.List;

public class PageBean<T> {
    private int pc;//页码
    private int ps;//页大小
    private int tr;//总行数
    private List<T> list;//结果集
    private boolean hasprevious;//是否有上一页
    private boolean hasnext;//是否有下一页
    private int previous;//上一页页码
    private int next;//下一页页码

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getTr() {
        return tr;
    }

    public void setTr(int tr) {
        this.tr = tr;
    }

    public int getTp() {
        return (int)Math.ceil(((double)tr)/ps);
    }


    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isHasprevious() {
        if (pc==1) return false;
        return true;
    }

    public boolean isHasnext() {
        if (pc==getTp()) return false;
        return true;
    }

    public int getPrevious() {
        return pc-1;
    }

    public int getNext() {
        return pc+1;
    }


}
