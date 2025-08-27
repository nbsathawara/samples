import 'package:flutter/material.dart';

extension TextEditingControllerType on TextEditingController {
  bool isEmptyText() {
    return text.trim().isEmpty;
  }

  double getDoubleValue() {
    return isEmptyText() ? 0 : double.tryParse(text.trim())!;
  }
}
