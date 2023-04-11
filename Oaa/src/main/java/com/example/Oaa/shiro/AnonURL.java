package com.example.Oaa.shiro;

import java.util.HashSet;
import java.util.Set;

public class AnonURL {
    public static Set<String> anonUrl = new HashSet();

    static{
        anonUrl.add("/**");
    }

}
