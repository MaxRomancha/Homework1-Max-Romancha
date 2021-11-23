package MainPackage;

//import java.io.IOException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class MainClass {

    enum TemperatureGrade {
        FROST,
        COOL,
        WARM,
        HOT
    }


    public static void main(String[] args) {

        //Making the instance of the class Scanner, used for obtaining the input.
        Scanner scanner = new Scanner(System.in);
        //Making the instance of the class Random, used for generating random numbers.
        Random random = new Random();


        System.out.println("Task 1. Converting integer to sum of its digits.");

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

        printResultsToConsole(Integer.toString(task1Input), Integer.toString(task1ExpectedResult), Integer.toString(task1Result), isTask1ResultCorrect);


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
        task2Result = task2(task2Result);
        boolean isTask2ResultCorrect = (Arrays.equals(task2Result, task2ExpectedResult));

        printResultsToConsole("", Arrays.toString(task2ExpectedResult), Arrays.toString(task2Result), isTask2ResultCorrect);


        System.out.println("Task 2.1. An array of N primes.");
        System.out.print("Please enter the array size for Task 2.1: ");

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

        printResultsToConsole(Integer.toString(arraySizeForTask2_1), Arrays.toString(task2_1ExpectedResult), Arrays.toString(task2_1Result), isTask2_1ResultCorrect);

        System.out.println("Task 3. Arithmetic mean");

        //generating random array size
        int task3ArraySize = random.nextInt(10) + 2;

        //generating random integer numbers
        int[] task3Input = new int[task3ArraySize];
        for (int i = 0; i < task3Input.length; i++) {
            task3Input[i] = random.nextInt(50);
        }

        float task3Result = task3(task3Input);
        float task3ExpectedResult = (float) task3Alternative(task3Input);

        boolean isTask3ResultCorrect = (Float.compare(task3Result, task3ExpectedResult) == 0);

        printResultsToConsole(Arrays.toString(task3Input), Float.toString(task3ExpectedResult), Float.toString(task3Result), isTask3ResultCorrect);


        System.out.println("Task 3.1. Geometric mean");

        double task3_1Result = task3_1(task3Input);
        double task3_1Expected = task3_1Alternative(task3Input);
        boolean isTask3_1ResultCorrect = (Double.compare(task3_1Result, task3_1Expected) == 0);

        printResultsToConsole(Arrays.toString(task3Input), Double.toString(task3_1Expected), Double.toString(task3_1Result), isTask3_1ResultCorrect);

        System.out.println("Task 3.2. Calculating Arithmetic/Geometric mean on user input.");

        boolean task3_2inputIsCorrect = true;

        System.out.println("Please choose an option:");
        System.out.println("1) Arithmetic mean.");
        System.out.println("2) Geometric mean.");
        System.out.print("Your choice [1]/[2]: ");
        byte task3_2UserChoice = 1;
        try {
            task3_2UserChoice = scanner.nextByte();
        } catch (Exception e) {
            System.out.println("Invalid selection! Using option 1 (Arithmetic mean) instead.");
            //task3_2UserChoice = 1;
        }

        switch (task3_2UserChoice) {
            case 1:
                System.out.println("Option 1 (Arithmetic mean) is selected.");
                break;
            case 2:
                System.out.println("Option 2 (Geometric mean) is selected.");
                break;
            default:
                System.out.println("Invalid selection! Using option 1 (Arithmetic mean) instead.");
                task3_2UserChoice = 1;
                break;
        }

        System.out.print("How many numbers do you want to enter? ");

        int task3_2numbersAmount = 0;
        try {
            task3_2numbersAmount = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Not an integer number!");
            task3_2inputIsCorrect = false;
        }

        if (task3_2numbersAmount < 1) {
            System.out.println("Entered value is incorrect. Task 3.2 will be skipped.");
            task3_2inputIsCorrect = false;
        } else {
            int[] task3_2Input = new int[task3_2numbersAmount];

            for (int i = 0; i < task3_2numbersAmount; i++) {
                System.out.printf("Please enter integer number %s of %s: ", i + 1, task3_2numbersAmount);
                int enteredValue;
                try {
                    enteredValue = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Entered value is not an integer. Task 3.2 will be skipped.");
                    task3_2inputIsCorrect = false;
                    break;
                }

                if (task3_2UserChoice == 2 && enteredValue < 0) {
                    System.out.println("Calculating geometric mean for negatives is impossible. Entered number will be made positive.");
                    enteredValue = -enteredValue;
                }

                task3_2Input[i] = enteredValue;

            }
            if (task3_2inputIsCorrect) {
                System.out.printf("Entered numbers:%n%s%n", Arrays.toString(task3_2Input));

                double task3_2Result;
                double task3_2Expected;

                task3_2Result = task3_2(task3_2Input, task3_2UserChoice);
                task3_2Expected = task3_2Alternative(task3_2Input, task3_2UserChoice);
                boolean isTask3_2ResultCorrect = (Double.compare(task3_2Result, task3_2Expected) == 0);

                printResultsToConsole(Arrays.toString(task3_2Input), Double.toString(task3_2Expected), Double.toString(task3_2Result), isTask3_2ResultCorrect);

            } else {
                System.out.println("Task 3.2 is skipped.");
            }

        }


        System.out.println("Task 4. Sorting integers in ascending order.");

        int task4AmountOfNumbers = random.nextInt(5) + 5;
        int[] task4Input = new int[task4AmountOfNumbers];

        for (int i = 0; i < task4AmountOfNumbers; i++) {
            task4Input[i] = random.nextInt(200);
        }

        int[] task4Result = task4(task4Input);
        int[] task4ResultAlternativeBogosort = task4AlternativeBogosort(task4Input);
        int[] task4ResultAlternativeQuicksort = task4AlternativeQuicksort(task4Input, 0, task4Input.length - 1);

        boolean isTask4ResultCorrect = Arrays.equals(task4Result, task4ResultAlternativeQuicksort);
        boolean isTask4ResultCorrectVsBogosort = Arrays.equals(task4Result, task4ResultAlternativeBogosort);

        System.out.println("Bubble sort (\"Actual result\") vs Quicksort (\"Expected result\")");
        printResultsToConsole(Arrays.toString(task4Input), Arrays.toString(task4ResultAlternativeQuicksort), Arrays.toString(task4Result), isTask4ResultCorrect);

        System.out.println("Bubble sort (\"Actual result\") vs Bogosort (\"Expected result\")");
        printResultsToConsole(Arrays.toString(task4Input), Arrays.toString(task4ResultAlternativeBogosort), Arrays.toString(task4Result), isTask4ResultCorrectVsBogosort);

        System.out.println("Task 5. Count occurrences of a substring in a string.");

        String task5InputMainString = "Java School allows you to try java programming language on practice. Java one of the most popular programming languages. I love Java!!!";
        String task5InputSubstring = "Java";

        int task5Result = task5(task5InputSubstring, task5InputMainString);
        int task5ExpectedResult = 4;
        boolean isTask5ResultCorrect = (task5Result == task5ExpectedResult);

        printResultsToConsole("Search [" + task5InputSubstring + "] in [" + task5InputMainString + "]", Integer.toString(task5ExpectedResult), Integer.toString(task5Result), isTask5ResultCorrect);

        System.out.println("Task 5.1. Array of found occurrence indexes of a substring in a string.");

        int[] task5_1Result = task5_1(task5InputSubstring, task5InputMainString);
        int[] task5_1ExpectedResult = {0, 30, 69, 128};
        boolean isTask5_1ResultCorrect = Arrays.equals(task5_1Result, task5_1ExpectedResult);

        printResultsToConsole("Search [" + task5InputSubstring + "] in [" + task5InputMainString + "]", Arrays.toString(task5_1ExpectedResult), Arrays.toString(task5_1Result), isTask5_1ResultCorrect);


        System.out.println("Task 5.2. Array of found occurrence indexes of a substring in a string, entered by user.");

        int[] task5_2Result = task5_2();

        System.out.printf("Indexes for %s found occurrences: %s%n%n", task5_2Result.length, Arrays.toString(task5_2Result));
        promptEnterKey();

        System.out.println("Optional task 1. Generating an array with random integers.");


        int[] optionalTask1Result = optionalTask1();

        System.out.println(Arrays.toString(optionalTask1Result));
        for (int i = 0; i < optionalTask1Result.length; i++) {
            System.out.printf("%s day, %s degrees;%n", i + 1, optionalTask1Result[i]);
        }

        promptEnterKey();


        System.out.println("Optional task 2. ENUM usage.");

        int optionalTask2Input = random.nextInt(201) - 100;

        Enum optionalTask2Result = optionalTask2(optionalTask2Input);

        System.out.printf("Temperature of %s is qualified as: %s%n", optionalTask2Input, optionalTask2Result);

        promptEnterKey();

        System.out.println("Optional task 2.1. Return array of 2 relevant strings.");

        String[] optionalTask2_1Result = new String[2];

        optionalTask2_1Result = optionalTask2_1(optionalTask2Result);
        System.out.printf("Clothes for %s weather are: %s%n", optionalTask2Result, Arrays.toString(optionalTask2_1Result));

        promptEnterKey();

        System.out.println("Returning relevant string output per element on a randomly generated array.");

        System.out.print("Please enter number of days: ");

        int optionalTask3Input;

        try {
            optionalTask3Input = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input! Using number of days [10] instead.");
            optionalTask3Input = 10;
        }

        if (optionalTask3Input < 1) {
            System.out.println("Entered value is too low. Using number of days [1] instead");
            optionalTask3Input = 1;
        }
        optionalTask3(optionalTask3Input);

        promptEnterKey();

        System.out.println("The end of the program reached.");
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

        int[] resultedArray = arrayToFillWithPrimes.clone();

        //First number to check for primality
        int candidateNumberForPrimality = 2;

        for (int arrayIndex = 0; arrayIndex < resultedArray.length; arrayIndex++) {
            while (!isNumberPrime(candidateNumberForPrimality)) {
                candidateNumberForPrimality++;
            }
            resultedArray[arrayIndex] = candidateNumberForPrimality;
            candidateNumberForPrimality++;
        }

        return resultedArray;
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
        System.out.print("Press \"ENTER\" to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
        System.out.println();
    }


    public static float task3(int[] inputArray) {
        //Calculating of arithmetic mean.
        int sum = 0;
        for (int j : inputArray) {
            sum += j;
        }
        return (float) sum / inputArray.length;
    }

    public static double task3Alternative(int[] inputArray) {
        // Calculation of arithmetic mean with the Java Stream API
        return Arrays.stream(inputArray).average().orElse(Double.NaN);
    }

    public static double task3_1(int[] inputArray) {
        // Calculation of geometric mean
        double result = 1;
        for (int j : inputArray) {
            result *= j;
        }
        result = Math.pow(result, 1.0 / inputArray.length);

        //rounding the result to 5 decimal places
        // https://www.baeldung.com/java-round-decimal-number
        BigDecimal bd = new BigDecimal(Double.toString(result));
        bd = bd.setScale(5, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double task3_1Alternative(int[] inputArray) {
        // Calculation of geometric mean, but calculating Nth root using Newton's method
        // Source: https://www.geeksforgeeks.org/n-th-root-number/
        double result = 1;
        for (int j : inputArray) {
            result *= j;
        }

        // initially guessing a random number between
        // 0 and 9
        double xPre = Math.random() % 10;

        // smaller eps, denotes more accuracy
        double eps = 0.0000001;

        // initializing difference between two
        // roots by INT_MAX
        double delX = 2147483647;

        // xK denotes current value of x
        double xK = 0.0;

        // loop until we reach desired accuracy
        while (delX > eps) {
            // calculating current value from previous
            // value by newton's method
            xK = ((inputArray.length - 1.0) * xPre +
                    result / Math.pow(xPre, inputArray.length - 1)) / (double) inputArray.length;
            delX = Math.abs(xK - xPre);
            xPre = xK;
        }

        //rounding the result to 5 decimal places
        // https://www.baeldung.com/java-round-decimal-number
        BigDecimal bd = new BigDecimal(Double.toString(xK));
        bd = bd.setScale(5, RoundingMode.HALF_UP);
        return bd.doubleValue();


    }

    public static double task3_2(int[] inputArray, byte userChoice) {
        double result = 0;
        switch (userChoice) {
            case 1:
                result = task3(inputArray);
                break;

            case 2:
                result = task3_1(inputArray);
                break;
        }
        return result;
    }

    public static double task3_2Alternative(int[] inputArray, byte userChoice) {
        double result = 0;
        switch (userChoice) {
            case 1:
                result = task3Alternative(inputArray);
                break;

            case 2:
                result = task3_1Alternative(inputArray);
                break;
        }
        return result;
    }

    public static int[] task4(int[] inputArray) {

        int[] sortedArray = inputArray.clone();

        for (int i = 0; i < sortedArray.length; i++) {
            for (int j = i + 1; j < sortedArray.length; j++) {
                if (sortedArray[i] > sortedArray[j]) {
                    sortedArray[i] += sortedArray[j];
                    sortedArray[j] = sortedArray[i] - sortedArray[j];
                    sortedArray[i] -= sortedArray[j];
                }
            }
        }

        return (sortedArray);
    }

    public static int[] task4AlternativeBogosort(int[] arrayToSort) {
        int[] resultedArray = arrayToSort.clone();

        while (!isArraySortedInAscendingOrder(resultedArray)) {
            shuffleArray(resultedArray);
        }

        return resultedArray;
    }

    public static void shuffleArray(int[] arrayToShuffle) {
        List<Integer> shuffledList = new ArrayList<>();
        for (int i = 0; i < arrayToShuffle.length; i++) {
            shuffledList.add(arrayToShuffle[i]);
        }
        Collections.shuffle(shuffledList);

        for (int i = 0; i < arrayToShuffle.length; i++) {
            arrayToShuffle[i] = shuffledList.get(i);
        }

    }

    public static boolean isArraySortedInAscendingOrder(int[] arrayToCheck) {
        for (int i = 1; i < arrayToCheck.length; i++) {
            if (arrayToCheck[i] < arrayToCheck[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static int[] task4AlternativeQuicksort(int arrayToSort[], int begin, int end) {
        //Source: https://www.baeldung.com/java-quicksort

        int[] arr = arrayToSort.clone();

        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            arr = task4AlternativeQuicksort(arr, begin, partitionIndex - 1);
            arr = task4AlternativeQuicksort(arr, partitionIndex + 1, end);
        }

        return arr;

    }

    private static int partition(int arr[], int begin, int end) {
        //Source: https://www.baeldung.com/java-quicksort

        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        int swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }

    public static int task5(String substringToSearch, String stringWhereToSearch) {

        substringToSearch = substringToSearch.toLowerCase();
        stringWhereToSearch = stringWhereToSearch.toLowerCase();

        int matchesFound = 0;

        int i = 0;
        while (stringWhereToSearch.substring(i).contains(substringToSearch)) {
            i += stringWhereToSearch.substring(i).indexOf(substringToSearch) + 1;
            matchesFound++;
        }

        return matchesFound;
    }

    public static int[] task5_1(String substringToSearch, String stringWhereToSearch) {

        substringToSearch = substringToSearch.toLowerCase();
        stringWhereToSearch = stringWhereToSearch.toLowerCase();

        int countOfOccurrences = task5(substringToSearch, stringWhereToSearch);

        int[] foundIndexes = new int[countOfOccurrences];

        int currentIndex = 0;

        for (int i = 0; i < countOfOccurrences; i++) {
            currentIndex += stringWhereToSearch.substring(currentIndex).indexOf(substringToSearch);
            foundIndexes[i] = currentIndex;
            currentIndex++;
        }


        return foundIndexes;
    }

    public static int[] task5_2() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the substring (string to search):");

        String task5_2InputSubstring = scanner.nextLine();

        System.out.println("Please enter the main string (string where to search):");

        String stringWhereToSearch = scanner.nextLine();

        return task5_1(task5_2InputSubstring, stringWhereToSearch);
    }

    public static void printResultsToConsole(String input, String expectedResult, String actualResult, Boolean isAMatch) {

        String resultsTable = "| %-" + Math.max(input.length(), 5) + "s | %-" + Math.max(expectedResult.length(), 15) + "s | %-" + Math.max(actualResult.length(), 15) + "s | %-5s | %n";

        System.out.printf(resultsTable, "Input", "Expected Result", "Actual Result", "Match");
        System.out.printf(resultsTable, input, expectedResult, actualResult, isAMatch);
        System.out.println();
        promptEnterKey();


    }

    public static int[] optionalTask1() {
        int[] resultArray = new int[29];


        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = randomTemperature();
        }

        return resultArray;
    }

    public static int randomTemperature() {
        Random random = new Random();
        return random.nextInt(201) - 100;
    }

    public static Enum optionalTask2(int temperature) {

        byte grade;
        Enum temperatureGrade = MainClass.TemperatureGrade.FROST;

        if (temperature < -50) {
            grade = 0;
        } else if (temperature < 0) {
            grade = 1;
        } else if (temperature < 50) {
            grade = 2;
        } else {
            grade = 3;
        }

        switch (grade) {
            case 0:
                temperatureGrade = MainClass.TemperatureGrade.FROST;
                break;
            case 1:
                temperatureGrade = MainClass.TemperatureGrade.COOL;
                break;
            case 2:
                temperatureGrade = MainClass.TemperatureGrade.WARM;
                break;
            case 3:
                temperatureGrade = MainClass.TemperatureGrade.HOT;
                break;
        }

        return temperatureGrade;

    }

    public static String[] optionalTask2_1(Enum temperatureGrade) {

        String[] resultArray = new String[2];

        String[] clothes = {"Parka", "Coat", "Jacket", "T-Shirt"};
        String[] shoes = {"Arctic boots", "Boots", "Sneakers", "Sandals"};

        int temperatureIndex = 0;

        if (temperatureGrade == TemperatureGrade.FROST) {
            temperatureIndex = 0;
        } else if (temperatureGrade == TemperatureGrade.COOL) {
            temperatureIndex = 1;
        } else if (temperatureGrade == TemperatureGrade.WARM) {
            temperatureIndex = 2;
        } else if (temperatureGrade == TemperatureGrade.HOT) {
            temperatureIndex = 3;
        }

        resultArray[0] = clothes[temperatureIndex];
        resultArray[1] = shoes[temperatureIndex];
        return resultArray;

    }

    public static int[] optionalTask3(int numberOfDays) {
        int[] weatherForecast = new int[numberOfDays];

        for (int i = 0; i < weatherForecast.length; i++) {
            weatherForecast[i] = randomTemperature();
        }

        for (int i = 0; i < weatherForecast.length; i++) {
            System.out.printf("Day %s: %s, %s+%s%n", i + 1, weatherForecast[i], optionalTask2_1(optionalTask2(weatherForecast[i]))[0], optionalTask2_1(optionalTask2(weatherForecast[i]))[1]);
        }

        return weatherForecast;

    }
}
