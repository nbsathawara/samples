import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:quizz_app/resources/Dimensions.dart';

class AnswerWidget extends StatelessWidget {
  const AnswerWidget({
    super.key,
    required this.userAnswer,
    required this.correctAnswer,
    required this.data,
  });

  final Object? userAnswer;
  final Object? correctAnswer;
  final Map<String, Object> data;

  @override
  Widget build(BuildContext context) {
    var isCorrectAnswer = userAnswer == correctAnswer;
    return Expanded(
      child: Padding(
        padding: Dimensions.normalLRMargin,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              data['question'] as String,
              style: GoogleFonts.lato(
                fontSize: 15,
                color: Colors.white,
                fontWeight: FontWeight.bold,
              ),
            ),
            Dimensions.smallDivider,
            Text(
              'Answer : $userAnswer',
              style: GoogleFonts.lato(
                fontSize: 14,
                color: Colors.amber,
                fontWeight: FontWeight.bold,
              ),
            ),
            if (!isCorrectAnswer)
              Text(
                'Correct Answer : $correctAnswer',
                style: GoogleFonts.lato(
                  fontSize: 14,
                  color: Colors.blue,
                  fontWeight: FontWeight.bold,
                ),
              ),
          ],
        ),
      ),
    );
  }
}
