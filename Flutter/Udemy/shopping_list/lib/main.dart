import 'package:flutter/material.dart';
import 'package:shopping_list/resources/themes.dart';
import 'package:shopping_list/screens/grocery_list.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: Themes.appTheme(),
      darkTheme: Themes.appDarkTheme(),
      debugShowCheckedModeBanner: false,
      home: const GroceryList(),
    );
  }
}
