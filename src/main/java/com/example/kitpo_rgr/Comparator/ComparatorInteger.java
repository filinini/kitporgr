package com.example.kitpo_rgr.Comparator;

import com.example.kitpo_rgr.Builder.UserTypeInteger;

import java.io.Serializable;

public class ComparatorInteger implements Comparator, Serializable {
    @Override
    public int compare(Object o1, Object o2) {
        if (((UserTypeInteger) o1).value == ((UserTypeInteger) o2).value) return 0;
        if (((UserTypeInteger) o1).value > ((UserTypeInteger) o2).value) return 1;
        else return -1;
    }
}