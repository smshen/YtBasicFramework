package com.yitong.utils.image;

import static com.yitong.logs.Logs.d;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.yitong.logs.Logs;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.util.Base64;

/**
 * 图片下载
 * 
 * @Description
 * @author 刘国山 lgs@yitong.com.cn
 * @version 1.0 2013-6-18
 * @class com.yitong.zjrc.mbank.utils.image.ImageUtil
 */
public class ImageUtil {
	private static String tag = "ImageUtil";

	public static InputStream getRequest(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(8000);
		if (conn.getResponseCode() == 200 && conn.getInputStream() != null) {
			return conn.getInputStream();
		}
		return null;
	}

	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		return outSteam.toByteArray();
	}

	public static Drawable loadImageFromUrl(String url) {
		URL m;
		InputStream i = null;
		try {
			m = new URL(url);
			i = (InputStream) m.getContent();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Drawable d = Drawable.createFromStream(i, "src");
		return d;
	}

	public static Drawable getDrawableFromUrl(String url) throws Exception {
		return Drawable.createFromStream(getRequest(url), null);
	}

	public static Bitmap getBitmapFromUrl(String url) throws Exception {
		byte[] bytes = getBytesFromUrl(url);
		return byteToBitmap(bytes);
	}

	public static Bitmap getRoundBitmapFromUrl(String url, int pixels)
			throws Exception {
		byte[] bytes = getBytesFromUrl(url);
		Bitmap bitmap = byteToBitmap(bytes);
		return toRoundCorner(bitmap, pixels);
	}

	public static Drawable geRoundDrawableFromUrl(String url, int pixels)
			throws Exception {
		byte[] bytes = getBytesFromUrl(url);
		BitmapDrawable bitmapDrawable = (BitmapDrawable) byteToDrawable(bytes);
		return toRoundCorner(bitmapDrawable, pixels);
	}

	public static byte[] getBytesFromUrl(String url) throws Exception {
		return readInputStream(getRequest(url));
	}

	public static Bitmap byteToBitmap(byte[] byteArray) {
		if (byteArray.length != 0) {
			return BitmapFactory
					.decodeByteArray(byteArray, 0, byteArray.length);
		} else {
			return null;
		}
	}

	public static Drawable byteToDrawable(byte[] byteArray) {
		ByteArrayInputStream ins = new ByteArrayInputStream(byteArray);
		return Drawable.createFromStream(ins, null);
	}

	public static byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	public static Bitmap drawableToBitmap(Drawable drawable) {

		Bitmap bitmap = Bitmap
				.createBitmap(
						drawable.getIntrinsicWidth(),
						drawable.getIntrinsicHeight(),
						drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
								: Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;
	}

	/**
	 * 图片去色,返回灰度图片
	 * 
	 * @param bmpOriginal
	 *            传入的图片
	 * @return 去色后的图片
	 */
	public static Bitmap toGrayscale(Bitmap bmpOriginal) {
		int width, height;
		height = bmpOriginal.getHeight();
		width = bmpOriginal.getWidth();

		Bitmap bmpGrayscale = Bitmap.createBitmap(width, height,
				Bitmap.Config.RGB_565);
		Canvas c = new Canvas(bmpGrayscale);
		Paint paint = new Paint();
		ColorMatrix cm = new ColorMatrix();
		cm.setSaturation(0);
		ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
		paint.setColorFilter(f);
		c.drawBitmap(bmpOriginal, 0, 0, paint);
		return bmpGrayscale;
	}

	/**
	 * 去色同时加圆角
	 * 
	 * @param bmpOriginal
	 *            原图
	 * @param pixels
	 *            圆角弧度
	 * @return 修改后的图片
	 */
	public static Bitmap toGrayscale(Bitmap bmpOriginal, int pixels) {
		return toRoundCorner(toGrayscale(bmpOriginal), pixels);
	}

	/**
	 * 把图片变成圆角
	 * 
	 * @param bitmap
	 *            需要修改的图片
	 * @param pixels
	 *            圆角的弧度
	 * @return 圆角图片
	 */
	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	/**
	 * 使圆角功能支持BitampDrawable
	 * 
	 * @param bitmapDrawable
	 * @param pixels
	 * @return
	 */
	public static BitmapDrawable toRoundCorner(BitmapDrawable bitmapDrawable,
			int pixels) {
		Bitmap bitmap = bitmapDrawable.getBitmap();
		bitmapDrawable = new BitmapDrawable(toRoundCorner(bitmap, pixels));
		return bitmapDrawable;
	}

	/**
	 * 保存图片
	 * 
	 * @Description
	 * @param picSavePath
	 * @param imageUrl
	 * @param bitmap
	 * @Author dmq(dmq@yitong.com.cn) 2014-4-16 下午7:16:12
	 */
	public static void saveImage(String picSavePath, String imageUrl,
			Bitmap bitmap) {
		// 判断SD卡是否存在，并且是否具有读写权限
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			// 获得存储卡的路径

			File file = new File(picSavePath);
			// 判断文件目录是否存在
			if (!file.exists()) {
				file.mkdir();
			}

			String bitmapName = "";

			if (imageUrl.indexOf(".") != -1 && imageUrl.indexOf("/") != -1) {
				bitmapName = imageUrl.substring(
						(imageUrl.lastIndexOf("/") + 1),
						imageUrl.lastIndexOf("."));
			} else if (imageUrl.indexOf(".") != -1) {
				bitmapName = imageUrl.substring(0, imageUrl.lastIndexOf("."));
			} else if (imageUrl.indexOf("/") != -1) {
				bitmapName = imageUrl
						.substring((imageUrl.lastIndexOf("/") + 1));
			} else {
				bitmapName = imageUrl;
			}

			File bitmapFile = new File(picSavePath + bitmapName);
			d("TAG", "SD存储图片地址：" + bitmapFile.getPath());
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
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @Description bitmap转为base64
	 * @param bitmap
	 * @return
	 * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-4-23
	 */
	public static String bitmapToBase64(Bitmap bitmap) {

		String result = null;
		ByteArrayOutputStream baos = null;
		try {
			if (bitmap != null) {
				baos = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

				baos.flush();
				baos.close();

				byte[] bitmapBytes = baos.toByteArray();
				result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (baos != null) {
					baos.flush();
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
//	//图片限制大小 为宽度最大不能超过
//	private static int MAX_WIDTH= 200;
//	public static Bitmap compressBitmap(Bitmap bitmap){
//		int compress = bitmap.getWidth()/MAX_WIDTH; 
//		compress++;
//		Logs.d(tag, "压缩比例"+compress);
//		//等比压缩
//		ThumbnailUtils.extractThumbnail(bitmap, bitmap.getWidth()/compress, bitmap.getHeight()/compress);
//		return bitmap;
//	}

	

}
