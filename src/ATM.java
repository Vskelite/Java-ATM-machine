import java.sql.SQLOutput;
import java.util.*;

public class ATM{
    public static void main(String[] args) {
        ATMop obj = new ATMop();
    }
}
class Data{
    float balance;
}

class ATMop {
    HashMap<Integer , Data >  map  = new HashMap<>();
    Scanner sc = new Scanner(System.in);
    ATMop(){
        System.out.println("Welcome to our ATM");
        op();
    }

    public void op(){
        System.out.println("Enter valid pin");

        int pin = sc.nextInt();

        if(map.containsKey(pin)){
            Data obj = map.get(pin);
            menu(obj);
        }else{
            System.out.println("Please create account first ");
            System.out.println("Set PIN code");
            System.out.println("Set PIN greater than 2  and less than 5");

            int setPIN = sc.nextInt();

            Data obj = new Data();
            if(Integer.toString(setPIN).length() < 5 && 2<Integer.toString(setPIN).length() ){
                obj.balance = 0;
                map.put(setPIN , obj);
                menu(obj);
            }
            else{
                System.out.println("Invalid PIN system terminate");
            }
        }
    }


    public void menu(Data obj){
        System.out.println("******************************************************************************");
        System.out.println("Enter your choice");

        System.out.println("1 . Check balance");
        System.out.println("2 . Withdraw money");
        System.out.println("3 . Deposit money");
        System.out.println("4 . Transfer Money");
        System.out.println("5 . Exit");
        System.out.println("6 . Check another account");
        System.out.println("Enter option to choose");
        int x = sc.nextInt();

        if(x == 1){
            check_balance(obj);
        }
        else if(x==2){
            withdraw(obj);
        }
        else if(x==3){
            deposite(obj);
        }
        else if(x == 4){
            transfer(obj);
        }
        else if(x == 5){
            System.out.println("Thank you for using our ATM");
        }
        else if(x == 6){
            op();
        }
        else{
            System.out.println("Enter valid option");
            menu(obj);
        }
    }

    public void check_balance(Data obj){
        System.out.println("Your current balance " + obj.balance);
        System.out.println("******************************************************************************");
        menu(obj);
    }

    public void deposite(Data obj){
        System.out.println("Enter the amount ");
        float d = sc.nextFloat();
        obj.balance += d;
        System.out.println("Amount deposited successfully ");
        System.out.println("******************************************************************************");
        menu(obj);
    }

    public void withdraw(Data obj){
        System.out.println("Enter the amount ");
        float d = sc.nextFloat();
        if(d > obj.balance) {
            System.out.println("Insufficient balance");
            menu(obj);
        }
        obj.balance -= d;
        System.out.println("Amount withdraw successfully ");
        System.out.println("******************************************************************************");
        menu(obj);
    }
    public void transfer(Data sender){
        System.out.println("Enter recipient's PIN:");
        int recipientPIN = sc.nextInt();

        if(map.containsKey(recipientPIN)){
            Data recipient = map.get(recipientPIN);
            System.out.println("Enter transfer Amount:");
            float transferAmount = sc.nextFloat();

            if(transferAmount > 0 && transferAmount <= sender.balance){
                sender.balance -= transferAmount;
                recipient.balance += transferAmount;
                System.out.println("Transfer Successful!!");
            }else{
                System.out.println("Invalid transfer amount or insufficient balance");
            }
        }else{
            System.out.println("Recipient's account not found.");
        }

        System.out.println("***********************************************************************");
        menu(sender);
    }


}