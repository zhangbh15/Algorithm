package Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Amazon Fresh is running a promotion in which customers receive prizes for
 * purchasing a secret combination of fruits. The combination will change each
 * day, and the team running the promotion wants to use a code list to make it
 * easy to change the combination. The code list contains groups of fruits.
 * Both the order of the groups within the code list and the order of the fruits
 * within the groups matter. However, between the groups of fruits, any number,
 * and type of fruit is allowable. The term "anything" is used to allow for any
 * type of fruit to appear in that location within the group.
 *
 * Consider the following secret code list: [[apple, apple], [banana, anything, banana]]
 * Based on the above secret code list, a customer who made either of the following
 * purchases would win the prize:
 * orange, apple, apple, banana, orange, banana
 * apple, apple, orange, orange, banana, apple, banana, banana
 * Write an algorithm to output 1 if the customer is a winner else output 0.
 *
 * Input
 * The input to the function/method consists of two arguments:
 * codeList, a list of lists of strings representing the order and grouping of
 * specific fruits that must be purchased in order to win the prize for the day.
 * shoppingCart, a list of strings representing the order in which a customer
 * purchases fruit.
 * Output
 * Return an integer 1 if the customer is a winner else return 0.
 * Note
 * 'anything' in the codeList represents that any fruit can be ordered in place of
 * 'anything' in the group. 'anything' has to be something, it cannot be "nothing."
 * 'anything' must represent one and only one fruit.
 * If secret code list is empty then it is assumed that the customer is a winner.
 *
 * Example 1:
 *
 * Input: codeList = [[apple, apple], [banana, anything, banana]]
 * shoppingCart = [orange, apple, apple, banana, orange, banana]
 * Output: 1
 * Explanation:
 * codeList contains two groups - [apple, apple] and [banana, anything, banana].
 * The second group contains 'anything' so any fruit can be ordered in place of
 * 'anything' in the shoppingCart. The customer is a winner as the customer has
 * added fruits in the order of fruits in the groups and the order of groups in the
 * codeList is also maintained in the shoppingCart.
 *
 * Example 2:
 *
 * Input: codeList = [[apple, apple], [banana, anything, banana]]
 * shoppingCart = [banana, orange, banana, apple, apple]
 * Output: 0
 * Explanation:
 * The customer is not a winner as the customer has added the fruits in order of groups
 * but group [banana, orange, banana] is not following the group [apple, apple] in the codeList.
 *
 * Example 3:
 *
 * Input: codeList = [[apple, apple], [banana, anything, banana]]
 * shoppingCart = [apple, banana, apple, banana, orange, banana]
 * Output: 0
 * Explanation:
 * The customer is not a winner as the customer has added the fruits in an order which is not
 * following the order of fruit names in the first group.
 *
 * Example 4:
 *
 * Input: codeList = [[apple, apple], [apple, apple, banana]]
 * shoppingCart = [apple, apple, apple, banana]
 * Output: 0
 * Explanation:
 * The customer is not a winner as the first 2 fruits form group 1, all three fruits would
 * form group 2, but can't because it would contain all fruits of group 1.
 */
public class AmazonFreshPromotion {
    private static final String ANYTHING = "anything";

    public int canWin(List<List<String>> codeList, List<String> shoppingCart) {
        if (codeList == null || codeList.size() == 0 || shoppingCart == null || shoppingCart.size() == 0) {
            return 0;
        }

        return dfs(codeList, shoppingCart, 0, 0) ? 1 : 0;
    }

    private boolean dfs(List<List<String>> codeList, List<String> shoppingCart, int codeIdx, int itemIdx) {
        if (codeIdx == codeList.size()) {
            return true;
        }
        List<String> code = codeList.get(codeIdx);
        if (itemIdx + code.size() > shoppingCart.size()) {
            return false;
        }

        for (int i = 0; i < code.size(); i++) {
            String str = code.get(i);
            if (str.equals(ANYTHING) || str.equals(shoppingCart.get(itemIdx + i))) {
                continue;
            }

            return dfs(codeList, shoppingCart, codeIdx, itemIdx + 1);
        }

        return dfs(codeList, shoppingCart, codeIdx + 1, itemIdx + code.size());
    }

    public static void main(String[] args) {
        // test cases
        String[][] codeList1 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
        String[] shoppingCart1 = {"orange", "apple", "apple", "banana", "orange", "banana"};
        String[][] codeList2 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
        String[] shoppingCart2 = {"banana", "orange", "banana", "apple", "apple"};
        String[][] codeList3 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
        String[] shoppingCart3 = {"apple", "banana", "apple", "banana", "orange", "banana"};
        String[][] codeList4 = { { "apple", "apple" }, { "apple", "apple", "banana" } };
        String[] shoppingCart4 = {"apple", "apple", "apple", "banana"};
        String[][] codeList5 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
        String[] shoppingCart5 = {"orange", "apple", "apple", "banana", "orange", "banana"};
        String[][] codeList6 = { { "apple", "apple" }, { "banana", "anything", "banana" }  };
        String[] shoppingCart6 = {"apple", "apple", "orange", "orange", "banana", "apple", "banana", "banana"};
        String[][] codeList7= { { "anything", "apple" }, { "banana", "anything", "banana" }  };
        String[] shoppingCart7 = {"orange", "grapes", "apple", "orange", "orange", "banana", "apple", "banana", "banana"};
        String[][] codeList8 = {{"apple", "orange"}, {"orange", "banana", "orange"}};
        String[] shoppingCart8 = {"apple", "orange", "banana", "orange", "orange", "banana", "orange", "grape"};
        String[][] codeList9= { { "anything", "anything", "anything", "apple" }, { "banana", "anything", "banana" }  };
        String[] shoppingCart9 = {"orange", "apple", "banana", "orange", "apple", "orange", "orange", "banana", "apple", "banana"};

        // test
        test(codeList1, shoppingCart1, 1);
        test(codeList2, shoppingCart2, 0);
        test(codeList3, shoppingCart3, 0);
        test(codeList4, shoppingCart4, 0);
        test(codeList5, shoppingCart5, 1);
        test(codeList6, shoppingCart6, 1);
        test(codeList7, shoppingCart7, 1);
        test(codeList8, shoppingCart8, 1);
        test(codeList9, shoppingCart9, 1);
    }

    public static void test(String[][] codeList, String[] shoppingCart, int expect) {
        AmazonFreshPromotion so = new AmazonFreshPromotion();
        List<List<String>> codes = new ArrayList<>();
        for (String[] strs : codeList) {
            codes.add(Arrays.asList(strs));
        }


        System.out.println(so.canWin(codes, Arrays.asList(shoppingCart)) == expect);
    }

}
