package com.example.finance;

public class FinancialForecasting {

    // Recursive method to predict future value
    // Formula: FutureValue = CurrentValue * (1 + GrowthRate)^Periods
    public static double predictFutureValue(double currentValue, double growthRate, int periods) {
        // Base case: If no more periods left, return the current value
        if (periods <= 0) {
            return currentValue;
        }
        
        // Recursive step: Calculate value for next period
        double nextValue = currentValue * (1 + growthRate);
        return predictFutureValue(nextValue, growthRate, periods - 1);
    }
    
    // Optimized approach using Memoization/Iterative or fast exponentiation
    // to avoid excessive recursion (though tail-recursion is not optimized in Java natively)
    public static double predictFutureValueOptimized(double currentValue, double growthRate, int periods) {
        // Math.pow is an optimized iterative/native approach: O(1) or O(log N) depending on implementation
        return currentValue * Math.pow(1 + growthRate, periods);
    }

    public static void main(String[] args) {
        double initialInvestment = 1000.0; // $1000
        double annualGrowthRate = 0.05;    // 5% growth
        int years = 10;                    // 10 years

        System.out.println("Initial Investment: $" + initialInvestment);
        System.out.println("Annual Growth Rate: " + (annualGrowthRate * 100) + "%");
        System.out.println("Periods (Years): " + years);

        double futureValueRecursive = predictFutureValue(initialInvestment, annualGrowthRate, years);
        System.out.printf("\nFuture Value (Recursive): $%.2f\n", futureValueRecursive);
        
        double futureValueOpt = predictFutureValueOptimized(initialInvestment, annualGrowthRate, years);
        System.out.printf("Future Value (Optimized): $%.2f\n", futureValueOpt);
        
        // Recursive method time complexity: O(n) where n is the number of periods, due to n recursive calls.
        // Space complexity: O(n) due to the call stack size.
        // Optimization: For simple growth, we can use the mathematical formula with fast exponentiation,
        // reducing time complexity to O(log n) or O(1) and space complexity to O(1).
    }
}
