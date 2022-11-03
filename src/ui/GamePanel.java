package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


//游戏组件均在pannel中
/**
 * java中的游戏面板：JPanel
 * 自定义游戏面板
 * 1.继承JPanel
 * 2.构造方法，初始化属性
 * @author Administrator
 * 画图步骤
 * 1.在类中定义图片取名
 * 2.构造方法中调用工具画图
 * 3.在画图方法paint中，画图
 */

public class GamePanel extends JPanel{
	//分数
	int score;
	boolean gameover=false;
	//调用hero方法，创建
	Hero hero=new Hero();
	//创建Empty
	//Empty ep=new Empty();
	//定义背景图
	BufferedImage bg;
	//鼠标坐标定义
	int mx,my,i;
	Point mp,lmp;
	
	//Emptylist集合定义	
	/*
	 * 使用集合的原因——
	 * 1、敌机的数量无法确定（无法使用数组）
	 */
	List<Empty> eps=new ArrayList<Empty>();
	//Hero的子弹
	List<Fire> fs =new ArrayList<Fire>();
	/*
	 * ————GameStart————
	 * 线程的创建格式：
	 * new Thread(){public void run(){线程所做的事情}}.start();
	 */
	public void action() {
		new Thread() {
			public void run() {
				while(true) {
					if(!gameover) {
						EmptyEnter();//出现敌人
						EmptyMove();
						shoot();//发射子弹
						fireMove();
						shootEp();//判断是否打到敌机
						hero.moveToMouse(mx, my);//根据鼠标的位置单位距离的移动Hero
						//Hero的碰撞
						hitHero();
						}
						//间隔休息
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					repaint();//刷新
				}
			}
		}.start();
	}
	protected void hitHero() {
		for(int i=0;i<eps.size();i++) {
			Empty e=eps.get(i);
			if(e.hitH(hero)) {
				eps.remove(e);
				score+=100;
				hero.hp-=1;
				if(hero.hp==0) {
					gameover=true;
				}
			}
		}
	}
	//遍历子弹
	protected void shootEp() {
			for(int i=0;i<fs.size();i++) {
				Fire f=fs.get(i);
				//判断是否击中
				bang(f);
			}
	}
	//遍历Empty
	//判断是否碰撞
	private void bang(Fire f) {
		for(int i=0;i<eps.size();i++) {
			Empty e=eps.get(i);
			if(e.shootBy(f)) {
				//1.敌机消失，子弹消失
				e.hp-=1;
				if(e.hp==0) {
					eps.remove(e);
					fs.remove(f);
					score+=100;
				}
			}
		}
	}
	//子弹移动
	protected void fireMove() {
		for(int i=0;i<fs.size();i++) {
			//获取
			Fire e =fs.get(i);
			e.fireMove();
			if(e.y+e.h<=0) {
				fs.remove(e);
			}
		}
	}
	//自动发射子弹
	int f_count;
	protected void shoot() {
		f_count++;
		if(f_count%20==0) {
			f_count=0;
			Fire fire=new Fire(hero.x+hero.w/2,hero.y-5,2);
			fs.add(fire);
			//左
			Fire fire1=new Fire(hero.x+10,hero.y,1);
			fs.add(fire1);
			//右
			Fire fire2=new Fire(hero.x+45+hero.w/2,hero.y,3);
			fs.add(fire2);
		}
	}
	//敌机移动方法
	protected void EmptyMove() { 
		for(int i=0;i<eps.size();i++) {
			//获取
			Empty e =eps.get(i);
//			e.y+=3;
			e.epMove();
			if(e.y>=768) {
				//index--;
				eps.remove(e);
			}
		}
	}
	//Empty进场 
	//进场与敌机移动频率相同的问题用count计数解决
	int ep_count,index;//记录执行次数
	protected void EmptyEnter() {
		ep_count++;
		if(ep_count%20==0) {
			ep_count=0;
			Empty e=new Empty();
			eps.add(e);
			//index++;
			//System.out.println("Empty"+index);
		}
		
		//加入集合
		
	}

	//——————————构造方法——————
	public GamePanel(GameFrame frame) {
		//设置背景
		setBackground(Color.black);
		bg=App.getImg("/img/GamePanel3.jpg");
		//使用鼠标监听器
		/*
		 * 1.创建鼠标适配器
		 * 2.确定需要监听鼠标的事件
		 * 		mouseMoved();
		 * 		mouseCliked()单击
		 * 		mousePressed()按下
		 * 		mouseEntered()进入界面
		 * 		mouseExited()移出界面
		 * 3.将适配器加入到监听器中
		 */
		MouseAdapter adapter =new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(gameover) {
					hero=new Hero();
					gameover=false;
					score=0;
					eps.clear();
					fs.clear();
				}
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				//MouseEvent 是鼠标的事件，监控记录
				//Hero坐标等同鼠标坐标
				mx=e.getX()-hero.w/2;
				my=e.getY()-hero.h/2;
				//System.out.println("额");
				mp=getMousePosition();
				//刷新界面
				//重新调用paint
//				hero.moveToMouse(mx, my);
			}
		};
		//步骤3，添加到监听器
		addMouseListener(adapter);
		addMouseMotionListener(adapter);

		/*
		 * 键盘监听
		 * 1.创建键盘监听器
		 * 2.确定所要监听的事件
		 * 3.将适配器加入到监听器中(窗体的键盘监听器)
		 */
		KeyAdapter kd=new KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent e) {
				//按下键盘时，将会触发
				int keyCode=e.getKeyCode();
				if(keyCode==37) {//left
					hero.x-=10;
				}
				if(keyCode==38) {//up
					hero.y-=10;
				}
				if(keyCode==KeyEvent.VK_RIGHT) {//right
					hero.x+=10;
				}
				if(keyCode==KeyEvent.VK_DOWN) {//down
					hero.y+=10;
				}
				repaint();
			}
			
		};
		//addKeyListener(kd);
		frame.addKeyListener(kd);//需要加入窗体中
	}

	/*
	 * 
	 * 专用画图方法
	 * Graphics g表示画笔
	 * paint:paint alt+? enter
	 */
	//画图具有顺序，先画会被后画覆盖
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(bg, 0, 0, null);//背景
		for(i=0;i<eps.size();i++) {
			Empty ep=eps.get(i);
			g.drawImage(ep.img, ep.x, ep.y, ep.w,ep.h,null);
		}
		for(i=0;i<fs.size();i++) {
			Fire fire=fs.get(i);
			g.drawImage(fire.img, fire.x, fire.y, fire.w,fire.h,null);
		}
		g.drawImage(hero.img, hero.x, hero.y,hero.w,hero.h,null);//注意：此处的hero是Hero类的对象
		//————————U I——————
		//血量
		for(int i=0;i<hero.hp;i++) {
			g.drawImage(hero.img,300+i*((int)(hero.w/1.5/1.5)+10), 15,(int)(hero.w/1.5/1.5),(int)(hero.h/1.5/1.5),null);
		}
	
		//画分数
		g.setColor(Color.white);
		g.setFont(new Font("楷体",Font.BOLD,20));
		g.drawString("分数："+score, 20, 30);
		
		if(gameover) {
			g.setColor(Color.red);
			g.setFont(new Font("黑体",Font.BOLD,40));
			g.drawString("G a m e  O v e r",256-150, 200);
			g.setColor(Color.yellow);
			g.setFont(new Font("黑体",Font.BOLD,15));
			g.drawString("点击任意键重新开始",256-140, 600);
		}
	}
}
