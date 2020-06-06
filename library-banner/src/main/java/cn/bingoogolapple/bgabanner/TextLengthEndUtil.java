package cn.bingoogolapple.bgabanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: chichapaofan
 * @CreateDate: 2020/4/20
 * @Description:字符串长度显示超过长度显示...
 */
public class TextLengthEndUtil {
    private static String regEx = "[\u4e00-\u9fa5]"; // 中文范围

    /**
     * 格式化字符串
     *
     * @param string   原始输入字符串
     * @param maxCount 最大字符限制，中文算作2个字符，其他都算1个字符
     * @return
     */
    private static String formatText(String string, int maxCount) {
        if ((string == null || string.length() == 0)
                && getChCount(string) > maxCount) {
            string = subStrByLen(string, maxCount - 1);
        }
        return string;
    }

    /**
     * 截取字符串，超出最大字数截断并显示"..."
     *
     * @param str    原始字符串
     * @param length 最大字数限制（以最大字数限制7个为例，当含中文时，length应设为2*7，不含中文时设为7）
     * @return 处理后的字符串
     */
    public static String subStrByLen(String str, int length) {
        if (str == null || str.length() == 0) {
            return "";
        }
        int chCnt = getStrLen(str);
        // 超出进行截断处理
        if (chCnt > length) {
            int cur = 0;
            int cnt = 0;
            StringBuilder sb = new StringBuilder();
            while (cnt <= length && cur < str.length()) {
                char nextChar = str.charAt(cur);
                if (isChCharacter(String.valueOf(nextChar))) {
                    cnt += 2;
                } else {
                    cnt++;
                }
                if (cnt <= length) {
                    sb.append(nextChar);
                } else {
                    return sb.toString() + "...";
                }
                cur++;
            }
            return sb.toString() + "...";
        }
        // 未超出直接返回
        return str;
    }

    /**
     * 获取字符串中的中文字数
     */
    private static int getChCount(String str) {
        int cnt = 0;
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            cnt++;
        }
        return cnt;
    }

    /**
     * 判断字符是不是中文
     */
    private static boolean isChCharacter(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        if (str.length() > 1) {
            return false;
        }
        return Pattern.matches(regEx, str);
    }

    /**
     * 获取字符长度，中文算作2个字符，其他都算1个字符
     */
    public static int getStrLen(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return str.length() + getChCount(str);
    }
}
