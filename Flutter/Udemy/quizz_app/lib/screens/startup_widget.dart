import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:quizz_app/resources/Dimensions.dart';
import 'package:quizz_app/resources/Images.dart';
import 'package:quizz_app/resources/Strings.dart';
import 'package:quizz_app/resources/Styles.dart';

class StartUpwidget extends StatelessWidget {
  StartUpwidget(this.startQuiz, {super.key});

  final void Function() startQuiz;

  @override
  Widget build(context) {
    return Center(
      child: Column(
        mainAxisSize: MainAxisSize.min,
        children: [
          Image.asset(
            Images.quizLogo,
            width: 300,
            color: Color.fromARGB(150, 255, 255, 255),
          ),
          Dimensions.veryLargeDivider,
          Text(Strings.lblStatrtUp, style: Styles.appTextStyle),
          Dimensions.veryLargeDivider,
          OutlinedButton.icon(
            onPressed: startQuiz,
            style: OutlinedButton.styleFrom(foregroundColor: Colors.white),
            icon: Icon(Icons.arrow_right_alt),
            label: Text(Strings.lblStartQuiz, style: Styles.normalTextStyle),
          ),
        ],
      ),
    );
  }
}
