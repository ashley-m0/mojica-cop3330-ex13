package oop.example;

import java.util.Scanner;
import java.lang.Math;

/*
 *  UCF COP3330 Summer 2021 Assignment 1 Solution
 *  Copyright 2021 Ashley Mojica
 */

/*
Exercise 13 - Determining Compound Interest
Simple interest is something you use only when making a quick guess. Most investments use a compound interest
formula, which will be much more accurate. And this formula requires you to incorporate exponents into your
programs.

Write a program to compute the value of an investment compounded over time. The program should ask for the
starting amount, the number of years to invest, the interest rate, and the number of periods per year to compound.

The formula you’ll use for this is A = P(1 + r/n)^(n*t) where:
P is the principal amount.
r is the annual rate of interest.
t is the number of years the amount is invested.
n is the number of times the interest is compounded per year.
A is the amount at the end of the investment.

Example Output:
What is the principal amount? 1500
What is the rate? 4.3
What is the number of years? 6
What is the number of times the interest is compounded per year? 4
$1500 invested at 4.3% for 6 years compounded 4 times per year is $1938.84.

Constraints:
Prompt for the rate as a percentage (like 15, not .15). Divide the input by 100 in your program.
Ensure that fractions of a cent are rounded up to the next penny.
Ensure that the output is formatted as money.

Challenges:
Ensure that all of the inputs are numeric and that the program will not let the user proceed without valid inputs.
Create a version of the program that works in reverse, so you can determine the initial amount you’d need to
invest to reach a specific goal.
Implement this program as a GUI app that automatically updates the values when any value changes.
 */

public class App 
{
    static Scanner input = new Scanner(System.in);
    public static void main( String[] args )
    {
        App myApp = new App();
        String principalString = myApp.getPrincipal();
        String interestString = myApp.getInterest();
        String timeString = myApp.getTime();
        String compoundString = myApp.getCompounds();


        double principal = myApp.convertStringToDouble(principalString);
        double interest = myApp.convertStringToDouble(interestString);
        double time = myApp.convertStringToDouble(timeString);
        double compound = myApp.convertStringToDouble(compoundString);

        interest = myApp.convertPercentToDecimal(interest);

        double worth = myApp.calcWorth(principal, interest, time, compound);

        String message = myApp.generateMessage(principalString, interestString, compoundString, timeString, worth);
        System.out.print(message);
    }

    public String getPrincipal(){
        System.out.print("What is the principal amount? ");
        String principal = input.nextLine();
        return principal;
    }

    public String getInterest(){
        System.out.print("What is the rate? ");
        String interest = input.nextLine();
        return interest;
    }

    public String getTime(){
        System.out.print("What is the number of years? ");
        String time = input.nextLine();
        return time;
    }

    public String getCompounds(){
        System.out.print("What is the number of times the interest is compounded per year? ");
        String compound = input.nextLine();
        return compound;
    }

    public double convertStringToDouble(String word){
        return Double.parseDouble(word);
    }

    public double convertPercentToDecimal(double percent){
        return (percent / 100);
    }

    public double calcWorth(double principal, double interest, double time, double compound){
        // A = P(1 + r/n)^(n*t)
        double num = 1.0 + (interest /compound);
        double exponent = compound * time;
        double worth = principal * Math.pow(num, exponent);
        double leftover = worth % 0.010;

        if (leftover >= 0.005){
            worth -= leftover;
            worth += 0.01;
        } else {
            worth -= leftover;
        }


        return worth;
    }

    public String generateMessage(String principal, String interest, String compounds, String time, double worth){
        return String.format("$%s invested at %s%% for %s years compounded %s times per year is $%.2f.", principal, interest, time, compounds, worth);
    }
}
