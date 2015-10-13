package com.vladkel.dametenebra.utils.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import com.vladkel.dametenebra.utils.ftp.progressbar.ProgressBar;

/**
 * @author eliott
 *
 */
public class FtpClient {

	private FTPClient client;
	private String url;
	private String login;
	private String mdp;

	public FtpClient(String u, String l, String m) {
		url = u;
		login = l;
		mdp = m;
		client = new FTPClient();
	}

	public boolean connect() {

		boolean log = false;

		try {
			client.connect(url);
			log = client.login(login, mdp);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (log)
			System.out.println("########## Vous êtes connecté ##########");
		else
			System.out.println("Vous n'êtes pas connecté");

		return log;
	}

	public boolean disconnect() {

		try {
			client.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (client.isConnected() == false) {
			System.out
					.println("########## Vous n'êtes plus connecté ##########");
			return true;
		}

		return false;
	}

	public void testServer() {

		connect();
		disconnect();
	}

	public boolean store(String file) {

		FileInputStream fis = null;
		boolean done = false;

		if (client.isConnected() == false)
			this.connect();

		client.enterLocalPassiveMode();

		try {
			fis = new FileInputStream(file);
			client.setFileType(FTPClient.BINARY_FILE_TYPE);
			done = client.storeFile(file, fis);
			System.out.println("Storing " + file + ", please wait . . . ");
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		disconnect();

		if (done) {
			System.out.println("Storing completed.");
			return true;
		}

		System.out.println("A problem is came, please try again.");
		return false;
	}

	public boolean store(String file, boolean needABar) {
		FileInputStream fis = null;
		OutputStream os = null;
		File myFile = new File(file);
		System.out.println(myFile.length());

		if (client.isConnected() == false)
			this.connect();

		client.enterLocalPassiveMode();
		System.out.println("Storing " + myFile.getName() + ", please wait . . . ");

		try {
			fis = new FileInputStream(file);
			ProgressBar pb = new ProgressBar(file);
			client.setFileType(FTPClient.BINARY_FILE_TYPE);
			os = client.storeFileStream(file);
			byte[] buffer = new byte[1024];
			int total = 0;
			int taille_lue = 0;

			while ((taille_lue = fis.read(buffer)) > 0) {
				total += taille_lue;
				os.write(buffer, 0, taille_lue);
				pb.progressbar.setValue((int) ((total * 100) / myFile.length()));
			}
			
			os.flush();
			os.close();
			fis.close();
			pb.dispose();
			disconnect();
			System.out.println("Storing completed.");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("A problem is came, please try again.");
		return false;
	}

	public boolean store(String file, String directory, boolean needABar) {

		if (client.isConnected() == false)
			this.connect();

		if (directory != null) {
			try {
				client.changeWorkingDirectory(directory);
				System.out.println("FTP directory now on "
						+ client.printWorkingDirectory());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (needABar)
			return store(file, true);
		
		return store(file);
	}

	public boolean retrieve(String file) {

		FileOutputStream fos = null;
		boolean done = false;

		if (client.isConnected() == false)
			this.connect();

		client.enterLocalActiveMode();

		try {
			fos = new FileOutputStream(file);
			client.setFileType(FTPClient.BINARY_FILE_TYPE);
			done = client.retrieveFile(file, fos);
			System.out.println("Retrieving " + file + ", please wait . . . ");
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		disconnect();

		if (done) {
			System.out.println("Retrieving completed.");
			return done;
		}

		System.out.println("A problem is came, please try again.");
		return false;
	}

	public boolean retrieve(String file, boolean needABar) {

		FileOutputStream fos = null;
		InputStream fis = null;

		if (client.isConnected() == false)
			this.connect();

		client.enterLocalPassiveMode();
		System.out.println("Retrieving " + file + ", please wait . . . ");

		try {
			FTPFile remotedFile = client.mlistFile(file);
			long remoteFileSize = remotedFile.getSize();
			ProgressBar pb = new ProgressBar(file);
			fos = new FileOutputStream(file);
			client.setFileType(FTPClient.BINARY_FILE_TYPE);

			fis = client.retrieveFileStream(file);

			if (fis == null) {
				System.out.println("A problem is came, retrieving not completed. Please try again.");
				System.out.println(client.getReplyString());
				fos.flush();
				fos.close();
				pb.dispose();
				disconnect();
				return false;
			}

			byte[] buffer = new byte[1024];
			int total = 1;
			int taille_lue = 0;

			while ((taille_lue = fis.read(buffer)) > 0) {
				total += taille_lue;
				fos.write(buffer, 0, taille_lue);
				pb.progressbar.setValue((int) ((total * 100) / remoteFileSize));
			}

			fis.close();
			fos.flush();
			fos.close();
			pb.dispose();
			disconnect();
			System.out.println("Retrieving completed.");
			return true;

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("A problem is came, please try again.");
		return false;
	}

	public boolean retrieve(String file, String directory, boolean needABar) {

		if (client.isConnected() == false)
			this.connect();

		if (directory != null) {
			try {
				client.changeWorkingDirectory(directory);
				System.out.println("FTP directory now on "
						+ client.printWorkingDirectory());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (needABar)
			return retrieve(file, true);
		return retrieve(file);
	}

	public void listRepertory() {

		if (client.isConnected() == false)
			this.connect();

		client.enterLocalPassiveMode();

		FTPFile[] files = null;

		try {
			files = client.listFiles();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (FTPFile file : files)
			System.out.println(file.getName() + "  " + file.getType() + "  "
					+ file.getSize());

		disconnect();
	}

	public void listRepertory(String repertory) {

		if (client.isConnected() == false)
			this.connect();

		if (repertory != null) {
			try {
				client.changeWorkingDirectory(repertory);
				System.out.println("FTP directory now on "
						+ client.printWorkingDirectory());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		listRepertory();
	}

}
