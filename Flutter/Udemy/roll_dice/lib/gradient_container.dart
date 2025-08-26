import 'package:flutter/material.dart';
import 'package:roll_dice/dice_roller.dart'; 

class GradientContainer extends StatelessWidget {
  GradientContainer({required this.colors, super.key});

  GradientContainer.purple({super.key})
    : colors = [
        Color.fromARGB(255, 26, 2, 80),
        Color.fromARGB(255, 38, 23, 59),
      ];

  final List<Color> colors;
  var diceImg = 'assets/images/dice-2.png';

  @override
  Widget build(context) {
    return Container(
      decoration: BoxDecoration(
        gradient: LinearGradient(
          colors: colors,
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        ),
      ),
      child: Center(
        child: DiceRoller(),
      ),
    );
  }
}
