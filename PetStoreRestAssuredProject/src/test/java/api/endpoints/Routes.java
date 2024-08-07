package api.endpoints;

/*
Swagger URI --> https://petstore.swagger.io
Create user (Post): https://petstore.swagger.io/v2/user
Get user (Get): Thttps://petstore.swagger.io/v2/user/{username}
Update user (Put): https://petstore.swagger.io/v2/user/{username}
Delete user (Delete): https://petstore.swagger.io/v2/user/{username}
 */
public class Routes {

    public  static String base_url = "https://petstore.swagger.io/v2";
    //user module
    public static String post_url = base_url+"/user";
    public static String get_url = base_url+"/user/{username}";
    public static String update_url = base_url+"/user/{username}";
    public static String delete_url = base_url+"/user/{username}";

    //pet module
    public  static String post_url_pet = base_url+"/pet";
    public static String get_url_pet = base_url+"/pet/{id}";
    public static String update_url_pet = base_url+"/pet/{id}";
    public static String delete_url_pet = base_url+"/pet/{id}";

    public  static String post_url_store = base_url+"/store/order";
    public static String get_url_store = base_url+"/store/order/{id}";
    public static String get_url_store_inventory = base_url+"/store/inventory";
    public static String delete_url_store = base_url+"/store/order/{id}";

}
