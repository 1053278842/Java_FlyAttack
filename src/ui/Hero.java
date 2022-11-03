package ui;

/*
 * 操作机类
 * 属性对应特点，方法对应行为
 */
public class Hero extends FlyObject {
	int sp;
	int hp;
	public Hero() {
		img=App.getImg("/img/Hero.png");//创建图片
		x=200;
		y=500;
		//使用图片大小
		w=(int) (img.getWidth()*1.5f);
		h=(int)(img.getHeight()*1.5f);
		sp=8;
		hp=3;
	}
	
	//让hero移动到鼠标的坐标mx,my
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
