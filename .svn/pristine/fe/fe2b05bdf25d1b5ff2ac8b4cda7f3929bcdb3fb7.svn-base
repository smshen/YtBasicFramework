package com.yitong.utils.image;

import static com.yitong.logs.Logs.d;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.yitong.logs.Logs;
import com.yitong.security.MD5;
import com.yitong.utils.CommonUtil;
import com.yitong.utils.StringTools;

/**
 * @Description 图片异步加载 工具 组合 CacheManger ImageUtil 工具类使用 实现双缓存
 *              图片异步加载（2013-06-4更新维护）
 * @author 刘国山 lgs@yitong.com.cn
 * @version 1.0 2013-6-18
 * @class com.yitong.zjrc.mbank.utils.image.AsyncImageLoader
 */
public class AsyncImageLoader {

	private final static String TAG = "AsyncImageLoader";

	// SoftReference是软引用，是为了更好的为了系统回收变量
	// private static HashMap<String, SoftReference<Drawable>> imageCache;

	private BitmapCache imageCacheMap = null;

	// 缓存数量
	private final static int CACHE_IMG_SIZE = 10;

	private static AsyncImageLoader asyncImageLoader = null;

	/* 下载保存路径 */
	public static String picSavePath = CommonUtil.getSDPath() + "/ydyx/image/";;

	private List<File> cacheFiles;

	private final static String SDPICPATH = "CACHE_PIC";
	// 限制图片宽度
	private static int MAX_WIDTH = 300;

	private CacheManger cacheManger;

	// private static BlockingQueue queue;
	// // 引入线程池
	// private static ThreadPoolExecutor executor;

	//
	// static {
	// //imageCache = new HashMap<String, SoftReference<Drawable>>();
	// }
	//
	//

	static {
		asyncImageLoader = new AsyncImageLoader();
		// 线程池：最大50条，每次执行：1条，空闲线程结束的超时时间：180秒
		// queue = new LinkedBlockingQueue();
		// executor = new ThreadPoolExecutor(1, 5, 180, TimeUnit.SECONDS,
		// queue);
	}

	public static AsyncImageLoader getController() {
		return asyncImageLoader;
	}

	public AsyncImageLoader() {
		imageCacheMap = new BitmapCache(CACHE_IMG_SIZE);
		picSavePath = Environment.getExternalStorageDirectory() + "/"
				+ SDPICPATH + "/";
		d(TAG, "图片工具类初始化");
		cacheManger = new CacheManger();
		// 执行获取图片集合
		cacheFiles = cacheManger.getCacheFiles(picSavePath);

	}

	/**
	 * 获取缓存图片集合
	 * 
	 * @Description
	 * @return
	 * @author 刘国山 lgs@yitong.com.cn
	 * @version 1.0 2012-8-29
	 */
	// private List<File> getCacheFiles() {
	// try {
	// List<File> filesList = null;
	// File file = new File(picSavePath);
	// // 判断文件目录是否存在
	// if (!file.exists()) {
	// file.mkdir();
	// }
	// File[] files = file.listFiles();
	// d(TAG, "图片工具类初始化");
	// // 如果SD缓存文件数量大于设置的数量
	// if (null != files && files.length > 0) {
	// d(TAG, "初始化数量为" + files.length);
	// filesList = new ArrayList<File>();
	// Collections.addAll(filesList, files);
	// SortList<File> sortList = new SortList<File>();
	// // 最后修改时间正序排列
	// sortList.Sort(filesList, "lastModified", null);
	// }
	// return filesList;
	// } catch (Exception e) {
	// e(TAG, "图片工具类初始化异常，返回NULL，下次执行重调");
	// return null;
	// }
	// }

	/**
	 * 
	 * @Description
	 * @param imageUrl
	 *            图片地址
	 * @param imageView
	 *            图片显示控件
	 * @param imageCallback
	 *            回调接口实现
	 * @return
	 * @author 刘国山 lgs@yitong.com.cn
	 * @version 1.0 2011-12-12
	 */
	public void loadDrawable(final String imageUrl, final ImageView imageView,
			final ImageCallback imageCallback) {

		final Handler handler = new Handler() {
			public void handleMessage(Message message) {
				// Logs.d(TAG, "message：" + message.what);
				if (message.what > 0) {
					Logs.d(TAG, "图片装载成功开始执行imageLoaded设置图片");
					imageCallback.imageLoaded((Bitmap) message.obj, imageView,
							imageUrl);
				} else {
					Logs.d(TAG, "图片装载失败。。。");
				}

			}
		};
		// 建立新一个读取图片的线程
		begainLoadPic(imageUrl, 0, handler);
	}

	public void loadDrawable(final String imageUrl, int maxWidth,
			final ImageView imageView, final ImageCallback imageCallback) {

		final Handler handler = new Handler() {
			public void handleMessage(Message message) {
				// Logs.d(TAG, "message：" + message.what);
				if (message.what > 0) {
					Logs.d(TAG, "图片装载成功开始执行imageLoaded设置图片");
					imageCallback.imageLoaded((Bitmap) message.obj, imageView,
							imageUrl);
				} else {
					Logs.d(TAG, "图片装载失败。。。");
				}

			}
		};
		// 建立新一个读取图片的线程
		begainLoadPic(imageUrl, maxWidth, handler);
	}

	/**
	 * 
	 * @Description
	 * @param imageUrl
	 *            图片下载URL
	 * @param maxWidth
	 *            图片最大宽度限制，如果为0 则使用默认 300PX
	 * @param handler
	 * @Author Lewis(lgs@yitong.com.cn) 2014年6月24日 下午5:13:30
	 */
	void begainLoadPic(String imageUrl, int maxWidth, Handler handler) {

		// 用线程池来做下载图片的任务
		// executor.execute(new Thread(new LoadPicThread(imageUrl, handler)));

		// 建立新一个新的线程下载图片
		Thread thread = new Thread(new LoadPicThread(imageUrl, maxWidth,
				handler));
		thread.start();
	}

	// 获取数据线程
	class LoadPicThread implements Runnable {
		String imageUrl;
		Handler handler;
		int maxWidth;
		// 是否压缩
		boolean isCompress;

		LoadPicThread(String imageUrl, Handler handler) {
			this.imageUrl = imageUrl;
			this.handler = handler;
			this.maxWidth = 0;
			this.isCompress = true;
		}

		LoadPicThread(String imageUrl, int maxWidth, Handler handler) {
			this.imageUrl = imageUrl;
			this.handler = handler;
			this.maxWidth = maxWidth;
			this.isCompress = true;
		}

		public void run() {
			Bitmap bitmap = null;

			String bitmapName;
			try {
				bitmapName = MD5.md5(imageUrl);
			} catch (Exception e) {
				// MD5失败后
				e.printStackTrace();
				if (imageUrl.indexOf(".") != -1 && imageUrl.indexOf("/") != -1) {
					bitmapName = imageUrl.substring(
							(imageUrl.lastIndexOf("/") + 1),
							imageUrl.lastIndexOf("."));
				} else if (imageUrl.indexOf(".") != -1) {
					bitmapName = imageUrl.substring(0,
							imageUrl.lastIndexOf("."));
				} else if (imageUrl.indexOf("/") != -1) {
					bitmapName = imageUrl
							.substring((imageUrl.lastIndexOf("/") + 1));
				} else {
					bitmapName = imageUrl;
				}
			}

			if (maxWidth > 0) {
				bitmapName = bitmapName + "_" + maxWidth;
			}

			// 缓存读取
			if (imageCacheMap.get(bitmapName) != null) {
				// 从缓存中获取
				bitmap = imageCacheMap.get(bitmapName);
				// d(TAG, "缓存中抓取图片URL：" + imageUrl);
				// SD卡读取
			} else if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {// 判断SD卡是否存在，并且是否具有读写权限
				File cacheDir = new File(picSavePath + bitmapName);
				if (cacheDir.exists()) {
					// d(TAG, "SD卡中抓取图片URL：" + imageUrl + "，名称：" + bitmapName);
					try {
						bitmap = BitmapFactory.decodeFile(picSavePath
								+ bitmapName);
					} catch (OutOfMemoryError err) {
						bitmap = null;
					}
				}
			}
			// 缓存 和 SD 卡都没有取到 执行下载
			if (bitmap == null) {
				Logs.d(TAG, "缓存中没有图片，网络加载");
				try {
					// drawable = ImageUtil.geRoundDrawableFromUrl(imageUrl,
					// 20);
					bitmap = ImageUtil.getBitmapFromUrl(imageUrl);
					int compress = 0;
					// 用户设置最大宽度不为0
					if (maxWidth > 0) {
						compress = bitmap.getWidth() / maxWidth;
					} else {
						compress = bitmap.getWidth() / MAX_WIDTH;
					}
					compress++;
					// 图片宽度
					int comWidth = bitmap.getWidth() / compress;
					int comHeight = bitmap.getHeight() / compress;
					d(TAG, "启动" + bitmapName + "==" + bitmap.getWidth() + ":"
							+ bitmap.getHeight() + "比例" + compress + "压缩后比例："
							+ comWidth + ":" + comHeight + "图片下载线程URL："
							+ imageUrl);
					// 等比压缩
					bitmap = ThumbnailUtils.extractThumbnail(bitmap, comWidth,
							comHeight);
				} catch (Exception e) {
					d(TAG, "图片下载异常：" + imageUrl);
					e.printStackTrace();
				} catch (OutOfMemoryError e) {
					d(TAG, "內存溢出：" + imageUrl);
					e.printStackTrace();
				}
				// 图片加载成功进行双缓存操作
				if (bitmap != null) {
					Logs.d(TAG, "图片下载成功，执行双缓存操作！");
					imageCacheMap.put(bitmapName, bitmap);

					// 判断SD卡是否存在，并且是否具有读写权限
					if (Environment.getExternalStorageState().equals(
							Environment.MEDIA_MOUNTED)) {
						// 获得存储卡的路径

						File file = new File(picSavePath);
						// 判断文件目录是否存在
						if (!file.exists()) {
							file.mkdir();
						}
						File bitmapFile = new File(picSavePath + bitmapName);
						d(TAG, "SD存储图片地址：" + bitmapFile.getPath());
						if (!bitmapFile.exists()) {
							try {
								bitmapFile.createNewFile();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						FileOutputStream fos;
						try {
							fos = new FileOutputStream(bitmapFile);
							bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
							fos.close();
							// 删除超出限定数量图片
							cacheFiles = cacheManger.delOverflowPic(cacheFiles,
									bitmapFile, picSavePath);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} else {
					Logs.d(TAG, "下载drawable空值！");
				}
			}
			if (bitmap != null) {
				Message message = handler.obtainMessage(1, bitmap);
				handler.sendMessage(message);
			} else {
				Message message = handler.obtainMessage(-1, bitmap);
				handler.sendMessage(message);
			}

		}
	}

	// 回调接口
	public interface ImageCallback {
		public void imageLoaded(Bitmap bitmap, ImageView imageView,
				String imageUrl);
	}

	/**
	 * @Title: downloadPic
	 * @Description:
	 * @param url
	 *            图片url
	 * @param imageView放入的图片组件
	 * @author wzx
	 * @param defaultPic
	 *            放入图片组件的默认图片
	 * @date 2011-12-1
	 */
	public void downloadPic(String url, ImageView imageView, int defaultPic) {

		// 图片地址为空 直接显示默认地址
		if (StringTools.isEmpty(url)) {
			imageView.setImageResource(defaultPic);
			return;
		}
		imageView.setImageResource(defaultPic);
		imageView.setTag(url);
		// 加载图片
		asyncImageLoader.loadDrawable(url, imageView, new ImageCallback() {
			public void imageLoaded(Bitmap bitmap, ImageView imageView,
					String imageUrl) {
				String path = (String) imageView.getTag();
				if (imageUrl.equals(path)) {
					// Logs.d(TAG, "设置回调图片");
					if (bitmap != null) {
						imageView.setImageBitmap(bitmap);
					} else {
						Logs.d(TAG, "下载的图片空，没有后续操作");
					}
				}
			}
		});

	}

	/**
	 * 加载图片
	 * 
	 * @Description
	 * @param url
	 *            图片下载地址
	 * @param maxWidth
	 *            最大宽度
	 * @param imageView
	 * @param defaultPic
	 * @Author Lewis(lgs@yitong.com.cn) 2014年6月24日 下午5:16:26
	 */
	public void downloadPic(String url, int maxWidth, ImageView imageView,
			int defaultPic) {

		// 图片地址为空 直接显示默认地址
		if (StringTools.isEmpty(url)) {
			imageView.setImageResource(defaultPic);
			return;
		}

		imageView.setImageResource(defaultPic);
		imageView.setTag(url);
		// 加载图片
		asyncImageLoader.loadDrawable(url, maxWidth, imageView,
				new ImageCallback() {
					public void imageLoaded(Bitmap bitmap, ImageView imageView,
							String imageUrl) {
						String path = (String) imageView.getTag();
						if (imageUrl.equals(path)) {
							// Logs.d(TAG, "设置回调图片");
							if (bitmap != null) {
								imageView.setImageBitmap(bitmap);
							} else {
								Logs.d(TAG, "下载的图片空，没有后续操作");
							}
						}
					}
				});
	}

	/**
	 * 清空缓存
	 */
	public void clear() {
		if (cacheFiles == null)
			return;
		for (File f : cacheFiles)
			f.delete();
	}

}
