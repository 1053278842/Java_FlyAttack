package ui;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * 1读取工具的类方法
 * 1调用工具
 * 2方便重复加载图片这个功能
 */
public class App {
	/*
	 * BufferedImage Java 中用来表示图片的类
	 * path是图片的路径
	 */
	public static BufferedImage getImg(String path) {
		//加载图片
		//javaIO是输送数据的通道
		//App.class会找到APP类的路径
		try {
			BufferedImage img =ImageIO.read(App.class.getResource(path));
			//如果找到 返回图片
			//System.out.println("找到背景图！");
			return img;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//如果找不到 就会返回找不到的原因
			//System.out.println("没找到背景图！");
			e.printStackTrace();//报错	
			return null;
		}
		
	}
}
