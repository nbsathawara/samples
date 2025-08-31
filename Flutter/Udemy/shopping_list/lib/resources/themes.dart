import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:shopping_list/resources/dimensions.dart';

class Themes {
  static ThemeData appTheme() {
    var kColorScheme = ColorScheme.fromSeed(
      brightness: Brightness.light,
      surface: const Color.fromARGB(255, 147, 229, 250),
      seedColor: const Color.fromARGB(255, 42, 51, 59),
    );

    return ThemeData().copyWith(
      colorScheme: kColorScheme,
      scaffoldBackgroundColor: const Color.fromARGB(255, 155, 236, 251),
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
      seedColor: const Color.fromARGB(255, 147, 229, 250),
      surface: const Color.fromARGB(255, 42, 51, 59),
    );

    return ThemeData.dark().copyWith(
      colorScheme: kColorScheme,
      scaffoldBackgroundColor: const Color.fromARGB(255, 50, 58, 60),
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
