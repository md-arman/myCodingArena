package com.practice.arena1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class StockBuySellProfit {

    /*@Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }*/

    //returns the max price that user can sell at after the 'buy' day
    private static int maxVal(List<Integer> remPriceArr)
    {
        Integer maxVal = remPriceArr.stream().sorted().max((a, b) -> a.compareTo(b)).get();
        //System.out.println(maxVal);
        return maxVal.intValue();
    }

    private static int myProfit(int arr[])
    {
        if(arr.length<2)
            return -1;
        //List<Integer> myPriceList = Stream.of(arr);
        List<Integer> myPriceList = Arrays.stream(arr).boxed().collect(Collectors.toList());

        //List<Integer> myRemPriceList = (ArrayList<Integer>) myPriceList.clone();
        List<Integer> myRemPriceList = new ArrayList<Integer>(myPriceList);

        //can use map if buy/sell/both values are also required.
        //Map<Integer, Integer> myProfitMap = new HashMap<Integer,Integer>();
        TreeSet<Integer> profitSet = new TreeSet<Integer>();

        int buy = 0;
        int profit = 0;
        int sell = 0;

        //assuming won't buy/sell on last day, hence size minus 1, hence profit = -1
        for(int i=0; i<myPriceList.size()-1; i++)
        {
            buy = myPriceList.get(i);

            //removing the buy value
            myRemPriceList.remove(0);
            //System.out.println(myRemPriceList);

            sell = maxVal(myRemPriceList);
            if (sell>buy)
                profit = sell - buy;
            else
                profit = -1;
            profitSet.add(profit);
        }
        //System.out.println(profitSet);
        return profitSet.last().intValue();
    }

    public static void main(String[] args) {
        int arr [] = {44,30,24,32,35,30,40,38,15};
        //output: 40-24=16

        //int arr [] = {10,12,4,5,9};
        //output: 9-4=5

        //int arr [] = {14,20,4,12,5,11};
        //output: 12-4=8

        //int arr [] = {10,9,8,2};
        //output: -1

        //int arr [] = {6};
        //-1

        //int arr [] = {6,6};
        //-1

        //int arr [] = {};
        //-1

        System.out.println(myProfit(arr));
    }
}