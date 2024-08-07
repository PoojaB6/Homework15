package delivery.api;

import delivery.dto.OrderDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import delivery.utils.ApiClient;

import static delivery.spec.Specifications.getAuthenticatedRequestSpecification;

public class OrderTest extends BaseSetupApi {

//lesson-14
    @Test
    void deleteOrderAndCheckStatusCode() {
        Response responseCreateOrder = ApiClient.createOrder(getAuthenticatedRequestSpecification(bearerToken));
        String orderId = responseCreateOrder.getBody().jsonPath().getString("id");
        ApiClient.deleteOrder(getAuthenticatedRequestSpecification(bearerToken), orderId);
        Response response = ApiClient.getOrdersById(getAuthenticatedRequestSpecification(bearerToken), orderId);
        softly.assertThat(response.getBody().asString()).isBlank();
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    //Lesson_15
    //using GET method to get all orders as array
    @Test
    void getOrderInformationAndCheckResponse() {
        Response response = ApiClient.getOrders(getAuthenticatedRequestSpecification(bearerToken));
        OrderDto[] responseArray = ApiClient.getOrdersAsArray(getAuthenticatedRequestSpecification(bearerToken));

        System.out.println("existing Orders: ");
        for (int loopIndex = 0; loopIndex < responseArray.length; loopIndex++) {
            System.out.println("Iteration n " + loopIndex);
            System.out.println("Order id: " + responseArray[loopIndex].id);
        }

        //using Delete method to delete all orders
        System.out.println("Delete all orders ");
        for (int loopIndex = 0; loopIndex < responseArray.length; loopIndex++) {
            System.out.println("Iteration # " + loopIndex);
            System.out.println("Delete order with id" + responseArray[loopIndex].id);
            ApiClient.deleteOrder(getAuthenticatedRequestSpecification(bearerToken), String.valueOf(responseArray[loopIndex].id));
        }
        //Using POST method to create new orders
        ApiClient.createOrder(getAuthenticatedRequestSpecification(bearerToken));
        ApiClient.createOrder(getAuthenticatedRequestSpecification(bearerToken));
        ApiClient.createOrder(getAuthenticatedRequestSpecification(bearerToken));
        ApiClient.createOrder(getAuthenticatedRequestSpecification(bearerToken));
        ApiClient.createOrder(getAuthenticatedRequestSpecification(bearerToken));

        ////Using GET method to get new orders as array
        OrderDto[] checkCreatedOrdersAfterArrayDeletion = ApiClient.getOrdersAsArray(getAuthenticatedRequestSpecification(bearerToken));
        softly.assertThat(checkCreatedOrdersAfterArrayDeletion.length).isEqualTo(7);
    }

        @Test
        void CreateOrderAndCheckResponse () {
            Response response = ApiClient.createOrder(getAuthenticatedRequestSpecification(bearerToken));

            softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
            softly.assertThat(response.getContentType()).isEqualTo(ContentType.JSON.toString());
            softly.assertThat(response.getBody().jsonPath().getString("id")).isNotEmpty();
            softly.assertThat(response.getBody().jsonPath().getString("status")).isEqualTo("OPEN");
            softly.assertThat(response.getBody().jsonPath().getString("customerName")).isNotEmpty();
        }
    }
