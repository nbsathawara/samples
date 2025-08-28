import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:meals_app/resources/dimensions.dart';

class Themes {
  static ThemeData appTheme() {
    var kColorScheme = ColorScheme.fromSeed(
      brightness: Brightness.light,
      seedColor: const Color.fromARGB(255, 239, 138, 60),
    );

    return ThemeData().copyWith(
      colorScheme: kColorScheme,
      appBarTheme: const AppBarTheme().copyWith(
        backgroundColor: kColorScheme.onPrimaryContainer,
        foregroundColor: kColorScheme.primaryContainer,
        titleTextStyle: TextStyle(
          fontWeight: FontWeight.bold,
          fontSize: Dimensions.normalFontSize,
        ),
      ),
      textTheme: GoogleFonts.latoTextTheme(),
      cardTheme: const CardThemeData().copyWith(
        color: kColorScheme.secondaryContainer,
        margin: Dimensions.cardMargin,
      ),
      elevatedButtonTheme: ElevatedButtonThemeData(
        style: ElevatedButton.styleFrom(
          backgroundColor: kColorScheme.primaryContainer,
        ),
      ),
    );
  }

  static ThemeData appDarkTheme() {
    var kColorScheme = ColorScheme.fromSeed(
      brightness: Brightness.dark,
      seedColor: const Color.fromARGB(255, 131, 57, 0),
    );

    return ThemeData.dark().copyWith(
      colorScheme: kColorScheme,
      appBarTheme: const AppBarTheme().copyWith(
        backgroundColor: kColorScheme.primaryContainer,
        foregroundColor: kColorScheme.onPrimaryContainer,
        titleTextStyle: TextStyle(
          fontWeight: FontWeight.bold,
          fontSize: Dimensions.normalFontSize,
        ),
      ),
      textTheme: GoogleFonts.latoTextTheme(),
      cardTheme: const CardThemeData().copyWith(
        color: kColorScheme.secondaryContainer,
        margin: Dimensions.cardMargin,
      ),
      elevatedButtonTheme: ElevatedButtonThemeData(
        style: ElevatedButton.styleFrom(
          backgroundColor: kColorScheme.primaryContainer,
          foregroundColor: kColorScheme.onPrimaryContainer,
        ),
      ),
    );
  }
}
