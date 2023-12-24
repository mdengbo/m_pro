package com.m.business.classes.controller;

public class p{
        static {
            System.out.println("p static0");
        }
        {
            System.out.println("p block");
        }
    static {
        System.out.println("p static1");
    }

    public p() {
        System.out.println("p construct");
    }
        
    }