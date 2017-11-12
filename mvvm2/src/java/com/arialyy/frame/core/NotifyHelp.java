package com.arialyy.frame.core;

import android.util.SparseArray;
import com.arialyy.frame.util.ReflectionUtil;

/**
 * Created by “AriaLyy@outlook.com” on 2016/4/5.
 * 更新帮助类
 */
public class NotifyHelp {
  private static final Object LOCK = new Object();
  private static volatile NotifyHelp INSTANCE = null;
  private SparseArray<SparseArray<OnNotifyCallback>> mNotifyObjs = new SparseArray<>();

  private NotifyHelp() {

  }

  public static NotifyHelp getInstance() {
    if (INSTANCE == null) {
      synchronized (LOCK) {
        INSTANCE = new NotifyHelp();
      }
    }
    return INSTANCE;
  }

  public void addObj(int flag, OnNotifyCallback callback) {
    SparseArray<OnNotifyCallback> array = mNotifyObjs.get(flag);
    if (array == null) {
      array = new SparseArray<>();
    }
    array.put(callback.hashCode(), callback);
    mNotifyObjs.put(flag, array);
  }

  public void removeObj(int flag) {
    mNotifyObjs.delete(flag);
  }

  public void clear() {
    mNotifyObjs.clear();
  }

  public void update(int flag) {
    update(flag, -1, null);
  }

  public void update(int flag, int action) {
    update(flag, action, null);
  }

  public void update(int flag, int action, Object obj) {
    if (mNotifyObjs == null || mNotifyObjs.size() == 0) {
      return;
    }
    SparseArray<OnNotifyCallback> array = mNotifyObjs.get(flag);
    if (array == null || array.size() == 0) {
      return;
    }
    try {
      int[] keys = (int[]) ReflectionUtil.getField(SparseArray.class, "mKeys").get(array);
      for (int key : keys) {
        if (key == 0) {
          continue;
        }
        OnNotifyCallback callback = array.get(key);
        if (callback != null) {
          callback.onNotify(action, obj);
        }
      }
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  public interface OnNotifyCallback {
    public void onNotify(int action, Object obj);
  }
}
