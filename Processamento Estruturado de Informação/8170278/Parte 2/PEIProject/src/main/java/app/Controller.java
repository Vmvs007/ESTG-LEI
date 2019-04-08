package app;

import org.json.JSONArray;
import org.json.JSONObject;

import static java.lang.Math.toIntExact;

import org.json.XML;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Building a RESTful Web Service retrieved from: https://spring.io/guides/gs/rest-service/#scratch
 * https://spring.io/guides/gs/rest-service/#scratch
 * Additionally see: https://spring.io/guides/gs/accessing-mongodb-data-rest/
 */
@RestController
public class Controller {

    @RequestMapping("/getRestaurants")
    public String getRestaurants(@RequestParam(value = "value") String value) {
        MongoConnector mongo = new MongoConnector();
        String res = mongo.getData("PEIProject", "SalesDetails", "borough", value);
        return res;
    }

    @RequestMapping("/aggregateRestaurantsByQueryString")
    public String aggregateRestaurantsByQueryString(@RequestParam(value = "query") String query) {
        //Example: %5B%7B%24group%3A%7B%22_id%22%3Anull%2Ccount%3A%7B%24sum%3A1%7D%7D%7D%5D para [{$group:{"_id":null,count:{$sum:1}}}] -> utilizar: https://meyerweb.com/eric/tools/dencoder/
        MongoConnector mongo = new MongoConnector();
        String res = mongo.aggregateDataByQueryString("restaurantsDB", "restaurants", query);
        return res;
    }

    @RequestMapping("/getdata")
    public String getData(@RequestParam(value = "store") String store, @RequestParam(value = "month") int mes, @RequestParam(value = "year") int ano) throws IOException {
        String out = "";
        MongoConnector mongo = new MongoConnector();
        String data;
        if (mes < 10)
            data = "" + ano + "-0" + mes + "";
        else
            data = "" + ano + "-" + mes + "";
        String lojaInfo = "[{$match:{StoreName:{$eq:'" + store + "'}}}]";

        String query = "[{$match:{StoreName: {$eq: '" + store + "'},OrderDate:{$gte:'" + data + "-01 00:00:00.000',$lte:'" + data + "-31 00:00:00.000'}}},{$group:{_id:{id:'$ReceiptID',data:'$OrderDate',cliente:'$Customer',currencyId:'$CurrencyRateID',total:'$SubTotal',taxAmt:'$TaxAmt',store:'$StoreName',storeid:'$Store'},produtos:{$push:{id:'$ProductID',quantidade:'$Quantity',lineId:'$ReceiptLineID',precoUnidade:'$UnitPrice',lineTotal:'$LineTotal'}}}}]"; // Todas as vendas de uma loja num determinado exercicio

        String query5 = "[{$match:{StoreName: {$eq: '" + store + "'}}},{$group:{_id:'$StoreName',TotalProductsByStore:{$sum:'$Quantity'}}}]";//Total de Produtos por store
        String query6 = "[{$match:{StoreName: {$eq: '" + store + "'}}},{$group:{_id:'$ReceiptID',first:{$first:'$SubTotal'}}},{$group:{_id:Soma,TotalSales:{$sum:'$first'}}}]";//Total das vendas por loja
        String query7 = "[{$match:{StoreName: {$eq: '" + store + "'}}},{$group:{_id:'$StoreName',AveragePrice:{$avg:'$UnitPrice'}}}]";// Valor médio do preço de venda dos produtos por loja
        String query8 = "[{$match:{StoreName: {$eq: '" + store + "'},OrderDate:{$gte:'" + data + "-01 00:00:00.000',$lte:'" + data + "-31 00:00:00.000'}}},{$group:{_id:'$ProductID'}},{$group:{_id:1,TotalDiffProducts:{$sum:1}}}]";// Número total de produtos diferentes por exercicio
        String query9 = "[{$match:{StoreName: {$eq: '" + store + "'},OrderDate:{$gte:'" + data + "-01 00:00:00.000',$lte:'" + data + "-31 00:00:00.000'}}},{$group:{_id:'$Customer'}},{$group:{_id:1,TotalDiffClients:{$sum:1}}}]";//Número total de clientes diferentes por exercicio
        String query10 = "[{$match:{StoreName: {$eq: '" + store + "'},OrderDate:{$gte:'" + data + "-01 00:00:00.000',$lte:'" + data + "-31 00:00:00.000'}}},{$group:{_id:'$Customer',TotalSalesPerClient:{$sum:'$LineTotal'}}},{$sort:{TotalSalesPerClient: -1}}]"; //Valor vendido por cada cliente
        String query11 = "[{$match:{StoreName: {$eq: '" + store + "'},OrderDate:{$gte:'" + data + "-01 00:00:00.000',$lte:'" + data + "-31 00:00:00.000'}}},{$group:{_id:'$ProductID',TotalUnitsPerProduct:{$sum:'$Quantity'}}},{$sort:{TotalUnitsPerProduct: -1}}]"; //Por produto, apresentar o total de unidade vendidas

        String query13 = "[{$match:{StoreName: {$eq: '" + store + "'},OrderDate:{$gte:'" + data + "-01 00:00:00.000',$lte:'" + data + "-31 00:00:00.000'}}},{$group:{_id:'$StoreName',TotalProductsByStore:{$sum:'$Quantity'}}}]";//Total de Produtos por exercicio


        String vendas = mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query);
        /*out += "Query 2 - " +mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query2)+"<br/>";
        out += "Query 3 - " +mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query3)+"<br/>";
        out += "Query 4 - " +mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query4)+"<br/>";
        out += "Query 5 - " +mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query5)+"<br/>";
        out += "Query 6 - " +mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query6)+"<br/>";
        out += "Query 7 - " +mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query7)+"<br/>";
        out += "Query 8 - " +mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query8)+"<br/>";
        out += "Query 9 - " +mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query9)+"<br/>";
        out += "Query 10 - " +mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query10)+"<br/>";
        out += "Query 11 - " +mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query11)+"<br/>";
        out += "Query 12 - " +mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query12)+"<br/>";
        out += "Query 13 - " +mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query13)+"<br/>";
*/

        JSONArray vendasifos = new JSONArray(vendas);
        String storeInfo = mongo.aggregateDataByQueryString("PEIProject", "StoreDetails", lojaInfo);
        JSONArray storeinfo = new JSONArray(storeInfo);

        String storeInfoXml = "" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<store xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n "+
	"xmlns=\"http://www.BikeOnTrack.pt/Store\" \n"+
	"xsi:schemaLocation=\"http://www.BikeOnTrack.pt/Store storeXSD.xsd\" \n"+
	"xmlns:cr=\"http://www.BikeOnTrack.pt/CurrencyRate\" \n"+
	"xmlns:sl=\"http://www.BikeOnTrack.pt/saleLine\" \n"+
	"xmlns:pr=\"http://www.BikeOnTrack.pt/Product\" \n"+
	"xmlns:cu=\"http://www.BikeOnTrack.pt/Customer\" \n"+
	"xmlns:sal=\"http://www.BikeOnTrack.pt/sale\" \n"+
	"xmlns:ex=\"http://www.BikeOnTrack.pt/Exercise\"> \n";

        for (Object a : vendasifos) {
           storeInfoXml+="\t<storeID>" + ((JSONObject)((JSONObject) a).get("_id")).get("storeid") + "</storeID>\n" +
                "<name>" + ((JSONObject)((JSONObject) a).get("_id")).get("store") + "</name>\n" ;
        }


        for (Object a : storeinfo) {


                   storeInfoXml+=

                    "\t<nif>" + ((JSONObject) a).get("Nif") + "</nif>\n" +
                    "\t<address>\n" +
                    "\t\t<street>" + ((JSONObject) a).getString("Street") + "</street>\n" +
                    "\t\t<doorNum>" + ((JSONObject) a).get("DoorNum") + "</doorNum>\n" +
                    "\t\t<postalCode>" + ((JSONObject) a).getString("PostalCode") + "</postalCode>\n" +
                    "\t\t<city>" + ((JSONObject) a).getString("City") + "</city>\n" +
                    "\t\t<district>" + ((JSONObject) a).getString("District") + "</district>\n" +
                    "\t\t<country>" + ((JSONObject) a).getString("Country") + "</country>\n" +
                    "\t</address>\n" +
                    "\t<contacts>\n" +
                    "\t\t<phone>" + ((JSONObject) a).get("Phone") + "</phone>\n" +
                    "\t\t<email>" + ((JSONObject) a).getString("Email") + "</email>\n" +
                    "\t</contacts>\n";
        }

        String totalProducts = mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query5);
        JSONArray totalproducts = new JSONArray(totalProducts);
        String totalProductsXml = "";
        for (Object a : totalproducts) {
            totalProductsXml = "<totalProducts>" + ((JSONObject) a).get("TotalProductsByStore") + "</totalProducts>\n";
        }

        String totalSales = mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query6);

        JSONArray totalsales = new JSONArray(totalSales);
        String totalSalesXml = "";
        for (Object a : totalsales) {
            totalSalesXml = "<totalSales>" + ((JSONObject) a).get("TotalSales") + "</totalSales>\n";
        }

        String averagePrice = mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query7);

        JSONArray averageprice = new JSONArray(averagePrice);
        String averagePriceXml = "";
        for (Object a : averageprice) {
            averagePriceXml = "<avegePrice>" + ((JSONObject) a).get("AveragePrice") + "</avegePrice>\n";
        }


        String exercicio = "<exercise>\n" +
                "\t<ex:startDate>" + data + "-01</ex:startDate>\n" +
                "\t<ex:endDate>" + data + "-31</ex:endDate>\n" +
                "\t<ex:exerciseDate>" + data + "-31</ex:exerciseDate>\n";
        String exercicioTotalProducts = mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query13);
        JSONArray exerciciototalProducts = new JSONArray(exercicioTotalProducts);
        for (Object a : exerciciototalProducts) {
            exercicio += "\t<ex:totalProducts>" + ((JSONObject) a).get("TotalProductsByStore") + "</ex:totalProducts>\n";
        }



        String exercicioTotalDiffProducts = mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query8);
        JSONArray exerciciototalDiffProducts = new JSONArray(exercicioTotalDiffProducts);
        for (Object a : exerciciototalDiffProducts) {
            exercicio += "\t<ex:totalDifProducts>" + ((JSONObject) a).get("TotalDiffProducts") + "</ex:totalDifProducts>\n";
        }



        String exercicioTotalDiffClients = mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query9);
        JSONArray exerciciototalDiffClients = new JSONArray(exercicioTotalDiffClients);
        for (Object a : exerciciototalDiffClients) {
            exercicio += "\t<ex:totalDifClients>" + ((JSONObject) a).get("TotalDiffClients") + "</ex:totalDifClients>\n";
        }



        String exercicioValuePerClient = mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query10);
        JSONArray exerciciovaluePerClient = new JSONArray(exercicioValuePerClient);
        exercicio += "\t<ex:valuePerClient>\n";
        for (Object a : exerciciovaluePerClient) {
            exercicio+="\t\t<ex:client>\n";
                 String queryAux = "[{$match:{Customer:{$eq:" + ((JSONObject) a).get("_id") + "}}}]";
                 exercicio += "\t\t<ex:clientID>" + ((JSONObject) a).get("_id") + "</ex:clientID>\n";
                 String JsonAux = mongo.aggregateDataByQueryString("PEIProject", "CustomerDetails", queryAux);
                 JSONArray clientInfojson = new JSONArray(JsonAux);
                    for (Object b : clientInfojson) {
                        exercicio += "\t\t<ex:clientName>" + ((JSONObject) b).get("CustomerName") + "</ex:clientName>\n";
                    }
                exercicio += "\t\t<ex:value>" + ((JSONObject) a).get("TotalSalesPerClient") + "</ex:value>\n";
            exercicio+="\t\t</ex:client>\n";
        }
        exercicio+="\t</ex:valuePerClient>\n";

        String exercicioSalesPerProduct = mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query11);
        JSONArray exercicioSalesPerProductJson = new JSONArray(exercicioSalesPerProduct);
        for (Object a : exercicioSalesPerProductJson) {
            exercicio += "\t<ex:salesPerProduct>\n";
                 String queryAux = "[{$match:{ProductID:{$eq:" + ((JSONObject) a).get("_id") + "}}}]";
                 exercicio += "\t\t<ex:productID>" + ((JSONObject) a).get("_id") + "</ex:productID>\n";
                 String JsonAux= mongo.aggregateDataByQueryString("PEIProject", "ProductDetails", queryAux);
                 JSONArray productInfo = new JSONArray(JsonAux);
                    for (Object b : productInfo) {
                        exercicio += "\t\t<ex:productName>" + ((JSONObject) b).get("Name") + "</ex:productName>\n";
                    }
                exercicio += "\t\t<ex:units>" + ((JSONObject) a).get("TotalUnitsPerProduct") + "</ex:units>\n";
            exercicio+="\t</ex:salesPerProduct>\n";
        }
        String query12 = "[{$match:{StoreName: {$eq: '"+store+"'},OrderDate:{$gte:'"+data+"-01 00:00:00.000',$lte:'"+data+"-31 00:00:00.000'}}},{$group: {_id:\"$CurrencyRateID\",TotalVendidoMoeda:{$sum:\"$LineTotal\"}}}]"; //Valor total da venda por cada moeda utilizada
        String totalPerCurrency = mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query12);
        System.out.println(query12);
        JSONArray totalPerCurrencyJson = new JSONArray(totalPerCurrency);
        for (Object a : totalPerCurrencyJson) {
            exercicio += "\t<ex:totalValuePerCurrency>\n";
                 String queryAux = "[{$match:{CurrencyRateID:{$eq:" + ((JSONObject) a).get("_id") + "}}}]";
                 String JsonAux= mongo.aggregateDataByQueryString("PEIProject", "CurrencyDetails", queryAux);
                 JSONArray productInfo = new JSONArray(JsonAux);
                 if(productInfo.isEmpty()){
                     exercicio += "\t\t<ex:currencyID>13503</ex:currencyID>\n";
                     exercicio += "\t\t<ex:currencyCode>USD</ex:currencyCode>\n";
                 }else{
                     exercicio += "\t\t<ex:currencyID>" + ((JSONObject) a).get("_id") + "</ex:currencyID>\n";
                     for (Object b : productInfo) {
                        exercicio += "\t\t<ex:currencyCode>" + ((JSONObject) b).get("FromCurrencyCode") + "</ex:currencyCode>\n";
                    }
                 }

                exercicio += "\t\t<ex:total>" + ((JSONObject) a).get("TotalVendidoMoeda") + "</ex:total>\n";
            exercicio+="\t</ex:totalValuePerCurrency>\n";
        }




        JSONArray exercicioSale = new JSONArray(vendas);
        for(Object a : exercicioSale){
            exercicio+="\t<ex:sale>\n";
            exercicio += "\t\t<sal:saleID>" + ((JSONObject)((JSONObject) a).get("_id")).get("id") + "</sal:saleID>\n";
            exercicio += "\t\t<sal:orderDate>" + (((String)((JSONObject)((JSONObject) a).get("_id")).get("data")).split(" "))[0] + "</sal:orderDate>\n";

            String clienteInfo = "[{$match:{Customer:{$eq:" + ((JSONObject)((JSONObject) a).get("_id")).get("cliente") + "}}}]";
            String customer = mongo.aggregateDataByQueryString("PEIProject", "CustomerDetails", clienteInfo);
        JSONArray customerinfo = new JSONArray(customer);
        exercicio+="\t\t<sal:customer>\n" +
                "\t\t\t<cu:customerID>" + ((JSONObject)((JSONObject) a).get("_id")).get("cliente") + "</cu:customerID>\n";
        for (Object i : customerinfo) {
            exercicio+= "\t\t\t<cu:name>" + ((JSONObject) i).get("CustomerName") + "</cu:name>\n" +

                    "\t\t\t<cu:nif>" + ((JSONObject) i).get("Nif") + "</cu:nif>\n" +
                    "\t\t\t<cu:address>\n" +
                    "\t\t\t\t<cu:street>" + ((JSONObject) i).getString("Street") + "</cu:street>\n" +
                    "\t\t\t\t<cu:doorNum>" + ((JSONObject) i).get("DoorNum") + "</cu:doorNum>\n" +
                    "\t\t\t\t<cu:postalCode>" + ((JSONObject) i).getString("PostalCode") + "</cu:postalCode>\n" +
                    "\t\t\t\t<cu:city>" + ((JSONObject) i).getString("City") + "</cu:city>\n" +
                    "\t\t\t\t<cu:district>" + ((JSONObject) i).getString("District") + "</cu:district>\n" +
                    "\t\t\t\t<cu:country>" + ((JSONObject) i).getString("Country") + "</cu:country>\n" +
                    "\t\t\t</cu:address>\n" +
                    "\t\t\t<cu:contacts>\n" +
                    "\t\t\t\t<cu:phone>" + ((JSONObject) i).get("Phone") + "</cu:phone>\n" +
                    "\t\t\t\t<cu:email>" + ((JSONObject) i).getString("Email") + "</cu:email>\n" +
                    "\t\t\t</cu:contacts>\n";
        }
        exercicio+="\t\t</sal:customer>\n";

            String saleCurrency = "[{$match:{CurrencyRateID:{$eq:" + ((JSONObject)((JSONObject) a).get("_id")).get("currencyId") + "}}}]";
            String currency = mongo.aggregateDataByQueryString("PEIProject", "CurrencyDetails", saleCurrency);
            JSONArray currencyInfo = new JSONArray(currency);

                exercicio += "\t\t<sal:currencyRate>\n";
                for (Object i : currencyInfo) {
                    exercicio += "\t\t\t<cr:currencyRateID>" + ((JSONObject) i).get("CurrencyRateID") + "</cr:currencyRateID>\n" +
                            "\t\t\t<cr:currencyRateDate>" + ((JSONObject) i).get("CurrencyRateDate") + "</cr:currencyRateDate>\n" +
                            "\t\t\t<cr:fromCurrencyCode>" + ((JSONObject) i).get("FromCurrencyCode") + "</cr:fromCurrencyCode>\n" +
                            "\t\t\t<cr:toCurrencyCode>" + ((JSONObject) i).get("ToCurrencyCode") + "</cr:toCurrencyCode>\n" +
                            "\t\t\t<cr:rateVal>" + ((JSONObject) i).get("RateVal") + "</cr:rateVal>\n";
                }
                exercicio += "\t\t</sal:currencyRate>\n";

        exercicio += "\t\t<sal:total>" + ((JSONObject)((JSONObject) a).get("_id")).get("total") + "</sal:total>\n";
        exercicio += "\t\t<sal:taxAmt>" + ((JSONObject)((JSONObject) a).get("_id")).get("taxAmt") + "</sal:taxAmt>\n";

        String query3 = "[{$match:{ReceiptID: {$eq: " + ((JSONObject)((JSONObject) a).get("_id")).get("id") + "}}},{$group:{_id:'$ReceiptID',TotalProducts:{$sum:'$Quantity'}}}]"; // Total produtos por sale
            String totalPerSale = mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query3);
            JSONArray totalPerSaleJson = new JSONArray(totalPerSale);
            for (Object i : totalPerSaleJson) {
                exercicio += "\t\t<sal:totalProducts>" + ((JSONObject) i).get("TotalProducts") + "</sal:totalProducts>\n";

            }

            String query4 = "[{$match:{ReceiptID: {$eq: " + ((JSONObject)((JSONObject) a).get("_id")).get("id") + "}}},{$group:{_id:'$ReceiptID',TotalDiffProducts:{$sum:1}}}]"; // Total produtos diferentes por sale
            String totalDiffPerSale = mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query4);
            JSONArray totalDiffPerSaleJson = new JSONArray(totalDiffPerSale);
            for (Object i : totalDiffPerSaleJson) {
                exercicio += "\t\t<sal:totalDifProducts>" + ((JSONObject) i).get("TotalDiffProducts") + "</sal:totalDifProducts>\n";

            }
            String query2 = "[{$match:{ReceiptID: {$eq: " + ((JSONObject)((JSONObject) a).get("_id")).get("id") + "}}},{$group:{_id:'$ReceiptID',AveragePrice:{$avg:'$UnitPrice'}}}]"; // Media de preço de produto vendidos por sale
            String productAveragePrice = mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query2);
            JSONArray productAveragePriceJson = new JSONArray(productAveragePrice);
            for (Object i : productAveragePriceJson) {
                exercicio += "\t\t<sal:productAveragePrice>" + ((JSONObject) i).get("AveragePrice") + "</sal:productAveragePrice>\n";

            }

            String salesLine="<Sales>"+XML.toString(((JSONArray)((JSONObject) a).get("produtos")),"saleLines");
            for (Object i : (((JSONArray)((JSONObject) a).get("produtos")))) {

                exercicio+="\t\t<sal:saleLine>\n";
                exercicio += "\t\t\t<sl:saleLineID>" + ((JSONObject) i).get("lineId") + "</sl:saleLineID>\n";
                exercicio += "\t\t\t<sl:order>" + ((JSONObject) i).get("quantidade") + "</sl:order>\n";

                String product = "[{$match:{ProductID:{$eq:" + ((JSONObject) i).get("id") + "}}}]";
            String productInfo = mongo.aggregateDataByQueryString("PEIProject", "ProductDetails", product);
            JSONArray productInfoJson = new JSONArray(productInfo);
             salesLine+= XML.toString(productInfoJson, "produtos");

        for (Object b : productInfoJson) {
            exercicio+="\t\t\t<sl:product>\n";
            exercicio+= "\t\t\t\t<pr:productID>" + ((JSONObject) i).get("id") + "</pr:productID>\n" +
                    "\t\t\t\t<pr:name>" + ((JSONObject) b).get("Name") + "</pr:name>\n" +
                    "\t\t\t\t<pr:price>" + ((JSONObject) i).get("precoUnidade") + "</pr:price>\n" +
                    "\t\t\t\t<pr:colour>" + ((JSONObject) b).get("Color") + "</pr:colour>\n";
            exercicio +="\t\t\t</sl:product>\n";
        }
        exercicio += "\t\t\t<sl:saleLineTotal>" + ((JSONObject) i).get("lineTotal") + "</sl:saleLineTotal>\n";
        exercicio+="\t\t</sal:saleLine>\n";
            }
            salesLine+="</sales>";

            exercicio+="\t</ex:sale>\n" +
                    "</exercise>\n";
            }



        String fina=storeInfoXml+totalProductsXml+totalSalesXml+averagePriceXml+exercicio+"</store>";

        final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890abcdefghijklmnopqrstuvwxyz";

        final java.util.Random rand = new java.util.Random();

        // consider using a Map<String,Boolean> to say whether the identifier is being used or not
        final Set<String> identifiers = new HashSet<String>();
        StringBuilder builder = new StringBuilder();
    while(builder.toString().length() == 0) {
        int length = rand.nextInt(5)+5;
        for(int i = 0; i < length; i++) {
            builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
        }
        if(identifiers.contains(builder.toString())) {
            builder = new StringBuilder();
        }
    }


        java.io.FileWriter fw = new java.io.FileWriter("xml/"+builder.toString()+".xml");
    fw.write(fina);
    fw.close();
        return vendas;

    }


    @RequestMapping("/sales")
    public String sales() {
        //Example: %5B%7B%24group%3A%7B%22_id%22%3Anull%2Ccount%3A%7B%24sum%3A1%7D%7D%7D%5D para [{$group:{"_id":null,count:{$sum:1}}}] -> utilizar: https://meyerweb.com/eric/tools/dencoder/
        MongoConnector mongo = new MongoConnector();
        String query = "[{$match:{StoreName: {$in: [\"Better Bike Shop\"]}}},{$group:{_id:\"$StoreName\",MediaPrice:{$avg:\"$UnitPrice\"}}}]"; //
        String query2 = "[{$match:{StoreName: {$in: [\"Better Bike Shop\"]}}},{$group:{_id:\"$Customer\"}},{$group:{_id:1,count:{$sum:1}}}]";
        String res = mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query);
        String res2 = mongo.aggregateDataByQueryString("PEIProject", "SalesDetails", query2);
        return res + res2;
    }

}