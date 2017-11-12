package com.arialyy.frame.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.arialyy.frame.util.DrawableUtil;
import com.arialyy.frame.util.show.L;
import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;

/**
 * Created by “AriaLyy@outlook.com” on 2015/4/9.
 * 缓存工具
 */
public class CacheUtil extends AbsCache {
  private static final String TAG = "CacheUtil";

  /**
   * 默认使用默认路径
   */
  private CacheUtil(Context context) {
    this(context, DEFAULT_DIR);
  }

  /**
   * 指定缓存文件夹
   *
   * @param cacheDir 缓存文件夹名
   */
  private CacheUtil(Context context, @NonNull String cacheDir) {
    super(context, cacheDir);
  }

  /**
   * 是否使用内存缓存
   */
  private void setUseMemoryCache(boolean openMemoryCache) {
    setUseMemory(openMemoryCache);
  }

  /**
   * 是否使用磁场缓存
   */
  private void setUseDiskCache(boolean openDiskCache) {
    setUseMemory(openDiskCache);
  }

  /**
   * 打开某个目录下的缓存
   *
   * @param cacheDir 缓存目录，只需填写文件夹名，不需要写路径
   * @param valueCount 指定同一个key可以对应多少个缓存文件，基本都是传1
   * @param cacheSize 缓存大小
   * @see CacheParam
   */
  public void openCache(String cacheDir, int valueCount, long cacheSize) {
    openDiskCache(cacheDir, valueCount, cacheSize);
  }

  /**
   * 写入Bitmap类型缓存，注意：需要在特定的时候flush,一般在onPause()里面写,onDestroy()
   *
   * @param key 键值，一般是url
   * @param bitmap 需要写入的数据
   */
  public void putBitmapCache(String key, Bitmap bitmap) {
    byte[] data = DrawableUtil.getBitmapByte(bitmap);
    putByteCache(key, data);
  }

  /**
   * 获取缓存中的bitmap
   */
  public Bitmap getBitmapCache(String key) {
    byte[] data = getByteCache(key);
    return DrawableUtil.getBitmapFromByte(data);
  }

  /**
   * 写入String类型缓存，注意：需要在特定的时候flush,一般在onPause()里面写,onDestroy()
   *
   * @param key 键值，一般是url
   * @param data 需要写入的数据
   */
  public void putStringCache(String key, String data) {
    try {
      putByteCache(key, data.getBytes("utf-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  /**
   * 读取字符串缓存
   */
  public String getStringCache(String key) {
    byte[] data = getByteCache(key);
    String str = "";
    if (data != null) {
      try {
        str = new String(data, "utf-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    }
    return str;
  }

  /**
   * 写入byte类型缓存，注意：需要在特定的时候flush,一般在onPause()里面写,onDestroy()
   *
   * @param key 键值，一般是url
   * @param data 需要写入的数据
   */
  public void putByteCache(String key, byte[] data) {
    writeDiskCache(key, data);
  }

  /**
   * 读取byte类型缓存
   *
   * @param key 缓存的key
   */
  public byte[] getByteCache(String key) {
    return readDiskCache(key);
  }

  /**
   * 写入对象缓存后，注意：需要在特定的时候flush,一般在onPause()里面写,onDestroy()
   *
   * @param clazz 对象类型
   * @param key 缓存键值
   * @param object 对象
   */
  public void putObjectCache(Class<?> clazz, String key, Object object) {
    String json = new Gson().toJson(object, clazz);
    try {
      writeDiskCache(key, json.getBytes("utf-8"));
    } catch (UnsupportedEncodingException e) {
      L.e(TAG, "编码转换错误", e);
    }
  }

  /**
   * 读取对象缓存
   *
   * @param clazz 对象类型
   * @param key 缓存键值
   */
  public <T> T getObjectCache(Class<T> clazz, String key) {
    T object = null;
    try {
      byte[] data = readDiskCache(key);
      if (data == null) {
        return null;
      }
      object = new Gson().fromJson(new String(data, "utf-8"), clazz);
    } catch (UnsupportedEncodingException e) {
      L.e(TAG, "编码转换错误", e);
    }
    return object;
  }

  /**
   * 同步记录,不同步则提取不了缓存
   */
  public void flush() {
    flushDiskCache();
  }

  /**
   * 删除一条缓存
   *
   * @param key 缓存键值
   */
  public void remove(String key) {
    readDiskCache(key);
  }

  /**
   * 删除所有缓存
   */
  public void clearCache() {
    super.clearCache();
  }

  /**
   * 关闭磁盘缓存
   */
  public void close() {
    closeMemoryCache();
    closeDiskCache();
  }

  /**
   * 获取缓存大小
   */
  public long getCacheSize() {
    return super.getCacheSize();
  }

  public static class Builder {
    boolean openDiskCache = false;
    boolean openMemoryCache = false;
    String cacheDirName = DEFAULT_DIR;
    long diskCacheSize = NORMAL_DISK_CACHE_CAPACITY;
    int memoryCacheSize = MEMORY_CACHE_SIZE;
    Context context;

    public Builder(Context context) {
      this.context = context;
    }

    /**
     * 打开磁盘缓存
     */
    public Builder openDiskCache() {
      openDiskCache = true;
      return this;
    }

    /**
     * 打开内存缓存
     */
    public Builder openMemoryCache() {
      openMemoryCache = true;
      return this;
    }

    /**
     * 缓存文件夹名，只需要写文件夹名
     */
    public Builder setCacheDirName(String cacheDirName) {
      this.cacheDirName = cacheDirName;
      return this;
    }

    /**
     * 设置磁盘缓存大小
     */
    public Builder setDiskCacheSize(long cacheSize) {
      this.diskCacheSize = cacheSize;
      return this;
    }

    /**
     * 设置内存缓存大小
     */
    public Builder setMemoryCacheSize(int cacheSize) {
      this.memoryCacheSize = cacheSize;
      return this;
    }

    public CacheUtil build() {
      CacheUtil util = new CacheUtil(context);
      util.setUseMemoryCache(openMemoryCache);
      util.setUseDiskCache(openDiskCache);
      util.setMemoryCacheSize(memoryCacheSize);
      return new CacheUtil(context, cacheDirName);
    }
  }
}
