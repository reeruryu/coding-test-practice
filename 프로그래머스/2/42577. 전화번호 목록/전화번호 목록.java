import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean solution(String[] phoneBook) {

        Set<String> set = new HashSet<>();

        for (String phone: phoneBook) {
            set.add(phone);
        }
        
        for (String phone: phoneBook) {
            for (int i = 1; i < phone.length(); i++) {
                // System.out.println(phone.substring(0, i));
                if (set.contains(phone.substring(0, i))) {
                    return false;
                }
            }
        }

        return true;
    }
}