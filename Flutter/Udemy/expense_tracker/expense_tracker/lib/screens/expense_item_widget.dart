import 'package:expense_tracker/data/constants.dart';
import 'package:expense_tracker/models/expense.dart';
import 'package:expense_tracker/resources/dimensions.dart';
import 'package:flutter/material.dart';

class ExpenseItemWidget extends StatelessWidget {
  const ExpenseItemWidget({super.key, required this.expense});

  final Expense expense;

  @override
  Widget build(BuildContext context) {
    return Card(
      child: Padding(
        padding: Dimensions.expenseCardPadding,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(expense.title),
            Dimensions.smallDivider,
            Row(
              children: [
                Text('\$${expense.amount.toStringAsFixed(2)}'),
                const Spacer(),
                Row(
                  children: [
                    Icon(Constants.CategoryIcons[expense.category]),
                    Dimensions.smallDividerHorizontal,
                    Text(expense.formattedDate),
                  ],
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
