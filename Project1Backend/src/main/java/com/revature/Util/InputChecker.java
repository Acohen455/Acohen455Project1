package com.revature.Util;

public class InputChecker {


    //empty constructor so we can initialize this
    public InputChecker() {
    }

    //using regex to check if the string has non-roman alphabet characters
    public boolean RomanAlphabetChecker(String toCheck){
        if (!(toCheck.matches("^[A-Za-z0-9]+$"))){
            return false;
        } else {
            return true;
        }
    }


}
