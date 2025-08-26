import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:quizz_app/main.dart';
import 'package:quizz_app/resources/Dimensions.dart';

class ResultSummary extends StatelessWidget {
  const ResultSummary({super.key, required this.summary});

  final List<Map<String, Object>> summary;
  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
        child: Column(
          children: summary.map((data) {

            var isCorrectAnswer = data.isCorrectAnswer();
            var userAnswer=data['user_answer'];
            var correctAnswer=data['correct_answer'];

            return Padding(
              padding: Dimensions.smallTBMargin,
              child: Row(
                mainAxisSize: MainAxisSize.min,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  QuestionIndexWidget(isCorrectAnswer: isCorrectAnswer),
                  AnswerWidget(userAnswer: userAnswer, correctAnswer: correctAnswer),
                ],
              ),
            );
          }).toList(),
        ),
    );
  }
}

class AnswerWidget extends StatelessWidget {
  const AnswerWidget({
    super.key,
    required this.userAnswer,
    required this.correctAnswer,
  });

  final Object? userAnswer;
  final Object? correctAnswer;

  @override
  Widget build(BuildContext context) {
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
             'User Answer : $userAnswer',
              style: GoogleFonts.lato(
                fontSize: 14,
                color: Colors.amber,
                fontWeight: FontWeight.bold,
              ),
            ),
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

class QuestionIndexWidget extends StatelessWidget {
  const QuestionIndexWidget({
    super.key,
    required this.isCorrectAnswer,
  });

  final bool isCorrectAnswer;

  @override
  Widget build(BuildContext context) {
    return CircleAvatar(
      backgroundColor: isCorrectAnswer
          ? Colors.green[900]
          : Colors.red[900],
      child: Text(
        ((data['question_index'] as int) + 1).toString(),
        style: GoogleFonts.lato(
          fontSize: Dimensions.normalFontSize,
          color: Colors.white,
          fontWeight: FontWeight.bold,
        ),
      ),
    );
  }
}
