import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:quizz_app/resources/Dimensions.dart';

class QuestionIndexWidget extends StatelessWidget {
  const QuestionIndexWidget({
    super.key,
    required this.isCorrectAnswer,
    required this.data,
  });

  final bool isCorrectAnswer;
  final Map<String, Object> data;

  @override
  Widget build(BuildContext context) {
    return CircleAvatar(
      backgroundColor: isCorrectAnswer ? Colors.green[900] : Colors.red[900],
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
