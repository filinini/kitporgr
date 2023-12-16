package com.example.kitpo_rgr.Builder;

import com.example.kitpo_rgr.Comparator.Comparator;

public interface UserType {
    String typeName(); // Имя типа
    Object create(); // Создает объект ИЛИ
    Object clone(); // Клонирует текущий
    Object readValue(); // Создает и читает объект
    Object parseValue(String ss); // Создает и парсит содержимое из строки
    Comparator getTypeComparator();
}
