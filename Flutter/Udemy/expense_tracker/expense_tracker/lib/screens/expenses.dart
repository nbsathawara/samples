import 'package:expense_tracker/data/constants.dart';
import 'package:expense_tracker/models/expense.dart';
import 'package:flutter/material.dart';

class Expenses extends StatefulWidget {
  const Expenses({super.key});

  @override
  State<Expenses> createState() => _ExpensesState();
}

class _ExpensesState extends State<Expenses> {
  final List<Expense> _expenses = [
    Expense(
      category: Category.food,
      date: DateTime.now(),
      amount: 19.99,
      title: 'Flutter Course',
    ),
    Expense(
      category: Category.leisure,
      date: DateTime.now(),
      amount: 39.99,
      title: 'cinema',
    ),
    Expense(
      category: Category.travel,
      date: DateTime.now(),
      amount: 159.99,
      title: 'Flight to LA',
    ),
    Expense(
      category: Category.work,
      date: DateTime.now(),
      amount: 9.99,
      title: 'Cab to Office',
    ),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          Text('Chart...'),
          Expanded(
            child: ListView.builder(
              itemCount: _expenses.length,
              itemBuilder: (context, index) => Text(_expenses[index].title),
            ),
          ),
        ],
      ),
    );
  }
}
