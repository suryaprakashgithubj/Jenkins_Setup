//package API_Testing;
//
//import io.restassured.RestAssured;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//import io.restassured.http.ContentType;
//
//import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;
//
//import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
//
//public class Get_Request {
//	public static void main(String[] args) {
//
//        RestAssured.baseURI = "https://gorest.co.in/";
//
//        System.out.println("============== GET REQUEST ==============");
//
//        Response getResponse =
//
//                given()
//
//                .when()
//
//                    .get("public/v2/users/3453454526");
//
//        System.out.println("Status Code : "
//                + getResponse.getStatusCode());
//
//        System.out.println("Response Body : ");
//        System.out.println(getResponse.getBody().asString());
//
//        System.out.println("Response Time : "
//                + getResponse.getTime());
//
//        System.out.println("Content Type : "
//                + getResponse.getContentType());
//
//
//
//        System.out.println("\n============== VALIDATIONS ==============");
//
////        // Validations
////        given()
////
////        .when()
////
////            .get("/api/users/2")
////
////        .then()
////
////            .statusCode(200)
////
////            .body("data.first_name",
////                    equalTo("Janet"))
////
////            .body("data.last_name",
////                    equalTo("Weaver"))
////
////            .time(lessThan(5000L))
////
////            .header("Content-Type",
////                    containsString("application/json"))
////
////            .log().all();
////
////
////
////        System.out.println("\n============== JSON EXTRACTION ==============");
////
////        // JSON Extraction
////        JsonPath jsonPath =
////                getResponse.jsonPath();
////
////        int id =
////                jsonPath.getInt("data.id");
////
////        String email =
////                jsonPath.getString("data.email");
////
////        String firstName =
////                jsonPath.getString("data.first_name");
////
////        System.out.println("ID : " + id);
////
////        System.out.println("Email : " + email);
////
////        System.out.println("First Name : "
////                + firstName);
////
////
////
////        System.out.println("\n============== POST REQUEST ==============");
////
////        // Request Body
////        String postRequestBody =
////                "{ \"name\":\"Vaishali\", \"job\":\"Tester\" }";
////
////        // POST Request
////        Response postResponse =
////
////                given()
////
////                    .contentType(ContentType.JSON)
////
////                    .body(postRequestBody)
////
////                .when()
////
////                    .post("/api/users");
////
////        // POST Validation
////        postResponse.then()
////
////                .statusCode(201)
////
////                .log().all();
////
////        // Extract Generated ID
////        String generatedId =
////                postResponse.jsonPath().getString("id");
////
////        System.out.println("Generated ID : "
////                + generatedId);
////
////
////
////        System.out.println("\n============== PUT REQUEST ==============");
////
////        // PUT Request Body
////        String putRequestBody =
////                "{ \"name\":\"Vaishali\", \"job\":\"Senior Tester\" }";
////
////        // PUT Request
////        given()
////
////            .contentType(ContentType.JSON)
////
////            .body(putRequestBody)
////
////        .when()
////
////            .put("/api/users/2")
////
////        .then()
////
////            .statusCode(200)
////
////            .log().all();
////
////
////
////        System.out.println("\n============== DELETE REQUEST ==============");
////
////        // DELETE Request
////        given()
////
////        .when()
////
////            .delete("/api/users/2")
////
////        .then()
////
////            .statusCode(204)
////
////            .log().all();
////
////
////
////        System.out.println("\n============== PATH PARAMETER ==============");
////
////        // Path Parameter
////        given()
////
////            .pathParam("id", 2)
////
////        .when()
////
////            .get("/api/users/{id}")
////
////        .then()
////
////            .statusCode(200)
////
////            .log().all();
////
////
////
////        System.out.println("\n============== QUERY PARAMETER ==============");
////
////        // Query Parameter
////        given()
////
////            .queryParam("page", 2)
////
////        .when()
////
////            .get("/api/users")
////
////        .then()
////
////            .statusCode(200)
////
////            .log().all();
////
////
////
////        System.out.println("\n============== HEADERS ==============");
////
////        // Headers Example
////        given()
////
////            .header("Content-Type",
////                    "application/json")
////
////        .when()
////
////            .get("/api/users/2")
////
////        .then()
////
////            .statusCode(200)
////
////            .log().headers();
////
////
////
////        System.out.println("\n============== BEARER TOKEN AUTH ==============");
////
////        // Bearer Token Example
////
////        String token = "YOUR_TOKEN";
////
////        given()
////
////            .header("Authorization",
////                    "Bearer " + token)
////
////        .when()
////
////            .get("https://gorest.co.in/public/v2/users")
////
////        .then()
////
////            .statusCode(200)
////
////            .log().all();
////
////
////
////        System.out.println("\n============== REQUEST CHAINING ==============");
////
////        // POST Request
////        Response chainResponse =
////
////                given()
////
////                    .contentType(ContentType.JSON)
////
////                    .body(postRequestBody)
////
////                .when()
////
////                    .post("/api/users");
////
////        // Extract ID
////        String chainId =
////                chainResponse.jsonPath().getString("id");
////
////        System.out.println("Chain ID : "
////                + chainId);
////
////        // GET using same ID
////        given()
////
////            .pathParam("id", chainId)
////
////        .when()
////
////            .get("/api/users/{id}")
////
////        .then()
////
////            .log().all();
////
////
////
////        System.out.println("\n============== JSON SCHEMA VALIDATION ==============");
////
////        // Schema Validation
////        given()
////
////        .when()
////
////            .get("/api/users/2")
////
////        .then()
////
////            .assertThat()
////
////            .body(matchesJsonSchemaInClasspath(
////                    "schema.json"))
////
////            .log().all();
////
////
////
////        System.out.println("\n============== PRINT HEADERS ==============");
////
////        // Print Headers
////        getResponse.getHeaders().forEach(
////                System.out::println);
////
////
////
////        System.out.println("\n============== PRINT COOKIES ==============");
////
////        // Print Cookies
////        getResponse.getCookies().forEach(
////                (k,v) -> System.out.println(
////                        k + " : " + v));
//
//
//
//        System.out.println("\n============== TEST COMPLETED ==============");
//    }
//}
