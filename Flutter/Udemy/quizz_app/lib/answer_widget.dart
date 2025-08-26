import 'package:flutter/material.dart';
import 'package:quizz_app/resources/Dimensions.dart';
import 'package:quizz_app/resources/Styles.dart';

class AnswerWidget extends StatelessWidget {
  final String answer;
  final Function() onTap;

  const AnswerWidget({required this.answer, required this.onTap, super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: Dimensions.normalLRTMargin,
      child: ElevatedButton(
        onPressed: onTap,
        style: ElevatedButton.styleFrom(
          padding: Dimensions.normalMarginAll,
          backgroundColor: Color.fromARGB(255, 33, 1, 95),
          foregroundColor: Colors.white,
        ),
        child: Text(answer, textAlign: TextAlign.center, style: Styles.normalTextStyle),
      ),
    );
  }
}
