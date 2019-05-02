

import java.io.*;
import java.util.*;

public class HW3
{
    public static void main(String[] args)
    {
        //determines which part of HW. Part false is 9.7. Part true is 11.3
        boolean part = false;
        if(part ==false)
        {
            //makes account, the sets interest, withdraws, deposits
            //then lazily sets the date to whenever it's run and prints balance, interest, and date.
            Account thingy = new Account(1122, 20_000);
            thingy.setInterest(4.5);
            thingy.withdraw(2_500);
            thingy.deposit(3_000);
            System.out.println("Balance: "+thingy.getBalance() + "\nMonthly"
            +"Interest: "+ thingy.getMonthlyInterestRate() + "\nDate: " +
                    thingy.getCreation());

            //buffer line. Makes it look nice. Nothing else, really.
            System.out.println("");
            //set part two to run
            part = true;
        }
        if(part == true)
        {
            //makes a checking and savings and sets a depressingly real interest rate for each
            Checkings sword = new Checkings(1123, 5_000);
            Savings shield = new Savings(1124, 5_000);
            sword.setInterest(0);
            shield.setInterest(.2);

            //prints out using toString()
            System.out.println(sword.toString() + "\n");
            System.out.println(shield.toString()+ "\n");

            //tests overdraft. Show charges erroring.
            sword.withdraw(5001);
            sword.withdraw(50);
            sword.withdraw(1_000);

            //tests overdrawing. shows charges erroring.
            System.out.println("");
            shield.withdraw(5_000);
            shield.withdraw(100);


        }
    }
}

class Account
{
    //private variables
    private int id;
    private double balance;
    private double annualInterestRate;
    private Date dateCreated = new Date();

    //constructors
    Account()
    {id =0; balance=annualInterestRate=0;}
    Account(int id, double balance)
    {this.id = id; this.balance=balance;}

    //return and set ID
    int getId()
    {return id;}
    void setId(int id)
    {this.id = id;}

    //return and set balance
    double getBalance()
    {return balance;}
    void setBalance(double balance)
    {this.balance = balance;}

    //return and set interest
    double getInterest()
    {return annualInterestRate;}
    void setInterest(double interest)
    {annualInterestRate = interest;}

    //return creation date
    Date getCreation()
    {return dateCreated;}

    //returns monthly interest rate
    double getMonthlyInterestRate()
    {return (annualInterestRate/12);}

    //returns the interest required to pay this month
    double getMonthlyInterest()
    {return (balance*getMonthlyInterestRate());}


    //withdraws from account
    void withdraw(double amount)
    {balance = balance - amount;}

    //deposits in account
    void deposit(double amount)
    {balance = balance + amount;}
}


class Savings extends Account
{
    //creates saving account
    Savings()
    {setId(0000); setBalance(0);setInterest(0);}
    Savings(int id, double balance)
    {setId(id); setBalance(balance);}

    //override of withdraw specific to savings
    @Override
    void withdraw(double amount)
    {
        //test for overdrawing. if cannot overdraw, errors
        double balance = getBalance() - amount;
        if(balance >= 0)
          setBalance(balance);
        else
            System.out.println("You cannot overdraw this account");
    }

    //override toString specific to printing out savings
    @Override
    public String toString()
    {
        return "Id: " + this.getId() + "\nBalance: " + this.getBalance() +
                "\nInterest: " + this.getInterest() + "\nDate: " +
                this.getCreation();
    }

}


class Checkings extends Account
{
    //creates checkings acount.
    Checkings()
    {setId(0000); setBalance(0);setInterest(0);}
    Checkings(int id, double balance)
    {setId(id); setBalance(balance);}

    //overrides withdraw to specifics to checkings
    @Override
    void withdraw(double amount)
    {
        //checks balance. if balance overdrawn, still allows to -500
        double balance = getBalance() - amount;
        if(balance <= -500 && getBalance() >0)
        {
            setBalance(balance);
            System.out.println("You now have overdraft fees.");
        }
        //Cannot overdraw any past -500
        else if (balance <= -500)
        {
           System.out.println("You have hit your overdraft limit. You cannot withdraw more");
        }
        //doesn't hit negative number, normal withdraw
        else
            setBalance(balance);
    }

    //toString specific to checkings account.
    @Override
    public String toString()
    {
        return "Id: " + this.getId() + "\nBalance: " + this.getBalance() +
                "\nInterest: " + this.getInterest() + "\nDate: " +
                this.getCreation();
    }

}
