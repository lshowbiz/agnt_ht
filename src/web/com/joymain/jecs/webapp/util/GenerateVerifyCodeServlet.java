package com.joymain.jecs.webapp.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Title: 产生随机验证码的Servlet
 * Description:
 * Copyright: Copyright (c) 2006
 * Company: joymain.jecs
 * @author Asii
 * @version 1.0
 */
public class GenerateVerifyCodeServlet extends HttpServlet {
	private static final String CONTENT_TYPE = "image/jpeg";
	private List<int[]> canUsedColors;
	// Initialize global variables
	public void init() throws ServletException {
		this.canUsedColors=new ArrayList<int[]>();
		
		this.canUsedColors.add(new int[]{255,128,128});
		this.canUsedColors.add(new int[]{255,0,0});
		this.canUsedColors.add(new int[]{128,64,64});
		this.canUsedColors.add(new int[]{128,0,0});
		this.canUsedColors.add(new int[]{64,0,0});
		this.canUsedColors.add(new int[]{0,0,0});
		this.canUsedColors.add(new int[]{255,128,64});
		this.canUsedColors.add(new int[]{255,128,0});
		this.canUsedColors.add(new int[]{128,64,0});
		this.canUsedColors.add(new int[]{128,128,0});
		this.canUsedColors.add(new int[]{0,255,0});
		this.canUsedColors.add(new int[]{0,128,0});
		this.canUsedColors.add(new int[]{0,128,128});
		this.canUsedColors.add(new int[]{0,128,64});
		this.canUsedColors.add(new int[]{0,64,64});
		this.canUsedColors.add(new int[]{0,64,128});
		this.canUsedColors.add(new int[]{0,128,255});
		this.canUsedColors.add(new int[]{0,128,192});
		this.canUsedColors.add(new int[]{128,128,255});
		this.canUsedColors.add(new int[]{255,128,255});
		this.canUsedColors.add(new int[]{255,0,255});
		this.canUsedColors.add(new int[]{255,0,128});
		this.canUsedColors.add(new int[]{128,0,255});
		this.canUsedColors.add(new int[]{64,0,128});
	}

	// Process the HTTP Get request
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("validate is start");
		response.setContentType(CONTENT_TYPE);
		int iWidth = 80, iHeight = 27;
		BufferedImage image = new BufferedImage(iWidth, iHeight, BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 设定背景色
		g.setColor(Color.white);
		g.fillRect(0, 0, iWidth, iHeight);
		// 画边框
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, iWidth - 1, iHeight - 1);
		// 取随机产生的认证码(4位数字)
		String rand = String.valueOf(Math.random() * 1000000);  
//		String rand = "1111";//默认修改为1111
		switch (rand.length()) {
			case 1:
				rand = "000" + rand;
				break;
			case 2:
				rand = "00" + rand;
				break;
			case 3:
				rand = "0" + rand;
				break;
			default:
				rand = rand.substring(0, 4);
				break;
		}

		Random random = new Random();
		// 设定背景色
		// g.setColor(new Color(201 + random.nextInt(50), 201 +
		// random.nextInt(50), 201 + random.nextInt(50)));

		// 将认证码存入SESSION
		request.getSession().setAttribute("verifyCode", rand);
		
		// 随机产生20个干扰点,使图象中的认证码不易被其它程序探测到
		//
		for (int iIndex = 0; iIndex < 20; iIndex++) {
			int x = random.nextInt(iWidth);
			int y = random.nextInt(iHeight);
			g.setColor(getRandColor(100, 200));
			g.drawLine(x, y, x, y);
		}

		for (int iIndex = 0; iIndex < 20; iIndex++) {
			int x = random.nextInt(iWidth);
			int y = random.nextInt(iHeight);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.setColor(getRandColor(100, 200));
			g.drawLine(x, y, x + xl, y + yl);
		}
		
		// 将认证码显示到图象中
		g.setColor(getRandColor(0, 100));
		g.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 25));
		for (int i = 1; i <= 4; i++) {
			String singleRand = rand.substring(i - 1, i);
			int[] currentColor=canUsedColors.get(random.nextInt(canUsedColors.size()));
			g.setColor(new Color(currentColor[0], currentColor[1], currentColor[2]));
			g.drawString(singleRand, 13 * (i - 1) + 12, 22);
		}
		//g.drawString(rand, 10, 15);
		
		// 图象生效
		g.dispose();
		// 输出图象到页面
		ImageIO.write(image, "JPEG", response.getOutputStream());
		// System.out.println("validate is end");
	}

	Color getRandColor(int fc, int bc) { // 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	// Clean up resources
	public void destroy() {
	}
}
