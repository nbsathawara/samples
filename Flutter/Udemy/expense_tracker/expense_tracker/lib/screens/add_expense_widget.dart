import 'package:expense_tracker/data/constants.dart';
import 'package:expense_tracker/data/extensions.dart';
import 'package:expense_tracker/models/expense.dart';
import 'package:expense_tracker/resources/dimensions.dart';
import 'package:expense_tracker/resources/strings.dart';
import 'package:flutter/material.dart';

class AddExpense extends StatefulWidget {
  const AddExpense({super.key, required this.onAddExpense});

  final void Function(Expense expense) onAddExpense;

  @override
  State<AddExpense> createState() {
    return _AddExpenseState();
  }
}

class _AddExpenseState extends State<AddExpense> {
  final _titleTEController = TextEditingController();
  final _amountTEController = TextEditingController();

  DateTime? _selectedDate;
  var _selectedCategory = Category.leisure;

  void _showDatePicker() async {
    final now = DateTime.now();
    final firstDate = DateTime(now.year - 1, now.month, now.day);

    var date = await showDatePicker(
      context: context,
      initialDate: now,
      firstDate: firstDate,
      lastDate: now,
    );

    setState(() {
      _selectedDate = date;
    });
  }

  void _saveExpense() {
    var errorMsg = isValidData();
    if (errorMsg.isEmpty) {
      widget.onAddExpense(
        Expense(
          category: _selectedCategory,
          date: _selectedDate!,
          amount: _amountTEController.getDoubleValue(),
          title: _titleTEController.text.trim(),
        ),
      );
      Navigator.pop(context);
    } else {
      showDialog(
        context: context,
        builder: (ctx) => AlertDialog(
          title: const Text(Strings.lblMessage),
          content: Text(errorMsg),
          actions: [
            TextButton(
              onPressed: () {
                Navigator.pop(ctx);
              },
              child: const Text(Strings.lblOK),
            ),
          ],
        ),
      );
    }
  }

  String isValidData() {
    var errorMsg = Strings.emptyString;
    final amount = _amountTEController.getDoubleValue();

    if (_titleTEController.isEmptyText()) {
      errorMsg = '${Strings.lblInvalid} title.';
    } else if (_amountTEController.isEmptyText() || amount <= 0) {
      errorMsg = '${Strings.lblInvalid} amount.';
    } else if (_selectedDate == null) {
      errorMsg = '${Strings.lblInvalid} date.';
    }
    return errorMsg;
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsetsGeometry.fromLTRB(16, 48, 16, 16),
      child: Column(
        children: [
          titleWidget(),
          Row(
            children: [
              amountWidget(),
              Dimensions.normalDividerHorizontal,
              selectDateWidget(),
            ],
          ),
          Dimensions.normalDivider,
          Row(
            children: [
              selectCategoryWidget(),
              const Spacer(),
              ElevatedButton(
                onPressed: _saveExpense,
                child: const Text(Strings.lblSaveExpense),
              ),
              Dimensions.normalDividerHorizontal,
              ElevatedButton(
                onPressed: () {
                  Navigator.pop(context);
                },
                child: const Text(Strings.lblCancel),
              ),
            ],
          ),
        ],
      ),
    );
  }

  DropdownButton<Category> selectCategoryWidget() {
    return DropdownButton(
      value: _selectedCategory,
      items: Category.values
          .map(
            (category) => DropdownMenuItem(
              value: category,
              child: Text(category.name.toUpperCase()),
            ),
          )
          .toList(),
      onChanged: (value) {
        setState(() {
          _selectedCategory = value as Category;
        });
      },
    );
  }

  TextField titleWidget() {
    return TextField(
      maxLength: 50,
      decoration: const InputDecoration(label: Text('Title')),
      controller: _titleTEController,
    );
  }

  Expanded amountWidget() {
    return Expanded(
      child: TextField(
        decoration: const InputDecoration(
          prefixText: '\$ ',
          label: Text('Amount'),
        ),
        controller: _amountTEController,
        keyboardType: TextInputType.number,
      ),
    );
  }

  Expanded selectDateWidget() {
    return Expanded(
      child: Row(
        mainAxisAlignment: MainAxisAlignment.end,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          IconButton(
            onPressed: _showDatePicker,
            icon: const Icon(Icons.calendar_month),
          ),
          Text(
            _selectedDate == null
                ? 'Select Date'
                : Constants.dateFormatter.format(_selectedDate!),
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
