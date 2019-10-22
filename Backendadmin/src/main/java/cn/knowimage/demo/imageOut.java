package cn.knowimage.demo;

import net.sf.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class imageOut {
    public static String graphicsGeneration(List<List<List<String>>> allValue, List<String> titles, List<String[]> headers , String receiver, int totalcol) throws Exception {
        int rows = 0;
        for (List<List<String>> typeV : allValue) {
            if (typeV != null && typeV.size() > 0) {
                rows += (2+typeV.size());
            }
        }
        // 实际数据行数+标题+备注
        int totalrow = 1+rows;
        int imageWidth = 1000;
        int imageHeight = totalrow * 30 + 20;
        int rowheight = 30;
        int startHeight = 10;
        int startWidth = 10;
        int colwidth = ((imageWidth - 20) / totalcol);

        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, imageWidth, imageHeight);
        //画背景
        graphics.setColor(new Color(0, 112, 192));
        int startH = 1;
        for (List<List<String>> typeV : allValue) {
            if (typeV != null && typeV.size() > 0) {
                graphics.fillRect(startWidth+1, startHeight+startH*rowheight+1, imageWidth - startWidth-5-1,rowheight-1);
                startH+=2+typeV.size();
            }
        }

        graphics.setColor(new Color(220, 240, 240));
        // 画横线

        for (int j = 0; j < totalrow - 1; j++) {
            graphics.setColor(Color.black);
            graphics.drawLine(startWidth, startHeight + (j + 1) * rowheight, imageWidth - 5,
                    startHeight + (j + 1) * rowheight);
        }

        // 画竖线
        graphics.setColor(Color.black);
        startH = 1;
        int rightLine = 0 ;
        for (List<List<String>> typeV : allValue) {

            if (typeV != null && typeV.size() > 0) {
                for (int k = 0; k < totalcol+1; k++) {
                    rightLine = getRightMargin(k,startWidth, colwidth,imageWidth);
                    graphics.drawLine(rightLine, startHeight + startH*rowheight, rightLine,
                            startHeight + (typeV.size()+1+startH)*rowheight);
                }
                startH+=2+typeV.size();
            }
        }

        // 设置字体
        Font font = new Font("华文楷体", Font.BOLD, 20);
        graphics.setFont(font);

        // 写标题
        startH = 1;
        int i = 0;
        for (List<List<String>> typeV : allValue) {
            if (typeV != null && typeV.size() > 0) {
                graphics.drawString(titles.get(i), imageWidth / 3 + startWidth+30, startHeight + startH*rowheight - 10);
                startH+=2+typeV.size();
            }
            i++;
        }


        // 写入表头
        graphics.setColor(Color.WHITE);
        font = new Font("华文楷体", Font.BOLD, 20);
        graphics.setFont(font);
        startH = 2;
        i = 0;
        for (List<List<String>> typeV : allValue) {
            if (typeV != null && typeV.size() > 0) {

                String[] headCells = headers.get(i);
                for (int m = 0; m < headCells.length; m++) {
                    rightLine = getRightMargin(m,startWidth, colwidth,imageWidth)+5;
                    graphics.drawString(headCells[m].toString(), rightLine,
                            startHeight + rowheight * startH - 10);
                }
                startH+=2+typeV.size();
            }
            i++;
        }


        // 写入内容
        graphics.setColor(Color.black);
        font = new Font("华文楷体", Font.PLAIN, 20);
        graphics.setFont(font);
        startH = 3;
        i = 0;
        for (List<List<String>> typeV : allValue) {
            if (typeV != null && typeV.size() > 0) {
                for (int n = 0; n < typeV.size(); n++) {
                    List<String> arr = typeV.get(n);
                    for (int l = 0; l < arr.size(); l++) {
                        rightLine = getRightMargin(l,startWidth, colwidth,imageWidth)+5;
                        graphics.drawString(arr.get(l).toString(), rightLine,
                                startHeight + rowheight * (n + startH) - 10);
                    }
                }
                startH+=2+typeV.size();
            }
            i++;
        }

        String path = "1.jpg";
        ImageIO.write(image, "jpg", new File(path));
        return path;
    }

    /**
     * 获取竖线和文字的水平位置
     * @param k
     * @param startWidth
     * @param colwidth
     * @param imageWidth
     * @return
     */
    private static int getRightMargin(int k, int startWidth, int colwidth, int imageWidth) {
        int rightLine = 0;
        if (k == 0) {
            rightLine = startWidth;
        } else if (k == 1) {
            rightLine = startWidth + colwidth / 2;
        } else if (k == 2) {
            rightLine = startWidth + 3 * colwidth / 2;
        } else if (k == 3) {
            rightLine = startWidth + 7 * colwidth / 2;
        } else if (k == 4) {
            rightLine = imageWidth - 5;
        }
        return rightLine;
    }

//    static void initChartData() throws Exception {
//        List<List<List<String>>> allValue = new ArrayList<>();
//        List<String> content1 =Arrays.asList(new String[]{"刘丹丹","25","163cm","未婚"});
//        List<String> content2 = Arrays.asList(new String[]{"刘丹丹","25","163cm","未婚"});
//        List<String> content3 = Arrays.asList(new String[]{"刘丹丹","宿迁","本科","未婚"});
//        List<List<String>> contentArray1 = new ArrayList<>();
//        contentArray1.add(content1);
//        contentArray1.add(content2);
//        List<List<String>> contentArray2 = new ArrayList<>();
//        contentArray2.add(content3);
//        allValue.add(contentArray1);
//        allValue.add(contentArray2);
//
//        List<String[]> headTitles = new ArrayList<>();
//        String[] h1 = new String[]{"名字","年龄","身高","婚姻"};
//        String[] h2 = new String[]{"名字","籍贯","学历","婚姻"};
//        headTitles.add(h1);
//        headTitles.add(h2);
//
//        List<String> titles = new ArrayList<>();
//        titles.add("制造部门人员统计");
//        titles.add("SQE部门人员统计");
//        graphicsGeneration(allValue,titles,headTitles ,"",4);
//    }
//
//    public static void main(String[] args) throws Exception {
//        initChartData();
//    }

}
