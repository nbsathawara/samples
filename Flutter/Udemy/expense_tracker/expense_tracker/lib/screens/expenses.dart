import 'package:expense_tracker/models/expense.dart';
import 'package:expense_tracker/resources/Strings.dart';
import 'package:expense_tracker/screens/add_expense_widget.dart';
import 'package:expense_tracker/screens/expense_list_widget.dart';
import 'package:flutter/material.dart';

class Expenses extends StatefulWidget {
  const Expenses({super.key});

  @override
  State<Expenses> createState() => _ExpensesState();
}

class _ExpensesState extends State<Expenses> {
  final List<Expense> _expenses = [];

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
    showModalBottomSheet(
      isScrollControlled: true,
      context: context,
      builder: (ctx) => AddExpense(onAddExpense: _addExpense),
    );
  }

  void _addExpense(expense) => setState(() {
    _expenses.add(expense);
  });

  void _removeExpense(expense) {
    final index = _expenses.indexOf(expense);

    setState(() {
      _expenses.remove(expense);
    });

    ScaffoldMessenger.of(context).clearSnackBars();
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        duration: Duration(seconds: 3),
        action: SnackBarAction(
          label: Strings.lblUndo,
          onPressed: () {
            setState(() {
              _expenses.insert(index, expense);
            });
          },
        ),
        content: const Text(Strings.lblExpenseRemoved),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    var mainContent = _expenses.isNotEmpty
        ? ExpenseListWidget(
            expenses: _expenses,
            onRemoveExpense: _removeExpense,
          )
        : Center(child: const Text(Strings.lblNoExpense));

    return Scaffold(
      appBar: _appBar(),
      body: Column(
        children: [
          Text('Chart...'),
          Expanded(child: mainContent),
        ],
      ),
    );
  }
}
