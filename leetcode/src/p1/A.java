package p1;

public class A {
    public static void main(String[] args) {
        String s = "ab#c";String t = "ad#c";
         // backspaceCompare(s,t);
        System.out.println(backSpace());
    }

     public static boolean backSpace(){
         String s = "ab#c";String t = "ad#c";
         int i = s.length()-1;
         int j = s.length()-1;
         int skipS=0;
         int skipT = 0;
         while(i >=0 || j>=0){
             while(i>=0){
                 if(s.charAt(i) =='#'){
                     skipS++;
                     i--;
                 }else if(skipS > 0){
                     skipS--;
                     i--;
                 }else{
                     break;
                 }
             }

             while(j >=0){
                 if(s.charAt(j) == '#'){
                     skipT++;
                     j--;
                 }else if(skipT > 0){
                     skipT--;
                     j--;
                 }else{
                     break;
                 }
             }
             if(i >=0 || j>=0){
                 if(s.charAt(i) != t.charAt(j)){
                     return false;
                 }
             }else{
                 if(i >=0 || j>=0){
                     return false;
                 }
             }
             i--;
             j--;
         }
         return true;
     }




    // back space string compare
    public static boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;

        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) {

            // Process s
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else break;
            }

            // Process t
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else break;
            }

            // Compare characters
            if (i >= 0 && j >= 0) {
                if (s.charAt(i) != t.charAt(j)) return false;
            } else {
                if (i >= 0 || j >= 0) return false;
            }

            i--;
            j--;
        }

        return true;
    }
}
