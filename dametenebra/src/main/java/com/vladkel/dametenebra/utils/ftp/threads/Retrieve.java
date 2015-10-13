package com.vladkel.dametenebra.utils.ftp.threads;

import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

import com.vladkel.dametenebra.ihm.IhmPhoto;
import com.vladkel.dametenebra.utils.file.FileUtils;
import com.vladkel.dametenebra.utils.ftp.FtpClient;

public class Retrieve implements Runnable{

	FtpClient client;
	File file;
	String repertory;
	Thread next;
	IhmPhoto ihm;
	
	FileUtils utils;
	
	public Retrieve(FtpClient c, File f, String r){
		client = c;
		file = f;
		repertory = r;
	}
	
	public Retrieve(FtpClient c, File f, String r, IhmPhoto i){
		client = c;
		file = f;
		repertory = r;
		ihm = i;
	}
	
	public Retrieve(FtpClient c, File f, String r, Thread t){
		client = c;
		file = f;
		repertory = r;
		next = t;
	}
	
	public void run() {
		
		utils = new FileUtils();
		
		client.retrieve(file.getName(), repertory, true);
		try {
			File temp = new File(file.getName());
			utils.copyFile(temp, file);
			utils.deleteFile(temp);
		} catch (IOException e) {
			System.out.println("Un problème est survenu dans la copie du fichier. Veuillez réessayer.");
			e.printStackTrace();
		}
		
		if(next != null){
			next.start();
		}
		else{
			ihm.setIcon(new ImageIcon("data/img/mini/" + file.getName()));
			System.out.println("New icon : " + "data/img/mini/" + file.getName());
		}

	}
	
}

