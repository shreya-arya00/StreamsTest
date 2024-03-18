package org.example;

import java.util.*;
import java.util.stream.Collectors;

class Transaction {
    private String transactionid;
    private String type;
    private double amount;

    public Transaction(String transactionid, String type, double amount) {
        this.transactionid = transactionid;
        this.type = type;
        this.amount = amount;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

class Account {
    private String accountid;
    private String customerName;
    private List<Transaction> transactions;

    public Account(String accountid, String customerName, List<Transaction> transactions) {
        this.accountid = accountid;
        this.customerName = customerName;
        this.transactions = transactions;
    }

    public String getAccountid() {
        return accountid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}

public class Main2 {
    public static void main(String[] args) {
        // Sample data
        Transaction t1 = new Transaction("1", "Credit", 100.0);
        Transaction t2 = new Transaction("2", "Debit", 200.0);
        Transaction t3 = new Transaction("3", "Credit", 150.0);
        Transaction t4 = new Transaction("4", "Debit", 300.0);
        List<Transaction> transactions = Arrays.asList(t1, t2, t3, t4);

        Account a1 = new Account("101", "John", Arrays.asList(t1, t2));
        Account a2 = new Account("102", "Jane", Arrays.asList(t3, t4));
        List<Account> accounts = Arrays.asList(a1, a2);

        // 1. Filtering Transactions Based on Minimum Amount
        double minAmount = 200.0;
        List<Transaction> filteredTransactions = transactions.stream()
                .filter(transaction -> transaction.getAmount() >= minAmount)
                .collect(Collectors.toList());

        // 2. Grouping Transactions by Account ID
        Map<String, List<Transaction>> transactionsByAccountId = accounts.stream()
                .flatMap(account -> account.getTransactions().stream())
                .collect(Collectors.groupingBy(Transaction::getTransactionid));

        // 3. Finding Transactions with Highest Amount Within Each Account Group
        Map<String, Optional<Transaction>> maxAmountByAccount = accounts.stream()
                .collect(Collectors.toMap(Account::getAccountid,
                        account -> account.getTransactions().stream()
                                .max(Comparator.comparing(Transaction::getAmount))));

        // 4. Calculating Total Transaction Amount for Each Customer
        Map<String, Double> totalTransactionAmountByCustomer = accounts.stream()
                .collect(Collectors.groupingBy(Account::getCustomerName,
                        Collectors.summingDouble(account ->
                                account.getTransactions().stream().mapToDouble(Transaction::getAmount).sum())));

        // 5. Sorting Accounts by Total Transaction Amount
        List<Account> sortedAccountsByTotalTransactionAmount = accounts.stream()
                .sorted(Comparator.comparingDouble(account ->
                        account.getTransactions().stream().mapToDouble(Transaction::getAmount).sum()))
                .collect(Collectors.toList());

        // Printing results
        System.out.println("Filtered Transactions: " + filteredTransactions);
        System.out.println("Transactions Grouped by Account ID: " + transactionsByAccountId);
        System.out.println("Max Amount by Account: " + maxAmountByAccount);
        System.out.println("Total Transaction Amount by Customer: " + totalTransactionAmountByCustomer);
        System.out.println("Sorted Accounts by Total Transaction Amount: " + sortedAccountsByTotalTransactionAmount);
    }
}
