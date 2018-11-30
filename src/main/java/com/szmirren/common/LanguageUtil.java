package com.szmirren.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;

import com.szmirren.view.AlertUtil;

/**
 * 国际化语言的工具
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class LanguageUtil {
	private static Logger LOG = Logger.getLogger(LanguageUtil.class);
	/** 模板文件夹名称 */
	private static final String LANGUAGE = "config/" + Constant.LANGUAGE;

	/**
	 * 查看是否存在模板文件夹,如果不存在则创建
	 */
	public static void existsTemplate() {
		if (Files.notExists(Paths.get(LANGUAGE))) {
			LOG.debug("执行创建模板...");
			try {
				Files.createDirectory(Paths.get(LANGUAGE));
				URL resource = Thread.currentThread().getContextClassLoader().getResource(LANGUAGE);
				URLConnection conn = resource.openConnection();
				if (conn instanceof JarURLConnection) {
					LOG.debug("jar");
					jarCreateTemplate((JarURLConnection) conn);
				} else {
					URI uri = resource.toURI();
					Files.list(Paths.get(uri)).forEach(x -> {
						Path out = Paths.get(LANGUAGE, x.getFileName().toString());
						try {
							Files.copy(x, out);
						} catch (IOException e) {
							LOG.debug("创建模板-->失败:" + e);
							AlertUtil.showErrorAlert("创建模板-->失败:" + e);
						}
					});
				}

			} catch (Exception e) {
				LOG.debug("创建模板-->失败:" + e);
				AlertUtil.showErrorAlert("创建模板-->失败:" + e);
			}

		}
	}

	/**
	 * 将jar里面的文件复制到模板文件夹
	 * 
	 * @param jarConn
	 * @return
	 * @throws IOException
	 */
	public static void jarCreateTemplate(JarURLConnection jarConn) throws IOException {
		try (JarFile jarFile = jarConn.getJarFile()) {
			Enumeration<JarEntry> entrys = jarFile.entries();
			while (entrys.hasMoreElements()) {
				JarEntry entry = entrys.nextElement();
				if (entry.getName().startsWith(jarConn.getEntryName()) && !entry.getName().endsWith("/")) {
					String fileName = entry.getName().replace(LANGUAGE + "/", "");
					InputStream inpt = Thread.currentThread().getContextClassLoader().getResourceAsStream(entry.getName());
					Files.copy(inpt, Paths.get(LANGUAGE, fileName));
				}
			}
		}

	}

}
