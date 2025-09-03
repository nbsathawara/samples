package com.example.myfirstapp

fun main() {
    val myBankAccount = BankAccount("Nikhil", 2222.22)

    println(myBankAccount)

    myBankAccount.deposit(222.0)
    myBankAccount.withdraw(2222.22)
    myBankAccount.deposit(333.33)
    myBankAccount.withdraw(555.55)
    myBankAccount.displayTransactions()
}

data class BankAccount(val name: String, var balance: Double) {
    private val transactions = mutableListOf<String>()

    fun accountBalance(): Double {
        return balance
    }

    fun deposit(amount: Double) {
        balance += amount
        transactions.add("Deposit : $amount - Balance :$balance")
    }

    fun withdraw(amount: Double) {
        if (amount > balance) {
            println("Insufficient balance!!! - Balance : $balance")
        } else {
            balance -= amount
            transactions.add("Withdraw : $amount - Balance :$balance")
        }
    }

    fun displayTransactions() {
        println("Transaction History")
        for (transaction in transactions) {
            println(transaction)
        }
    }
}