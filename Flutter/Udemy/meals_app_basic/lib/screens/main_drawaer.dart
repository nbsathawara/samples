import 'package:flutter/material.dart';
import 'package:meals_app/resources/dimensions.dart';

class MainDrawer extends StatelessWidget {
  const MainDrawer({super.key, required this.onSelectScreen});

  final void Function(String identifier) onSelectScreen;

  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: Column(
        children: [
          headerWidget(context),
          mealTile(context),
          settingsTile(context),
        ],
      ),
    );
  }

  ListTile mealTile(BuildContext context) {
    return ListTile(
      leading: Icon(
        Icons.restaurant,
        size: 26,
        color: Theme.of(context).colorScheme.onSurface,
      ),
      title: Text(
        'Meals',
        style: Theme.of(context).textTheme.titleSmall!.copyWith(
          color: Theme.of(context).colorScheme.onSurface,
          fontSize: Dimensions.normalFontSize,
        ),
      ),
      onTap: () {
        onSelectScreen('meals');
      },
    );
  }

  ListTile settingsTile(BuildContext context) {
    return ListTile(
      leading: Icon(
        Icons.settings,
        size: 26,
        color: Theme.of(context).colorScheme.onSurface,
      ),
      title: Text(
        'Filters',
        style: Theme.of(context).textTheme.titleSmall!.copyWith(
          color: Theme.of(context).colorScheme.onSurface,
          fontSize: Dimensions.normalFontSize,
        ),
      ),
      onTap: () {
        onSelectScreen('filters');
      },
    );
  }

  DrawerHeader headerWidget(BuildContext context) {
    return DrawerHeader(
      padding: Dimensions.normalMarginAll,
      decoration: BoxDecoration(
        gradient: LinearGradient(
          colors: [
            Theme.of(context).colorScheme.primaryContainer,
            Theme.of(
              context,
            ).colorScheme.primaryContainer.withValues(alpha: 0.8),
          ],
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        ),
      ),
      child: Row(
        children: [
          Icon(Icons.fastfood, size: 48, color: Theme.of(context).primaryColor),
          Dimensions.normalDividerHorizontal,
          Text(
            'Cooking Up!!!!',
            style: Theme.of(context).textTheme.titleLarge!.copyWith(
              color: Theme.of(context).primaryColor,
            ),
          ),
        ],
      ),
    );
  }
}
