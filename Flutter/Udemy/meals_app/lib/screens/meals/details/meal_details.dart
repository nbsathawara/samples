import 'package:flutter/material.dart';
import 'package:meals_app/models/meal.dart';
import 'package:meals_app/resources/dimensions.dart';

class MealDetails extends StatelessWidget {
  final Meal meal;
  const MealDetails({super.key, required this.meal});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(meal.title)),
      body: SingleChildScrollView(
        child: Column(
          spacing: 0,
          children: [
            MealImage(meal: meal),
            IngredientsWidget(meal: meal),
            StepsWidget(meal: meal),
          ],
        ),
      ),
    );
  }
}

class StepsWidget extends StatelessWidget {
  const StepsWidget({super.key, required this.meal});

  final Meal meal;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: double.infinity,
      child: Card(
        elevation: Dimensions.defaultElevation,
        clipBehavior: Clip.hardEdge,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(Dimensions.smallRadius),
        ),
        child: Column(
          children: [
            Dimensions.smallDivider,
            Text(
              'Steps',
              style: Theme.of(context).textTheme.titleLarge!.copyWith(
                color: Theme.of(context).colorScheme.primary,
                fontWeight: FontWeight.bold,
              ),
            ),
            Dimensions.normalDivider,
            for (final step in meal.steps)
              Padding(
                padding: Dimensions.smallMarginAll,
                child: Text(
                  step,
                  textAlign: TextAlign.center,
                  style: Theme.of(context).textTheme.bodyMedium!.copyWith(
                    color: Theme.of(context).colorScheme.onSurface,
                  ),
                ),
              ),
            Dimensions.smallDivider,
          ],
        ),
      ),
    );
  }
}

class IngredientsWidget extends StatelessWidget {
  const IngredientsWidget({super.key, required this.meal});

  final Meal meal;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: double.infinity,
      child: Card(
        elevation: Dimensions.defaultElevation,
        clipBehavior: Clip.hardEdge,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(Dimensions.smallRadius),
        ),
        child: Column(
          children: [
            Dimensions.smallDivider,
            Text(
              'Ingredients',
              style: Theme.of(context).textTheme.titleLarge!.copyWith(
                color: Theme.of(context).colorScheme.primary,
                fontWeight: FontWeight.bold,
              ),
            ),
            Dimensions.normalDivider,
            for (final ingredient in meal.ingredients)
              Text(
                ingredient,
                style: Theme.of(context).textTheme.bodyMedium!.copyWith(
                  color: Theme.of(context).colorScheme.onSurface,
                ),
              ),
            Dimensions.smallDivider,
          ],
        ),
      ),
    );
  }
}

class MealImage extends StatelessWidget {
  const MealImage({super.key, required this.meal});

  final Meal meal;

  @override
  Widget build(BuildContext context) {
    return Card(
      clipBehavior: Clip.hardEdge,
      elevation: Dimensions.defaultElevation,
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(Dimensions.smallRadius),
      ),
      child: Image.network(
        meal.imageUrl,
        height: 250,
        width: double.infinity,
        fit: BoxFit.cover,
      ),
    );
  }
}
