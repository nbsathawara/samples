import 'package:flutter/material.dart';
import 'package:meals_app/data/constants.dart';
import 'package:meals_app/data/dummy_data.dart';
import 'package:meals_app/models/meal.dart';
import 'package:meals_app/resources/strings.dart';
import 'package:meals_app/screens/category/categories.dart';
import 'package:meals_app/screens/filters.dart';
import 'package:meals_app/screens/main_drawaer.dart';
import 'package:meals_app/screens/meals/list/meals.dart';

const kInitialFilters = {
  Filter.glutenFree: false,
  Filter.lactoseFree: false,
  Filter.vegeterian: false,
  Filter.vegan: false,
};

class Tabs extends StatefulWidget {
  const Tabs({super.key});

  @override
  State<StatefulWidget> createState() => _TabsState();
}

class _TabsState extends State<Tabs> {
  int _selectedTabIndex = 0;
  final List<Meal> _favoriteMeals = [];
  Map<Filter, bool> _filtersMap = kInitialFilters;

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

  void _setScreens(String identifier) async {
    Navigator.pop(context); //close drawer
    if (identifier == 'filters') {
      final filterResult = await Navigator.push<Map<Filter, bool>>(
        context,
        MaterialPageRoute(
          builder: (context) => FilterScreen(currentFilter: _filtersMap),
        ),
      );
      setState(() {
        _filtersMap = filterResult ?? kInitialFilters;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    final filteredMeals = dummyMeals.where((meal) {
      if (_filtersMap[Filter.glutenFree]! && !meal.isGlutenFree) return false;
      if (_filtersMap[Filter.lactoseFree]! && !meal.isLactoseFree) return false;
      if (_filtersMap[Filter.vegeterian]! && !meal.isVegetarian) return false;
      if (_filtersMap[Filter.vegan]! && !meal.isVegan) return false;
      return true;
    }).toList();

    String currentTabTitle = "Categories";
    Widget currentTabPage = CategoriesScreen(
      availableMeals: filteredMeals,
      onToggleFavoriteMeal: _toggleFavoriteMeal,
    );

    if (_selectedTabIndex == 1) {
      currentTabTitle = "Your Favorites";
      currentTabPage = MealsScreen(
        title: Strings.emptyString,
        meals: _favoriteMeals,
        onToggleFavoriteMeal: _toggleFavoriteMeal,
      );
    }

    return Scaffold(
      appBar: AppBar(title: Text(currentTabTitle)),
      body: currentTabPage,
      drawer: MainDrawer(
        onSelectScreen: (identifier) => _setScreens(identifier),
      ),
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
