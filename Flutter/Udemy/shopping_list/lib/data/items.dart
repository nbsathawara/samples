import 'package:shopping_list/data/categories.dart';
import 'package:shopping_list/data/constants.dart';
import 'package:shopping_list/models/grocery_item.dart';

var groceryItems = [
  GroceryItem(
    id: 'a',
    name: 'Milk',
    qty: 1,
    category: categories[Categories.dairy]!,
  ),
  GroceryItem(
    id: 'b',
    name: 'Bananas',
    qty: 5,
    category: categories[Categories.fruit]!,
  ),
  GroceryItem(
    id: 'c',
    name: 'Beef Steak',
    qty: 1,
    category: categories[Categories.meat]!,
  ),
];
