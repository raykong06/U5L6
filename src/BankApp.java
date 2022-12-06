public class BankApp {

    // Instance Variables
    BagelShop shop;
    Bank bank;
    CreditCard card1;
    CreditCard card2;
    CreditCard defaultCard;
    boolean saleOn;
    int originalBagelPrice;

    public BankApp(BagelShop shop, Bank bank, CreditCard card)
    {
        this.shop = shop;
        this.bank = bank;
        this.card1 = card;
        defaultCard = card;
        card2 = null;
        saleOn = false;
        originalBagelPrice = shop.getBagelPrice();
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

    public String creditCardPayment(CreditCard card, int payment)
    {
        bank.makePayment(card, payment);
        String str = "You made a payment of $" + payment + "! \n";
        if (card.getBalanceOwed() > bank.getHIGH_BALANCE_AMT())
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
                "1: Visit the Bagel Shop\n" +
                "2: Make a Credit Card Payment\n" +
                "3: Pick Default Credit Card\n" +
                "4: Compare Credit Card Balances\n" +
                "\n" +
                "Bagel Shop Owner Actions:\n" +
                "A: Deposit Profits to Bank\n" +
                "B: Check Inventory\n" +
                "C: Check Undeposited Profit" +
                "D: Make a Sale for Customers\n" +
                "E: End Sale\n";
        
        return str;
    }
}
