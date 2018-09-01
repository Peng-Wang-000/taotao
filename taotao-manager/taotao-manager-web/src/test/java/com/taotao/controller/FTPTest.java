package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.common.utils.FtpUtil;

public class FTPTest {

	public void testFTPClient() throws Exception{
		//创建一个FTPClient客户端
		FTPClient ftpClient = new FTPClient();
		//登录指定主机
		ftpClient.connect("172.16.27.10");
		ftpClient.login("ftpuser", "ftpuser");
		String pathname = "/home/ftpuser/test/images";
		//设置文件路径
		ftpClient.changeWorkingDirectory(pathname );
		//默认上传文件的格式是文本，由于上传的是图片，需要修改文件格式
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		//获得本地文件输入流
		InputStream inputStream = new FileInputStream(new File("F:\\myself\\Pic\\test.jpg"));
		//保存上传文件
		ftpClient.storeFile("hello.jpg", inputStream );
		//关闭链接
		ftpClient.logout();
		
	}
	/**
	 * 采用FTPUtil来操作
	 * @throws Exception
	 */
	public void testFTPUtil() throws Exception{
		String host = "172.16.27.10";
		int port = 21;
		String username = "ftpuser";
		String password = "ftpuser";
		String basePath = "/home/ftpuser";
		String filePath = "/2017/12/12";
		String filename = "hello.jpg";
		//获得本地文件输入流
		InputStream input = new FileInputStream(new File("F:\\myself\\Pic\\test.jpg"));
		FtpUtil.uploadFile(host, port, username, password, basePath, filePath, filename, input);
		
	}
}
