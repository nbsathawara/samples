import 'package:flutter/material.dart';
import 'package:meals_app/models/category.dart';
import 'package:meals_app/models/meal.dart';
import 'package:meals_app/resources/dimensions.dart';

class MealsScreen extends StatelessWidget {
  final Category category;
  final List<Meal> meals;

  const MealsScreen({super.key, required this.category, required this.meals});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(category.title)),
      body: meals.isEmpty ? EmptyWidget() : MealsListWidget(meals: meals),
    );
  }
}

class MealsListWidget extends StatelessWidget {
  const MealsListWidget({super.key, required this.meals});

  final List<Meal> meals;

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: meals.length,
      itemBuilder: (context, index) => Text(meals[index].title),
    );
  }
}

class EmptyWidget extends StatelessWidget {
  const EmptyWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
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
