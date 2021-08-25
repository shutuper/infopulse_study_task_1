package com.company.factorial;

public class FactorialCounter {

    public static int countFactRecursion(int temp){
        if (temp < 0) throw new IllegalArgumentException("Number should be >= 0");
        else if (temp < 2) return 1;
        else return temp * countFactRecursion(temp - 1);
    }

    public static int countFactIteration(long temp) {
        if (temp < 0) throw new IllegalArgumentException("Number should be >= 0");
        else if (temp < 2) return 1;
        else {
            int result = 1;
            for (int i = 2; i <= temp; i++) {
                result *= i;
            }
            return result;
        }
    }

}
