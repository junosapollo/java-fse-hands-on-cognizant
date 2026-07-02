package com.example.finance;

public class FinancialForecasting {

    // recursive method to predict future value
    public static double predictFutureValue(double currentValue, double growthRate, int periods) {
        // base case if no more periods left return the current value
        if (periods <= 0) {
            return currentValue;
        }
        
        // recursive step calculate value for next period
        double nextValue = currentValue * (1 + growthRate);
        return predictFutureValue(nextValue, growthRate, periods - 1);
    }
    
    // optimized approach using memoizationiterative or fast exponentiation
    // to avoid excessive recursion though tailrecursion is not optimized in java natively
    public static double predictFutureValueOptimized(double currentValue, double growthRate, int periods) {
        // mathpow is an optimized iterativenative approach or depending on implementation
        return currentValue * Math.pow(1 + growthRate, periods);
    }

    public static void main(String[] args) {
        double initialInvestment = 1000.0;
        double annualGrowthRate = 0.05;    // growth
        int years = 10;                    // years

        System.out.println("Initial Investment: $" + initialInvestment);
        System.out.println("Annual Growth Rate: " + (annualGrowthRate * 100) + "%");
        System.out.println("Periods (Years): " + years);

        double futureValueRecursive = predictFutureValue(initialInvestment, annualGrowthRate, years);
        System.out.printf("\nFuture Value (Recursive): $%.2f\n", futureValueRecursive);
        
        double futureValueOpt = predictFutureValueOptimized(initialInvestment, annualGrowthRate, years);
        System.out.printf("Future Value (Optimized): $%.2f\n", futureValueOpt);
        
        // recursive method time complexity where n is the number of periods due to n recursive calls
        // space complexity due to the call stack size
        // optimization for simple growth we can use the mathematical
        // reducing time complexity to or and space complexity to
    }
}
