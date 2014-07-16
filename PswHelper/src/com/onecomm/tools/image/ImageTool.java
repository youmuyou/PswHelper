package com.onecomm.tools.image;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author CentralPerk
 *
 */
public class ImageTool {

	
	/** ���ַ�������д��ͼƬBitmap
	 * @param data �ı�����
	 * @param width bitmap��
	 * @param height bitmap��
	 * @param bgColor ����ɫ
	 * @param textColor  ������ɫ
	 * @param textSize  ���ִ�С
	 * @param xPosition X����ʼλ��
	 * @param textSpace �м��
	 * @return bitmap ,nullд��ʧ��
	 */
	public static Bitmap writeDataToImage(ArrayList<String> data,int width,int height,int bgColor,int textColor,
			                              int textSize,int xPosition,int textSpace) {
		try {

			Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
			Canvas canvas = new Canvas(bitmap);
			canvas.drawColor(bgColor); // ������ɫ
			
			Paint p = new Paint();
			p.setColor(textColor); // ������ɫ
			p.setTextSize(textSize); // �����С
			for (int i = 0; i < data.size(); i++) {
				canvas.drawText(data.get(i), xPosition, (i + 1) * textSpace + i*textSize, p);
			}
			return bitmap;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}
