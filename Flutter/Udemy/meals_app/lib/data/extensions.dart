import 'package:flutter/material.dart';

extension StringExtension on String {
  String capitalize() {
    if (isEmpty) {
      return this; // Return the original string if it's empty
    }
    return "${this[0].toUpperCase()}${substring(1)}";
  }
}

extension TextEditingControllerType on TextEditingController {
  bool isEmptyText() {
    return text.trim().isEmpty;
  }

  double getDoubleValue() {
    return isEmptyText() ? 0 : double.tryParse(text.trim())!;
  }
}
