import 'package:flutter/material.dart';
import 'package:meals_app/models/meal.dart';
import 'package:meals_app/resources/dimensions.dart';
import 'package:meals_app/screens/meals/details/meal_details.dart';
import 'package:meals_app/screens/meals/list/meal_item.dart';

class MealsScreen extends StatelessWidget {
  final String title;
  final List<Meal> meals;
  final void Function(Meal meal) onToggleFavoriteMeal;

  const MealsScreen({
    super.key,
    required this.title,
    required this.meals,
    required this.onToggleFavoriteMeal,
  });

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(title)),

      body: meals.isEmpty
          ? EmptyWidget()
          : MealsListWidget(
              meals: meals,
              onToggleFavoriteMeal: onToggleFavoriteMeal,
            ),
    );
  }
}

class MealsListWidget extends StatelessWidget {
  const MealsListWidget({
    super.key,
    required this.meals,
    required this.onToggleFavoriteMeal,
  });
  final void Function(Meal meal) onToggleFavoriteMeal;
  final List<Meal> meals;

  void selectMeal(BuildContext context, Meal meal) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (ctx) =>
            MealDetails(meal: meal, onToggleFavoriteMeal: onToggleFavoriteMeal),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: meals.length,
      itemBuilder: (context, index) => MealItem(
        meal: meals[index],
        onSelectMeal: (meal) {
          selectMeal(context, meal);
        },
      ),
    );
  }
}

class EmptyWidget extends StatelessWidget {
  const EmptyWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        mainAxisSize: MainAxisSize.min,
        children: [
          Text(
            'Sorry...Nothing here!!',
            style: Theme.of(context).textTheme.headlineLarge!.copyWith(
              color: Theme.of(context).colorScheme.onSurface,
            ),
          ),
          Dimensions.normalDivider,
          Text(
            'Try selecting differnt category...',
            style: Theme.of(context).textTheme.bodyLarge!.copyWith(
              color: Theme.of(context).colorScheme.onSurface,
            ),
          ),
        ],
      ),
    );
  }
}
