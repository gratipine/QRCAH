package com.endarpine.cahqr;

/**
 * Created by Galina on 11/28/2016.
 */

import org.junit.Assert;
import org.junit.Test;
import java.util.Scanner;

public class testRead {
    @Test
    public void testRead(){
        //read a string out loud
        Scanner in = new Scanner(System.in);
        String correct = "Haha";
        while(correct!="y"&&correct!="n"){
            System.out.println("Is the string you hear the correct string? y/n");
            correct = in.next();
        }
        Assert.assertTrue(correct=="y");
    }
}
