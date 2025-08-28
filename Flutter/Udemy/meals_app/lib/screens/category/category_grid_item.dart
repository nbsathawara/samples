import 'package:flutter/material.dart';
import 'package:meals_app/models/category.dart';
import 'package:meals_app/resources/dimensions.dart';

class CategoryGridItem extends StatelessWidget {
  final Category category;
  final Function() onSelectCategory;

  const CategoryGridItem({
    super.key,
    required this.category,
    required this.onSelectCategory,
  });

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: onSelectCategory,
      splashColor: Theme.of(context).primaryColor,
      borderRadius: BorderRadius.circular(Dimensions.defualtRadius),
      child: CategoryItem(category: category),
    );
  }
}

class CategoryItem extends StatelessWidget {
  const CategoryItem({super.key, required this.category});

  final Category category;

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: Dimensions.mediumMarginAll,
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(Dimensions.defualtRadius),
        gradient: LinearGradient(
          colors: [
            category.color.withValues(alpha: 0.55),
            category.color.withValues(alpha: 0.9),
          ],
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        ),
      ),
      child: Text(
        category.title,
        style: Theme.of(context).textTheme.titleLarge!.copyWith(
          color: Theme.of(context).colorScheme.onSurface,
        ),
      ),
    );
  }
}
