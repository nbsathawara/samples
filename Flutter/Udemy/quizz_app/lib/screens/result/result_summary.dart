import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:quizz_app/main.dart';
import 'package:quizz_app/resources/Dimensions.dart';
import 'package:quizz_app/screens/result/answer_widget.dart';
import 'package:quizz_app/screens/result/question_index_widget.dart';

class ResultSummary extends StatelessWidget {
  const ResultSummary({super.key, required this.summary});

  final List<Map<String, Object>> summary;
  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        children: summary.map((data) {
          var isCorrectAnswer = data.isCorrectAnswer();
          var userAnswer = data['user_answer'];
          var correctAnswer = data['correct_answer'];

          return Padding(
            padding: Dimensions.smallMarginAll,
            child: Row(
              mainAxisSize: MainAxisSize.min,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                QuestionIndexWidget(
                  isCorrectAnswer: isCorrectAnswer,
                  data: data,
                ),
                AnswerWidget(
                  userAnswer: userAnswer,
                  correctAnswer: correctAnswer,
                  data: data,
                ),
              ],
            ),
          );
        }).toList(),
      ),
    );
  }
}
