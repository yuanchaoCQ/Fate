/*
 * Copyright (c) 2015, Xinlong.inc and/or its affiliates. All rights reserved.
 */
package com.wk.cpd.mvc.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.CharEncoding;
import org.springframework.util.ObjectUtils;

/**
 * 字符串操作工具类
 * 
 * @since 1.0
 * @author Lanxiaowei@citic-finance.com
 * @date 2015-6-11上午10:14:15
 *
 */
public class StringUtils {
    public static final String UTF8 = "UTF-8";

    public static final String UTF16 = "UTF-16";

    public static final String GB2312 = "GB2312";

    public static final String GBK = "GBK";

    public static final String ISO8859_1 = "ISO-8859-1";

    public static final String ISO8859_2 = "ISO-8859-2";

    private static final String QUOTE = "&quot;";

    private static final String AMP = "&amp;";

    private static final String LT = "&lt;";

    private static final String GT = "&gt;";

    private static final int FILLCHAR = '=';

    private static final String CVT = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    private static final String NUMBER_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static Random randGen = new Random();

    public static final String EMPTY = "";

    public static final int INDEX_NOT_FOUND = -1;

    private static final String[] CHAR_STRING_ARRAY = new String[128];

    private static final int PAD_LIMIT = 8192;

    private static final int SESSION_ID_BYTES = 16;

    private static final String HEXES = "0123456789ABCDEF";
    private static final String[] escapeIgnore = new String[] { "*", "+", "-", ".", "/", "@", "_" };
    private static final String[] encodeURIIgnore = new String[] { "!", "#", "$", "&", "'", "(", ")", "*", "+", ",",
            "-", ".", "/", ":", ";", "=", "?", "@", "_", "~" };
    private static final String[] encodeURIComponentIgnore = new String[] { "!", "'", "(", ")", "*", "-", ".", "_",
            "~" };

    private static Pattern metaPattern = Pattern.compile("<meta\\s+([^>]*http-equiv=\"?content-type\"?[^>]*)>",
            Pattern.CASE_INSENSITIVE);
    private static Pattern charsetPattern = Pattern.compile("charset=\\s*([a-z][_\\-0-9a-z]*)",
            Pattern.CASE_INSENSITIVE);

    /** HTML实体 */
    private static Map<String, Integer> htmlEntities = new HashMap<String, Integer>();
    static {
        htmlEntities.put("nbsp", new Integer(160));
        htmlEntities.put("iexcl", new Integer(161));
        htmlEntities.put("cent", new Integer(162));
        htmlEntities.put("pound", new Integer(163));
        htmlEntities.put("curren", new Integer(164));
        htmlEntities.put("yen", new Integer(165));
        htmlEntities.put("brvbar", new Integer(166));
        htmlEntities.put("sect", new Integer(167));
        htmlEntities.put("uml", new Integer(168));
        htmlEntities.put("copy", new Integer(169));
        htmlEntities.put("ordf", new Integer(170));
        htmlEntities.put("laquo", new Integer(171));
        htmlEntities.put("not", new Integer(172));
        htmlEntities.put("shy", new Integer(173));
        htmlEntities.put("reg", new Integer(174));
        htmlEntities.put("macr", new Integer(175));
        htmlEntities.put("deg", new Integer(176));
        htmlEntities.put("plusmn", new Integer(177));
        htmlEntities.put("sup2", new Integer(178));
        htmlEntities.put("sup3", new Integer(179));
        htmlEntities.put("acute", new Integer(180));
        htmlEntities.put("micro", new Integer(181));
        htmlEntities.put("para", new Integer(182));
        htmlEntities.put("middot", new Integer(183));
        htmlEntities.put("cedil", new Integer(184));
        htmlEntities.put("sup1", new Integer(185));
        htmlEntities.put("ordm", new Integer(186));
        htmlEntities.put("raquo", new Integer(187));
        htmlEntities.put("frac14", new Integer(188));
        htmlEntities.put("frac12", new Integer(189));
        htmlEntities.put("frac34", new Integer(190));
        htmlEntities.put("iquest", new Integer(191));
        htmlEntities.put("Agrave", new Integer(192));
        htmlEntities.put("Aacute", new Integer(193));
        htmlEntities.put("Acirc", new Integer(194));
        htmlEntities.put("Atilde", new Integer(195));
        htmlEntities.put("Auml", new Integer(196));
        htmlEntities.put("Aring", new Integer(197));
        htmlEntities.put("AElig", new Integer(198));
        htmlEntities.put("Ccedil", new Integer(199));
        htmlEntities.put("Egrave", new Integer(200));
        htmlEntities.put("Eacute", new Integer(201));
        htmlEntities.put("Ecirc", new Integer(202));
        htmlEntities.put("Euml", new Integer(203));
        htmlEntities.put("Igrave", new Integer(204));
        htmlEntities.put("Iacute", new Integer(205));
        htmlEntities.put("Icirc", new Integer(206));
        htmlEntities.put("Iuml", new Integer(207));
        htmlEntities.put("ETH", new Integer(208));
        htmlEntities.put("Ntilde", new Integer(209));
        htmlEntities.put("Ograve", new Integer(210));
        htmlEntities.put("Oacute", new Integer(211));
        htmlEntities.put("Ocirc", new Integer(212));
        htmlEntities.put("Otilde", new Integer(213));
        htmlEntities.put("Ouml", new Integer(214));
        htmlEntities.put("times", new Integer(215));
        htmlEntities.put("Oslash", new Integer(216));
        htmlEntities.put("Ugrave", new Integer(217));
        htmlEntities.put("Uacute", new Integer(218));
        htmlEntities.put("Ucirc", new Integer(219));
        htmlEntities.put("Uuml", new Integer(220));
        htmlEntities.put("Yacute", new Integer(221));
        htmlEntities.put("THORN", new Integer(222));
        htmlEntities.put("szlig", new Integer(223));
        htmlEntities.put("agrave", new Integer(224));
        htmlEntities.put("aacute", new Integer(225));
        htmlEntities.put("acirc", new Integer(226));
        htmlEntities.put("atilde", new Integer(227));
        htmlEntities.put("auml", new Integer(228));
        htmlEntities.put("aring", new Integer(229));
        htmlEntities.put("aelig", new Integer(230));
        htmlEntities.put("ccedil", new Integer(231));
        htmlEntities.put("egrave", new Integer(232));
        htmlEntities.put("eacute", new Integer(233));
        htmlEntities.put("ecirc", new Integer(234));
        htmlEntities.put("euml", new Integer(235));
        htmlEntities.put("igrave", new Integer(236));
        htmlEntities.put("iacute", new Integer(237));
        htmlEntities.put("icirc", new Integer(238));
        htmlEntities.put("iuml", new Integer(239));
        htmlEntities.put("eth", new Integer(240));
        htmlEntities.put("ntilde", new Integer(241));
        htmlEntities.put("ograve", new Integer(242));
        htmlEntities.put("oacute", new Integer(243));
        htmlEntities.put("ocirc", new Integer(244));
        htmlEntities.put("otilde", new Integer(245));
        htmlEntities.put("ouml", new Integer(246));
        htmlEntities.put("divide", new Integer(247));
        htmlEntities.put("oslash", new Integer(248));
        htmlEntities.put("ugrave", new Integer(249));
        htmlEntities.put("uacute", new Integer(250));
        htmlEntities.put("ucirc", new Integer(251));
        htmlEntities.put("uuml", new Integer(252));
        htmlEntities.put("yacute", new Integer(253));
        htmlEntities.put("thorn", new Integer(254));
        htmlEntities.put("yuml", new Integer(255));
        htmlEntities.put("fnof", new Integer(402));
        htmlEntities.put("Alpha", new Integer(913));
        htmlEntities.put("Beta", new Integer(914));
        htmlEntities.put("Gamma", new Integer(915));
        htmlEntities.put("Delta", new Integer(916));
        htmlEntities.put("Epsilon", new Integer(917));
        htmlEntities.put("Zeta", new Integer(918));
        htmlEntities.put("Eta", new Integer(919));
        htmlEntities.put("Theta", new Integer(920));
        htmlEntities.put("Iota", new Integer(921));
        htmlEntities.put("Kappa", new Integer(922));
        htmlEntities.put("Lambda", new Integer(923));
        htmlEntities.put("Mu", new Integer(924));
        htmlEntities.put("Nu", new Integer(925));
        htmlEntities.put("Xi", new Integer(926));
        htmlEntities.put("Omicron", new Integer(927));
        htmlEntities.put("Pi", new Integer(928));
        htmlEntities.put("Rho", new Integer(929));
        htmlEntities.put("Sigma", new Integer(931));
        htmlEntities.put("Tau", new Integer(932));
        htmlEntities.put("Upsilon", new Integer(933));
        htmlEntities.put("Phi", new Integer(934));
        htmlEntities.put("Chi", new Integer(935));
        htmlEntities.put("Psi", new Integer(936));
        htmlEntities.put("Omega", new Integer(937));
        htmlEntities.put("alpha", new Integer(945));
        htmlEntities.put("beta", new Integer(946));
        htmlEntities.put("gamma", new Integer(947));
        htmlEntities.put("delta", new Integer(948));
        htmlEntities.put("epsilon", new Integer(949));
        htmlEntities.put("zeta", new Integer(950));
        htmlEntities.put("eta", new Integer(951));
        htmlEntities.put("theta", new Integer(952));
        htmlEntities.put("iota", new Integer(953));
        htmlEntities.put("kappa", new Integer(954));
        htmlEntities.put("lambda", new Integer(955));
        htmlEntities.put("mu", new Integer(956));
        htmlEntities.put("nu", new Integer(957));
        htmlEntities.put("xi", new Integer(958));
        htmlEntities.put("omicron", new Integer(959));
        htmlEntities.put("pi", new Integer(960));
        htmlEntities.put("rho", new Integer(961));
        htmlEntities.put("sigmaf", new Integer(962));
        htmlEntities.put("sigma", new Integer(963));
        htmlEntities.put("tau", new Integer(964));
        htmlEntities.put("upsilon", new Integer(965));
        htmlEntities.put("phi", new Integer(966));
        htmlEntities.put("chi", new Integer(967));
        htmlEntities.put("psi", new Integer(968));
        htmlEntities.put("omega", new Integer(969));
        htmlEntities.put("thetasym", new Integer(977));
        htmlEntities.put("upsih", new Integer(978));
        htmlEntities.put("piv", new Integer(982));
        htmlEntities.put("bull", new Integer(8226));
        htmlEntities.put("hellip", new Integer(8230));
        htmlEntities.put("prime", new Integer(8242));
        htmlEntities.put("Prime", new Integer(8243));
        htmlEntities.put("oline", new Integer(8254));
        htmlEntities.put("frasl", new Integer(8260));
        htmlEntities.put("weierp", new Integer(8472));
        htmlEntities.put("image", new Integer(8465));
        htmlEntities.put("real", new Integer(8476));
        htmlEntities.put("trade", new Integer(8482));
        htmlEntities.put("alefsym", new Integer(8501));
        htmlEntities.put("larr", new Integer(8592));
        htmlEntities.put("uarr", new Integer(8593));
        htmlEntities.put("rarr", new Integer(8594));
        htmlEntities.put("darr", new Integer(8595));
        htmlEntities.put("harr", new Integer(8596));
        htmlEntities.put("crarr", new Integer(8629));
        htmlEntities.put("lArr", new Integer(8656));
        htmlEntities.put("uArr", new Integer(8657));
        htmlEntities.put("rArr", new Integer(8658));
        htmlEntities.put("dArr", new Integer(8659));
        htmlEntities.put("hArr", new Integer(8660));
        htmlEntities.put("forall", new Integer(8704));
        htmlEntities.put("part", new Integer(8706));
        htmlEntities.put("exist", new Integer(8707));
        htmlEntities.put("empty", new Integer(8709));
        htmlEntities.put("nabla", new Integer(8711));
        htmlEntities.put("isin", new Integer(8712));
        htmlEntities.put("notin", new Integer(8713));
        htmlEntities.put("ni", new Integer(8715));
        htmlEntities.put("prod", new Integer(8719));
        htmlEntities.put("sum", new Integer(8721));
        htmlEntities.put("minus", new Integer(8722));
        htmlEntities.put("lowast", new Integer(8727));
        htmlEntities.put("radic", new Integer(8730));
        htmlEntities.put("prop", new Integer(8733));
        htmlEntities.put("infin", new Integer(8734));
        htmlEntities.put("ang", new Integer(8736));
        htmlEntities.put("and", new Integer(8743));
        htmlEntities.put("or", new Integer(8744));
        htmlEntities.put("cap", new Integer(8745));
        htmlEntities.put("cup", new Integer(8746));
        htmlEntities.put("int", new Integer(8747));
        htmlEntities.put("there4", new Integer(8756));
        htmlEntities.put("sim", new Integer(8764));
        htmlEntities.put("cong", new Integer(8773));
        htmlEntities.put("asymp", new Integer(8776));
        htmlEntities.put("ne", new Integer(8800));
        htmlEntities.put("equiv", new Integer(8801));
        htmlEntities.put("le", new Integer(8804));
        htmlEntities.put("ge", new Integer(8805));
        htmlEntities.put("sub", new Integer(8834));
        htmlEntities.put("sup", new Integer(8835));
        htmlEntities.put("nsub", new Integer(8836));
        htmlEntities.put("sube", new Integer(8838));
        htmlEntities.put("supe", new Integer(8839));
        htmlEntities.put("oplus", new Integer(8853));
        htmlEntities.put("otimes", new Integer(8855));
        htmlEntities.put("perp", new Integer(8869));
        htmlEntities.put("sdot", new Integer(8901));
        htmlEntities.put("lceil", new Integer(8968));
        htmlEntities.put("rceil", new Integer(8969));
        htmlEntities.put("lfloor", new Integer(8970));
        htmlEntities.put("rfloor", new Integer(8971));
        htmlEntities.put("lang", new Integer(9001));
        htmlEntities.put("rang", new Integer(9002));
        htmlEntities.put("loz", new Integer(9674));
        htmlEntities.put("spades", new Integer(9824));
        htmlEntities.put("clubs", new Integer(9827));
        htmlEntities.put("hearts", new Integer(9829));
        htmlEntities.put("diams", new Integer(9830));
        htmlEntities.put("quot", new Integer(34));
        htmlEntities.put("amp", new Integer(38));
        htmlEntities.put("lt", new Integer(60));
        htmlEntities.put("gt", new Integer(62));
        htmlEntities.put("OElig", new Integer(338));
        htmlEntities.put("oelig", new Integer(339));
        htmlEntities.put("Scaron", new Integer(352));
        htmlEntities.put("scaron", new Integer(353));
        htmlEntities.put("Yuml", new Integer(376));
        htmlEntities.put("circ", new Integer(710));
        htmlEntities.put("tilde", new Integer(732));
        htmlEntities.put("ensp", new Integer(8194));
        htmlEntities.put("emsp", new Integer(8195));
        htmlEntities.put("thinsp", new Integer(8201));
        htmlEntities.put("zwnj", new Integer(8204));
        htmlEntities.put("zwj", new Integer(8205));
        htmlEntities.put("lrm", new Integer(8206));
        htmlEntities.put("rlm", new Integer(8207));
        htmlEntities.put("ndash", new Integer(8211));
        htmlEntities.put("mdash", new Integer(8212));
        htmlEntities.put("lsquo", new Integer(8216));
        htmlEntities.put("rsquo", new Integer(8217));
        htmlEntities.put("sbquo", new Integer(8218));
        htmlEntities.put("ldquo", new Integer(8220));
        htmlEntities.put("rdquo", new Integer(8221));
        htmlEntities.put("bdquo", new Integer(8222));
        htmlEntities.put("dagger", new Integer(8224));
        htmlEntities.put("Dagger", new Integer(8225));
        htmlEntities.put("permil", new Integer(8240));
        htmlEntities.put("lsaquo", new Integer(8249));
        htmlEntities.put("rsaquo", new Integer(8250));
        htmlEntities.put("euro", new Integer(8364));
    }

    /**
     * 
     * @Title: capitalize @Description: 单词首字母转换为大写 @param
     *         str @param @return @return String @throws
     */
    public static String capitalize(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        return new StringBuilder(strLen).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1))
                .toString();
    }

    /**
     * 
     * @Title: uncapitalize @Description: 单词首字母转换为小写 @param
     *         str @param @return @return String @throws
     */
    public static String uncapitalize(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        return new StringBuilder(strLen).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1))
                .toString();
    }

    /**
     * 
     * @Title: isEmpty @Description: 判断字符串是否为Null或者空字符串 @param
     *         cs @param @return @return boolean @throws
     */
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * 
     * @Title: isEmptyString @Description: 判断字符串是否为Null或者空字符串 @param
     *         s @return @return boolean @throws
     */
    public static boolean isEmptyString(String s) {
        return s == null || s.length() == 0;
    }

    /**
     * 
     * @Title: isNotEmpty @Description: 判断字符串是否不为Null且不为空字符串 @param
     *         cs @param @return @return boolean @throws
     */
    public static boolean isNotEmpty(CharSequence cs) {
        return !StringUtils.isEmpty(cs);
    }

    /**
     * 
     * @Title: isBlankString @Description: 判断字符串是否为Null或全部为连续的空格 @param
     *         s @param @return @return boolean @throws
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(cs.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * @Title: isNotBlank @Description: 判断字符串是否不为Null且不为连续的空格 @param
     *         cs @param @return @return boolean @throws
     */
    public static boolean isNotBlank(CharSequence cs) {
        return !StringUtils.isBlank(cs);
    }

    /**
     * 
     * @Title: trim @Description: 字符串去两头空格,如果原字符串为Null，返回Null @param
     *         str @param @return @return String @throws
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     * 
     * @Title: trimToNull @Description: 字符串去两头空格,如果原字符串为Null，返回Null @param
     *         str @param @return @return String @throws
     */
    public static String trimToNull(String str) {
        String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }

    /**
     * 
     * @Title: trimToEmpty @Description: 字符串去两头空格,如果原字符串为Null，返回空字符串 @param
     *         str @param @return @return String @throws
     */
    public static String trimToEmpty(String str) {
        return str == null ? EMPTY : str.trim();
    }

    /**
     * 
     * @Title: deleteWhitespace @Description:
     *         删除字符串中的所有空格字符(删除两头以及中间的所有空格字符) @param str @return @return
     *         String @throws
     */
    public static String deleteWhitespace(String str) {
        if (isEmpty(str)) {
            return str;
        }
        int sz = str.length();
        char[] chs = new char[sz];
        int count = 0;
        for (int i = 0; i < sz; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                chs[count++] = str.charAt(i);
            }
        }
        if (count == sz) {
            return str;
        }
        return new String(chs, 0, count);
    }

    /**
     * 
     * @Title: equals @Description: 判断两个字符串是否相等 @param cs1 @param
     *         cs2 @param @return @return boolean @throws
     */
    public static boolean equals(CharSequence cs1, CharSequence cs2) {
        return cs1 == null ? cs2 == null : cs1.equals(cs2);
    }

    /**
     * 
     * @Title: substring @Description: 字符串截取(没有索引越界异常) @param str @param
     *         start @param end @param @return @return String @throws
     */
    public static String substring(String str, int start, int end) {
        if (str == null) {
            return null;
        }

        if (end < 0) {
            end = str.length() + end;
        }
        if (start < 0) {
            start = str.length() + start;
        }

        if (end > str.length()) {
            end = str.length();
        }

        if (start > end) {
            return EMPTY;
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }
        return str.substring(start, end);
    }

    /**
     * 
     * @Title: left @Description: 从最左边开始截取指定长度的字符串 @param str @param
     *         len @param @return @return String @throws
     */
    public static String left(String str, int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return EMPTY;
        }
        if (str.length() <= len) {
            return str;
        }
        return str.substring(0, len);
    }

    /**
     * 
     * @Title: right @Description: 从最右边开始截取指定长度的字符串 @param str @param
     *         len @param @return @return String @throws
     */
    public static String right(String str, int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return EMPTY;
        }
        if (str.length() <= len) {
            return str;
        }
        return str.substring(str.length() - len);
    }

    /**
     * 
     * @Title: substringBefore @Description: 截取指定子串之前的字符串 @param str @param
     *         separator null-->原样返回，空字符串-->返回空字符串 @param @return @return
     *         String @throws
     */
    public static String substringBefore(String str, String separator) {
        if (isEmpty(str) || separator == null) {
            return str;
        }
        if (separator.length() == 0) {
            return EMPTY;
        }
        int pos = str.indexOf(separator);
        if (pos == INDEX_NOT_FOUND) {
            return str;
        }
        return str.substring(0, pos);
    }

    /**
     * 
     * @Title: substringAfter @Description: 截取指定子串之后的字符串 @param str @param
     *         separator null-->原样返回，空字符串-->返回空字符串 @param @return @return
     *         String @throws
     */
    public static String substringAfter(String str, String separator) {
        if (isEmpty(str)) {
            return str;
        }
        if (separator == null) {
            return EMPTY;
        }
        int pos = str.indexOf(separator);
        if (pos == INDEX_NOT_FOUND) {
            return EMPTY;
        }
        return str.substring(pos + separator.length());
    }

    /**
     * 
     * @Title: repeat @Description: 将指定字符串重复repeat次数 @param str @param repeat
     *         重复次数 @return @return String @throws
     */
    public static String repeat(String str, int repeat) {
        if (str == null) {
            return null;
        }
        if (repeat <= 0) {
            return EMPTY;
        }
        int inputLength = str.length();
        if (repeat == 1 || inputLength == 0) {
            return str;
        }
        if (inputLength == 1 && repeat <= PAD_LIMIT) {
            return repeat(str.charAt(0), repeat);
        }

        int outputLength = inputLength * repeat;
        switch (inputLength) {
        case 1:
            return repeat(str.charAt(0), repeat);
        case 2:
            char ch0 = str.charAt(0);
            char ch1 = str.charAt(1);
            char[] output2 = new char[outputLength];
            for (int i = repeat * 2 - 2; i >= 0; i--, i--) {
                output2[i] = ch0;
                output2[i + 1] = ch1;
            }
            return new String(output2);
        default:
            StringBuilder buf = new StringBuilder(outputLength);
            for (int i = 0; i < repeat; i++) {
                buf.append(str);
            }
            return buf.toString();
        }
    }

    /**
     * 
     * @Title: repeat @Description: 将一个字符串重复repeat次，并且中间加上指定的分隔符 @param str
     *         源字符串 @param separator 分隔符 @param repeat 重复次数 @return @return
     *         String @throws
     */
    public static String repeat(String str, String separator, int repeat) {
        if (str == null || separator == null) {
            return repeat(str, repeat);
        } else {
            String result = repeat(str + separator, repeat);
            return removeEnd(result, separator);
        }
    }

    /**
     * 
     * @Title: repeat @Description: 将某个字符重复repeat次 @param ch @param
     *         repeat @return @return String @throws
     */
    public static String repeat(char ch, int repeat) {
        char[] buf = new char[repeat];
        for (int i = repeat - 1; i >= 0; i--) {
            buf[i] = ch;
        }
        return new String(buf);
    }

    public static String rightPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0) {
            return str;
        }
        if (pads > PAD_LIMIT) {
            return rightPad(str, size, String.valueOf(padChar));
        }
        return str.concat(repeat(padChar, pads));
    }

    /**
     * 
     * @Title: rightPad @Description: 扩充字符串长度，不足部分使用指定填充字符从右边开始填充 @param str
     *         源字符串 @param size 扩充后长度 @param padStr 填充字符 @return @return
     *         String @throws
     */
    public static String rightPad(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = " ";
        }
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str;
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return rightPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return str.concat(padStr);
        } else if (pads < padLen) {
            return str.concat(padStr.substring(0, pads));
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return str.concat(new String(padding));
        }
    }

    /**
     * 
     * @Title: rightPad @Description:
     *         扩充字符串长度，不足部分使用指定填充字符从右边开始填充,默认使用空格进行填充 @param str 源字符串 @param
     *         size 填充后长度 @return @return String @throws
     */
    public static String rightPad(String str, int size) {
        return rightPad(str, size, ' ');
    }

    /**
     * 
     * @Title: leftPad @Description:
     *         扩充字符串长度，不足部分使用指定填充字符从左边开始填充,默认使用空格进行填充 @param str @param
     *         size @return @return String @throws
     */
    public static String leftPad(String str, int size) {
        return leftPad(str, size, ' ');
    }

    /**
     * 
     * @Title: leftPad @Description: 扩充字符串长度，不足部分使用指定填充字符从左边开始填充 @param
     *         str @param size @param padChar @return @return String @throws
     */
    public static String leftPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0) {
            return str;
        }
        if (pads > PAD_LIMIT) {
            return leftPad(str, size, String.valueOf(padChar));
        }
        return repeat(padChar, pads).concat(str);
    }

    /**
     * 
     * @Title: leftPad @Description: 扩充字符串长度，不足部分使用指定填充字符从左边开始填充 @param
     *         str @param size @param padStr @return @return String @throws
     */
    public static String leftPad(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = " ";
        }
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str;
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return leftPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return padStr.concat(str);
        } else if (pads < padLen) {
            return padStr.substring(0, pads).concat(str);
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return new String(padding).concat(str);
        }
    }

    /**
     * 
     * @Title: center @Description: 扩充字符串长度使源字符串居中，扩充部分使用指定填充符填充，默认填充符为空格 @param
     *         str @param size @return @return String @throws
     */
    public static String center(String str, int size) {
        return center(str, size, ' ');
    }

    /**
     * 
     * @Title: center @Description: 扩充字符串长度使源字符串居中，扩充部分使用指定填充符填充 @param
     *         str @param size @param padChar @return @return String @throws
     */
    public static String center(String str, int size, char padChar) {
        if (str == null || size <= 0) {
            return str;
        }
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str;
        }
        str = leftPad(str, strLen + pads / 2, padChar);
        str = rightPad(str, size, padChar);
        return str;
    }

    /**
     * 
     * @Title: center @Description: 扩充字符串长度使源字符串居中，扩充部分使用指定填充符填充 @param str
     *         源字符串 @param size 扩充后长度 @param padStr 填充符 @return @return
     *         String @throws
     */
    public static String center(String str, int size, String padStr) {
        if (str == null || size <= 0) {
            return str;
        }
        if (isEmpty(padStr)) {
            padStr = " ";
        }
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str;
        }
        str = leftPad(str, strLen + pads / 2, padStr);
        str = rightPad(str, size, padStr);
        return str;
    }

    /**
     * 
     * @Title: join @Description: 将一个数组按照指定的连接符拼接成一个字符串 @param array @param
     *         separator 连接符 @param startIndex 起始索引 @param endIndex
     *         结束索引 @param @return @return String @throws
     */
    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        int noOfItems = (endIndex - startIndex);
        if (noOfItems <= 0) {
            return EMPTY;
        }

        StringBuilder buf = new StringBuilder(noOfItems * 16);

        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    /**
     * 
     * @Title: join @Description: 将一个数组按照指定的连接符拼接成一个字符串(重载) @param array @param
     *         separator @param @return @return String @throws
     */
    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    /**
     * 
     * @Title: join @Description: 将集合按照指定的连接符拼接成一个字符串 @param iterator @param
     *         separator @return @return String @throws
     */
    public static String join(Iterator<?> iterator, String separator) {
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return EMPTY;
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return first == null ? "" : first.toString();
        }

        StringBuilder buf = new StringBuilder(256);
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            buf.append(separator);
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    /**
     * 
     * @Title: join @Description: 将集合按照指定的连接符拼接成一个字符串(重载) @param iterable @param
     *         separator @return @return String @throws
     */
    public static String join(Iterable<?> iterable, String separator) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), separator);
    }

    /**
     * 
     * @Title: removeStart @Description:
     *         删除源字符串中的以remove开头的字符(若源字符串不以remove开头，则原样返回) @param str @param
     *         remove @return @return String @throws
     */
    public static String removeStart(String str, String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (str.startsWith(remove)) {
            return str.substring(remove.length());
        }
        return str;
    }

    /**
     * 
     * @Title: removeEnd @Description:
     *         删除源字符串中的以remove结尾的字符(若源字符串不以remove结尾，则原样返回) @param str @param
     *         remove @return @return String @throws
     */
    public static String removeEnd(String str, String remove) {
        if (isEmpty(str) || isEmpty(remove)) {
            return str;
        }
        if (str.endsWith(remove)) {
            return str.substring(0, str.length() - remove.length());
        }
        return str;
    }

    /**
     * 
     * @Title: startsWith @Description: 判断源字符串是否以指定前缀开头 @param str 源字符串 @param
     *         prefix 指定前缀字符串 @param ignoreCase 是否忽略大小写 @return @return
     *         boolean @throws
     */
    public static boolean startsWith(CharSequence str, CharSequence prefix, boolean ignoreCase) {
        if (str == null || prefix == null) {
            return (str == null && prefix == null);
        }
        if (prefix.length() > str.length()) {
            return false;
        }
        return regionMatches(str, ignoreCase, 0, prefix, 0, prefix.length());
    }

    /**
     * 
     * @Title: startsWithIgnoreCase @Description: 判断源字符串是否以指定前缀开头(忽略大小写) @param
     *         str @param prefix @return @return boolean @throws
     */
    public static boolean startsWithIgnoreCase(CharSequence str, CharSequence prefix) {
        return startsWith(str, prefix, true);
    }

    /**
     * 
     * @Title: regionMatches @Description: 字符串指定范围内是否匹配 @param cs 源字符串 @param
     *         ignoreCase 是否忽略大小写 @param thisStart 源字符串匹配的起始索引 @param substring
     *         子字符串 @param start 匹配的起始索引 @param length 匹配的长度 @return @return
     *         boolean 返回匹配结果true/false @throws
     */
    public static boolean regionMatches(CharSequence cs, boolean ignoreCase, int thisStart, CharSequence substring,
            int start, int length) {
        if (cs instanceof String && substring instanceof String) {
            return ((String) cs).regionMatches(ignoreCase, thisStart, ((String) substring), start, length);
        }
        return cs.toString().regionMatches(ignoreCase, thisStart, substring.toString(), start, length);
    }

    /**
     * 
     * @Title: reverse @Description: 反转字符串，如"abcd"转换成"dcba" @param
     *         str @return @return String @throws
     */
    public static String reverse(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * 
     * @Title: isAllLowerCase @Description: 判断字符串是否全部小写 @param
     *         cs @return @return boolean @throws
     */
    public static boolean isAllLowerCase(CharSequence cs) {
        if (cs == null || isEmpty(cs)) {
            return false;
        }
        int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isLowerCase(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * @Title: isAllUpperCase @Description: 判断字符串是否全部大写 @param
     *         cs @return @return boolean @throws
     */
    public static boolean isAllUpperCase(CharSequence cs) {
        if (cs == null || isEmpty(cs)) {
            return false;
        }
        int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isUpperCase(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * @Title: isAsciiPrintable @Description: 判断是否是ASCII可打印字符串 @param
     *         cs @return @return boolean @throws
     */
    public static boolean isAsciiPrintable(CharSequence cs) {
        if (cs == null) {
            return false;
        }
        int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (isAsciiPrintable(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * @Title: isAsciiPrintable @Description: 判断是否为ASCII码可打印字符 @param
     *         ch @return @return boolean @throws
     */
    public static boolean isAsciiPrintable(char ch) {
        return ch >= 32 && ch < 127;
    }

    /**
     * 
     * @Title: isAsciiControl @Description: 判断是否为ASCII码控制字符 @param
     *         ch @return @return boolean @throws
     */
    public static boolean isAsciiControl(char ch) {
        return ch < 32 || ch == 127;
    }

    /**
     * 
     * @Title: isAsciiAlpha @Description: 判断是否为英文字母 @param ch @return @return
     *         boolean @throws
     */
    public static boolean isAsciiAlpha(char ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
    }

    /**
     * 
     * @Title: isAsciiAlphaUpper @Description: 判断是否为英文大写字母 @param
     *         ch @return @return boolean @throws
     */
    public static boolean isAsciiAlphaUpper(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    /**
     * 
     * @Title: isAsciiAlphaLower @Description: 判断是否为英文小写字母 @param
     *         ch @return @return boolean @throws
     */
    public static boolean isAsciiAlphaLower(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    /**
     * 
     * @Title: isAsciiNumeric @Description: 判断是否为数字字符 @param ch @return @return
     *         boolean @throws
     */
    public static boolean isAsciiNumeric(char ch) {
        return ch >= '0' && ch <= '9';
    }

    /**
     * 
     * @Title: isAsciiAlphanumeric @Description: 判断是否为英文字母或数字字符 @param
     *         ch @return @return boolean @throws
     */
    public static boolean isAsciiAlphanumeric(char ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9');
    }

    /**
     * 
     * @Title: isAscii @Description: 判断是否为ASCII字符 @param ch @return @return
     *         boolean @throws
     */
    public static boolean isAscii(char ch) {
        return ch < 128;
    }

    /**
     * 
     * @Title: unicodeEscaped @Description:
     *         char转换成Unicode字符，如"A"-->"\u0041" @param ch @return @return
     *         String @throws
     */
    public static String unicodeEscaped(char ch) {
        if (ch < 0x10) {
            return "\\u000" + Integer.toHexString(ch);
        } else if (ch < 0x100) {
            return "\\u00" + Integer.toHexString(ch);
        } else if (ch < 0x1000) {
            return "\\u0" + Integer.toHexString(ch);
        }
        return "\\u" + Integer.toHexString(ch);
    }

    /**
     * 
     * @Title: unicodeEscaped @Description:
     *         char转换成Unicode字符，如"A"-->"\u0041" @param ch @return @return
     *         String @throws
     */
    public static String unicodeEscaped(Character ch) {
        if (ch == null) {
            return null;
        }
        return unicodeEscaped(ch.charValue());
    }

    /**
     * 
     * @Title: unicodeEscaped @Description:
     *         String转换成Unicode字符，如"A"-->"\u0041" @param str @return @return
     *         String @throws
     */
    public static String unicodeEscaped(String str) {
        return unicodeEscaped(toCharacterObject(str));
    }

    /**
     * 
     * @Title: toCharacterObject @Description: char转成Character @param
     *         ch @return @return Character @throws
     */
    public static Character toCharacterObject(char ch) {
        return Character.valueOf(ch);
    }

    /**
     * 
     * @Title: toCharacterObject @Description: String转成Character @param
     *         str @return @return Character @throws
     */
    public static Character toCharacterObject(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return Character.valueOf(str.charAt(0));
    }

    /**
     * 
     * @Title: toChar @Description: Character转成char @param ch @return @return
     *         char @throws
     */
    public static char toChar(Character ch) {
        if (ch == null) {
            throw new IllegalArgumentException("The Character must not be null");
        }
        return ch.charValue();
    }

    /**
     * 
     * @Title: toChar @Description: Character转成char @param ch @param
     *         defaultValue ch为Null时返回的默认值 @return @return char @throws
     */
    public static char toChar(Character ch, char defaultValue) {
        if (ch == null) {
            return defaultValue;
        }
        return ch.charValue();
    }

    /**
     * 
     * @Title: toChar @Description: String转成char @param str @return @return
     *         char @throws
     */
    public static char toChar(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new IllegalArgumentException("The String must not be empty");
        }
        return str.charAt(0);
    }

    /**
     * 
     * @Title: toChar @Description: String转成char @param str @param defaultValue
     *         str为Null或空字符串时返回的默认值 @return @return char @throws
     */
    public static char toChar(String str, char defaultValue) {
        if (StringUtils.isEmpty(str)) {
            return defaultValue;
        }
        return str.charAt(0);
    }

    /**
     * 
     * @Title: toString @Description: Character转成String @param
     *         ch @return @return String @throws
     */
    public static String char2String(Character ch) {
        if (ch == null) {
            return null;
        }
        return char2String(ch.charValue());
    }

    /**
     * 
     * @Title: char2String @Description: char转成String @param ch @return @return
     *         String @throws
     */
    public static String char2String(char ch) {
        if (ch < 128) {
            return CHAR_STRING_ARRAY[ch];
        }
        return new String(new char[] { ch });
    }

    /**
     * 
     * @Title: swichCharset @Description: 编码转换 @param str @param
     *         oldCharset @param newCharset @param @return @param @throws
     *         UnsupportedEncodingException @return String @throws
     */
    public static String swichCharset(String str, String oldCharset, String newCharset)
            throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        return new String(str.getBytes(oldCharset), newCharset);
    }

    /**
     * 字符串数组转换成List集合
     * 
     * @param array
     * @return
     */
    public static List<String> arrayToList(String[] array) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    /**
     * 字符串替换
     * 
     * @param source 源字符串
     * @param oldString 待替换字符串
     * @param newString 替换字符串
     * @return
     */
    public static final String replace(String source, String oldString, String newString) {
        if (source == null) {
            return null;
        }
        int i = 0;
        if ((i = source.indexOf(oldString, i)) >= 0) {
            char[] line2 = source.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = source.indexOf(oldString, i)) > 0) {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return source;
    }

    /**
     * 字符串替换(忽略大小写)
     * 
     * @param source 源字符串
     * @param oldString 待替换字符串
     * @param newString 替换字符串
     * @return
     */
    public static final String replaceIgnoreCase(String source, String oldString, String newString) {
        if (source == null) {
            return null;
        }
        String lcsource = source.toLowerCase();
        String lcOldString = oldString.toLowerCase();
        int i = 0;
        if ((i = lcsource.indexOf(lcOldString, i)) >= 0) {
            char[] source2 = source.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(source2.length);
            buf.append(source2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = lcsource.indexOf(lcOldString, i)) > 0) {
                buf.append(source2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(source2, j, source2.length - j);
            return buf.toString();
        }
        return source;
    }

    /**
     * 字符串替换(忽略大小写)并且返回被替换元素的个数
     * 
     * @param source 源字符串
     * @param oldString 待替换字符串
     * @param newString 替换字符串
     * @return count 被替换元素个数
     */
    public static final String replaceIgnoreCase(String source, String oldString, String newString, int[] count) {
        if (source == null) {
            return null;
        }
        String lcsource = source.toLowerCase();
        String lcOldString = oldString.toLowerCase();
        int i = 0;
        int counter = 0;
        if ((i = lcsource.indexOf(lcOldString, i)) < 0) {
            return source;
        }
        char[] source2 = source.toCharArray();
        char[] newString2 = newString.toCharArray();
        int oLength = oldString.length();
        StringBuffer buf = new StringBuffer(source2.length);
        buf.append(source2, 0, i).append(newString2);
        i += oLength;
        int j = i;
        while ((i = lcsource.indexOf(lcOldString, i)) > 0) {
            counter++;
            buf.append(source2, j, i - j).append(newString2);
            i += oLength;
            j = i;
        }
        buf.append(source2, j, source2.length - j);
        count[0] = counter + 1;
        return buf.toString();
    }

    /**
     * 对传入的字符串进行Base64编码
     * 
     * @param data
     * @return
     */
    public static String encodeBase64(String data) {
        return encodeBase64(data.getBytes());
    }

    private static String encodeBase64(byte[] data) {
        int c;
        int len = data.length;
        StringBuffer ret = new StringBuffer(((len / 3) + 1) * 4);
        for (int i = 0; i < len; ++i) {
            c = (data[i] >> 2) & 0x3f;
            ret.append(CVT.charAt(c));
            c = (data[i] << 4) & 0x3f;
            if (++i < len) {
                c |= (data[i] >> 4) & 0x0f;
            }
            ret.append(CVT.charAt(c));
            if (i < len) {
                c = (data[i] << 2) & 0x3f;
                if (++i < len) {
                    c |= (data[i] >> 6) & 0x03;
                }
                ret.append(CVT.charAt(c));
            } else {
                ++i;
                ret.append((char) FILLCHAR);
            }
            if (i < len) {
                c = data[i] & 0x3f;
                ret.append(CVT.charAt(c));
            } else {
                ret.append((char) FILLCHAR);
            }
        }
        return ret.toString();
    }

    /**
     * 生成指定长度的随机字符串 length如果小于1，则返回null
     * 
     * @param length 返回字符串长度
     * @return String 指定长度的随机字符串
     */
    public static final String randomString(int length) {
        if (length < 1) {
            return null;
        }
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = NUMBER_LETTERS.toCharArray()[randGen.nextInt(71)];
        }
        return new String(randBuffer);
    }

    /**
     * 对HTML字符串<>转义
     * 
     * @param in HTML字符串
     * @return
     */
    public static final String escapeHTMLTags(String in) {
        if (in == null) {
            return null;
        }
        char ch;
        int i = 0;
        int last = 0;
        char[] input = in.toCharArray();
        int len = input.length;
        StringBuffer out = new StringBuffer((int) (len * 1.3));
        for (; i < len; i++) {
            ch = input[i];
            if (ch > '>') {
                continue;
            } else if (ch == '<') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(LT.toCharArray());
            } else if (ch == '>') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(GT.toCharArray());
            }
        }
        if (last == 0) {
            return in;
        }
        if (i > last) {
            out.append(input, last, i - last);
        }
        return out.toString();
    }

    /**
     * 对XML转义< > & ""
     * 
     * @param string
     * @return
     */
    public static final String escapeXML(String string) {
        if (string == null) {
            return null;
        }
        char ch;
        int i = 0;
        int last = 0;
        char[] input = string.toCharArray();
        int len = input.length;
        StringBuffer out = new StringBuffer((int) (len * 1.3));
        for (; i < len; i++) {
            ch = input[i];
            if (ch > '>') {
                continue;
            } else if (ch == '<') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(LT.toCharArray());
            } else if (ch == '&') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(AMP.toCharArray());
            } else if (ch == '"') {
                if (i > last) {
                    out.append(input, last, i - last);
                }
                last = i + 1;
                out.append(QUOTE.toCharArray());
            }
        }
        if (last == 0) {
            return string;
        }
        if (i > last) {
            out.append(input, last, i - last);
        }
        return out.toString();
    }

    /**
     * 数字0-9转换成汉字显示 如果数字大于9，则返回空字符串
     * 
     * @param i 数字0-9
     * @return
     */
    public static String getCapitalization(int i) {
        String str = "";
        if (i == 1) {
            str = "一";
        } else if (i == 2) {
            str = "二";
        } else if (i == 3) {
            str = "三";
        } else if (i == 4) {
            str = "四";
        } else if (i == 5) {
            str = "五";
        } else if (i == 6) {
            str = "六";
        } else if (i == 7) {
            str = "七";
        } else if (i == 8) {
            str = "八";
        } else if (i == 9) {
            str = "九";
        } else {
            str = "零";
        }
        return str;
    }

    /**
     * 按指定的分隔符分割字符串
     * 
     * @param source 源字符串
     * @param spliter 分隔符
     * @return 字符串数组
     */
    public static String[] split(String source, String spliter) {
        StringTokenizer st = new StringTokenizer(source, spliter);
        String ret[] = new String[st.countTokens()];

        int i = 0;
        while (st.hasMoreTokens()) {
            ret[i++] = st.nextElement() + "";
        }
        return ret;
    }

    /**
     * 
     * @Title: getLevenshteinDistance @Description: 计算两个字符串的编辑距 @param s @param
     *         t @param threshold @return @return int @throws
     */
    public static int getLevenshteinDistance(CharSequence s, CharSequence t, int threshold) {
        if (s == null || t == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }
        if (threshold < 0) {
            throw new IllegalArgumentException("Threshold must not be negative");
        }

        int n = s.length();
        int m = t.length();

        if (n == 0) {
            return m <= threshold ? m : -1;
        } else if (m == 0) {
            return n <= threshold ? n : -1;
        }

        if (n > m) {
            CharSequence tmp = s;
            s = t;
            t = tmp;
            n = m;
            m = t.length();
        }

        int p[] = new int[n + 1];
        int d[] = new int[n + 1];
        int _d[];

        int boundary = Math.min(n, threshold) + 1;
        for (int i = 0; i < boundary; i++) {
            p[i] = i;
        }
        Arrays.fill(p, boundary, p.length, Integer.MAX_VALUE);
        Arrays.fill(d, Integer.MAX_VALUE);

        for (int j = 1; j <= m; j++) {
            char t_j = t.charAt(j - 1);
            d[0] = j;

            int min = Math.max(1, j - threshold);
            int max = Math.min(n, j + threshold);

            if (min > max) {
                return -1;
            }

            if (min > 1) {
                d[min - 1] = Integer.MAX_VALUE;
            }

            for (int i = min; i <= max; i++) {
                if (s.charAt(i - 1) == t_j) {
                    d[i] = p[i - 1];
                } else {
                    d[i] = 1 + Math.min(Math.min(d[i - 1], p[i]), p[i - 1]);
                }
            }
            _d = p;
            p = d;
            d = _d;
        }

        if (p[n] <= threshold) {
            return p[n];
        } else {
            return -1;
        }
    }

    /**
     * 
     * @Title: getBytesISO8859_1 @Description: 返回字符串的ISO_8859_1编码的字节数组 @param
     *         string @return @return byte[] @throws
     */
    public static byte[] getBytesISO8859_1(String string) {
        return StringUtils.getBytesUnchecked(string, CharEncoding.ISO_8859_1);
    }

    /**
     * @Author Lanxiaowei @Title: getBytesUsAscii @Description:
     *         返回字符串的US_ASCII编码的字节数组 @param string @return @return
     *         byte[] @throws
     */
    public static byte[] getBytesUsAscii(String string) {
        return StringUtils.getBytesUnchecked(string, CharEncoding.US_ASCII);
    }

    /**
     * @Author Lanxiaowei @Title: getBytesUtf16 @Description:
     *         返回字符串的UTF_16编码的字节数组 @param string @return @return byte[] @throws
     */
    public static byte[] getBytesUtf16(String string) {
        return StringUtils.getBytesUnchecked(string, CharEncoding.UTF_16);
    }

    /**
     * @Author Lanxiaowei @Title: getBytesUtf16Be @Description:
     *         返回字符串的UTF_16BE编码的字节数组 @param string @return @return
     *         byte[] @throws
     */
    public static byte[] getBytesUtf16Be(String string) {
        return StringUtils.getBytesUnchecked(string, CharEncoding.UTF_16BE);
    }

    /**
     * @Author Lanxiaowei @Title: getBytesUtf16Le @Description:
     *         返回字符串的UTF_16LE编码的字节数组 @param string @return @return
     *         byte[] @throws
     */
    public static byte[] getBytesUtf16Le(String string) {
        return StringUtils.getBytesUnchecked(string, CharEncoding.UTF_16LE);
    }

    /**
     * @Author Lanxiaowei @Title: getBytesUtf8 @Description:
     *         返回字符串的UTF-8编码的字节数组 @param string @return @return byte[] @throws
     */
    public static byte[] getBytesUtf8(String string) {
        return StringUtils.getBytesUnchecked(string, CharEncoding.UTF_8);
    }

    /**
     * @Author Lanxiaowei
     * @Title: getBytesUnchecked
     * @Description: 返回字符串的指定编码的字节数组
     * @param string
     * @param charsetName
     * @return
     * @return byte[]
     * @throws 非检查性异常
     */
    public static byte[] getBytesUnchecked(String string, String charsetName) {
        if (string == null) {
            return null;
        }
        try {
            return string.getBytes(charsetName);
        } catch (UnsupportedEncodingException e) {
            throw StringUtils.newIllegalStateException(charsetName, e);
        }
    }

    private static IllegalStateException newIllegalStateException(String charsetName, UnsupportedEncodingException e) {
        return new IllegalStateException(charsetName + ": " + e);
    }

    /**
     * @Author Lanxiaowei @Title: newString @Description:
     *         通过使用charsetName指代的编码解码字节数组来构造一个字符串 @param bytes @param
     *         charsetName @return @return String @throws
     */
    public static String newString(byte[] bytes, String charsetName) {
        if (bytes == null) {
            return null;
        }
        try {
            return new String(bytes, charsetName);
        } catch (UnsupportedEncodingException e) {
            throw StringUtils.newIllegalStateException(charsetName, e);
        }
    }

    /**
     * @Author Lanxiaowei @Title: newStringISO8859_1 @Description:
     *         通过使用ISO_8859_1编码解码字节数组来构造一个字符串 @param bytes @return @return
     *         String @throws
     */
    public static String newStringISO8859_1(byte[] bytes) {
        return StringUtils.newString(bytes, CharEncoding.ISO_8859_1);
    }

    /**
     * @Author Lanxiaowei @Title: newStringUsAscii @Description:
     *         通过使用US_ASCII编码解码字节数组来构造一个字符串 @param bytes @return @return
     *         String @throws
     */
    public static String newStringUsASCII(byte[] bytes) {
        return StringUtils.newString(bytes, CharEncoding.US_ASCII);
    }

    /**
     * @Author Lanxiaowei @Title: newStringUtf16 @Description:
     *         通过使用UTF_16编码解码字节数组来构造一个字符串 @param bytes @return @return
     *         String @throws
     */
    public static String newStringUtf16(byte[] bytes) {
        return StringUtils.newString(bytes, CharEncoding.UTF_16);
    }

    /**
     * @Author Lanxiaowei @Title: newStringUtf16Be @Description:
     *         通过使用UTF_16BE编码解码字节数组来构造一个字符串 @param bytes @return @return
     *         String @throws
     */
    public static String newStringUtf16Be(byte[] bytes) {
        return StringUtils.newString(bytes, CharEncoding.UTF_16BE);
    }

    /**
     * @Author Lanxiaowei @Title: newStringUtf16Le @Description:
     *         通过使用UTF_16LE编码解码字节数组来构造一个字符串 @param bytes @return @return
     *         String @throws
     */
    public static String newStringUtf16Le(byte[] bytes) {
        return StringUtils.newString(bytes, CharEncoding.UTF_16LE);
    }

    /**
     * @Author Lanxiaowei @Title: newStringUtf8 @Description:
     *         通过使用UTF-8编码解码字节数组来构造一个字符串 @param bytes @return @return
     *         String @throws
     */
    public static String newStringUtf8(byte[] bytes) {
        return StringUtils.newString(bytes, CharEncoding.UTF_8);
    }

    /**
     * @Author Lanxiaowei @Title: escapeHTML @Description: 转义HTML代码 @param
     *         s @return @return String @throws
     */
    public static String escapeHTML(String s) {
        int length = s.length();
        int newLength = length;
        boolean someCharacterEscaped = false;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            int cint = 0xffff & c;
            if (cint < 32) {
                switch (c) {
                case '\r':
                case '\n':
                case '\t':
                case '\f': {
                }
                    break;
                default: {
                    newLength -= 1;
                    someCharacterEscaped = true;
                }
                }
            } else {
                switch (c) {
                case '\"': {
                    newLength += 5;
                    someCharacterEscaped = true;
                }
                    break;
                case '&':
                case '\'': {
                    newLength += 4;
                    someCharacterEscaped = true;
                }
                    break;
                case '<':
                case '>': {
                    newLength += 3;
                    someCharacterEscaped = true;
                }
                    break;
                }
            }
        }
        if (!someCharacterEscaped) {
            return s;
        }
        StringBuffer sb = new StringBuffer(newLength);
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            int cint = 0xffff & c;
            if (cint < 32) {
                switch (c) {
                case '\r':
                case '\n':
                case '\t':
                case '\f': {
                    sb.append(c);
                }
                    break;
                default: {
                    // Remove this character
                }
                }
            } else {
                switch (c) {
                case '\"': {
                    sb.append("&quot;");
                }
                    break;
                case '\'': {
                    sb.append("&#39;");
                }
                    break;
                case '&': {
                    sb.append("&amp;");
                }
                    break;
                case '<': {
                    sb.append("&lt;");
                }
                    break;
                case '>': {
                    sb.append("&gt;");
                }
                    break;
                default: {
                    sb.append(c);
                }
                }
            }
        }
        return sb.toString();
    }

    /**
     * 
     * @Title: escapeSQL @Description: 转义SQL语句 @param s @return @return
     *         String @throws
     */
    public static String escapeSQL(String s) {
        int length = s.length();
        int newLength = length;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            switch (c) {
            case '\\':
            case '\"':
            case '\'':
            case '\0': {
                newLength += 1;
            }
                break;
            }
        }
        if (length == newLength) {
            return s;
        }
        StringBuffer sb = new StringBuffer(newLength);
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            switch (c) {
            case '\\': {
                sb.append("\\\\");
            }
                break;
            case '\"': {
                sb.append("\\\"");
            }
                break;
            case '\'': {
                sb.append("\\\'");
            }
                break;
            case '\0': {
                sb.append("\\0");
            }
                break;
            default: {
                sb.append(c);
            }
            }
        }
        return sb.toString();
    }

    /**
     * 
     * @Title: escapeJavaCode @Description: 转义Java代码 @param s @return @return
     *         String @throws
     */
    public static String escapeJavaCode(String s) {
        int length = s.length();
        int newLength = length;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            switch (c) {
            case '\"':
            case '\'':
            case '\n':
            case '\r':
            case '\t':
            case '\\': {
                newLength += 1;
            }
                break;
            }
        }
        if (length == newLength) {
            return s;
        }
        StringBuffer sb = new StringBuffer(newLength);
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            switch (c) {
            case '\"': {
                sb.append("\\\"");
            }
                break;
            case '\'': {
                sb.append("\\\'");
            }
                break;
            case '\n': {
                sb.append("\\n");
            }
                break;
            case '\r': {
                sb.append("\\r");
            }
                break;
            case '\t': {
                sb.append("\\t");
            }
                break;
            case '\\': {
                sb.append("\\\\");
            }
                break;
            default: {
                sb.append(c);
            }
            }
        }
        return sb.toString();
    }

    /**
     * 
     * @Title: escapeRegularExpression @Description: 转义正则表达式 @param
     *         s @return @return String @throws
     */
    public static String escapeRegularExpression(String s) {
        int length = s.length();
        int newLength = length;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (!((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
                newLength += 1;
            }
        }
        if (length == newLength) {
            return s;
        }
        StringBuffer sb = new StringBuffer(newLength);
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (!((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
                sb.append('\\');
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 
     * @Title: unescapeHTML @Description: 反转义HTML @param s @return @return
     *         String @throws
     */
    public static String unescapeHTML(String s) {
        StringBuffer result = new StringBuffer(s.length());
        int ampInd = s.indexOf("&");
        int lastEnd = 0;
        while (ampInd >= 0) {
            int nextAmp = s.indexOf("&", ampInd + 1);
            int nextSemi = s.indexOf(";", ampInd + 1);
            if (nextSemi != -1 && (nextAmp == -1 || nextSemi < nextAmp)) {
                int value = -1;
                String escape = s.substring(ampInd + 1, nextSemi);
                try {
                    if (escape.startsWith("#")) {
                        value = Integer.parseInt(escape.substring(1), 10);
                    } else {
                        if (htmlEntities.containsKey(escape)) {
                            value = ((Integer) (htmlEntities.get(escape))).intValue();
                        }
                    }
                } catch (NumberFormatException x) {
                }
                result.append(s.substring(lastEnd, ampInd));
                lastEnd = nextSemi + 1;
                if (value >= 0 && value <= 0xffff) {
                    result.append((char) value);
                } else {
                    result.append("&").append(escape).append(";");
                }
            }
            ampInd = nextAmp;
        }
        result.append(s.substring(lastEnd));
        return result.toString();
    }

    /**
     * 
     * @Title: toHexString @Description: 字符串进行十六进制的ASCII编码 @param
     *         s @return @return String @throws
     */
    public static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

    /**
     * 
     * @Title: escape @Description: URL中特殊字符编码(* + - . / @ _ 0-9 a-z
     *         A-Z不会被编码) @param src @return @return String @throws
     */
    public static String escape(String src) {
        if (isEmpty(src)) {
            return src;
        }
        char j = 0;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (int i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            boolean flag = false;
            for (int k = 0; k < escapeIgnore.length; k++) {
                if (escapeIgnore[k].charAt(0) == j) {
                    tmp.append(j);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j)) {
                    tmp.append(j);
                } else if (j < 256) {
                    tmp.append("%");
                    if (j < 16) {
                        tmp.append("0");
                    }
                    tmp.append(Integer.toString(j, 16));
                } else {
                    tmp.append("%u");
                    tmp.append(Integer.toString(j, 16));
                }
            }
        }
        return tmp.toString();
    }

    /**
     * 
     * @Title: encodeURI @Description:
     *         对URI进行完整的编码(!#$&'()*+,-./:;=?@_~0-9a-zA-Z不会被编码) @param
     *         src @return @return String @throws
     */
    public static String encodeURI(String src) {
        if (isEmpty(src)) {
            return src;
        }
        char j = 0;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (int i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            boolean flag = false;
            for (int k = 0; k < encodeURIIgnore.length; k++) {
                if (encodeURIIgnore[k].charAt(0) == j) {
                    tmp.append(j);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j)) {
                    tmp.append(j);
                } else if (j < 256) {
                    tmp.append("%");
                    if (j < 16) {
                        tmp.append("0");
                    }
                    tmp.append(Integer.toString(j, 16));
                } else {
                    tmp.append("%u");
                    tmp.append(Integer.toString(j, 16));
                }
            }
        }
        return tmp.toString();
    }

    /**
     * 
     * @Title: encodeURIComponent @Description:
     *         对URI参数部分进行编码(!'()*-._~0-9a-zA-Z不会被编码)
     *         主要适用于对URL传递的特殊字符或中文参数部分进行编码,而不是把整个URL传递给此函数
     *         eg:http://xxxx.do?qu=中文&r=50%,明显参数:中文和50%需要编码,
     *         因此你只需要把中文和50%传递给此函数进行编码 @param src @return @return String @throws
     */
    public static String encodeURIComponent(String src) {
        if (isEmpty(src)) {
            return src;
        }
        char j = 0;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (int i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            boolean flag = false;
            for (int k = 0; k < encodeURIComponentIgnore.length; k++) {
                if (encodeURIComponentIgnore[k].charAt(0) == j) {
                    tmp.append(j);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j)) {
                    tmp.append(j);
                } else if (j < 256) {
                    tmp.append("%");
                    if (j < 16) {
                        tmp.append("0");
                    }
                    tmp.append(Integer.toString(j, 16));
                } else {
                    tmp.append("%u");
                    tmp.append(Integer.toString(j, 16));
                }
            }
        }
        return tmp.toString();
    }

    /**
     * 
     * @Title: unescape @Description: URL中特殊字符解码，与escape功能相反 @param
     *         src @return @return String @throws
     */
    public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (-1 == pos) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    /**
     * 
     * @Title: getNumbericFromString @Description: 从字符串中提取出数字(可以提取整数和小数) @param
     *         text @return @return String @throws
     */
    public static String getNumbericFromString(String text) {
        if (isEmpty(text)) {
            return null;
        }
        StringBuffer reg = new StringBuffer();
        reg.append("0\\.\\d+|[1-9]{1}([0-9]+)?\\.\\d+|\\d+");
        Pattern pattern = Pattern.compile(reg.toString());
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    /**
     * 
     * @Title: getURLFromString @Description: 从字符串中提取URL链接,<br/>
     *         如:http://www.google.com.hk
     *         http://www.savings.com/popup/detail/coupon-723379.html @param
     *         text @return @return String @throws
     */
    public static String getURLFromString(String text) {
        if (isEmpty(text)) {
            return null;
        }
        StringBuffer reg = new StringBuffer();
        reg.append("\\b((ftp|http|https?)://[-\\w]+(\\.\\w[-\\w]*)+|(?i:[a-z0-9](?:[-a-z0-9]*[a-z0-9])?\\.)+");
        reg.append("(?-i:com\\b|edu\\b|biz\\b|gov\\b|in(?:t|fo)\\b|mil\\b|net\\b|org\\b|[a-z][a-z]\\b))");
        reg.append(
                "(:\\d+)?(/[^.!,?;\"'<>()\\[\\]{}\\s\\x7F-\\xFF]*(?:[.!,?]+[^.!,?;\"'<>()\\[\\]{}\\s\\x7F-\\xFF]+)*)?");
        Pattern pattern = Pattern.compile(reg.toString());
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    /**
     * 
     * @Title: cleanComment @Description: 剔除JS/HTML注释 @param @param
     *         str @param @return @return String @throws
     */
    public static String cleanComment(String str) {
        if (isEmpty(str)) {
            return null;
        }
        // 剔除此类注释：<!-- 这里是注释 -->
        Pattern pattern = Pattern.compile("<!--[\\w\\W\r\\n]*?-->");
        Matcher matcher = pattern.matcher(str);
        str = matcher.replaceAll("");
        // 剔除此类注释：/**这里是注释 */
        pattern = Pattern.compile("/\\*[\\w\\W\r\\n]*?\\*/");
        matcher = pattern.matcher(str);
        str = matcher.replaceAll("");
        // 剔除此类注释：//这里是注释
        str = str.replaceAll("//[^\n]*\n", "");
        return str;
    }

    /**
     * 
     * @Title: cleanJavaScript @Description: 剔除所有的JavaScript代码 对于
     *         <script></script>标签之间含有</script>字符串的情况， 可能会有BUG，后续再完善 上述BUG已修复，支持
     *         <script>标签嵌套 @param @param str @param @return @return
     *         String @throws
     */
    public static String cleanJavaScript(String str) {
        /*
         * if (isEmptyString(str)) { return null; } return
         * str.replaceAll("(?s)<script.*?>(.*?)</script>","");
         */
        if (isEmpty(str)) {
            return null;
        }
        str = str.toLowerCase().replaceAll("<script(?:[^<]++|<(?!/script>))*+</script>", "<script>");
        while (str.contains("</script>")) {
            str = str.replaceAll("<script(?:[^<]++|<(?!/script>))*+</script>", "");
        }
        return str;
    }

    /**
     * 
     * @Title: cleanCSS @Description: 剔除<style></style>
     *         标签之间的所有CSS代码 @param @param str @param @return @return
     *         String @throws
     */
    public static String cleanCSS(String str) {
        if (isEmpty(str)) {
            return null;
        }
        return str.toLowerCase().replaceAll("(?s)<style.*?>(.*?)</style>", "");
    }

    /**
     * 
     * Unicode字符串转换成中文
     * 
     * @param dataStr
     * @return
     */
    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16);
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }

    /**
     * 
     * 判断字符串是否以数字结尾
     * 
     * @param str
     * @return
     */
    public static boolean endsWithNum(String str) {
        if (isEmpty(str)) {
            return false;
        }
        String temp = str.replaceAll("\\d{1}$", "");
        return temp.length() != str.length();
    }

    /**
     * 
     * 查找字符串中第一个大写字母的索引位置
     * 
     * @param str
     * @return
     */
    public static int findFirstUpperWordIndex(String str) {
        if (isEmpty(str)) {
            return -1;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 
     * 剔除字符串结尾多余的换行符 如：I like java.\n\n\n\n --> I like java.
     * 
     * @param str
     * @return
     */
    public static String removeBreakLineOfEnd(String str) {
        if (isEmpty(str)) {
            return null;
        }
        return str.replaceAll("([^\n]*)\n+$", "$1");
    }

    /**
     * 
     * 将返回的JSON数据组装成一个HTML代码字符串
     * 
     * @param data HTML代码片段
     * @param pageTitle HTML页面的title名称
     * @param template HTML模版
     * @return
     */
    public static String gernerateHTML(String data, String pageTitle, String template) {
        if (isEmpty(data) || isEmpty(template)) {
            return "";
        }
        if (isEmpty(pageTitle)) {
            pageTitle = "New Page";
        }
        return String.format(template, data, pageTitle);
    }

    /**
     * 
     * 从网页meta标签中提取出页面编码，<br/>
     * 若页面未指定，默认返回null
     * 
     * @param html
     * @return
     */
    public static String getCharsetFromMeta(String html) {
        html = html.replace("contenexType", "content-type");
        html = html.replace("charseexType", "charset").replace("Content-Type", "content-type");
        Matcher metaMatcher = metaPattern.matcher(html);
        String encoding = null;
        if (metaMatcher.find()) {
            Matcher charsetMatcher = charsetPattern.matcher(metaMatcher.group(1));
            if (charsetMatcher.find()) {
                encoding = new String(charsetMatcher.group(1));
            }
        }
        return encoding;
    }

    /**
     * 
     * 替换HTML页面meta部分声明的charset编码
     * 
     * @param html html页面内容
     * @param targetCharset 目标编码
     * @return
     */
    public static String replaceHTMLCharsetFromMeta(String html, String targetCharset) {
        if (isEmpty(html)) {
            return null;
        }
        if (isEmpty(targetCharset)) {
            return html;
        }
        Pattern pattern = Pattern
                .compile("<meta\\s+http-equiv=\"Content-Type\"\\s+content=\"[\\s\\S]*?charset=(\\S+?)\"\\s*/>");
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            String meta = matcher.group();
            String oldCharset = matcher.group(1);
            String newMeta = meta.replace(oldCharset, targetCharset);
            html = html.replace(meta, newMeta);
        }
        return html;
    }

    /**
     * 字节数组转换成十六进制字符串
     * 
     * @param bytes
     * @return
     */
    public static String bytes2Hex(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        StringBuilder hex = new StringBuilder(2 * bytes.length);
        for (final byte b : bytes) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
    }

    /**
     * 字符串转换成十六进制字符串
     * 
     * @param String str 待转换的ASCII字符串
     * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
     */
    public static String str2HexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit = 0;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    /**
     * 十六进制转换字符串
     * 
     * @param String str Byte字符串(Byte之间无分隔符 如:[616C6B])
     * @return String 对应的字符串
     */
    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n = 0;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    /**
     * 字节数组转换成十六进制字符串
     * 
     * @param byte[] b byte数组
     * @return String 每个Byte值之间空格分隔
     */
    public static String byte2HexStr(byte[] b) {
        String stmp = "";
        StringBuilder sb = new StringBuilder("");
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            sb.append((stmp.length() == 1) ? "0" + stmp : stmp);
            sb.append(" ");
        }
        return sb.toString().toUpperCase().trim();
    }

    /**
     * bytes字符串转换为字节数组
     * 
     * @param String src Byte字符串，每个Byte之间没有分隔符
     * @return byte[]
     */
    public static byte[] hexStr2Bytes(String src) {
        int m = 0, n = 0;
        int l = src.length() / 2;
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = Byte.decode("0x" + src.substring(i * 2, m) + src.substring(m, n));
        }
        return ret;
    }

    /**
     * String的字符串转换成unicode的String
     * 
     * @param String strText 全角字符串
     * @return String 每个unicode之间无分隔符
     * @throws Exception
     */
    public static String strToUnicode(String strText) {
        char c;
        StringBuilder str = new StringBuilder();
        int intAsc = 0;
        String strHex = null;
        for (int i = 0; i < strText.length(); i++) {
            c = strText.charAt(i);
            intAsc = (int) c;
            strHex = Integer.toHexString(intAsc);
            if (intAsc > 128) {
                str.append("\\u" + strHex);
            } else { // 低位在前面补00
                str.append("\\u00" + strHex);
            }
        }
        return str.toString();
    }

    /**
     * unicode的String转换成String的字符串
     * 
     * @param String hex 16进制值字符串 （一个unicode为2byte）
     * @return String 全角字符串
     */
    public static String unicodeToString(String hex) {
        int t = hex.length() / 6;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String s = hex.substring(i * 6, (i + 1) * 6);
            // 高位需要补上00再转
            String s1 = s.substring(2, 4) + "00";
            // 低位直接转
            String s2 = s.substring(4);
            // 将16进制的string转为int
            int n = Integer.valueOf(s1, 16) + Integer.valueOf(s2, 16);
            // 将int转换为字符
            char[] chars = Character.toChars(n);
            str.append(new String(chars));
        }
        return str.toString();
    }

    /**
     * @Title: string2Unicode @Description: 字符串转换成Unicode编码形式 @param @param
     *         str @param @return @return String @throws
     */
    public static String string2Unicode(String str, String encoding) {
        try {
            str = new String(str.getBytes("ISO-8859-1"), encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        char[] utfBytes = str.toCharArray();
        String unicodeBytes = "";
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            String hexB = Integer.toHexString(utfBytes[byteIndex]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        return unicodeBytes;
    }

    /**
     * @Title: enUnicode @Description: 字符串Unicode编码 @param @param
     *         s @param @return @return String @throws
     */
    public static String enUnicode(String s) {
        StringBuilder sb = new StringBuilder(s.length() * 3);
        for (char c : s.toCharArray()) {
            if (c < 256) {
                sb.append(c);
            } else {
                sb.append("\\u");
                sb.append(Character.forDigit((c >>> 12) & 0xf, 16));
                sb.append(Character.forDigit((c >>> 8) & 0xf, 16));
                sb.append(Character.forDigit((c >>> 4) & 0xf, 16));
                sb.append(Character.forDigit((c) & 0xf, 16));
            }
        }
        return sb.toString();
    }

    /**
     * @Title: unicode2String @Description: Unicode编码字符串还原成String @param @param
     *         unicodeStr @param @return @return String @throws
     */
    public static String unicode2String(String str) {
        str = (str == null ? "" : str);
        if (str.indexOf("\\u") == -1) {
            return str;
        }
        StringBuffer buffer = new StringBuffer(1000);
        for (int i = 0; i < str.length() - 6;) {
            String strTemp = str.substring(i, i + 6);
            String value = strTemp.substring(2);
            int c = 0;
            for (int j = 0; j < value.length(); j++) {
                char tempChar = value.charAt(j);
                int t = 0;
                switch (tempChar) {
                case 'a':
                    t = 10;
                    break;
                case 'b':
                    t = 11;
                    break;
                case 'c':
                    t = 12;
                    break;
                case 'd':
                    t = 13;
                    break;
                case 'e':
                    t = 14;
                    break;
                case 'f':
                    t = 15;
                    break;
                default:
                    t = tempChar - 48;
                    break;
                }
                c += t * ((int) Math.pow(16, (value.length() - j - 1)));
            }
            buffer.append((char) c);
            i = i + 6;
        }
        return buffer.toString();
    }

    /**
     * 判断是否为中文字符
     * 
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * @Title: isMessyCode @Description: 判断是否乱码 @param @param
     *         strName @param @return @return boolean @throws
     */
    public static boolean isMessyCode(String strName) {
        Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = ch.length;
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    count = count + 1;
                }
            }
        }
        float result = count / chLength;
        if (result > 0.20008) {
            return true;
        }
        return false;
    }

    /**
     * @Title: reverseString @Description: 逗号分割的字符倒序排列 @param @param
     *         str @param @return @return String @throws
     */
    public static String reverseString(String str, String split) {
        if (!str.endsWith(",")) {
            str += ",";
        }
        String[] arrayData = str.split(split);
        List<String> list = Arrays.asList(arrayData);
        Collections.reverse(list);
        return join(list, ",").replace("\",{", "\"},{") + "}";
    }

    /**
     * 全角字符转换成半角字符
     * 
     * @param source
     * @return
     */
    public static String convertStringToNarrow(String source) {
        StringBuffer result = new StringBuffer();
        char[] ch = source.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == 12288) {
                result.append(' ');
            } else if (ch[i] == 12290) {
                result.append('.');
            } else if (ch[i] >= 65281 && ch[i] <= 65374) {
                result.append((char) (ch[i] - 65248));
            } else {
                result.append(ch[i]);
            }
        }
        return result.toString();
    }

    /**
     * 半角字符转换全角字符
     * 
     * @param source
     * @return
     */
    public static String convertStringToWide(String source) {
        StringBuffer result = new StringBuffer();
        char[] ch = source.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == 32) {
                result.append('　');
            } else if (ch[i] == 46) {
                result.append('。');
            } else if (ch[i] >= 33 && ch[i] <= 126) {
                result.append((char) (ch[i] + 65248));
            } else {
                result.append(ch[i]);
            }
        }
        return result.toString();
    }

    /**
     * 从get/set方法名中提取出属性名称
     * 
     * @return
     */
    public static String getPropertyNameFromGetSet(String methodName) {
        if (null == methodName) {
            return null;
        }
        if (methodName.length() <= 3 || (!methodName.startsWith("get") && !methodName.startsWith("set"))) {
            return methodName;
        }
        return uncapitalize(methodName.substring(3));
    }

    /**
     * 骆驼命名法转换成数据库字段命名法，如studentName-->student_name
     * 
     * @param propertyName 属性名称
     * @param prefix 添加前缀
     * @param stuffix 添加后缀
     * @return
     */
    public static String splitCamelName(String propertyName, String prefix, String stuffix) {
        if (isNotEmpty(propertyName)) {
            return propertyName;
        }
        char[] dest = new char[propertyName.length()];
        propertyName.getChars(0, propertyName.length(), dest, 0);
        StringBuilder builder = new StringBuilder();
        if (isNotEmpty(prefix)) {
            builder.append(prefix).append("_");
        }
        for (int i = 0; i < dest.length; i++) {
            if (i == 0) {
                builder.append(Character.toLowerCase(dest[i]));
                continue;
            }
            if (Character.isUpperCase(dest[i])) {
                builder.append("_").append(Character.toLowerCase(dest[i]));
            } else {
                builder.append(dest[i]);
            }
        }
        if (isNotEmpty(stuffix)) {
            builder.append("_").append(stuffix);
        }
        return builder.toString();
    }

    /**
     * 骆驼命名法转换成数据库字段命名法(重载)，如studentName-->student_name
     * 
     * @param propertyName 属性名称
     * @return
     */
    public static String splitCamelName(String propertyName) {
        return splitCamelName(propertyName, null, null);
    }

    /**
     * 数据库字段名称转换成类属性名，如stu_name-->stuName
     * 
     * @param fieldName 数据库字段名称
     * @param prefix 前缀
     * @param stuffix 后缀
     * @return
     */
    public static String splitDBFieldName(String fieldName, String prefix, String stuffix) {
        if (isEmpty(fieldName)) {
            return fieldName;
        }
        if (isNotEmpty(prefix)) {
            if (prefix.endsWith("_")) {
                fieldName = fieldName.replaceAll("^" + prefix + "(.*)", "$1");
            } else {
                fieldName = fieldName.replaceAll("^" + prefix + "_(.*)", "$1");
            }
        } else {
            fieldName = fieldName.replaceAll("^_(.*)", "$1");
        }
        if (isNotEmpty(stuffix)) {
            if (stuffix.startsWith("_")) {
                fieldName = fieldName.replaceAll("(.*)" + stuffix + "$", "$1");
            } else {
                fieldName = fieldName.replaceAll("(.*)_" + stuffix + "$", "$1");
            }
        } else {
            fieldName = fieldName.replaceAll("(.*)_$", "$1");
        }
        if (fieldName.indexOf("_") == -1) {
            return fieldName;
        }
        String[] array = fieldName.split("_");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                builder.append(array[i].toLowerCase());
            } else {
                builder.append(capitalize(array[i]));
            }
        }
        return builder.toString();
    }

    /**
     * 数据库字段名称转换成类属性名(重载)，如stu_name-->stuName
     * 
     * @param fieldName 数据库字段名称
     * @return
     */
    public static String splitDBFieldName(String fieldName) {
        return splitDBFieldName(fieldName, null, null);
    }

    /**
     * 剔除结尾字符(如最后一个换行符、最后一个逗号等等)
     * 
     * @param str 待处理字符串
     * @param regular 正则表达式
     * @return 返回剔除后的字符
     */
    public static String replaceEndsWith(String str, String regular) {
        if (isEmpty(str)) {
            return null;
        }
        if (isEmpty(regular)) {
            return str;
        }
        return str.replaceAll(regular, "");
    }

    private static MessageDigest getDigest() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return md;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 
     * @Title: generateSessionId @Description:
     *         生成随机的SessionId @param @return @return String @throws
     */
    public static String generateSessionId() {
        // 取随机数发生器, 默认是SecureRandom
        Random random = new SecureRandom();
        byte bytes[] = new byte[SESSION_ID_BYTES];
        // 产生16字节的byte
        random.nextBytes(bytes);
        // 取摘要,默认是"MD5"算法
        bytes = getDigest().digest(bytes);
        StringBuffer result = new StringBuffer();
        // 转化为16进制字符串
        for (int i = 0; i < bytes.length; i++) {
            byte b1 = (byte) ((bytes[i] & 0xf0) >> 4);
            byte b2 = (byte) (bytes[i] & 0x0f);
            if (b1 < 10) {
                result.append((char) ('0' + b1));
            } else {
                result.append((char) ('A' + (b1 - 10)));
            }
            if (b2 < 10) {
                result.append((char) ('0' + b2));
            } else {
                result.append((char) ('A' + (b2 - 10)));
            }
        }
        return (result.toString());
    }

    /**
     * 
     * GBK to UTF-8
     * 
     * @param chenese
     * @return
     */
    public static String gbk2utf8(String gbk) {
        char c[] = gbk.toCharArray();
        byte[] fullByte = new byte[3 * c.length];
        for (int i = 0; i < c.length; i++) {
            int m = (int) c[i];
            String word = Integer.toBinaryString(m);

            StringBuffer sb = new StringBuffer();
            int len = 16 - word.length();
            // 补零
            for (int j = 0; j < len; j++) {
                sb.append("0");
            }
            sb.append(word);
            sb.insert(0, "1110");
            sb.insert(8, "10");
            sb.insert(16, "10");
            String s1 = sb.substring(0, 8);
            String s2 = sb.substring(8, 16);
            String s3 = sb.substring(16);

            byte b0 = Integer.valueOf(s1, 2).byteValue();
            byte b1 = Integer.valueOf(s2, 2).byteValue();
            byte b2 = Integer.valueOf(s3, 2).byteValue();
            byte[] bf = new byte[3];
            bf[0] = b0;
            fullByte[i * 3] = bf[0];
            bf[1] = b1;
            fullByte[i * 3 + 1] = bf[1];
            bf[2] = b2;
            fullByte[i * 3 + 2] = bf[2];
        }
        try {
            return new String(fullByte, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 
     * String型变量转换成int型变量
     * 
     * @param str 要进行转换的字符串
     * @return intVal 如果str不可以转换成int型数据，返回-1表示异常,否则返回转换后的值
     * @since 1.0
     */
    public static int str2Int(String str) {
        int intVal;
        try {
            intVal = Integer.parseInt(str);
        } catch (Exception e) {
            intVal = 0;
        }
        return intVal;
    }

    public static double str2Double(String str) {
        double dVal = 0;
        try {
            dVal = Double.parseDouble(str);
        } catch (Exception e) {
            dVal = 0;
        }
        return dVal;
    }

    public static long str2Long(String str) {
        long longVal = 0;
        try {
            longVal = Long.parseLong(str);
        } catch (Exception e) {
            longVal = 0;
        }
        return longVal;
    }

    public static float stringToFloat(String floatstr) {
        Float floatee;
        floatee = Float.valueOf(floatstr);
        return floatee.floatValue();
    }

    public static String floatToString(float value) {
        Float floatee = new Float(value);
        return floatee.toString();
    }

    /**
     * 
     * int型变量转换成String型变量
     * 
     * @param intVal 要进行转换的整数
     * @return str 如果intVal不可以转换成String型数据，返回空值表示异常,否则返回转换后的值
     */
    /**
     * int型变量转换成String型变量
     * 
     * @param intVal 要进行转换的整数
     * @return str 如果intVal不可以转换成String型数据，返回空值表示异常,否则返回转换后的值
     */
    public static String int2Str(int intVal) {
        String str;
        try {
            str = String.valueOf(intVal);
        } catch (Exception e) {
            str = "";
        }
        return str;
    }

    /**
     * 
     * long型变量转换成String型变量
     * 
     * @param longVal 要进行转换的整数
     * @return str 如果longVal不可以转换成String型数据，返回空值表示异常,否则返回转换后的值
     */

    public static String long2Str(long longVal) {
        String str;
        try {
            str = String.valueOf(longVal);
        } catch (Exception e) {
            str = "";
        }
        return str;
    }

    /**
     * 
     * null 处理
     * 
     * @param str 要进行转换的字符串
     * @return 如果str为null值，返回空串"",否则返回str
     */
    public static String null2Blank(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    /**
     * 
     * null 处理
     * 
     * @param d 要进行转换的日期对像
     * @return 如果d为null值，返回空串"",否则返回d.toString()
     */

    public static String null2Blank(Date d) {
        if (d == null) {
            return "";
        }
        return d.toString();
    }

    /**
     * 
     * null 处理
     * 
     * @param str 要进行转换的字符串
     * @return 如果str为null值，返回空串整数0,否则返回相应的整数
     */
    public static int null2Zero(String str) {
        int intTmp;
        intTmp = str2Int(str);
        if (intTmp == -1) {
            return 0;
        }
        return intTmp;
    }

    /**
     * 
     * 把null转换为字符串"0"
     * 
     * @param str
     * @return
     */
    public static String null2SZero(String str) {
        str = null2Blank(str);
        if (str.equals("")) {
            return "0";
        }
        return str;
    }

    /**
     * @Author Lanxiaowei @Title: isNumeric @Description: 判断字符串是否全部由数字组成 @param
     *         str @return @return boolean @throws
     */
    public static boolean isNumeric(String str) {
        if (isBlank(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 格式化字符串，去除空的元素
     * <p>
     * 例如：<br>
     * ,23,23,------>23,23<br>
     * ,23,23------>23,23<br>
     * 23, ,23,------>23,23<br>
     * 
     * @param source 源字符串
     * @return 格式化之后的字符串
     */
    public static String trimStrArrs(String source) {
        String target = null;
        if (source != null && source.trim().length() != 0) {
            StringBuffer sb = new StringBuffer();
            String arrs[] = source.trim().split(",");
            for (int i = 0; i < arrs.length; i++) {
                String tmp = arrs[i].trim();
                if (tmp.length() != 0) {
                    sb.append(tmp).append(",");
                }
            }
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
            }
            target = sb.toString();
        } else {
            target = "";
        }
        return target;
    }

    /**
     * 切割字符串，区分中英文 <blockquote>
     * 
     * <pre>
     * subTextString("1。发.。篇>所q阿s似hf的f＊*发千万s",3,1) returns "。"
     * subTextString("1。发.。篇>所q阿s似hf的f＊*发千万s",3,2) returns "。篇"
     * subTextString("1。发.。篇>所q阿s似hf的f＊*发千万s",3,3) returns "。篇>"
     * subTextString("1。发.。篇>所q阿s似hf的f＊*发千万s",3,4) returns "。篇>所q"
     * </pre>
     * 
     * </blockquote>
     * 
     * @param text 源字符串
     * @param start 偏移量 偏移多少个中文
     * @param length 取中文的长度
     * @return
     */
    public static String subTextString(String text, double start, double length) {
        if (start > 0) {
            String prefix = subTextString(text, 0, start);
            if (prefix.equals(text)) {
                return null;
            }
            text = text.substring(prefix.length());
        }
        int textLength = text.length();
        int byteLength = 0;
        StringBuffer returnStr = new StringBuffer();
        for (int i = 0; i < textLength && byteLength < length * 2; i++) {
            String str_i = text.substring(i, i + 1);
            if (str_i.getBytes().length == 1) {// 英文
                byteLength++;
            } else {// 中文
                byteLength += 2;
            }
            if (byteLength <= length * 2) {
                returnStr.append(str_i);
            }
        }
        return returnStr.toString();
    }

    /**
     * 检测字符串的长度，中文占2位 <blockquote>
     * 
     * <pre>
     * getCharLen("1") returns 1
     * getCharLen("1发") returns 3
     * getCharLen("1。发" returns 5
     * </pre>
     * 
     * </blockquote>
     * 
     * @param text
     * @return
     */
    public static int getCharLen(String text) {
        int textLength = text.length();
        int byteLength = 0;
        for (int i = 0; i < textLength; i++) {
            String str_i = text.substring(i, i + 1);
            if (str_i.getBytes().length == 1) {// 英文
                byteLength++;
            } else {// 中文
                byteLength += 2;
            }
        }
        return byteLength;
    }

    /**
     * 去除传入的ver中的英文字符
     * 
     * @param ver
     * @return
     */
    public static String getVer(String ver) {
        return ver.replaceAll("[a-zA-Z]", "");
    }

    /**
     * 转换成string
     * 
     * @param obj
     * @return
     */
    public static String getString(Object obj) {
        if (ObjectUtils.isEmpty(obj)) {
            return "";
        }
        return obj.toString();
    }

    /**
     * 转换成string
     * 
     * @param str
     * @return
     */
    public static String getString(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        return str;
    }
}
