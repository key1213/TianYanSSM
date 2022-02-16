package com.example.entity;

public class Page {
    Integer offset;
    Integer limit;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "page{" +
                "offset=" + offset +
                ", limit=" + limit +
                '}';
    }
}
