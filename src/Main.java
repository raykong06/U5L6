import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        BankApp app = new BankApp();
        String message;
        String choice = "";

        // Set Info
        System.out.print("Please enter your name: ");
        String name = s.nextLine();
        System.out.print("Please a 4-digit PIN for your credit card: ");
        String PIN = s.nextLine();
        message = app.setCard1(name,PIN);
        System.out.println(message + "\n");

        // Set Bagel Shop
        System.out.println("--MAKE YOUR BAGEL SHOP--");
        System.out.print("Please enter your bagel shop's name: ");
        String bagelName = s.nextLine();
        System.out.print("Please state how much inventory you would like to start with: ");
        int inventory = s.nextInt();
        System.out.print("Please state the price per bagel (whole dollars): ");
        int bagelPrice = s.nextInt();
        message = app.setShop(bagelName,inventory,bagelPrice);
        System.out.println(message + "\n");

        // Choice Main Menu
        while (!choice.equals("X"))
        {
            choice = "0";
            message = app.toString();
            System.out.println("\n" + message +"\n\n");
            while (!choice.equals("1") || !choice.equals("2") || !choice.equals("3") || !choice.equals("4") || !choice.equals("5")
            || !choice.equals("A") || !choice.equals("B") || !choice.equals("C") || !choice.equals("D") || !choice.equals("E"))
            {
                System.out.print("Please select your choice: ");
                choice = s.nextLine();
            }

            if (!choice.equals("X"))
            {
                if (choice.equals("1") || choice.equals("2"))
                {
                    System.out.print("How many bagels would you like to ");
                    if (choice.equals("1"))
                    {
                        System.out.print("buy?: ");
                    }
                    else
                    {
                        System.out.print("return?: ");
                    }
                    int quantity = s.nextInt();
                    System.out.print("Please enter your PIN: ");
                    PIN = s.nextLine();
                    message = app.visitBagelShop(Integer.parseInt(choice), quantity, PIN);
                }
                else if (choice.equals("3"))
                {
                    System.out.print("Please enter the amount you would like to pay on your default card: ");
                    int payment = s.nextInt();
                    message = app.creditCardPayment(payment);
                }
                else if (choice.equals("4"))
                {
                    System.out.print("Please enter the card you would like to set as your default (1 or 2): ");
                    int card = s.nextInt();
                    message = app.defaultCardAction(card);
                }
                else if (choice.equals("5"))
                {
                    message = app.compareBalance();
                }
                else if (choice.equals("A"))
                {
                    message = app.depositShopProfits();
                }
                else if (choice.equals("B"))
                {
                    message = app.getInventory();
                }
                else if (choice.equals("C"))
                {
                    message = app.checkProfits();
                }
                else if (choice.equals("D"))
                {
                    System.out.print("How much (whole) $ off would you like each bagel to be?: ");
                    int markdown = s.nextInt();
                    message = app.setSale(markdown);
                }
                else if (choice.equals("E"))
                {
                    message = app.resetSale();
                }
                System.out.println(message);
            }
        }
        System.out.println("THANK YOU FOR PLAYING!");
    }
}
