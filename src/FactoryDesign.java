public class FactoryDesign {
    public  Checker createChecker(String mode,int[][] data){
    
 if(mode.equals("0"))
 return new Mode0(data);

else if (mode.equals("3")) {
    return new Mode3Verifier(data);
}

else if (mode.equals("27")) {
    return new Mode27Verifier(data);
}

    return null;
}

}
