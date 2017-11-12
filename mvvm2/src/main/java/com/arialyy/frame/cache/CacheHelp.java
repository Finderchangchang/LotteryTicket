package com.arialyy.frame.cache;

import android.text.TextUtils;
import com.arialyy.frame.util.show.L;
import java.util.Map;
import java.util.Set;

/**
 * Created by “AriaLyy@outlook.com” on 2015/4/9.
 */
public class CacheHelp {

  /**
   * 设置缓存有效时间
   */
  public static void setCacheAvailableTime(long availableTime) {
    CacheObj.CACHE_INTERVAL = availableTime;
  }

  /**
   * 储存缓存
   */
  public static void saveCache(CacheUtil util, String key, String value) {
    if (TextUtils.isEmpty(value)) {
      L.e("缓存数据位null");
      return;
    }
    CacheObj cache = util.getObjectCache(CacheObj.class, key);
    if (cache == null) {
      cache = new CacheObj();
    }
    cache.setCacheTime(System.currentTimeMillis());
    cache.setAvailableNum(cache.getAvailableNum() + 1);
    cache.setData(value);
    util.putObjectCache(CacheObj.class, key, cache);
  }

  /**
   * 获取有效的缓存
   */
  public static CacheObj getAvailableCache(CacheUtil util, String url, Map<String, String> param) {
    if (TextUtils.isEmpty(url)) {
      return null;
    }
    String key = getKey(url, param);
    CacheObj cache = util.getObjectCache(CacheObj.class, key);
    if (cache == null) {
      return null;
    }
    if (cache.isAvailable()) {
      return cache;
    }
    return null;
  }

  /**
   * 获取缓存，有效或无效
   */
  public static CacheObj getCache(CacheUtil util, String url, Map<String, String> param) {
    if (TextUtils.isEmpty(url)) {
      return null;
    }
    String key = getKey(url, param);
    return util.getObjectCache(CacheObj.class, key);
  }

  /**
   * 通过url和请求参数获取key
   */
  public static String getKey(String url, Map<String, String> param) {
    if (param == null || param.size() == 0) {
      return url;
    }
    String p = "";
    for (Map.Entry<String, String> entry : param.entrySet()) {
      p += "[" + entry.getKey() + "," + entry.getValue() + "];";
    }
    url += ";" + p;
    return url;
  }
}