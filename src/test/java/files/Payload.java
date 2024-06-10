package files;


public class Payload {
    public static String AddPlace()
    {
        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Frontline house\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}";
    }

    public static String UpdatePlaceBody()
    {
        return "{\n" +
                "\"place_id\":\"8d2573bdf6ceec0e474c5f388fa917fb\",\n" +
                "\"address\":\"70 Summer walk, USA\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}";
    }

    public static String LE2()
    {
        return "{\n" +
                "    \"client_id\": \"6jhdjdh8884gud992008871\",\n" +
                "    \"client_secret\": \"BDBF717DEE8AF923A69E2540A6B0FC9C\"\n" +
                "}";
    }

    public static String LoginBody()
    {
        return "{\n" +
                "    \"email\" : \"sasidhar.chennamsetty+3@sureify.com\",\n" +
                "    \"password\" : \"Sureify@123\",\n" +
                "    \"device_info\": {\n" +
                "        \"id\": \"4caffierh85cgsyjgchis3\",\n" +
                "        \"token\": \"APA91bHmOrWYiFjfm8I8Q\"\n" +
                "    }\n" +
                "}";
    }


}
