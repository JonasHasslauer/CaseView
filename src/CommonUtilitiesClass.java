public class CommonUtilitiesClass {

    public static String setRightSpelling(String rawString){
        StringBuilder sb = new StringBuilder();

        sb.append(Character.toUpperCase(rawString.toCharArray()[0]));
        for (int i = 1; i < rawString.toCharArray().length; i++) {
            sb.append(Character.toLowerCase(rawString.toCharArray()[i]));
        }
        return sb.toString();

    }

}
