import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:uuid/uuid.dart';

class Constants {
  static const uuid = Uuid();

  static const categoryIcons = {
    Category.food: Icons.lunch_dining,
    Category.travel: Icons.flight_takeoff,
    Category.leisure: Icons.movie,
    Category.work: Icons.work,
    Category.others: Icons.auto_awesome_mosaic,
  };

  //Date Formats
  static final dateFormatter = DateFormat.yMd();
}

//Enums
enum Category { food, travel, leisure, work, others }
