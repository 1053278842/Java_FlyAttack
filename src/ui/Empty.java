package ui;

public class Empty extends FlyObject {
	int hp;
	public Empty() {
		img=App.getImg("/img/Empty.png");
		w=img.getWidth();
		h=img.getHeight();
		x=(int)((512-w)*Math.random());//¡¾0,num) 
		y=-h ;
		sp=(int) (5*Math.random()+5);
		hp=1;
	}
	public void epMove() {
		y+=sp;
	}
	public boolean shootBy(Fire f) {
		boolean bl=x<=f.x+f.w&&
							 x>=f.x-w&&
							 y<=f.y+f.h&&
							 y>=f.y-h;
		return bl;
	}
	public boolean hitH(Hero f) {
		boolean bl=x<=f.x+f.w&&
				 x>=f.x-w&&
				 y<=f.y+f.h&&
				 y>=f.y-h;
				 return bl;
	}
}
