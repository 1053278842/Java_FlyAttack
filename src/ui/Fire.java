package ui;

public class Fire extends FlyObject{
	int dir=2;//子弹的方向
	public Fire(int hx,int hy,int dir) {//Hero子弹
		img=App.getImg("/img/Chess_b.png");
//		w=img.getWidth();
//		h=img.getHeight()
		w=20;
		h=20;
		x=hx-w/2;
		y=hy;
		this.dir=dir;
	}
	public void fireMove() {
		if(dir==1) { 
			x-=1;
			y-=8;
		}else if(dir==2) {
			y-=8;
		}else if(dir==3) {
			x+=1;
			y-=8;
		}
		
		
	}
}
