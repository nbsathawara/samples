import 'dart:math';

import 'package:flutter/material.dart';
import 'package:roll_dice/styled_text.dart';

class DiceRoller extends StatefulWidget {
  const DiceRoller({super.key});

  @override
  State<DiceRoller> createState() => _DiceRollerState();
}

final random = Random();

class _DiceRollerState extends State<DiceRoller> {
  var diceRoll = random.nextInt(6) + 1;

  void rollDice() {
    setState(() {
      diceRoll = random.nextInt(6) + 1;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisSize: MainAxisSize.min,
      children: [
        Image.asset('assets/images/dice-$diceRoll.png', width: 200),
        SizedBox(height: 20),
        TextButton(onPressed: rollDice, child: StyledText('Roll Dice')),
      ],
    );
  }
}
