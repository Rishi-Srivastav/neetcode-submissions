class Solution {
    public String longestPalindrome(String s) {
        String longstr = "";

        for (int i = 0; i < s.length(); i++) {
            String odd = expand(s, i, i);
            String even = expand(s, i, i + 1);

            String slong = odd.length() > even.length() ? odd : even;

            longstr = longstr.length() > slong.length()
                    ? longstr
                    : slong;
        }

        return longstr;
    }

    public String expand(String s, int i, int j) {
        int start = i;
        int end = j;

    while (i >= 0 && j < s.length()
            && s.charAt(i) == s.charAt(j)) {

        i--;
        j++;
    }

    return s.substring(i + 1, j);
    }
}