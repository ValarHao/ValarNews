package com.valarhao.valarnews.common.util;

import java.util.List;

public class HtmlUtil {

    private static final String HEADER_STYLE = "<style>div.headline{display:none;}</style>";
    private static final String CSS_TAG = "<link rel=\"stylesheet\" type=\"text/css\" href=\"%s\"/>";
    private static final String JS_TAG = "<script src=\"%s\"></script>";
    public static final String MIME_TYPE = "text/html; charset=utf-8";
    public static final String ENCODING = "utf-8";

    /**
     * 根据css链接生成Link标签
     * @param url String
     * @return String
     */
    public static String createCssTag(String url) {
        return String.format(CSS_TAG, url);
    }

    /**
     * 根据多个css链接生成Link标签
     * @param urls List<String>
     * @return String
     */
    public static String createCssTag(List<String> urls) {
        final StringBuilder sb = new StringBuilder();
        for (String url : urls) {
            sb.append(createCssTag(url));
        }
        return sb.toString();
    }

    /**
     * 根据js链接生成Script标签
     * @param url String
     * @return String
     */
    public static String createJsTag(String url) {
        return String.format(JS_TAG, url);
    }

    /**
     * 根据多个js链接生成Script标签
     * @param urls List<String>
     * @return String
     */
    public static String createJsTag(List<String> urls) {
        final StringBuilder sb = new StringBuilder();
        for (String url : urls) {
            sb.append(createJsTag(url));
        }
        return sb.toString();
    }

    /**
     * 生成完整的HTML文档
     */
    public static String createHtmlData(String html, List<String> cssList, List<String> jsList) {
        final String css = HtmlUtil.createCssTag(cssList);
        final String js = HtmlUtil.createJsTag(jsList);
        return css.concat(HEADER_STYLE).concat(html).concat(js);
    }
}
