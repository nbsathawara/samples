import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:meals_app/resources/themes.dart';
import 'package:meals_app/screens/tabs.dart';

void main() {
  runApp(const ProviderScope(child: MyApp()));
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: Themes.appTheme(),
      darkTheme: Themes.appDarkTheme(),
      debugShowCheckedModeBanner: false,
      home: const Tabs(),
    );
  }
}
