package helpers;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.Cookie;

import java.util.Iterator;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class ApiHelper {

    public String baseUrl = "http://demowebshop.tricentis.com",
            addToWishlist = "/addproducttocart/details/53/2",
            bodyForAddToWishList = "addtocart_53.EnteredQuantity=1";

    RequestSpecification spec;
    //String apiCookie = "Nop.customer=cf4e6b33-2823-44b3-8f0e-1fee89039cad; ARRAffinity=7f10010dd6b12d83d6aefe199065b2e8fe0d0850a7df2983b482815225e42439; __utmc=78382081; __utmz=78382081.1611045863.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __RequestVerificationToken=iAPt5bMZfUxypUzAh7iygf032kLjgGPklPg-y_8WkrHl9Trw9DRR8lw4SRPWXDhfVeuuBp98h4QnD-QTBA-fqVyFSnm5UwNFOSBRulEs3ug1; ASP.NET_SessionId=v302dgp3mz3auqufjeesnwdb; __utma=78382081.373186156.1611045863.1611048018.1611051155.3; Nop.customer=cf4e6b33-2823-44b3-8f0e-1fee89039cad; nop.CompareProducts=CompareProductIds=31; _atshc={\"http://demowebshop.tricentis.com/simple-computer\":4}; __utmt=1; NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=53&RecentlyViewedProductIds=36&RecentlyViewedProductIds=31&RecentlyViewedProductIds=75; __atuvc=12%7C3; __atuvs=6006b0b87dfde00000a; __utmb=78382081.32.10.1611051155";
    String apiCookie = "";

    public ApiHelper() {
        spec = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie(apiCookie)
                .baseUri(baseUrl);
    }

    public void saveAuthorisationCookieFromSet(Set<Cookie> cookiesSet) {
        for (Cookie cookie : cookiesSet) {
            if (cookie.getName().contains("Nop.customer")) {
                String name = cookie.getName();
                String value = cookie.getValue();
                apiCookie += name += "=";
                apiCookie += value += "; ";
            }
            if (cookie.getName().contains("ARRAffinity")) {
                String name = cookie.getName();
                String value = cookie.getValue();
                apiCookie += name += "=";
                apiCookie += value += ";";
            }
        }
        spec.cookie(apiCookie);
    }

    public Response addItemToWishList() {
        return given()
                .body(bodyForAddToWishList)
                .spec(spec)
                .when()
                .log().all()
                .post(addToWishlist);
    }
}
