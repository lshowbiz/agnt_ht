package com.joymain.jecs.util.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpClientUtil {

	//上传文件到FTP服务器
	public static boolean uploadFile(String url,// FTP服务器hostname
			int port,// FTP服务器端口
			String username, // FTP登录账号
			String password, // FTP登录密码
			String path, // FTP服务器保存目录
			String filename, // 上传到FTP服务器上的文件名
			InputStream input // 输入流
	) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		ftp.setControlEncoding("utf-8");
		try {
			int reply;
			ftp.connect(url, port);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftp.makeDirectory(path);
			ftp.changeWorkingDirectory(path);
			ftp.storeFile(filename, input);
			input.close();
			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}
	
	//从FTP服务器下载文件 
	public static boolean downFile(
			String url, // FTP服务器hostname
			int port,// FTP服务器端口
			String username, // FTP登录账号
			String password, // FTP登录密码
			String remotePath,// FTP服务器上的相对路径
			String fileName,// 要下载的文件名
			String localPath// 下载后保存到本地的路径

	) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		ftp.setControlEncoding("utf-8");
		try {
			int reply;
			ftp.connect(url, port);

			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}

			ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			FTPFile[] fs = ftp.listFiles();
			for (FTPFile ff : fs) {

				if (ff.getName().equals(fileName)) {
					
					System.out.println("="+ff.getName());
					File localFile = new File(localPath + "/" + ff.getName());
					OutputStream is = new FileOutputStream(localFile);
					ftp.retrieveFile(ff.getName(), is);
					is.close();
				}
			}
			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {

				}
			}
		}
		return success;
	}
	
	//从FTP服务器删除文件 
	public static boolean delFile(
			String url, // FTP服务器hostname
			int port,// FTP服务器端口
			String username, // FTP登录账号
			String password, // FTP登录密码
			String remotePath,// FTP服务器上的相对路径
			String fileName// 文件名
	) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		ftp.setControlEncoding("utf-8");
		try {
			int reply;
			ftp.connect(url, port);

			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}

			ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			ftp.deleteFile(fileName);
			
			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {

				}
			}
		}
		return success;
	}

	// 测试例子：将本地文件上传到FTP服务器上
	public void testUpLoadFromDisk() {

		try {
			FileInputStream in = new FileInputStream(new File("D:/test.txt"));
			boolean flag = uploadFile("127.0.0.1", 21, "administrator",
					"zyuc2011", "test", "test.txt", in);
			System.out.println(flag);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}

	// 测试例子：将FTP服务器上文件下载到本地
	public void testDownFile() {

		try {
			boolean flag = downFile("127.0.0.1", 21, "administrator",
					"zyuc2011", "test", "test.txt", "D:/");
			System.out.println(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
