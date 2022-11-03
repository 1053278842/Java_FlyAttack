package ui;

import javax.swing.JFrame;
/**
 * 窗体类：JFrame
 * @author Administrator
 *自自定义窗体：
 *1.继承JFtame类
 *2.构造方法，初始化属性
 */
public class GameFrame extends JFrame {
	int w,h;
	public GameFrame() {//构造方法
	//方法来源于父类JFrame的方法
		//标题
		setTitle("The War III-Hunter Plane");
		//大小 setSize(长，高)；
		w=512;
		h=768;
		setSize(w,h);
		//窗体位置 居中显示、
			// Alt + ? 代码提示
		setLocationRelativeTo(null);//针对屏幕某一点居中显示，null指左上角的点（无针对点）
		//固定边界	设置——重新——大小——可能性：是否可以重新更改（边界）setLocationRelativeTo(null);
		setResizable(false);
		//关闭选项 Q：已有关闭为何还要关闭？ A:那是界面关闭，实际程序仍在运行
		//设置-默认-关闭-设置：	窗体类。退出窗体  			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		//调用窗体的构造方法 制作窗体
		//3是对象，2是对象的名字
		//  1		2			3		
		GameFrame frame= new GameFrame();
		//调用面板构造方法 制作面板
		GamePanel panel= new GamePanel(frame);
		//将面板加入到窗体中
		//————GameStart
		panel.action();
		//显示窗体
		frame.setVisible(true);
		frame.add(panel);
	}
	
	
}
