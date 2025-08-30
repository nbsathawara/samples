import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:meals_app/models/meal.dart';

final favoriteMealsProvider =
    StateNotifierProvider<FavoriteMealsnotifier, List<Meal>>((ref) {
      return FavoriteMealsnotifier();
    });

class FavoriteMealsnotifier extends StateNotifier<List<Meal>> {
  FavoriteMealsnotifier() : super([]);

  bool toggleFavoriteMeal(Meal meal) {
    var isFavoriteMeal = state.contains(meal);
    if (isFavoriteMeal) {
      state = state.where((x) => x.id != meal.id).toList();
      return false;
    } else {
      state = [...state, meal];
      return true;
    }
  }
}
