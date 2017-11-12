package com.arialyy.frame.cache;

/**
 * Created by “AriaLyy@outlook.com” on 2015/4/9.
 * 缓存对象
 */
class CacheObj {
  /**
   * 缓存有效请求时间
   */
  public static long CACHE_INTERVAL = 120 * 1000;
  /**
   * 缓存有效请求次数
   */
  public static final int CACHE_NUM_INTERVAL = 5;
  private long cacheTime = 0;
  private long availableNum = 0;

  private String data;

  /**
   * 缓存是否有效
   */
  public boolean isAvailable() {
    if (System.currentTimeMillis() - cacheTime > CACHE_INTERVAL) {
      return false;
    } else if (availableNum >= CACHE_NUM_INTERVAL) {
      return false;
    }
    return true;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public long getCacheTime() {
    return cacheTime;
  }

  public void setCacheTime(long cacheTime) {
    this.cacheTime = cacheTime;
  }

  public long getAvailableNum() {
    return availableNum;
  }

  public void setAvailableNum(long availableNum) {
    this.availableNum = availableNum;
  }
}
