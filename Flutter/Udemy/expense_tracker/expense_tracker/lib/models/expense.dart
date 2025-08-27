import 'package:expense_tracker/data/constants.dart';

class Expense {
  final String id;
  final double amount;
  final String title;
  final DateTime date;
  final Category category;

  String get formattedDate => Constants.dateFormatter.format(date);

  Expense({
    required this.category,
    required this.date,
    required this.amount,
    required this.title,
  }) : id = Constants.uuid.v4();
}

class ExpenseBucket {
  final Category category;
  final List<Expense> expenses;

  ExpenseBucket({required this.category, required this.expenses});

  ExpenseBucket.forCategory(List<Expense> allExpense,this.category)
    : expenses = allExpense
          .where((expense) => expense.category == category)
          .toList();

  double get totalExpenses {
    double sum = 0;
    for (final expense in expenses) {
      sum += expense.amount;
    }
    return sum;
  }
}
