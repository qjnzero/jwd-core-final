package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.exception.InvalidStateException;

public class Main {

    public static void main(String[] args){
        try {
            Application.start();
        } catch (InvalidStateException e) {
            e.printStackTrace();
        }
    }
}