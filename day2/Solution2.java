class Solution2 {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder("");
        
        int start = 0;
        while (start < number.length() && answer.length() != number.length() - k) {
            int fixIdx = k + answer.length() + 1;
            int maxIdx = start;
            int max = number.charAt(maxIdx) - 48;
            for (int i = start; i < fixIdx; i++) {
                if (number.charAt(i) - 48 > max) { 
                    max = number.charAt(i) - 48;
                    maxIdx = i;
                }
            }
            answer.append(max);
            start = maxIdx + 1;
        }
        
        return answer.toString();
    }
}
