package com.example.myfirstapp


fun main() {
    //val winner=rockPaperScissor()

//    val daisy = Dog("Daisy", "Dwarf poodle", age = 1)
//    daisy.info()
//    daisy.bark()

//    val coffee = Coffee(sugarCount = 1, name = "Nikhil", size = "XL", creamAmount = 1)
//    makeCoffee(coffee)

    val shoppingList = mutableListOf("Processor", "RAM", "Graphics Card RTX 3060", "SSD")
    shoppingList.add("Cooling System")
    println(shoppingList)
    shoppingList.remove("Graphics Card RTX 3060")
    shoppingList.add("Graphics Card RTX 4090")
    println(shoppingList)
}

data class Coffee(
    val sugarCount: Int = 0,
    val name: String,
    val size: String,
    val creamAmount: Int = 1
)

fun makeCoffee(coffee: Coffee) {
    if (coffee.sugarCount == 1) println("Coffee with ${coffee.sugarCount} spoon of sugar for ${coffee.name}")
    else if (coffee.sugarCount == 0) println("Coffee with no sugar for ${coffee.name}")
    else println("Coffee with ${coffee.sugarCount} spoons of sugar for ${coffee.name}")
}

class Dog(val name: String, val breed: String, var age: Int = 0) {

    fun info() {
        println("$name is a $breed and is $age years old.")
    }

    fun bark() {
        println("$name says woff woff...")
    }
}

fun rockPaperScissor() {
    var playerChoice = ""
    println("Rock , Paper , Scissors??")
    playerChoice = readln()

    var computerChoice = ""
    val randomNumber = (1..3).random()
    when (randomNumber) {
        1 -> computerChoice = "Rock"
        2 -> computerChoice = "Paper"
        3 -> computerChoice = "Scissors"
    }
    println("Computer Choice : $computerChoice")

    val winner = when {
        playerChoice == computerChoice -> "Tie"
        playerChoice == "Rock" && computerChoice == "Scissors" -> "Player"
        playerChoice == "Paper" && computerChoice == "Rock" -> "Player"
        playerChoice == "Scissors" && computerChoice == "Paper" -> "Player"
        else -> "Computer"
    }
    println("Winner : $winner")
}
