import 'package:expense_tracker/resources/Dimensions.dart';
import 'package:flutter/material.dart';

class AddExpense extends StatefulWidget {
  const AddExpense({super.key});

  @override
  State<AddExpense> createState() {
    return new _AddExpenseState();
  }
}

class _AddExpenseState extends State<AddExpense> {
  final _titleTEController = TextEditingController();
  final _amountTEController = TextEditingController();

  void _showDatePicker() {

    final now=DateTime.now();
    final firstDate=DateTime(now.year-1,now.month,now.day);

    showDatePicker(context: context, initialDate: now,firstDate: firstDate, lastDate: now);
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: Dimensions.mediumMarginAll,
      child: Column(
        children: [
          TextField(
            maxLength: 50,
            decoration: const InputDecoration(label: Text('Title')),
            controller: _titleTEController,
          ),
          Row(
            children: [
              Expanded(
                child: TextField(
                  decoration: const InputDecoration(
                    prefixText: '\$ ',
                    label: Text('Amount'),
                  ),
                  controller: _amountTEController,
                  keyboardType: TextInputType.number,
                ),
              ),
              Dimensions.normalDividerHorizontal,
              Expanded(
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.end,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    IconButton(
                      onPressed: _showDatePicker,
                      icon: const Icon(Icons.calendar_month),
                    ),
                    const Text('Select Date'),
                  ],
                ),
              ),
            ],
          ),

          Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              ElevatedButton(
                onPressed: () {},
                child: const Text("Save Expense"),
              ),
              ElevatedButton(
                onPressed: () {
                  Navigator.pop(context);
                },
                child: const Text("Cancel"),
              ),
            ],
          ),
        ],
      ),
    );
  }

  @override
  void dispose() {
    _titleTEController.dispose();
    _amountTEController.dispose();
    super.dispose();
  }
}
