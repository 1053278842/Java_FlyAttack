package ui;

import javax.swing.JFrame;
/**
 * �����ࣺJFrame
 * @author Administrator
 *���Զ��崰�壺
 *1.�̳�JFtame��
 *2.���췽������ʼ������
 */
public class GameFrame extends JFrame {
	int w,h;
	public GameFrame() {//���췽��
	//������Դ�ڸ���JFrame�ķ���
		//����
		setTitle("The War III-Hunter Plane");
		//��С setSize(������)��
		w=512;
		h=768;
		setSize(w,h);
		//����λ�� ������ʾ��
			// Alt + ? ������ʾ
		setLocationRelativeTo(null);//�����Ļĳһ�������ʾ��nullָ���Ͻǵĵ㣨����Ե㣩
		//�̶��߽�	���á������¡�����С���������ԣ��Ƿ�������¸��ģ��߽磩setLocationRelativeTo(null);
		setResizable(false);
		//�ر�ѡ�� Q�����йر�Ϊ�λ�Ҫ�رգ� A:���ǽ���رգ�ʵ�ʳ�����������
		//����-Ĭ��-�ر�-���ã�	�����ࡣ�˳�����  			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		//���ô���Ĺ��췽�� ��������
		//3�Ƕ���2�Ƕ��������
		//  1		2			3		
		GameFrame frame= new GameFrame();
		//������幹�췽�� �������
		GamePanel panel= new GamePanel(frame);
		//�������뵽������
		//��������GameStart
		panel.action();
		//��ʾ����
		frame.setVisible(true);
		frame.add(panel);
	}
	
	
}
