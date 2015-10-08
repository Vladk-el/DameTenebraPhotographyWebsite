package com.vladkel.dametenebra.utils.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.MimetypesFileTypeMap;

/**
 * @author eliott
 *
 */
public class FileUtils {

	public static void copyFile(File src, File dest) throws IOException {

		InputStream in = new BufferedInputStream(new FileInputStream(src));
		OutputStream out = new BufferedOutputStream(new FileOutputStream(dest));
		byte[] buf = new byte[4096];
		int n;
		System.out.println("File copying . . .");
		while ((n = in.read(buf, 0, buf.length)) > 0)
			out.write(buf, 0, n);

		in.close();
		out.close();
		System.out.println("Done.");
	}

	@Deprecated
	public static void copyFile(String src, String dest) {

		try {
			Runtime.getRuntime().exec("cmd /c start COPY " + src + " " + dest);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void deleteFile(File src) {
		src.delete();
	}

	public static boolean isAnImage(File file) {
		String mimetype = new MimetypesFileTypeMap().getContentType(file);
		String type = mimetype.split("/")[0];
		if (type.equals("image"))
			return true;
		return false;
	}

}
