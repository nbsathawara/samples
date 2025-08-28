
import 'package:meals_app/data/constants.dart';

class Meal {
  const Meal({
    required this.id,
    required this.categories,
    required this.title,
    required this.imageUrl,
    required this.ingredients,
    required this.steps,
    required this.duration,
    required this.complexity,
    required this.affordability,
    required this.isGlutenFree,
    required this.isLactoseFree,
    required this.isVegan,
    required this.isVegetarian,
  });

  final String id, title, imageUrl;
  final List<String> categories, ingredients, steps;
  final int duration;
  final Complexity complexity;
  final Affordability affordability;
  final bool isGlutenFree, isLactoseFree, isVegan, isVegetarian;
}
