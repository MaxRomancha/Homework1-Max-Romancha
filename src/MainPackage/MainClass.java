package MainPackage;

//import java.io.IOException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {

        //Making the instance of the class Scanner, used for obtaining the input.
        Scanner scanner = new Scanner(System.in);
        //Making the instance of the class Random, used for generating random numbers.
        Random random = new Random();

        System.out.println("Task 1. Converting integer to sum of its digits.");
        promptEnterKey();


        //Generating random input number for task 1.
        int task1Input = random.nextInt(1000);

        //Getting expected result from the reference file.
        int task1ExpectedResult;
        try {
            task1ExpectedResult = Integer.parseInt(Files.readAllLines(Paths.get("txt/task1_reference.txt")).get(task1Input));
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error: Expected result is not available!");
            task1ExpectedResult = -1;
        }


        int task1Result = task1(task1Input);
        boolean isTask1ResultCorrect = (task1Result == task1ExpectedResult);

        printTaskResultsToConsole(task1Input, task1ExpectedResult, task1Result, isTask1ResultCorrect);
        promptEnterKey();

        System.out.println("Task 2. An array of 100 primes.");

        int amountOfNumbersForTask2 = 100;

        //creating an array for Task 2
        int[] task2Result = new int[amountOfNumbersForTask2];

        //getting an array of primes from the reference file
        int[] task2ExpectedResult = new int[amountOfNumbersForTask2];
        for (int i = 0; i < task2ExpectedResult.length; i++) {

            try {
                task2ExpectedResult[i] = Integer.parseInt(Files.readAllLines(Paths.get("txt/task2_reference.txt")).get(i));
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("Error: Expected result is not available!");
                task2ExpectedResult[i] = -1;
            }

        }


        //calculating primes and comparing the result with the expected values
        task2(task2Result);
        boolean isTask2ResultCorrect = (Arrays.equals(task2Result, task2ExpectedResult));

        //printing results to console
        System.out.println("Expected:");
        System.out.println(Arrays.toString(task2ExpectedResult));
        System.out.println("Actual result:");
        System.out.println(Arrays.toString(task2Result));
        System.out.println("Is correct:");
        System.out.println(isTask2ResultCorrect);

        promptEnterKey();

        System.out.println("Task 2.1. An array of N primes.");


        System.out.println("Please enter the array size for Task 2.1:");

        int arraySizeForTask2_1;
        try {
            arraySizeForTask2_1 = scanner.nextInt();
        } catch (Exception e) {
            System.out.printf("Invalid Input! Using array size from Task 2 instead: [%s]%n", amountOfNumbersForTask2);
            promptEnterKey();
            arraySizeForTask2_1 = amountOfNumbersForTask2;
        }
        if (arraySizeForTask2_1 < 1) {
            System.out.printf("Entered value [%s] is too small. Using array size of [1] instead.%n", arraySizeForTask2_1);
            promptEnterKey();
            arraySizeForTask2_1 = 1;
            //throw new ArithmeticException("Array size is lesser than 1.");
        }


        //getting an array of primes from the reference file
        int[] task2_1ExpectedResult = new int[arraySizeForTask2_1];
        for (int i = 0; i < task2_1ExpectedResult.length; i++) {

            try {
                task2_1ExpectedResult[i] = Integer.parseInt(Files.readAllLines(Paths.get("txt/task2_reference.txt")).get(i));
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("Error: Expected result is not available!");
                task2_1ExpectedResult[i] = -1;
            }

        }


        //generating the array and filling it with primes
        int[] task2_1Result = task2_1(arraySizeForTask2_1);

        boolean isTask2_1ResultCorrect = (Arrays.equals(task2_1Result, task2_1ExpectedResult));

        // printing the results of Task 2.1 to console
        System.out.println("task2_1Result");
        System.out.println(Arrays.toString(task2_1Result));
        System.out.println("task2_1ExpectedResult");
        System.out.println(Arrays.toString(task2_1ExpectedResult));
        System.out.println(isTask2_1ResultCorrect);

        promptEnterKey();

        System.out.println("Task 3. Arithmetic mean");

        //generating random array size
        int task3ArraySize = random.nextInt(10) + 2;

        //generating random integer numbers
        int[] task3Input = new int[task3ArraySize];
        for (int i = 0; i < task3Input.length; i++) {
            task3Input[i] = random.nextInt(50);
        }

        float task3Result = task3(task3Input);
        float task3ExpectedResult = (float) task3Alternate(task3Input);

        System.out.println(Arrays.toString(task3Input));
        System.out.println("task3Result=" + task3Result);
        System.out.println("task3ExpectedResult=" + task3ExpectedResult);
        System.out.println(task3Result == task3ExpectedResult);


        promptEnterKey();

        System.out.println("Task 3.1. Geometric mean");

        double task3_1Result = task3_1(task3Input);
        double task3_1Expected = task3_1Alternative(task3Input);
        System.out.println("task3_1Result="+task3_1Result);
        System.out.println("task3_1Expected="+task3_1Expected);
        System.out.println(task3_1Expected==task3_1Result);

        promptEnterKey();

        //the end of the main method
    }

    public static int task1(int inputInteger) {
        //calculating the sum of the digits

        int sumOfDigits = 0;
        while (inputInteger > 0) {
            sumOfDigits += inputInteger % 10;
            inputInteger = inputInteger / 10;
        }

        return (sumOfDigits);


    }

    public static int[] task2(int[] arrayToFillWithPrimes) {
        //filling the array with prime numbers

        int candidateNumberForPrimality = 2;

        for (int arrayIndex = 0; arrayIndex < arrayToFillWithPrimes.length; arrayIndex++) {
            while (!isNumberPrime(candidateNumberForPrimality)) {
                candidateNumberForPrimality++;
            }
            arrayToFillWithPrimes[arrayIndex] = candidateNumberForPrimality;
            candidateNumberForPrimality++;
        }

        return arrayToFillWithPrimes;
    }

    private static boolean isNumberPrime(int numberToCheck) {
        for (int i = 2; i < numberToCheck; i++) {
            if (numberToCheck % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int[] task2_1(int arraySize) {
        int[] task2_1Result = new int[arraySize];
        return task2(task2_1Result);
    }

    public static void promptEnterKey() {
        //prompting the user to press ENTER.
        System.out.println("Press \"ENTER\" to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }


    public static void printTaskResultsToConsole(int taskInput, int taskExpectedResult, int taskResult, boolean isTaskResultCorrect) {
        System.out.printf("Input: %s%n", taskInput);
        System.out.printf("Expected result: %s%n", taskExpectedResult);
        System.out.printf("Calculated result: %s%n", taskResult);
        System.out.printf("Match: %s%n%n", isTaskResultCorrect);
    }

    public static float task3(int[] inputArray) {
        //Calculating of arithmetic mean.
        int sum = 0;
        for (int j : inputArray) {
            sum += j;
        }
        return (float) sum / inputArray.length;
    }

    public static double task3Alternate(int[] inputArray) {
        // Calculation of arithmetic mean with the Java Stream API
        return Arrays.stream(inputArray).average().orElse(Double.NaN);
    }

    public static double task3_1 (int[] inputArray) {
        // Calculation of geometric mean
        double result = 1;
        for (int j : inputArray) {
            result *= j;
        }
        result=Math.pow(result,1.0 / inputArray.length);

        //rounding the result to 5 decimal places
        // https://www.baeldung.com/java-round-decimal-number
        BigDecimal bd = new BigDecimal(Double.toString(result));
        bd = bd.setScale(5, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double task3_1Alternative (int[] inputArray) {
        // Calculation of geometric mean, but calculating Nth root using Newton's method
        // https://www.geeksforgeeks.org/n-th-root-number/
        double result = 1;
        for (int j : inputArray) {
            result *= j;
        }

        // initially guessing a random number between
        // 0 and 9
        double xPre = Math.random() % 10;

        // smaller eps, denotes more accuracy
        double eps = 0.000001;

        // initializing difference between two
        // roots by INT_MAX
        double delX = 2147483647;

        // xK denotes current value of x
        double xK = 0.0;

        // loop until we reach desired accuracy
        while (delX > eps)
        {
            // calculating current value from previous
            // value by newton's method
            xK = ((inputArray.length - 1.0) * xPre +
                    result / Math.pow(xPre, inputArray.length - 1)) / (double)inputArray.length;
            delX = Math.abs(xK - xPre);
            xPre = xK;
        }

        //rounding the result to 5 decimal places
        // https://www.baeldung.com/java-round-decimal-number
        BigDecimal bd = new BigDecimal(Double.toString(xK));
        bd = bd.setScale(5, RoundingMode.HALF_UP);
        return bd.doubleValue();



    }

}
