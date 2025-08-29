import 'package:flutter/material.dart';
import 'package:meals_app/data/extensions.dart';
import 'package:meals_app/models/meal.dart';
import 'package:meals_app/resources/dimensions.dart';
import 'package:meals_app/screens/meals/list/meal_sub_item_.dart';
import 'package:transparent_image/transparent_image.dart';

class MealItem extends StatelessWidget {
  final Meal meal;
  final void Function(Meal meal) onSelectMeal;

  const MealItem({super.key, required this.meal, required this.onSelectMeal});

  @override
  Widget build(BuildContext context) {
    return Card(
      margin: Dimensions.smallMarginAll,
      elevation: Dimensions.defaultElevation,
      clipBehavior: Clip.hardEdge,
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(Dimensions.smallRadius),
      ),
      child: InkWell(
        splashColor: Theme.of(context).colorScheme.primary,
        onTap: () {
          onSelectMeal(meal);
        },
        child: Stack(
          children: [
            MealImage(meal: meal),
            Positioned(
              left: 0,
              bottom: 0,
              right: 0,
              child: Container(
                color: Colors.black54,
                padding: EdgeInsets.symmetric(horizontal: 44, vertical: 6),
                child: Column(
                  children: [
                    MealTitle(meal: meal),
                    Dimensions.smallDivider,
                    MealsubItems(meal: meal),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class MealsubItems extends StatelessWidget {
  const MealsubItems({super.key, required this.meal});

  final Meal meal;

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        MealSubItem(icon: Icons.schedule, label: '${meal.duration} min'),
        Dimensions.normalDividerHorizontal,
        MealSubItem(icon: Icons.work, label: meal.complexity.name.capitalize()),
        Dimensions.normalDividerHorizontal,
        MealSubItem(
          icon: Icons.attach_money,
          label: meal.affordability.name.capitalize(),
        ),
      ],
    );
  }
}

class MealImage extends StatelessWidget {
  const MealImage({super.key, required this.meal});

  final Meal meal;

  @override
  Widget build(BuildContext context) {
    return FadeInImage(
      placeholder: MemoryImage(kTransparentImage),
      image: NetworkImage(meal.imageUrl),
      fit: BoxFit.cover,
      height: 200,
      width: double.infinity,
    );
  }
}

class MealTitle extends StatelessWidget {
  const MealTitle({super.key, required this.meal});

  final Meal meal;

  @override
  Widget build(BuildContext context) {
    return Text(
      meal.title,
      maxLines: 2,
      textAlign: TextAlign.center,
      softWrap: true,
      overflow: TextOverflow.ellipsis,
      style: TextStyle(
        fontSize: 20,
        fontWeight: FontWeight.bold,
        color: Colors.white,
      ),
    );
  }
}
