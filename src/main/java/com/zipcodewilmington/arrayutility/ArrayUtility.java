package com.zipcodewilmington.arrayutility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<E> {

//    private ArrayList<E> arr;
    private E[] input;

    public ArrayUtility(E[] input){
//        this.arr = new ArrayList<E>(Arrays.asList(arr));
        this.input = input;
    }

//    public Integer countDuplicatesInMerge(E[] arrayToMerge, E valueToEvaluate){
//        this.arr.addAll(new ArrayList<E>(Arrays.asList(arrayToMerge)));
//        int count = 0;
//        for (E obj : this.arr){
//            if (obj.equals(valueToEvaluate)){
//                count++;
//            }
//        }
//        return count;
//    }

    public Integer countDuplicatesInMerge(E[] arrayToMerge, E valueTocheck){
        return (int) Arrays.stream(mergeArrays(arrayToMerge))
                .filter(thing -> thing == valueTocheck)
                .count();
    }

    // THIS ONE WORKS FOR OBJECTS, BUT CAN'T PROPERLY CAST TO INT, LONG, OR STRING
//    public E[] removeValue(E valueToRemove){
//        return (E[]) Arrays.stream(input)
//                .filter(n -> n != valueToRemove).toArray();
//    }

    // THERE IS PROBABLY A WAY TO DO THIS WITH STREAMS
    public E[] removeValue(E valueToRemove){
        E[] output = Arrays.copyOf(input, input.length - getNumberOfOccurrences(valueToRemove));
        int count = 0;
        for (int i = 0; i < input.length; i++){
            if (input[i] != valueToRemove){
                output[count] = input[i];
                count++;
            }
        }
        return output;
    }

    // CAN I DO THIS WITH A FILTER ???
    public E getMostCommonFromMerge(E[] arrayToMerge){
        input = mergeArrays(arrayToMerge);
        E mostCommon = this.input[0];
        int count = 0;
        for(E thing : this.input){
            if (getNumberOfOccurrences(thing) > count){
                mostCommon = thing;
                count = getNumberOfOccurrences(thing);
            }

        }
        return mostCommon;
    }

    // OLD METHOD WITHOUT STREAMS
//    public Integer getNumberOfOccurrences(E valueToEvaluate){
//        Integer count = 0;
//        for (E thing : this.arr){
//            if (thing.equals(valueToEvaluate)) count++;
//        }
//        return count;
//    }

    public Integer getNumberOfOccurrences(E valueToEvaluate){
        return (int) Arrays.stream(input)
                .filter(n -> n == valueToEvaluate).count();
    }

    //OLD METHOD - IS THERE A BETTER WAY ???
    public E[] mergeArrays (E[] arrayToMerge) {
        E[] catArray = Arrays.copyOf(input, input.length + arrayToMerge.length);
        for (int i = input.length; i < catArray.length; i++) {
            catArray[i] = arrayToMerge[i - input.length];
        }
        return catArray;
    }

    //THIS WILL RETURN A STREAM, NOT AN ARRAY
//    public E[] mergeArrays (E[] arrayToMerge){
//        Stream<E> stream1 = Arrays.stream(input);
//        Stream<E> stream2 = Arrays.stream(arrayToMerge);
//        return Arrays.Stream.concat(stream1, stream2);
//    }

}
