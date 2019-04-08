public class Main {

    public static void main(String[] args) {

        XMLManagement meuGestor = new XMLManagement("D:/PEI/PEI/product.xml","D:/PEI/PEI/productXSD.xsd");
        meuGestor.read(true);
        System.out.print(meuGestor.validate(true));
    }
}
