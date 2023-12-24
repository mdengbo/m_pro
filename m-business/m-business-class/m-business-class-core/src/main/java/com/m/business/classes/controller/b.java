package com.m.business.classes.controller;

public class b extends p {
    static {
        System.out.println("c static0");
    }
    {
        System.out.println("c block");
    }
    static {
        System.out.println("c static1");
    }

    public b() {
        System.out.println("c construct");
    }
    }
