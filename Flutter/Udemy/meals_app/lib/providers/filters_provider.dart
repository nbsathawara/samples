import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:meals_app/data/constants.dart';
import 'package:meals_app/providers/meal_provider.dart';

const kInitialFilters = {
  Filter.glutenFree: false,
  Filter.lactoseFree: false,
  Filter.vegeterian: false,
  Filter.vegan: false,
};

final filteredMealsProvider = Provider((ref) {
  final meals = ref.watch(mealsProvider);
  final activeFilters = ref.watch(filtersProvider);

  return meals.where((meal) {
    if (activeFilters[Filter.glutenFree]! && !meal.isGlutenFree) return false;
    if (activeFilters[Filter.lactoseFree]! && !meal.isLactoseFree) return false;
    if (activeFilters[Filter.vegeterian]! && !meal.isVegetarian) return false;
    if (activeFilters[Filter.vegan]! && !meal.isVegan) return false;
    return true;
  }).toList();
});

final filtersProvider =
    StateNotifierProvider<FiltersNotifier, Map<Filter, bool>>((ref) {
      return FiltersNotifier();
    });

class FiltersNotifier extends StateNotifier<Map<Filter, bool>> {
  FiltersNotifier() : super(kInitialFilters);

  void setFilter(Filter filter, bool isActive) {
    state = {...state, filter: isActive};
  }

  void setFilters(Map<Filter, bool> filters) {
    state = filters;
  }
}
