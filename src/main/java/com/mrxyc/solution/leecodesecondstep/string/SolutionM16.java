package com.mrxyc.solution.leecodesecondstep.string;

/**
 * 模式匹配
 *
 * 你有两个字符串，即pattern和value。 pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。
 *
 * 例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），该字符串也匹配像"a"、"ab"和"b"这样的模式。
 *
 * 但需注意"a"和"b"不能同时表示相同的字符串。编写一个方法判断value字符串是否匹配pattern字符串。
 */
public class SolutionM16 {

    public static void main(String[] args) {
        SolutionM16 solutionM16 = new SolutionM16();
        System.out.println(solutionM16.patternMatching("bbbaa",
                "xxxxxxy"));
    }


    public boolean patternMatching(String pattern, String value) {
        int aCount = 0;
        int bCount = 0;
        char[] chars = pattern.toCharArray();
        for (char ch : chars) {
            if (ch == 'a') {
                aCount++;
            } else {
                bCount++;
            }
        }
        //
        if (aCount < bCount) {
            int tmp = aCount;
            aCount = bCount;
            bCount = tmp;
            for (int i = 0; i < chars.length; i++) {
                chars[i] = chars[i] == 'a' ? 'b' : 'a';
            }
        }
        //如果value是空的，则看最小的count是否是0
        if (value.length() == 0) {
            return bCount == 0;
        }
        //如果pattern为空 则失败
        if (pattern.length() == 0) {
            return false;
        }
        //枚举a的长度
        for (int aLen = 0; aCount * aLen <= value.length(); aLen++) {
            //b的总长度
            int rest = value.length() - aCount * aLen;
            //没有b或者b刚好整除剩余长度
            if ((bCount == 0 && rest == 0) || (bCount != 0 && rest % bCount == 0)) {
                int bLen = bCount == 0 ? 0 : rest / bCount;
                boolean correct = true;
                int pos = 0;
                String aValue = "";
                String bValue = "";
                for (char ch : chars) {
                    if (ch == 'a') {
                        String curAValue = value.substring(pos, pos + aLen);
                        if (!"".equals(aValue)) {
                            correct = aValue.equals(curAValue);
                            if (!correct) {
                                break;
                            }
                        } else {
                            aValue = curAValue;
                        }
                        pos = pos + aLen;
                    } else {
                        String curBValue = value.substring(pos, pos + bLen);
                        if (!"".equals(bValue)) {
                            correct = bValue.equals(curBValue);
                            if (!correct) {
                                break;
                            }
                        } else {
                            bValue = curBValue;
                        }
                        pos = pos + bLen;
                    }
                }
                if (correct && !aValue.equals(bValue)) {
                    return true;
                }
            }
        }
        return false;
    }
}
