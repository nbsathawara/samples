import 'package:shopping_list/models/category.dart';

class GroceryItem {
  final String id, name;
  final int qty;
  final Category category;

  GroceryItem({
    required this.id,
    required this.name,
    required this.qty,
    required this.category,
  });
}
