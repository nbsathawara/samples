import 'package:flutter/material.dart';
import 'package:quizz_app/screens/quiz.dart';

void main() {
  runApp(Quiz());
}

extension MapType on Map {
  bool isCorrectAnswer() {
    var data = this;
    return data['user_answer'] == data['correct_answer'];
  }
}
