class InsufficientAmountException extends Exception{
    private double amount;
    public InsufficientAmountException(double needs) {
        this.amount=needs;
    }

    public double getAmount() {
        return amount;
    }
    @Override
    public String getMessage(){
        return String.format("Sorry, but you are short $"+getAmount());
    }
}