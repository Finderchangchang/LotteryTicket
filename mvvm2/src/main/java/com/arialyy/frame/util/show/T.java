package com.arialyy.frame.util.show;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Lyy on 2015/4/1.
 * Toast统一管理类
 */
public class T {

  private T() {
    throw new UnsupportedOperationException("cannot be instantiated");
  }

  /**
   * 是否显示Tost
   */
  public static boolean isShow = true;

  /**
   * 短时间显示Toast
   */
  public static void showShort(Context context, CharSequence message) {
    if (isShow) Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
  }

  /**
   * 短时间显示Toast
   */
  public static void showShort(Context context, int message) {
    if (isShow) Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
  }

  /**
   * 长时间显示Toast
   */
  public static void showLong(Context context, CharSequence message) {
    if (isShow) Toast.makeText(context, message, Toast.LENGTH_LONG).show();
  }

  /**
   * 长时间显示Toast
   */
  public static void showLong(Context context, int message) {
    if (isShow) Toast.makeText(context, message, Toast.LENGTH_LONG).show();
  }

  /**
   * 自定义显示Toast时间
   */
  public static void show(Context context, CharSequence message, int duration) {
    if (isShow) Toast.makeText(context, message, duration).show();
  }

  /**
   * 自定义显示Toast时间
   */
  public static void show(Context context, int message, int duration) {
    if (isShow) Toast.makeText(context, message, duration).show();
  }
}