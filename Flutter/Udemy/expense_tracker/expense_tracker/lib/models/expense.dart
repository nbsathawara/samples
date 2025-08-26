import 'package:expense_tracker/data/constants.dart';

class Expense {
  final String id;
  final double amount;
  final String title;
  final DateTime date;
  final Category category;

  Expense({
    required this.category,
    required this.date,
    required this.amount,
    required this.title,
  }) : id = Constants.uuid.v4();
}
