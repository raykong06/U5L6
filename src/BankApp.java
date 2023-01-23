public class BankApp {

    // Instance Variables
    BagelShop shop;
    Bank bank;
    CreditCard card1;
    CreditCard card2;
    CreditCard defaultCard;
    boolean saleOn;
    int originalBagelPrice;
    String message;

    public BankApp()
    {
        shop = null;
        bank = new Bank();
        card1 = null;
        defaultCard = null;
        card2 = null;
        saleOn = false;
        originalBagelPrice = 0;
        message = "";
    }

    public String setShop(String name, int inventory, int bagelPrice)
    {
        shop = new BagelShop(name, inventory, bagelPrice, bank);
        originalBagelPrice = bagelPrice;

        return "You successfully set up your bagel shop!";
    }

    public String setCard1(String name, String personalPIN)
    {
        card1 = new CreditCard(name, personalPIN);
        defaultCard = card1;
        return "You successfully set up your first credit card!";
    }

    // Customer Actions
    public boolean bagelShopAction(int choice, int quantity, String cardPIN)
    {
        if (!(defaultCard.checkPIN(cardPIN)))
        {
            return false;
        }
        else if (choice == 1)
        {
            shop.payForBagels(defaultCard, quantity, cardPIN);
        }
        else if (choice == 2)
        {
            shop.returnBagels(defaultCard, quantity, cardPIN);
        }
        return true;
    }

    public String visitBagelShop(int choice, int quantity, String cardPIN)
    {
        boolean action = bagelShopAction(choice, quantity, cardPIN);
        if (!action)
        {
            return "Wrong PIN!\n";
        }
        else if (choice == 1)
        {
            return "Payment successful! You were charged $" + (quantity * shop.getBagelPrice()) + ".\n";
        }
        else
        {
            return "Return successful! You were credited $" + (quantity * shop.getBagelPrice()) + ".\n";
        }
    }

    public String creditCardPayment(int payment)
    {
        bank.makePayment(defaultCard, payment);
        String str = "You made a payment of $" + payment + " to your default card! \n";
        if (defaultCard.getBalanceOwed() > bank.getHIGH_BALANCE_AMT())
        {
            str += "Since your balance was above the high balance amount, you were charged a $" + bank.getHIGH_BALANCE_AMT() + " fee.\n";
        }
        return str;
    }

    public void setDefaultCard(CreditCard card)
    {
        defaultCard = card;
    }

    public boolean checkCard2()
    {
        if (card2 == null)
        {
            return false;
        }
        return true;
    }

    public String defaultCardAction(int choice)
    {
        if (checkCard2())
        {
            String str = "You successfully set your default credit card to card ";
            if (choice == 1)
            {
                setDefaultCard(card1);
                str += "1.";
            }
            else
            {
                setDefaultCard(card2);
                str += "2.";
            }
            return str;
        }
        else
        {
            return "You do not have a second card! Please open a second credit card.";
        }
    }

    public String compareBalance()
    {
        if (checkCard2())
        {
            String str = "Card ";
            if ((bank.lowerBalance(card1,card2)) == card1)
            {
                str += "1 ";
            }
            else
            {
                str += "2 ";
            }
            str += "has the lower balance.";
            return str;
        }
        else
        {
            return "You do not have a second card! Please open a second credit card.";
        }
    }

    public void setCard2(String name, String personalPIN)
    {
        card2 = new CreditCard(name, personalPIN);
    }

    public String openCard2(String name, String personalPIN)
    {
        setCard2(name, personalPIN);
        return "You have successfully opened a second card!";
    }


    // Bagel Shop Owner Actions

    public String depositShopProfits()
    {
        shop.depositProfits();
        return "You successfully deposited $" + shop.getProfit() + "!";
    }

    public String getInventory()
    {
        return "You have " + shop.getInventory() + " bagels left.";
    }

    public String checkProfits()
    {
        return "You have $" + shop.getProfit() + " in undeposited profit.";
    }

    public void setMarkdown(int choice, int markdown)
    {
        if (choice == 1)
        {
            saleOn = true;
            shop.setBagelPrice(markdown);
        }
        else if (choice == 2)
        {
            saleOn = false;
            shop.setBagelPrice(originalBagelPrice);
        }
    }

    public String setSale(int markdown)
    {
        if (saleOn)
        {
            return "A sale is already occurring! Please end the sale to reset the discount!";
        }
        else
        {
            setMarkdown(1,markdown);
            return "You successfully set a discount of $" + markdown + "!";
        }
    }

    public String resetSale()
    {
        setMarkdown(2,0);
        return "You successfully ended the sale!";
    }

    public String toString()
    {
        String str = "MAIN MENU:\n" +
                "Customer Actions:" +
                "1: Buy Bagels\n" +
                "2: Return Bagels" +
                "3: Make a Credit Card Payment\n" +
                "4: Pick Default Credit Card\n" +
                "5: Compare Credit Card Balances\n" +
                "\n" +
                "Bagel Shop Owner Actions:\n" +
                "A: Deposit Profits to Bank\n" +
                "B: Check Inventory\n" +
                "C: Check Undeposited Profit" +
                "D: Make a Sale for Customers\n" +
                "E: End Sale\n" +
                "\n" +
                "X: Exit Program\n";

        return str;
    }
}
