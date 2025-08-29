import 'package:flutter/material.dart';
import 'package:meals_app/resources/dimensions.dart';

class MealSubItem extends StatelessWidget {
  final IconData icon;
  final String label;

  const MealSubItem({super.key, required this.icon, required this.label});

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        Icon(icon, size: 20, color: Colors.white),
        Dimensions.smallDividerHorizontal,
        Text(label, style: const TextStyle(color: Colors.white)),
      ],
    );
  }
}
