package com.example.kitpo_rgr.List;

import com.example.kitpo_rgr.Builder.UserType;
import com.example.kitpo_rgr.Comparator.Comparator;

public interface TListInterface {
    boolean clear();
    boolean pushFront(Object obj);
    boolean pushEnd(Object data);
    boolean add(Object data, int index);
    boolean delete(int index);
    Object find(int index);
    void forEach(DoIt action);
    void sort(Comparator comparator);
    UserType getBuilder();
    String toString();
}
