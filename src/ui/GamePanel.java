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


//��Ϸ�������pannel��
/**
 * java�е���Ϸ��壺JPanel
 * �Զ�����Ϸ���
 * 1.�̳�JPanel
 * 2.���췽������ʼ������
 * @author Administrator
 * ��ͼ����
 * 1.�����ж���ͼƬȡ��
 * 2.���췽���е��ù��߻�ͼ
 * 3.�ڻ�ͼ����paint�У���ͼ
 */

public class GamePanel extends JPanel{
	//����
	int score;
	boolean gameover=false;
	//����hero����������
	Hero hero=new Hero();
	//����Empty
	//Empty ep=new Empty();
	//���屳��ͼ
	BufferedImage bg;
	//������궨��
	int mx,my,i;
	Point mp,lmp;
	
	//Emptylist���϶���	
	/*
	 * ʹ�ü��ϵ�ԭ�򡪡�
	 * 1���л��������޷�ȷ�����޷�ʹ�����飩
	 */
	List<Empty> eps=new ArrayList<Empty>();
	//Hero���ӵ�
	List<Fire> fs =new ArrayList<Fire>();
	/*
	 * ��������GameStart��������
	 * �̵߳Ĵ�����ʽ��
	 * new Thread(){public void run(){�߳�����������}}.start();
	 */
	public void action() {
		new Thread() {
			public void run() {
				while(true) {
					if(!gameover) {
						EmptyEnter();//���ֵ���
						EmptyMove();
						shoot();//�����ӵ�
						fireMove();
						shootEp();//�ж��Ƿ�򵽵л�
						hero.moveToMouse(mx, my);//��������λ�õ�λ������ƶ�Hero
						//Hero����ײ
						hitHero();
						}
						//�����Ϣ
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					repaint();//ˢ��
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
	//�����ӵ�
	protected void shootEp() {
			for(int i=0;i<fs.size();i++) {
				Fire f=fs.get(i);
				//�ж��Ƿ����
				bang(f);
			}
	}
	//����Empty
	//�ж��Ƿ���ײ
	private void bang(Fire f) {
		for(int i=0;i<eps.size();i++) {
			Empty e=eps.get(i);
			if(e.shootBy(f)) {
				//1.�л���ʧ���ӵ���ʧ
				e.hp-=1;
				if(e.hp==0) {
					eps.remove(e);
					fs.remove(f);
					score+=100;
				}
			}
		}
	}
	//�ӵ��ƶ�
	protected void fireMove() {
		for(int i=0;i<fs.size();i++) {
			//��ȡ
			Fire e =fs.get(i);
			e.fireMove();
			if(e.y+e.h<=0) {
				fs.remove(e);
			}
		}
	}
	//�Զ������ӵ�
	int f_count;
	protected void shoot() {
		f_count++;
		if(f_count%20==0) {
			f_count=0;
			Fire fire=new Fire(hero.x+hero.w/2,hero.y-5,2);
			fs.add(fire);
			//��
			Fire fire1=new Fire(hero.x+10,hero.y,1);
			fs.add(fire1);
			//��
			Fire fire2=new Fire(hero.x+45+hero.w/2,hero.y,3);
			fs.add(fire2);
		}
	}
	//�л��ƶ�����
	protected void EmptyMove() { 
		for(int i=0;i<eps.size();i++) {
			//��ȡ
			Empty e =eps.get(i);
//			e.y+=3;
			e.epMove();
			if(e.y>=768) {
				//index--;
				eps.remove(e);
			}
		}
	}
	//Empty���� 
	//������л��ƶ�Ƶ����ͬ��������count�������
	int ep_count,index;//��¼ִ�д���
	protected void EmptyEnter() {
		ep_count++;
		if(ep_count%20==0) {
			ep_count=0;
			Empty e=new Empty();
			eps.add(e);
			//index++;
			//System.out.println("Empty"+index);
		}
		
		//���뼯��
		
	}

	//�����������������������췽��������������
	public GamePanel(GameFrame frame) {
		//���ñ���
		setBackground(Color.black);
		bg=App.getImg("/img/GamePanel3.jpg");
		//ʹ����������
		/*
		 * 1.�������������
		 * 2.ȷ����Ҫ���������¼�
		 * 		mouseMoved();
		 * 		mouseCliked()����
		 * 		mousePressed()����
		 * 		mouseEntered()�������
		 * 		mouseExited()�Ƴ�����
		 * 3.�����������뵽��������
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
				//MouseEvent �������¼�����ؼ�¼
				//Hero�����ͬ�������
				mx=e.getX()-hero.w/2;
				my=e.getY()-hero.h/2;
				//System.out.println("��");
				mp=getMousePosition();
				//ˢ�½���
				//���µ���paint
//				hero.moveToMouse(mx, my);
			}
		};
		//����3����ӵ�������
		addMouseListener(adapter);
		addMouseMotionListener(adapter);

		/*
		 * ���̼���
		 * 1.�������̼�����
		 * 2.ȷ����Ҫ�������¼�
		 * 3.�����������뵽��������(����ļ��̼�����)
		 */
		KeyAdapter kd=new KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent e) {
				//���¼���ʱ�����ᴥ��
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
		frame.addKeyListener(kd);//��Ҫ���봰����
	}

	/*
	 * 
	 * ר�û�ͼ����
	 * Graphics g��ʾ����
	 * paint:paint alt+? enter
	 */
	//��ͼ����˳���Ȼ��ᱻ�󻭸���
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(bg, 0, 0, null);//����
		for(i=0;i<eps.size();i++) {
			Empty ep=eps.get(i);
			g.drawImage(ep.img, ep.x, ep.y, ep.w,ep.h,null);
		}
		for(i=0;i<fs.size();i++) {
			Fire fire=fs.get(i);
			g.drawImage(fire.img, fire.x, fire.y, fire.w,fire.h,null);
		}
		g.drawImage(hero.img, hero.x, hero.y,hero.w,hero.h,null);//ע�⣺�˴���hero��Hero��Ķ���
		//����������������U I������������
		//Ѫ��
		for(int i=0;i<hero.hp;i++) {
			g.drawImage(hero.img,300+i*((int)(hero.w/1.5/1.5)+10), 15,(int)(hero.w/1.5/1.5),(int)(hero.h/1.5/1.5),null);
		}
	
		//������
		g.setColor(Color.white);
		g.setFont(new Font("����",Font.BOLD,20));
		g.drawString("������"+score, 20, 30);
		
		if(gameover) {
			g.setColor(Color.red);
			g.setFont(new Font("����",Font.BOLD,40));
			g.drawString("G a m e  O v e r",256-150, 200);
			g.setColor(Color.yellow);
			g.setFont(new Font("����",Font.BOLD,15));
			g.drawString("�����������¿�ʼ",256-140, 600);
		}
	}
}
