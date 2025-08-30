import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:meals_app/data/constants.dart';
import 'package:meals_app/providers/favorites_provider.dart';
import 'package:meals_app/providers/filters_provider.dart';
import 'package:meals_app/resources/strings.dart';
import 'package:meals_app/screens/category/categories.dart';
import 'package:meals_app/screens/filters.dart';
import 'package:meals_app/screens/main_drawaer.dart';
import 'package:meals_app/screens/meals/list/meals.dart';

class Tabs extends ConsumerStatefulWidget {
  const Tabs({super.key});

  @override
  ConsumerState<Tabs> createState() => _TabsState();
}

class _TabsState extends ConsumerState<Tabs> {
  int _selectedTabIndex = 0;

  void _changeTab(int index) {
    setState(() {
      _selectedTabIndex = index;
    });
  }

  _setScreens(String identifier) {
    Navigator.pop(context); //close drawer
    if (identifier == 'filters') {
      Navigator.push<Map<Filter, bool>>(
        context,
        MaterialPageRoute(builder: (context) => const FilterScreen()),
      );
    }
  }

  @override
  Widget build(BuildContext context) { 
    final filteredMeals =ref.watch(filteredMealsProvider);

    String currentTabTitle = "Categories";
    Widget currentTabPage = CategoriesScreen(availableMeals: filteredMeals);

    if (_selectedTabIndex == 1) {
      final favoriteMeals = ref.watch(favoriteMealsProvider);
      currentTabTitle = "Your Favorites";
      currentTabPage = MealsScreen(
        title: Strings.emptyString,
        meals: favoriteMeals,
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
