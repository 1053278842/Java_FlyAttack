package ui;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * 1��ȡ���ߵ��෽��
 * 1���ù���
 * 2�����ظ�����ͼƬ�������
 */
public class App {
	/*
	 * BufferedImage Java ��������ʾͼƬ����
	 * path��ͼƬ��·��
	 */
	public static BufferedImage getImg(String path) {
		//����ͼƬ
		//javaIO���������ݵ�ͨ��
		//App.class���ҵ�APP���·��
		try {
			BufferedImage img =ImageIO.read(App.class.getResource(path));
			//����ҵ� ����ͼƬ
			//System.out.println("�ҵ�����ͼ��");
			return img;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//����Ҳ��� �ͻ᷵���Ҳ�����ԭ��
			//System.out.println("û�ҵ�����ͼ��");
			e.printStackTrace();//����	
			return null;
		}
		
	}
}
