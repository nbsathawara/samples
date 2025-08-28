import 'package:flutter/material.dart';

class Dimensions {
  static const baseDeviceRatio = 3.5;

  static double verySmallMargin = 2.0;
  static double smallMargin = 5.0;
  static double normalMargin = 10.0;
  static double mediumMargin = 15;
  static double largeMargin = 20.0;
  static double veryLargeMargin = 50.0;
  static double verySmallFontSize = 4.0;
  static double smallFontSize = 8.0;
  static double mediumFontSize = 15.0;
  static double normalFontSize = 18.0;
  static double largeFontSize = 26.0;
  static double veryLargeFontSize = 48.0;
  static double customLblFontSize = 14.0;
  static double customBtnFontSize = 22.5;

  static double twelveFontSize = 12.0;
  static double fifteenFontSize = 15.0;
  static double seventeenFontSize = 17.0;

  static double defaultElevation = 2;

  static double smallRadius = 8;
  static double normalRadius = 16;

  static double titleHeight = 50.0;
  static double lblHeight = 30.0;
  static double iconSize = 50.0;
  static double iconVerySmallSize = 20.0;
  static double iconSmallSize = 30.0;
  static double iconLargeSize = 75.0;
  static double customIconSize = 35.0;

  static double dashboardIconSize = 90.0;
  static double invoicePreviewHeight = 150.0;
  static double customLblViewDividerHeight = 35.0;
  static double schemeSlabViewWidth = 100.0;
  static const divider = 1.0;

  static const zeroMargin = EdgeInsets.all(0);
  static const zeroDivider = SizedBox(height: 0);

  static final verySmallDivider = SizedBox(height: verySmallMargin);
  static final smallDivider = SizedBox(height: smallMargin);
  static final normalDivider = SizedBox(height: normalMargin);
  static final mediumDivider = SizedBox(height: mediumMargin);
  static final largeDivider = SizedBox(height: largeMargin);
  static final veryLargeDivider = SizedBox(height: veryLargeMargin);

  static final smallDividerHorizontal = SizedBox(width: smallMargin);
  static final mediumDividerHorizontal = SizedBox(width: mediumMargin);
  static final normalDividerHorizontal = SizedBox(width: normalMargin);
  static final largeDividerHorizontal = SizedBox(width: largeMargin);
  static final verySmallDividerHorizontal = SizedBox(width: verySmallMargin);

  static final smallMarginAll = EdgeInsets.all(smallMargin);

  static final verySmallMarginAll = EdgeInsets.all(verySmallMargin);

  static final verSmallTBMargin = EdgeInsets.only(
    top: verySmallMargin,
    bottom: verySmallMargin,
  );

  static final smallTBMargin = EdgeInsets.only(
    top: smallMargin,
    bottom: smallMargin,
  );

  static final smallLRMargin = EdgeInsets.only(
    left: smallMargin,
    right: smallMargin,
  );

  static final normalMarginAll = EdgeInsets.all(normalMargin);

  static final mediumMarginAll = EdgeInsets.all(mediumMargin);

  static final cardMargin = EdgeInsets.symmetric(horizontal: 16, vertical: 8);

  static final expenseCardPadding = EdgeInsets.symmetric(
    horizontal: 20,
    vertical: 16,
  );

  static final normalLRMargin = EdgeInsets.only(
    left: normalMargin,
    right: normalMargin,
  );

  static final normalLRTMargin = EdgeInsets.only(
    left: normalMargin,
    right: normalMargin,
    top: normalMargin,
  );

  static final normalTBMargin = EdgeInsets.only(
    bottom: normalMargin,
    top: normalMargin,
  );

  static final veryLargeLRMargin = EdgeInsets.only(
    left: veryLargeMargin,
    right: veryLargeMargin,
  );

  static final largeLRMargin = EdgeInsets.only(
    left: largeMargin,
    right: largeMargin,
  );

  static final largeMarginAll = EdgeInsets.all(largeMargin);

  static final answerPadding = EdgeInsets.only(
    left: veryLargeMargin,
    top: largeMargin,
    right: veryLargeMargin,
    bottom: largeMargin,
  );

  static final emptyView = Container(padding: zeroMargin, margin: zeroMargin);
}
