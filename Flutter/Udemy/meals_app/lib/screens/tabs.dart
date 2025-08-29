import 'package:flutter/material.dart';
import 'package:meals_app/models/meal.dart';
import 'package:meals_app/resources/dimensions.dart';
import 'package:meals_app/resources/strings.dart';
import 'package:meals_app/screens/category/categories.dart';
import 'package:meals_app/screens/meals/list/meals.dart';

class Tabs extends StatefulWidget {
  const Tabs({super.key});

  @override
  State<StatefulWidget> createState() => _TabsState();
}

class _TabsState extends State<Tabs> {
  int _selectedTabIndex = 0;
  final List<Meal> _favoriteMeals = [];

  void _toggleFavoriteMeal(Meal meal) {
    if (_favoriteMeals.contains(meal)) {
      setState(() {
        _favoriteMeals.remove(meal);
      });
      _showInfoMessage('Meal removed from favorites.');
    } else {
      setState(() {
        _favoriteMeals.add(meal);
      });
      _showInfoMessage('Meal added to favorites.');
    }
  }

  void _showInfoMessage(String message) {
    ScaffoldMessenger.of(context).clearSnackBars();
    ScaffoldMessenger.of(
      context,
    ).showSnackBar(SnackBar(content: Text(message)));
  }

  void _changeTab(int index) {
    setState(() {
      _selectedTabIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    Widget currentTabPage = CategoriesScreen(
      onToggleFavoriteMeal: _toggleFavoriteMeal,
    );

    if (_selectedTabIndex == 1) {
      currentTabPage = MealsScreen(
        title: "Your Favorites",
        meals: _favoriteMeals,
        onToggleFavoriteMeal: _toggleFavoriteMeal,
      );
    }

    return Scaffold(
      body: currentTabPage,
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: _selectedTabIndex,
        onTap: _changeTab,
        items: [
          BottomNavigationBarItem(
            icon: Icon(Icons.set_meal),
            label: "Categories",
          ),
          BottomNavigationBarItem(icon: Icon(Icons.star), label: "Favorites"),
        ],
      ),
    );
  }
}
