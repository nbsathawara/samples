import 'package:flutter/material.dart';
import 'package:meals_app/resources/themes.dart';
import 'package:meals_app/screens/category/categories.dart';

void main() {
  runApp(const App());
}

class App extends StatelessWidget {
  const App({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: Themes.appTheme(),
      darkTheme: Themes.appDarkTheme(),
      debugShowCheckedModeBanner: false,
      home: const CategoriesScreen(),
    );
  }
}
