package com.example.synthesizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class AudioClipTest {

    @Test
    public void runAllTes() {
         getSample();
         setSample();
//         getData();
    }

    @Test
    void getSample() {
        AudioClip test = new AudioClip();

        int index = 5;
        short maxValue = Short.MAX_VALUE;
        short minValue = Short.MIN_VALUE;
        test.setSample(index, maxValue);
        short testMaxValue = (short) test.getSample(index);

        Assertions.assertEquals(maxValue, testMaxValue);

    }

    @Test
    void setSample(){
        AudioClip test2 = new AudioClip();

        short maxValue = Short.MAX_VALUE;
        short minValue = Short.MIN_VALUE;

        ArrayList<Short> arrayListOfShort = new ArrayList<>();
//        Random random = new Random();
//        int oneValue;
        for ( int i = minValue; i<= maxValue; i++){
//            oneValue = random.nextInt();
            arrayListOfShort.add((short) i);
        }
//        System.out.println("Content of the arrayList are: " + arrayListOfShort);
        //TODO this test contains the setSample(), getSample()
        for ( int i =0; i< arrayListOfShort.size(); i++){
            test2.setSample(i, arrayListOfShort.get(i));
            Assertions.assertEquals(arrayListOfShort.get(i), test2.getSample(i));
        }

    }

    //TODO Still need to work on the getdata()
/*
     @Test
    void getData(){
        // test for getData();
         short maxValue = Short.MAX_VALUE;
         short minValue = Short.MIN_VALUE;

        AudioClip test3 = new AudioClip();
        ArrayList<Short> arrayListOfShort = new ArrayList<>();
        byte [] byteArr = new byte[test3.getDataSize()];


         for ( int i = minValue; i <maxValue; i++){
             arrayListOfShort.add((short) i);
         }
         for ( int i =0; i< arrayListOfShort.size(); i++){
             test3.setSample(i, arrayListOfShort.get(i));
             Assertions.assertEquals(arrayListOfShort.get(i), test3.getSample(i));
         }

        Assertions.assertArrayEquals(test3.getData(), byteArr);

    }

 */


}