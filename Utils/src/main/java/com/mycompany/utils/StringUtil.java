/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author tuantv
 */
public class StringUtil {

    public static boolean validString(String arg) {
        return arg != null
                && !arg.trim().isEmpty();
    }

    public static boolean validStrings(String[] args) {
        if (args == null) {
            return false;
        }
        for (String arg : args) {
            if (!validString(arg)) {
                return false;
            }
        }

        return true;
    }

    public static String readInputStream(InputStream inputStream) {
        String result = null;

        if (inputStream == null) {
            return result;
        }

        try {
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(isr);
            result = reader.readLine();
            isr.close();
            reader.close();

        } catch (IOException e) {
        }

        return result;
    }

}
