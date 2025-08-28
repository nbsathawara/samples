import 'package:expense_tracker/resources/dimensions.dart';
import 'package:expense_tracker/screens/expenses.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  SystemChrome.setPreferredOrientations([DeviceOrientation.portraitUp]).then((
    fn,
  ) {
    runApp(
      MaterialApp(
        theme: appTheme(),
        darkTheme: appDarkTheme(),
        debugShowCheckedModeBanner: false,
        home: Expenses(),
      ),
    );
  });
}

ThemeData appTheme() {
  var kColorScheme = ColorScheme.fromSeed(
    brightness: Brightness.light,
    seedColor: const Color.fromARGB(255, 96, 59, 181),
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

ThemeData appDarkTheme() {
  var kColorScheme = ColorScheme.fromSeed(
    brightness: Brightness.dark,
    seedColor: const Color.fromARGB(255, 5, 99, 125),
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
