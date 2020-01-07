package cn.knowimage.controller;


import gui.ava.html.image.generator.HtmlImageGenerator;

import java.util.ArrayList;
import java.util.List;

public class TestTable2 {

    private static String getSingleImageHtml(String title, List<String> headTitle, List<List<String>> contentArray) {
        // String html = "<!DOCTYPE html><html><body style=\"width:570px;\">";
        String html = "<table border=\"1\" style=\"border-collapse:collapse;width:800px;border:1px solid black;background: white;\" cellpadding=\"0\" cellspacing=\"0\">";

        html += "<thead>";
        html += "<tr ><th colspan=\"4\" style=\"text-align: center;width: 800px;height:40px;font-size: 22px;\">" + title
                + "</th></tr>";
        //这里为上标题
        html += "<tr>";
        System.out.println("title标题为----->:" + headTitle.toString());
        for (int i = 0; i < 2; i++) {
            String thEle = "";
            thEle = "<th style=\"height:30px;width:" + (i == 2 ? "100%" : "60%") // 在这里设置单元格的宽度
                    + ";text-align: center;margin:0;color: #ffffff;background:#0070c0;border:1px solid black;font-size: 20px;\">"
                    + headTitle.get(i) + "</th>";
            html += thEle;
        }
        html += "</tr>";
        html += "</thead>";

        html += "<tbody>";
        System.out.println("总数组为----->:" + contentArray.toString());
        for (List<String> contents : contentArray) {
            System.out.println("出入的数据为--->:" + contents.toString());
            html += "<tr>";
            for (int j = 0; j < 2; j++) {
                String tdEle = "";
                if(j == 0) {
                    tdEle = "<td style=\"height:30px;width:" + (j == 2 ? "100%" : "60%")
                            + ";text-align: center;margin:0;border:1px solid black;font-size: 18px;\">" + contents.get(j)
                            + "</td>";
                    html += tdEle;
                }else{
                    tdEle = "<td style=\"height:30px;width:" + (j == 2 ? "100%" : "60%")
                            + ";text-align: left;margin:0;border:1px solid black;font-size: 18px;\">" + contents.get(j)
                            + "</td>";
                    html += tdEle;
                }
            }
            html += "</tr>";
        }

        html += "</tbody>";
        html += "</table>";
        // html+="</body></html>";
        return html;
    }

    public static String graphicsHtmlGeneration(List<List<List<String>>> allValue, List<String> titles,
                                                List<List<String>> headers) throws Exception {
        int i = 0;
        String html = "";
        for (List<List<String>> list : allValue) {
            String title = titles.get(i);
            List<String> headTitle = headers.get(i);
            html += getSingleImageHtml(title, headTitle, list);
            i++;
        }
        if (html != null && html.length() > 0) {
            HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
            imageGenerator.loadHtml(html);
            try {
                // Thread.sleep(5000);
                imageGenerator.getBufferedImage();
                // Thread.sleep(8000);
                String path = "C:\\Users\\wh123\\Desktop\\1.png";
                imageGenerator.saveAsImage(path);
                return path;
            } catch (Exception e) {
            }
        }
        return null;

    }

    public static void main(String[] args) {

        //文本内容
        List<String> allValue1 = null;

        List<List<String>> allValue2 = new ArrayList<>();

        allValue1 = new ArrayList<>();
        allValue1.add("I期");
        allValue1.add("单一淋巴结或淋巴组织器官区(I)；单一结外器官或部位(IE)");
        allValue2.add(allValue1);

        allValue1 = new ArrayList<>();
        allValue1.add("II期");
        allValue1.add("膈上或膈下同侧受累淋巴结区≥2个；或病变局限侵犯结外器官或部位，并膈肌同侧一个以上淋巴结区(IIE)");
        allValue2.add(allValue1);

        allValue1 = new ArrayList<>();
        allValue1.add("III期");
        allValue1.add("膈上下两侧均有淋巴结受累(III)；伴结外器官或组织局部侵犯(IIIE)，或脾脏受累(IIIS)，或两者皆受累(IIISE)");
        allValue2.add(allValue1);

        allValue1 = new ArrayList<>();
        allValue1.add("V期");
        allValue1.add("一个或多个结外器官或组织广泛受累，伴或不伴淋巴结肿大");
        allValue2.add(allValue1);


        System.out.println("value2的值为----->:" + allValue2);
        List<List<List<String>>> allValue = new ArrayList<>();
        allValue.add(allValue2);

        //上标题
        List<String> titles = new ArrayList<>();
        titles.add("PTCL-U预后指数(PIT)");


        List<List<String>> headers = new ArrayList<>();
        List<String> header = new ArrayList<>();
        header.add("危险因子");
        header.add("预后风险");
        headers.add(header);
        try {
            TestTable2.graphicsHtmlGeneration(allValue, titles, headers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
