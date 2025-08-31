import 'package:flutter/material.dart';
import 'package:shopping_list/data/items.dart';

class GroceryList extends StatelessWidget {
  const GroceryList({super.key});

  @override
  Widget build(BuildContext context) {
    var groceries = groceryItems;
    return Scaffold(
      appBar: AppBar(title: const Text('Your Groceries')),
      body: ListView.builder(
        itemCount: groceries.length,
        itemBuilder: (context, index) {
          var grocery = groceries[index];
         return  ListTile(
            leading: Container(
              width: 25,
              height: 25,
              color: grocery.category.color,
            ),
            title: Text(grocery.name),
            trailing: Text(grocery.qty.toString()),
          );
        },
      ),
    );
  }
}
