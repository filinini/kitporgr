package com.example.kitpo_rgr;

import com.example.kitpo_rgr.Builder.UserType;
import com.example.kitpo_rgr.Builder.UserTypeInteger;
import com.example.kitpo_rgr.Builder.UserTypePropFract;
import com.example.kitpo_rgr.List.TList;

import java.util.ArrayList;
import java.util.Arrays;

public class UserFactory {


    public static ArrayList<String> getTypeNameList() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Integer","ProperFraction"));
        return list;
    }

    public static UserType getBuilderByName(String name) throws Exception
    {
        if (name.equals(UserTypePropFract.typename))
        {
            return new UserTypePropFract();
        }
        else if (name.equals(UserTypeInteger.typename))
        {
            return new UserTypeInteger();
        }
        else
        {
            return null;
        }
    }

}
