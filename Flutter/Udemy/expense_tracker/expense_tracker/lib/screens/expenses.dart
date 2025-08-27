import 'package:expense_tracker/data/constants.dart';
import 'package:expense_tracker/models/expense.dart';
import 'package:expense_tracker/screens/add_expense_widget.dart';
import 'package:expense_tracker/screens/expense_list_widget.dart';
import 'package:flutter/material.dart';

class Expenses extends StatefulWidget {
  const Expenses({super.key});

  @override
  State<Expenses> createState() => _ExpensesState();
}

class _ExpensesState extends State<Expenses> {
  final List<Expense> _expenses = [
    Expense(
      category: Category.work,
      date: DateTime.now(),
      amount: 19.99,
      title: 'Flutter Course',
    ),
    Expense(
      category: Category.leisure,
      date: DateTime.now(),
      amount: 39.99,
      title: 'Final Destination 5',
    ),
    Expense(
      category: Category.travel,
      date: DateTime.now(),
      amount: 159.99,
      title: 'Flight to LA',
    ),
    Expense(
      category: Category.food,
      date: DateTime.now(),
      amount: 9.99,
      title: 'Office Lunch',
    ),
  ];

  AppBar _appBar() {
    return AppBar(
      title: const Text('Expense Tracker'),
      actions: [
        IconButton(
          onPressed: _openAddExpenseOverlay,
          icon: const Icon(Icons.add),
        ),
      ],
    );
  }

  void _openAddExpenseOverlay() {
    showModalBottomSheet(context: context, builder: (ctx) => AddExpense());
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: _appBar(),
      body: Column(
        children: [
          Text('Chart...'),
          Expanded(child: ExpenseListWidget(expenses: _expenses)),
        ],
      ),
    );
  }
}
