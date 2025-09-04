package com.nbs.counterapp_mvvm

fun main() {
    //val base = BaseClass()
    //base.coreValues()

//    val secondary = Secondary()
//    secondary.coreValues()
//    secondary.role()

//    val another = Another()
//    another.role()

    val other = Other()
    other.coreValues()
    other.role()
    other.archery()
    other.sing()
}

open class BaseClass {

    open fun role() {
        println("Member of Base Class....")
    }

    fun coreValues() {
        println("Core Values....")
    }
}

open class Secondary : BaseClass() {
    override fun role() {
        //super.role()
        println("Member of Secondary Class....")
    }
}

class Another : BaseClass() {
    override fun role() {
        println("Member of Another Class....")
    }
}

class Other : Secondary(), Skill, AnotherSkill {

    override fun archery() {
       println("Archer...")
    }

    override fun sing() {
        println("Singer...")
    }
}

interface Skill {
    fun archery()
}

interface AnotherSkill {
    fun sing()
}

