package ui;

/*
 * ��������
 * ���Զ�Ӧ�ص㣬������Ӧ��Ϊ
 */
public class Hero extends FlyObject {
	int sp;
	int hp;
	public Hero() {
		img=App.getImg("/img/Hero.png");//����ͼƬ
		x=200;
		y=500;
		//ʹ��ͼƬ��С
		w=(int) (img.getWidth()*1.5f);
		h=(int)(img.getHeight()*1.5f);
		sp=8;
		hp=3;
	}
	
	//��hero�ƶ�����������mx,my
	public void moveToMouse(int mx,int my) {

		if(Math.abs(mx-x)>=sp) {
			if(mx-x>=0) {
				x+=sp;
			}else 
				x-=sp;
		}
		if(Math.abs(my-y)>=sp+4) {
			if(my-y<=0)
				y-=sp-4;
			else 
				y+=sp+4;
		}
	}

	
}
