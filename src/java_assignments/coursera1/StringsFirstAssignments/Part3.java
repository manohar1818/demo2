package java_assignments.coursera1.StringsFirstAssignments;



public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        if(stringb.contains(stringa)){
            stringb = stringb.replaceFirst(stringa,"");
            if(stringb.contains(stringa)){
                return true;
            }
            else
                return false;
        }
            return false;
    }

    public String lastPart(String stringa, String stringb) {
        int index = stringb.indexOf(stringa);
        if(index == -1)
            return  stringb;
        else {
            index= index + stringa.length();
            return stringb.substring(index);
        }
    }

    public void testing() {
        String stringa = "a";
        String stringb = "Java";
        System.out.println(stringa + " , " + stringb + ": " + twoOccurrences(stringa, stringb));
        stringa = "A java programmer always enjoy java";
        stringb = "java";
        System.out.println(stringa + ", " + stringb + " " + twoOccurrences(stringa, stringb));
        stringa = "hello world";
        stringb = "hello";
        System.out.println(stringa + " ," + stringb + " " + twoOccurrences(stringa, stringb));

        stringa = "an";
        stringb = "banana";
        System.out.println("The part of the string after " + stringa + " in " + stringb + " is "
                + lastPart(stringa, stringb));
        stringa = "zoo";
        stringb = "forest";
        System.out.println("The part of the string after " + stringa + " in " + stringb + " is "
                + lastPart(stringa, stringb));
    }

    public static void main(String[] args) {
        Part3 part3 = new Part3();
        part3.testing();
    }
}