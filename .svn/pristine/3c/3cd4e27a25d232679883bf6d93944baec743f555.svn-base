/*
 * 作者		刘国山 lgs@yitong.com.cn
 * 开发环境	Win7 Eclipse3.5 JDK1.6
 * 开发日期	2012-8-30
 */
package com.yitong.utils.image;

import static com.yitong.logs.Logs.d;
import static com.yitong.logs.Logs.e;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * SD图片缓存管理工具
 * @Description 
 * @author 刘国山 lgs@yitong.com.cn
 * @version 1.0 2013-6-18
 * @class com.yitong.zjrc.mbank.utils.image.CacheManger
 */
public class CacheManger {

	private final static String TAG = "CacheManger";

	// SD卡存数数量
	private final static int SD_CACHE_IMG_SIZE = 200;
	// 批量删除数量
	private final static int SD_DEL_COUNT = 50;

	/**
	 * 删除超出索引的图片 保持 先进先删规则
	 * 
	 * @Description
	 * @param cacheFiles
	 * @param file
	 * @author 刘国山 lgs@yitong.com.cn
	 * @version 1.0 2012-8-30
	 */
	public synchronized List<File> delOverflowPic(List<File> cacheFiles, File file,String picSavePath) {
		try {
			if (cacheFiles == null) {
				d(TAG, "SD缓存变量异常重新获取");
				cacheFiles = getCacheFiles(picSavePath);
			}
			if (cacheFiles != null) {
				// 添加到集合
				cacheFiles.add(file);
				d(TAG, "SD缓存数量：" + cacheFiles.size());
				// 如果SD缓存文件数量大于设置的数量
				if (cacheFiles.size() > SD_CACHE_IMG_SIZE) {

					// 超出缓存数量 + 批量删除数量
					int outSize = cacheFiles.size() - SD_CACHE_IMG_SIZE
							+ SD_DEL_COUNT;
					// 获取待删除集合
					List<File> delFiles = cacheFiles.subList(0, outSize);
					// 删除文件
					for (int i = 0; i < delFiles.size(); i++) {
						d(TAG, delFiles.get(i).getName() + "删除");
						if (delFiles.get(i).exists()) {
							delFiles.get(i).delete();
						}
					}
					// 删除集合元素
					cacheFiles = cacheFiles.subList(outSize, cacheFiles.size());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			e(TAG, "文件删除异常,捕获继续执行");
			// 重新初始化文件列表
			cacheFiles = getCacheFiles(picSavePath);
		}
		
		return cacheFiles;
	}
	
	/**
	 * 获取缓存图片集合
	 * 
	 * @Description
	 * @return
	 * @author 刘国山 lgs@yitong.com.cn
	 * @version 1.0 2012-8-29
	 */
	public List<File> getCacheFiles(String picSavePath) {
		try {
			List<File> filesList = null;
			File file = new File(picSavePath);
			// 判断文件目录是否存在
			if (!file.exists()) {
				file.mkdir();
			}
			File[] files = file.listFiles();
			d(TAG, "图片工具类初始化");
			// 如果SD缓存文件数量大于设置的数量
			if (null != files && files.length > 0) {
				d(TAG, "初始化数量为" + files.length);
				filesList = new ArrayList<File>();
				Collections.addAll(filesList, files);
				SortList<File> sortList = new SortList<File>();
				// 最后修改时间正序排列
				sortList.Sort(filesList, "lastModified", null);
			}
			return filesList;
		} catch (Exception e) {
			e(TAG, "图片工具类初始化异常，返回NULL，下次执行重调");
			return null;
		}
	}

}
