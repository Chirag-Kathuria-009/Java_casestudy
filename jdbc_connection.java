import java.io.*;
import java.lang.*;
import java.util.*;


class dep_thread extends Thread{
    bank b = new bank();
    public dep_thread(bank B){
        this.b = B;
    }
    Scanner sc = new Scanner(System.in);
    public void run(){
        System.out.println("Enter the amount to be deposited...");
        int amount = sc.nextInt();
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            //TODO: handle exception
        }
        
        System.out.println("Deposited amount is "+amount);
        b.deposit(amount);
    }
}

class withd_thread extends Thread{
    bank b = new bank();
    Scanner sc = new Scanner(System.in);
    public withd_thread(bank B){
        this.b = B;
    }
    public void run(){
        System.out.println("Enter the withdrawal amount  ");
        int amount = sc.nextInt();
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            //TODO: handle exception
        }
        
        System.out.println("Withdrawal amount is "+amount);
        b.withdraw(amount);
    }
}
class bank{
    int balance ;

    synchronized void deposit(int amt){
        System.out.println("Going to deposit money ");
        balance += amt;
        notify();

        System.out.println("Final balance after deposition of amount " +balance);
        //Thread.sleep(5000);
    }

    synchronized void withdraw(int amt){
        System.out.println("Going to withdraw money..");
        if(amt>balance){
            try{
                wait();
                deposit(amt);
            }catch(Exception e){
                System.out.println("Waiting for deposit to happen...");
            }
        }
        balance -= amt;

        System.out.println("Balance of account after completion of Withdrawal  "+balance);
    }
        

}
public class jdbc_connection {

    
    public static void main(String args[]){
        final bank b = new bank();

        new withd_thread(b).start();
        new dep_thread(b).start();
        //new withd_thread().start();
    }

}