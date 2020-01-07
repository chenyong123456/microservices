package cn.knowimage.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class tableImageOutColumn {
    /**
       *  生成纵向表格图片
       *  @param  cellsValue  以二维数组形式存放  表格里面的值
       *  @param  path  文件保存路径
       */
    public void myGraphicsGeneration(String cellsValue[][], String path,String title_info,String maxStr) {
        //  字体大小
        int  fontTitileSize  = 15;
        //  横线的行数
        int  totalrow  =  cellsValue.length;
        //  竖线的行数
        int  totalcol  =  0;
        if  (cellsValue[0]    !=  null)  {
            totalcol  =  cellsValue[0].length;
        }
        //  图片宽度
        int  imageWidth  =  600;
        //  单元格宽度
        int  colwidth  = (imageWidth-15)/totalcol;
        ArrayList<String> MaxStr = stringCut(maxStr, (imageWidth-15)/totalcol);
        //最多字符串分割后的行数
        int maxHeight=MaxStr.size();
        //  行高
        int  rowheight  =  15*maxHeight+15;
        System.out.println("rowheight=="+rowheight);
        //  图片高度
        int  imageHeight  =  (totalrow-1)*rowheight+(15 * maxHeight);
        //  起始高度
        int  startHeight  =  0;
        //  起始宽度
        int  startWidth  =  0;
        BufferedImage image  =  new  BufferedImage(imageWidth,  imageHeight,BufferedImage.TYPE_INT_RGB);
        Graphics graphics  =  image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0,  imageWidth,  imageHeight);
        graphics.setColor(new  Color(220,240,240));
        //设置字体
        Font  font  =  new  Font("微软雅黑",Font.PLAIN,fontTitileSize);
        graphics.setFont(font);
        ArrayList<String> arrayList ;
        //写标题  需要更改
        String title  =  title_info;
        arrayList = stringCut(title,imageWidth-30);
        for (int i = 0 ;i < arrayList.size();i++) {
            graphics.setColor(Color.black);
            String value = arrayList.get(i);
            graphics.drawString(value, startWidth + 15, startHeight + (15*(i+1)));
        }
        startHeight = startHeight + (15*(arrayList.size()))+10;
        //画横线
        for(int  j=0;j<totalrow;  j++){
            graphics.setColor(Color.black);
            graphics.drawLine(startWidth+10,  startHeight+(j+1)*rowheight,  startWidth+colwidth*totalcol+10,  startHeight+(j+1)*rowheight);
        }
        //画竖线
        for(int  k=0;k<totalcol+1;k++){
            graphics.setColor(Color.black);
            //此时纵向为标题，所以第一列的高度窄，此时第二根线则窄
            graphics.drawLine(startWidth+k*colwidth+10,  startHeight-20,  startWidth+k*colwidth+10,  startHeight+rowheight*(totalrow-1));
        }
        //写入内容
        for(int  n=0;n<cellsValue.length;n++){
            for(int  l=0;l<cellsValue[n].length;l++){
                if  (n  ==  0)  {
                    font  =  new  Font("微软雅黑",Font.PLAIN,fontTitileSize);
                    graphics.setFont(font);
                }else  if  (n  >  0  &&  l  >0)  {
                    font  =  new  Font("微软雅黑",Font.PLAIN,fontTitileSize);
                    graphics.setFont(font);
                    graphics.setColor(Color.black);
                }  else  {
                    font  =  new  Font("微软雅黑",Font.PLAIN,fontTitileSize);
                    graphics.setFont(font);
                    graphics.setColor(Color.BLACK);
                }
                arrayList = stringCut(cellsValue[n][l],colwidth);
                for (int i = 0 ;i < arrayList.size();i++) {
                    String value = arrayList.get(i);
                    if (n>0) {
                        graphics.drawString(value, startWidth + colwidth * l+12, startHeight+(n-1)*rowheight+((i+1)*15));
                    }
                    //System.out.println("value="+value+"坐标为:"+"x="+(startWidth + colwidth * l+12)+"y="+(startHeight + rowheight * (n + 2) - rowheight+15+(i*15)));
                    else {
                        System.out.println(value);
                        graphics.drawString(value, startWidth + colwidth * l+(colwidth-value.length()*15)/2, startHeight+n*rowheight+(i*15)-(10/2));
                    }
                }
            }
        }
        //  保存图片
        createImage(image,  path);
    }

/**
   *  将图片保存到指定位置
   *  @param  image  缓冲文件类
   *  @param  fileLocation  文件位置
   */
    public  void  createImage(BufferedImage  image,  String  fileLocation)  {
        try  {
            FileOutputStream fos  =  new  FileOutputStream(fileLocation);
            BufferedOutputStream bos  =  new  BufferedOutputStream(fos);
            JPEGImageEncoder encoder  =  JPEGCodec.createJPEGEncoder(bos);
            encoder.encode(image);
            bos.close();
        }  catch  (Exception  e)  {
            e.printStackTrace();
        }
    }
    public ArrayList<String> stringCut(String str , int colwidth ){
        int num = str.length();
        ArrayList list = new ArrayList<>();
        if (str.length()*15<colwidth){
            list.add(str);
        }else {
            for (int i = 0; i < num; i = i + colwidth/15) {
                String s = str.substring(i, (i + colwidth/15));
                list.add(s);
                if ((num - i - colwidth/15) < colwidth/15) {
                    list.add(str.substring(i + colwidth/15, num));
                    break;
                }
            }
        }
        return list;
    }
}
