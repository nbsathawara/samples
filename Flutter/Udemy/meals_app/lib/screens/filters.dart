import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:meals_app/data/constants.dart';
import 'package:meals_app/providers/filters_provider.dart';

class FilterScreen extends ConsumerWidget {
  const FilterScreen({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final activeFilters = ref.watch(filtersProvider);
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
      body: Column(
        children: [
          glutenFreeFilter(context, ref, activeFilters),
          lactoseFreeFilter(context, ref, activeFilters),
          vegeterianFilter(context, ref, activeFilters),
          veganfilter(context, ref, activeFilters),
        ],
      ),
    );
  }

  SwitchListTile veganfilter(
    BuildContext context,
    WidgetRef ref,
    activeFilters,
  ) {
    return SwitchListTile(
      value: activeFilters[Filter.vegan]!,
      onChanged: (value) {
        ref.read(filtersProvider.notifier).setFilter(Filter.vegan, value);
      },
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
    );
  }

  SwitchListTile vegeterianFilter(
    BuildContext context,
    WidgetRef ref,
    activeFilters,
  ) {
    return SwitchListTile(
      value: activeFilters[Filter.vegeterian]!,
      onChanged: (value) {
        ref.read(filtersProvider.notifier).setFilter(Filter.vegeterian, value);
      },
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
    );
  }

  SwitchListTile lactoseFreeFilter(
    BuildContext context,
    WidgetRef ref,
    activeFilters,
  ) {
    return SwitchListTile(
      value: activeFilters[Filter.lactoseFree]!,
      onChanged: (value) {
        ref.read(filtersProvider.notifier).setFilter(Filter.lactoseFree, value);
      },
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
    );
  }

  SwitchListTile glutenFreeFilter(
    BuildContext context,
    WidgetRef ref,
    activeFilters,
  ) {
    return SwitchListTile(
      value: activeFilters[Filter.glutenFree]!,
      onChanged: (value) {
        ref.read(filtersProvider.notifier).setFilter(Filter.glutenFree, value);
      },
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
    );
  }
}
