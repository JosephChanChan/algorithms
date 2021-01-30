package depth.first.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Joseph
 * @since 2020-03-25 22:22
 *
 * leetcode 17 medium
 *
 * Question Description:
 *  Given a string containing digits from 2-9 inclusive,
 *  return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Note that 1 does not map to any letters.
 * （图看leetcode官网）
 * Example:
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 *
 * Analysis:
 *  组合题。耗时28min AC
 *  时间复杂度：O(C(charArray.length, digits.length)) 从字符数组中选择digits长度个字符组合的数量
 *  空间复杂度：O(C(charArray.length, digits.length)) 存储每种组合的容器所占用的空间。递归深度是digits.length，相比容器占用空间可忽略不计
 */
public class LetterCombinationsPhoneNumber {

    private Map<String, String> mapping = new HashMap<>(10);

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        initializeMapping();
        if (digits.equals("") || digits.equals("1")) return result;

        String[] nums = digits.split("");
        recursion(0, nums, new StringBuilder(), result);
        return result;
    }

    private void recursion(int level, String[] nums, StringBuilder builder, List<String> list) {
        if (level == nums.length) {
            list.add(builder.toString());
            return;
        }

        String letters = mapping.get(nums[level]);
        char[] chars = letters.toCharArray();
        for (char cc : chars) {
            builder.append(cc);
            recursion(level + 1, nums, builder, list);
            builder.deleteCharAt(level);
        }
    }

    private void initializeMapping() {
        mapping.put("2", "abc");
        mapping.put("3", "def");
        mapping.put("4", "ghi");
        mapping.put("5", "jkl");
        mapping.put("6", "mno");
        mapping.put("7", "pqrs");
        mapping.put("8", "tuv");
        mapping.put("9", "wxyz");
    }


}
