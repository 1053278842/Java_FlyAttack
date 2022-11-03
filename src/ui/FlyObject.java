package ui;

import java.awt.image.BufferedImage;
/*
 * 飞行物父类
 * 方便继承定义(抽离相同特点（需要定义同样的特点）)
 * 提高代码复用性
 */
public class FlyObject {
	BufferedImage img;			//图片
	int x,y;						//位置
	int w,h;					//大小	
	int sp;//速度
}
