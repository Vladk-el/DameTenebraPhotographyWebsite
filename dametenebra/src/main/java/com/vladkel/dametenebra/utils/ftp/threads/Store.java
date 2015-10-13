package com.vladkel.dametenebra.utils.ftp.threads;

import java.io.File;
import java.io.IOException;

import com.vladkel.dametenebra.utils.file.FileUtils;
import com.vladkel.dametenebra.utils.ftp.FtpClient;

public class Store implements Runnable{
	
	FtpClient client;
	File src;
	File file;
	String repertory;
	Thread next;

	public Store(FtpClient c, File s, File f, String r){
		client = c;
		src = s;
		file = f;
		repertory = r;
	}
	
	public Store(FtpClient c, File s, File f, String r, Thread t){
		client = c;
		src = s;
		file = f;
		repertory = r;
		next = t;
	}
	
	public void run() {
		
		try {
			FileUtils.copyFile(src, file);
		} catch (IOException e) {
			System.out.println("Un problème est survenu dans la copie du fichier. Veuillez réessayer.");
			e.printStackTrace();
		}
		
		client.store(file.getName(), repertory, true);
		FileUtils.deleteFile(file);
		if(next != null)
			next.start();
		
	}


}
