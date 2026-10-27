package model;

import java.io.Serializable;
import model.annotation.MaxLength;

public class SavingsAccount extends Account implements Premium {

    private static final long serialVersionUID = 1L;

    private long minBalance;

    @MaxLength(10)
    private String branchName = "MainBranch";

    public SavingsAccount(String ownerName, long balance, long minBalance) {
        super(ownerName, balance);
        this.minBalance = minBalance;
    }

    @Override
    public double interestRate() {
        return 4.0;
    }

    @Override
    public boolean canWithdraw(long amount) {
        return amount > 0 && balance - amount >= minBalance;
    }
}