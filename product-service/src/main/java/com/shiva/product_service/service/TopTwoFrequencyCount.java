package com.shiva.product_service.service;

import java.util.*;

public class TopTwoFrequencyCount {
     static void main(String[] args) {
        List<Integer> numList = Arrays.asList(1,  4,  2,  3,  2,  4,  4, 2);
        Map<Integer,Integer> freq = new LinkedHashMap<>();
        for(Integer num : numList){
            freq.put(num, freq.getOrDefault(num,0)+1);
        }
        List<Map.Entry<Integer , Integer>> list = new ArrayList<>(freq.entrySet());
//        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
//        System.out.println("Top two frequency count: ");
//        for(int i = 0; i < Math.min(2, list.size()); i++){
//            System.out.println(list.get(i));
//        }
         System.out.println(list);
    }
}

