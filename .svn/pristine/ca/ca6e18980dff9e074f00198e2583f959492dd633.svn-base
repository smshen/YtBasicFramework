package com.yitong.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.yitong.logs.Logs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

public class FileUtil {
	public static final String SDCARD = Environment
			.getExternalStorageDirectory() + "/";

	/**
	 * 在SD卡上创建文件
	 * 
	 * @throws IOException
	 */
	public File creatSDFile(String fileName) throws IOException {
		File file = new File(SDCARD + fileName);
		file.createNewFile();
		return file;
	}

	/**
	 * 在SD卡上创建目录
	 * 
	 * @param dirName
	 */
	public File creatSDDir(String dirName) {
		File dir = new File(SDCARD + dirName);
		dir.mkdir();
		return dir;
	}

	/**
	 * 判断SD卡上的文件夹是否存在
	 */
	public boolean isFileExist(String fileName) {
		File file = new File(SDCARD + fileName);
		return file.exists();
	}

	/**
	 * 将一个InputStream里面的数据写入到SD卡中
	 */
	public File write2SDFromInput(String path, String fileName,
			InputStream input) {
		File file = null;
		OutputStream output = null;
		try {
			creatSDDir(path);
			file = creatSDFile(path + fileName);
			output = new FileOutputStream(file);
			byte buffer[] = new byte[4 * 1024];
			while ((input.read(buffer)) != -1) {
				output.write(buffer);
			}
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	/**
	 * @Description 判断文件是否存在
	 * @param path
	 * @return
	 * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-4-15
	 */
	public static Boolean exist(String path) {
		File file = new File(path);
		Boolean exist = false;
		try {
			exist = file.exists();
			file = null;
		} catch (Exception e) {
			Log.w("FileUtil", "file exists(" + path + ") error!");
		}
		return exist;
	}

	/**
	 * 创建文件
	 * 
	 * @param path
	 *            文件路径
	 * @param filename
	 *            文件名
	 * @return
	 */
	public static boolean createFile(String path, String filename) {
		File file = new File(path);
		Boolean createFlg = false;
		// 按照指定的路径创建文件夹
		if (!file.exists()) {
			file.mkdirs();
		}
		String local_file = file.getAbsolutePath() + "/" + filename;
		file = new File(local_file);
		if (!file.exists()) {
			try {
				// 创建新文件
				createFlg = file.createNewFile();
			} catch (Exception e) {
				Logs.d("FileUtil", e.getMessage());
			}
		}
		return createFlg;
	}

	/**
	 * @Description 文件重命名
	 * @param path
	 * @return
	 * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-4-15
	 */
	public static boolean renameTo(String oldPath, String newPath){
		boolean renameFlg = false;
		File ofile = new File(oldPath);
		if(ofile.exists()){
			File nfile = new File(newPath);
			renameFlg = ofile.renameTo(nfile);
		}
		return renameFlg;
	}
	
	/**
	 * @Description 删除指定路径文件
	 * @param path
	 * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-4-15
	 */
	public static void deleteFile(String path) {
		File file = new File(path);
		// 判断文件是否存在
		if (file.exists()) { 
			// 判断是否是文件
			if (file.isFile()) { 
				//删除
				file.delete(); 
			}
		}
	}

	public static void cache(String path, byte[] data) throws IOException {
		OutputStream os = null;
		try {
			os = new FileOutputStream(path);
			os.write(data);
		} catch (IOException e) {
			Log.w("FileUtil", "file cache(" + path + ") error!");
		} finally {
			if (null != os)
				os.close();
			os = null;
		}
	}

	public static boolean checkIsImgFile(String path) {
		boolean isImgFile = false;
		// 获取扩展名
		String fileEnd = path.substring(path.lastIndexOf(".") + 1,
				path.length()).toLowerCase();
		if (fileEnd.equals("png") || fileEnd.equals("jpg")) {
			isImgFile = true;
		} else {
			isImgFile = false;
		}
		return isImgFile;
	}

	public static boolean checkIsPdfFile(String path) {
		boolean isPdfFile = false;
		// 获取扩展名
		String fileEnd = path.substring(path.lastIndexOf(".") + 1,
				path.length()).toLowerCase();
		if (fileEnd.equals("pdf")) {
			isPdfFile = true;
		} else {
			isPdfFile = false;
		}
		return isPdfFile;
	}

	public static boolean checkIsVideoFile(String path) {
		boolean isVideoFile = false;
		// 获取扩展名
		String fileEnd = path.substring(path.lastIndexOf(".") + 1,
				path.length()).toLowerCase();
		if (fileEnd.equals("mp4") || fileEnd.equals("3gp")) {
			isVideoFile = true;
		} else {
			isVideoFile = false;
		}
		return isVideoFile;
	}

	public static String getFileName(String path) {
		// String fileEnd = path.substring(path.lastIndexOf("/") + 1,
		// path.length());
		String fileName = path.substring(path.lastIndexOf("/")).substring(1);
		return fileName;
	}

	public static Bitmap getDiskBitmap(String pathString) {
		Bitmap bitmap = null;
		try {
			File file = new File(pathString);
			if (file.exists()) {
				bitmap = BitmapFactory.decodeFile(pathString);
			}
		} catch (Exception e) {
		}
		return bitmap;
	}

}
