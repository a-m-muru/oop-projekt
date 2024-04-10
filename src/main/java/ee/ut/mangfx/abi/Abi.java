package abi;

public class Abi {
    public static int lisaJarjendiLoppu(Object[] jarjend, Object asi) {
        for (int i = 0; i < jarjend.length; i++) {
            if (jarjend[i] == asi) {
                return i;
            }
            if (jarjend[i] == null) {
                jarjend[i] = asi;
                return i;
            }
        }
        return -1;
    }
}
