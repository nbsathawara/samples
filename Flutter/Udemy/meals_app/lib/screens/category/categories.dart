import 'package:flutter/material.dart';
import 'package:meals_app/data/dummy_data.dart';
import 'package:meals_app/models/category.dart';
import 'package:meals_app/resources/dimensions.dart';
import 'package:meals_app/resources/strings.dart';
import 'package:meals_app/screens/category/category_grid_item.dart';
import 'package:meals_app/screens/meals/meals.dart';

class CategoriesScreen extends StatelessWidget {
  const CategoriesScreen({super.key});

  void _selectCategory(context, Category category) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (ctx) => MealsScreen(
          category: category,
          meals: dummyMeals
              .where((meal) => meal.categories.contains(category.id))
              .toList(),
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text(Strings.appName)),
      body: GridView(
        padding: Dimensions.largeMarginAll,
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 2,
          mainAxisSpacing: Dimensions.largeMargin,
          crossAxisSpacing: Dimensions.largeMargin,
          //childAspectRatio: 3 / 2,
        ),
        children: [
          for (final category in availableCategories)
            CategoryGridItem(
              category: category,
              onSelectCategory: () => _selectCategory(context, category),
            ),
        ],
      ),
    );
  }
}
