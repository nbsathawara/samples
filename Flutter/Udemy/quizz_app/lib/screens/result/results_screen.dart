import 'package:flutter/material.dart';
import 'package:quizz_app/main.dart';
import 'package:quizz_app/resources/Dimensions.dart';
import 'package:quizz_app/resources/Strings.dart';
import 'package:quizz_app/resources/Styles.dart';
import 'package:quizz_app/resources/questions.dart';
import 'package:quizz_app/screens/result/result_summary.dart';

class ResultsScreen extends StatelessWidget {
  const ResultsScreen({
    super.key,
    required this.selectedanswers,
    required this.restartQuiz,
  });

  final Function() restartQuiz;

  final List<String> selectedanswers;

  List<Map<String, Object>> getSummaryData() {
    final List<Map<String, Object>> summary = [];

    for (int i = 0; i < selectedanswers.length; ++i) {
      summary.add({
        'question_index': i,
        'question': questions[i].question,
        'correct_answer': questions[i].answers[0],
        'user_answer': selectedanswers[i],
      });
    }

    return summary;
  }

  @override
  Widget build(context) {
    var summary = getSummaryData();

    var totalQuestions = questions.length;
    var correctAnswers = summary.where((data) {
      return data.isCorrectAnswer();
    }).length;

    return Center(
      child: SafeArea(
        child: Column(
          mainAxisSize: MainAxisSize.max,
          children: [
            Text(
              'You have answeres $correctAnswers out of $totalQuestions questions correctly!!',
              style: Styles.appTextStyle,
              textAlign: TextAlign.center,
            ),
            Dimensions.largeDivider,
            Expanded(child: ResultSummary(summary: summary)),
            Dimensions.largeDivider,
            OutlinedButton.icon(
              onPressed: restartQuiz,
              style: OutlinedButton.styleFrom(foregroundColor: Colors.white),
              icon: Icon(Icons.refresh),
              label: Text(Strings.lblReStartQuiz, style: Styles.normalTextStyle),
            ),
          ],
        ),
      ),
    );
  }
}
