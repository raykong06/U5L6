public class Bank
{
    private int onHandCash;
    private int vendorAccountBalance;
    private final int HIGH_BALANCE_AMT;
    private final int HIGH_BALANCE_FEE;

    public Bank()
    {
        onHandCash = 0;
        vendorAccountBalance = 0;
        HIGH_BALANCE_AMT = 500;
        HIGH_BALANCE_FEE = 5;
    }

    /** Method used by credit holders to make a payment.
     * Reduces the balance on the card by the payment amount,
     * and increases the bank's onHandCash by the payment amount.
     *
     * After the payment has been applied to the card, if the card's
     * current balance owed exceeds HIGH_BALANCE_AMT, charge the card
     * a HIGH_BALANCE_FEE
     *
     * @param card  The CreditCard to apply a payment to
     * @param payment  The amount of the payment
     */
    public void makePayment(CreditCard card, int payment)
    {
        card.reduceBalance(payment);
        onHandCash += payment;

        if (card.getBalanceOwed() > HIGH_BALANCE_AMT)
        {
            card.chargeCard(HIGH_BALANCE_FEE);
        }
    }

    /** Method used by vendors (such as bagel shops!) to deposit profits.
     * Increases vendorAccountBalance by the depositAmount amount, and also
     * increases the bank's onHandCash by the depositAmount amount.
     *
     * @param depositAmount  The amount of the deposit
     */
    public void vendorDeposit(int depositAmount)
    {
        vendorAccountBalance += depositAmount;
        onHandCash += depositAmount;
    }

    /** Utility method to compare the balance of two credit cards; returns the
     * the card with the lower balance; if both have the exact same balance, returns null
     *
     * @param card1  The first CreditCard to compare
     * @param card2  The second CreditCard to compare
     * @return  The CreditCard with the lower balance, or null if both have same balance
     */
    public CreditCard lowerBalance(CreditCard card1, CreditCard card2)
    {
        if (card1.getBalanceOwed() < card2.getBalanceOwed())
        {
            return card1;
        }
        else if (card2.getBalanceOwed() < card1.getBalanceOwed())
        {
            return card2;
        }
        else
        {
            return null;
        }
    }

    public int getHIGH_BALANCE_AMT()
    {
        return HIGH_BALANCE_AMT;
    }

    public String toString()
    {
        String str = "Bank";
        str += "\nOn Hand Cash: " + onHandCash;
        str += "\nVendor Account Balance: " + vendorAccountBalance;
        return str;
    }
}
