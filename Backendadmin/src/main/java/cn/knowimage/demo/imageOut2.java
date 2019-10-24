package cn.knowimage.demo;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class imageOut2 {
    /**
       *  生成图片
       *  @param  cellsValue  以二维数组形式存放  表格里面的值
       *  @param  path  文件保存路径
       */
    public void myGraphicsGeneration(String cellsValue[][], String path,String title_info) {
        //  字体大小
        int  fontTitileSize  =  15;
        //  横线的行数
        int  totalrow  =  cellsValue.length+1;
        //  竖线的行数
        int  totalcol  =  0;
        if  (cellsValue[0]    !=  null)  {
            totalcol  =  cellsValue[0].length;
        }
        //  图片宽度
        int  imageWidth  =  1024;
        //  行高
        int  rowheight  =  100;
        //  图片高度
        int  imageHeight  =  totalrow*rowheight;
        //  起始高度
        int  startHeight  =  0;
        //  起始宽度
        int  startWidth  =  0;
        //  单元格宽度
        int  colwidth  =  (int)((imageWidth-20)/totalcol);
        BufferedImage image  =  new  BufferedImage(imageWidth,  imageHeight,BufferedImage.TYPE_INT_RGB);
        Graphics graphics  =  image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0,  imageWidth,  imageHeight);
        graphics.setColor(new  Color(220,240,240));
        ArrayList<String> arrayList = new ArrayList();
        //画横线
        for(int  j=0;j<totalrow;  j++){
            graphics.setColor(Color.black);
            graphics.drawLine(startWidth+10,  startHeight+(j+1)*rowheight,  startWidth+colwidth*totalcol+10,  startHeight+(j+1)*rowheight);
        }
        //画竖线
        for(int  k=0;k<totalcol+1;k++){
            graphics.setColor(Color.black);
            graphics.drawLine(startWidth+k*colwidth+10,  startHeight+rowheight,  startWidth+k*colwidth+10,  startHeight+rowheight*totalrow);
        }
        //设置字体
        Font  font  =  new  Font("微软雅黑",Font.PLAIN,fontTitileSize);
        graphics.setFont(font);

        //写标题
        String title  =  title_info;
        arrayList = stringCut(title,996);
        for (int i = 0 ;i < arrayList.size();i++) {
            String value = arrayList.get(i);
            graphics.drawString(value, startWidth + 15, startHeight + rowheight - (15*(arrayList.size()-i)));
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
                arrayList = stringCut(cellsValue[n][l].toString(),colwidth);
                for (int i = 0 ;i < arrayList.size();i++) {
                    String value = arrayList.get(i);
                    graphics.drawString(value, startWidth + colwidth * l+15, startHeight + rowheight * (n + 2) - 80+(i*15));
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
        System.out.println(list);
        return list;
    }
    public static void main(String[] args) {
        String str = "手术治疗，完全切除者，不推荐辅助化疗或辅助放疗";
        int num = str.length();
        ArrayList list = new ArrayList<>();
        for (int i=0;i<num;i=i+10){
            String s= str.substring(i,(i+10));
            list.add(s);
            if ((num-i-10)<10){
                list.add(str.substring(i+10,num));
                break;
            }
        }
        System.out.println(list);
    }
}
