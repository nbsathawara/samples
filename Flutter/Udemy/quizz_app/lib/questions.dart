import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:quizz_app/answer_widget.dart';
import 'package:quizz_app/resources/Dimensions.dart';
import 'package:quizz_app/resources/Styles.dart';
import 'package:quizz_app/resources/questions.dart';

class Questions extends StatefulWidget {
  const Questions({super.key, required this.onSelecteAnswer});

  final Function(String answer) onSelecteAnswer;

  @override
  State<Questions> createState() => _QuestionsState();
}

class _QuestionsState extends State<Questions> {
  var curQuestionIndex = 0;

  void answerQuestion(String answer) {
    widget.onSelecteAnswer(answer);
    setState(() {
      curQuestionIndex++;
    });
  }

  @override
  Widget build(BuildContext context) {
    final curQuestion = questions[curQuestionIndex];
    return SizedBox(
      width: double.infinity,
      child: Container(
        margin: Dimensions.largeMarginAll,
        child: Column(
          mainAxisSize: MainAxisSize.max,
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Text(
              curQuestion.question,
              textAlign: TextAlign.center,
              style: GoogleFonts.lato(
                color: Color.fromARGB(255, 201, 153, 251),
                fontSize: Dimensions.normalFontSize,
                fontWeight: FontWeight.bold,
              ),
            ),
            Dimensions.largeDivider,
            ...curQuestion.getshuffeledAnswers().map((answer) {
              return AnswerWidget(
                answer: answer,
                onTap: () {
                  answerQuestion(answer);
                },
              );
            }),
          ],
        ),
      ),
    );
  }
}
