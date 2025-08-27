import 'package:expense_tracker/models/expense.dart';
import 'package:expense_tracker/screens/expense_item_widget.dart';
import 'package:flutter/material.dart';

class ExpenseListWidget extends StatelessWidget {
  const ExpenseListWidget({super.key, required List<Expense> expenses})
    : _expenses = expenses;

  final List<Expense> _expenses;

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: _expenses.length,
      itemBuilder: (context, index) {
        var expense = _expenses[index];
        return ExpenseItemWidget(expense: expense);
      },
    );
  }
}
