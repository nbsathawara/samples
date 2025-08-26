import 'package:flutter/material.dart';

class Dimensions {
  static const baseDeviceRatio = 3.5;

  static double verySmallMargin = 2.0;
  static double smallMargin = 5.0;
  static double mediumMargin = 7.5;
  static double normalMargin = 10.0;
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

  static const zeroDivider = SizedBox(height: 0);

  static SizedBox verySmallDivider = SizedBox(height: verySmallMargin);
  static SizedBox smallDivider = SizedBox(height: smallMargin);
  static SizedBox normalDivider = SizedBox(height: normalMargin);
  static SizedBox largeDivider = SizedBox(height: largeMargin);
  static SizedBox veryLargeDivider = SizedBox(height: veryLargeMargin);

  static SizedBox smallDividerHorizontal = SizedBox(width: smallMargin);
  static SizedBox mediumDividerHorizontal = SizedBox(width: mediumMargin);
  static SizedBox normalDividerHorizontal = SizedBox(width: normalMargin);
  static SizedBox largeDividerHorizontal = SizedBox(width: largeMargin);
  static SizedBox verySmallDividerHorizontal = SizedBox(width: verySmallMargin);

  static const zeroMargin = EdgeInsets.all(0);

  static EdgeInsets smallMarginAll = EdgeInsets.all(smallMargin);

  static EdgeInsets verySmallMarginAll = EdgeInsets.all(verySmallMargin);

  static EdgeInsets verSmallTBMargin = EdgeInsets.only(
    top: verySmallMargin,
    bottom: verySmallMargin,
  );

  static EdgeInsets smallTBMargin = EdgeInsets.only(
    top: smallMargin,
    bottom: smallMargin,
  );

  static EdgeInsets smallLRMargin = EdgeInsets.only(
    left: smallMargin,
    right: smallMargin,
  );

  static EdgeInsets normalMarginAll = EdgeInsets.all(normalMargin);

  static EdgeInsets normalLRMargin = EdgeInsets.only(
    left: normalMargin,
    right: normalMargin,
  );

  static EdgeInsets normalLRTMargin = EdgeInsets.only(
    left: normalMargin,
    right: normalMargin,
    top: normalMargin,
  );

  static EdgeInsets normalTBMargin = EdgeInsets.only(
    bottom: normalMargin,
    top: normalMargin,
  );

  static EdgeInsets veryLargeLRMargin = EdgeInsets.only(
    left: veryLargeMargin,
    right: veryLargeMargin,
  );

  static EdgeInsets largeLRMargin = EdgeInsets.only(
    left: largeMargin,
    right: largeMargin,
  );

  static EdgeInsets largeMarginAll = EdgeInsets.all(largeMargin);

  static EdgeInsets answerPadding = EdgeInsets.only(
    left: veryLargeMargin,
    top: largeMargin,
    right: veryLargeMargin,
    bottom: largeMargin,
  );

  static Container emptyView = Container(
    padding: zeroMargin,
    margin: zeroMargin,
  );
}
