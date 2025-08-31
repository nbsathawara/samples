import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:meals_app/models/meal.dart';
import 'package:meals_app/providers/favorites_provider.dart';
import 'package:meals_app/resources/dimensions.dart';

class MealDetails extends ConsumerWidget {
  final Meal meal;
  const MealDetails({super.key, required this.meal});

  void _showInfoMessage(String message, BuildContext context) {
    ScaffoldMessenger.of(context).clearSnackBars();
    ScaffoldMessenger.of(
      context,
    ).showSnackBar(SnackBar(content: Text(message)));
  }

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final favoriteMeals = ref.watch(favoriteMealsProvider);

    return Scaffold(
      appBar: AppBar(
        title: Text(meal.title),
        actions: [
          IconButton(
            icon: AnimatedSwitcher(
              duration: Duration(milliseconds: 300),
              child: Icon(
                key: ValueKey(favoriteMeals.contains(meal)),
                favoriteMeals.contains(meal) ? Icons.star : Icons.star_border,
              ),
              transitionBuilder: (child, animation) {
                return RotationTransition(
                  turns: Tween(begin: 0.75, end: 1.0).animate(animation),
                  child: child,
                );
              },
            ),
            onPressed: () {
              var isFavorite = ref
                  .read(favoriteMealsProvider.notifier)
                  .toggleFavoriteMeal(meal);
              if (isFavorite) {
                _showInfoMessage('Meal added to favorites.', context);
              } else {
                _showInfoMessage('Meal removed from favorites.', context);
              }
            },
          ),
        ],
      ),
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
    return Hero(
      tag: meal.id,
      child: Card(
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
      ),
    );
  }
}
