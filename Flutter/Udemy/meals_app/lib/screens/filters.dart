import 'package:flutter/material.dart';
import 'package:meals_app/data/constants.dart';

class FilterScreen extends StatefulWidget {
  const FilterScreen({super.key, required this.currentFilter});
  final Map<Filter, bool> currentFilter;

  @override
  State<StatefulWidget> createState() => _FilterState();
}

class _FilterState extends State<FilterScreen> {
  var _isGlutenFree = false;
  var _isLactoseFree = false;
  var _isVegiterian = false;
  var _isVegan = false;

  @override
  void initState() {
    super.initState();
    _isGlutenFree = widget.currentFilter[Filter.glutenFree]!;
    _isLactoseFree = widget.currentFilter[Filter.lactoseFree]!;
    _isVegiterian = widget.currentFilter[Filter.vegeterian]!;
    _isVegan = widget.currentFilter[Filter.vegan]!;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Your Filters')),
      // drawer: MainDrawer(
      //   onSelectScreen: (identifier) {
      //     Navigator.pop(context);
      //     if (identifier == 'meals') {
      //       Navigator.pushReplacement(
      //         context,
      //         MaterialPageRoute(builder: (context) => Tabs()),
      //       );
      //     }
      //   },
      // ),
      body: PopScope(
        canPop: false,
        onPopInvokedWithResult: (didPop, result) {
          if (didPop) {
            return;
          }
          var filterMap = {
            Filter.glutenFree: _isGlutenFree,
            Filter.lactoseFree: _isLactoseFree,
            Filter.vegeterian: _isVegiterian,
            Filter.vegan: _isVegan,
          };
          Navigator.pop(context, filterMap);
        },
        child: Column(
          children: [
            glutenFreeFilter(context),
            lactoseFreeFilter(context),
            vegeterianFilter(context),
            veganfilter(context),
          ],
        ),
      ),
    );
  }

  SwitchListTile veganfilter(BuildContext context) {
    return SwitchListTile(
      value: _isVegan,
      title: Text(
        'Vegan',
        style: Theme.of(context).textTheme.titleLarge!.copyWith(
          color: Theme.of(context).colorScheme.onSurface,
        ),
      ),
      subtitle: Text(
        'Only include vegan meals.',
        style: Theme.of(context).textTheme.titleSmall!.copyWith(
          color: Theme.of(context).colorScheme.onSurface,
        ),
      ),
      activeThumbColor: Theme.of(context).colorScheme.tertiary,
      contentPadding: EdgeInsets.only(left: 34, right: 22),
      onChanged: (value) {
        setState(() {
          _isVegan = value;
        });
      },
    );
  }

  SwitchListTile vegeterianFilter(BuildContext context) {
    return SwitchListTile(
      value: _isVegiterian,
      title: Text(
        'Vegeterian',
        style: Theme.of(context).textTheme.titleLarge!.copyWith(
          color: Theme.of(context).colorScheme.onSurface,
        ),
      ),
      subtitle: Text(
        'Only include vegeterian meals.',
        style: Theme.of(context).textTheme.titleSmall!.copyWith(
          color: Theme.of(context).colorScheme.onSurface,
        ),
      ),
      activeThumbColor: Theme.of(context).colorScheme.tertiary,
      contentPadding: EdgeInsets.only(left: 34, right: 22),
      onChanged: (value) {
        setState(() {
          _isVegiterian = value;
        });
      },
    );
  }

  SwitchListTile lactoseFreeFilter(BuildContext context) {
    return SwitchListTile(
      value: _isLactoseFree,
      title: Text(
        'Lactosen-free',
        style: Theme.of(context).textTheme.titleLarge!.copyWith(
          color: Theme.of(context).colorScheme.onSurface,
        ),
      ),
      subtitle: Text(
        'Only include lactose-free meals.',
        style: Theme.of(context).textTheme.titleSmall!.copyWith(
          color: Theme.of(context).colorScheme.onSurface,
        ),
      ),
      activeThumbColor: Theme.of(context).colorScheme.tertiary,
      contentPadding: EdgeInsets.only(left: 34, right: 22),
      onChanged: (value) {
        setState(() {
          _isLactoseFree = value;
        });
      },
    );
  }

  SwitchListTile glutenFreeFilter(BuildContext context) {
    return SwitchListTile(
      value: _isGlutenFree,
      title: Text(
        'Gluten-free',
        style: Theme.of(context).textTheme.titleLarge!.copyWith(
          color: Theme.of(context).colorScheme.onSurface,
        ),
      ),
      subtitle: Text(
        'Only include gluten-free meals.',
        style: Theme.of(context).textTheme.titleSmall!.copyWith(
          color: Theme.of(context).colorScheme.onSurface,
        ),
      ),
      activeThumbColor: Theme.of(context).colorScheme.tertiary,
      contentPadding: EdgeInsets.only(left: 34, right: 22),
      onChanged: (value) {
        setState(() {
          _isGlutenFree = value;
        });
      },
    );
  }
}
