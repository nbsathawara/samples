import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:quizz_app/resources/Dimensions.dart';

class Styles {
  static TextStyle normalTextStyle = TextStyle(
    fontSize: Dimensions.normalFontSize,
    color: Colors.white,
    //fontFamily: Strings.boldFont,
  );
  static TextStyle questionTextStyle = TextStyle(
    fontSize: Dimensions.normalFontSize,
    color: Colors.white,
    //fontFamily: Strings.boldFont,
  );
  static TextStyle appTextStyle = GoogleFonts.lato(
    fontSize: Dimensions.normalFontSize,
    color: Color.fromARGB(255, 201, 153, 251),
    fontWeight: FontWeight.bold,
  );
}
