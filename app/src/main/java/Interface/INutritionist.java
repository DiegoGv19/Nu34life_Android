package Interface;

import java.util.List;

import Model.Nutritionist;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface INutritionist {


    @GET("nutritionist/email/{email}")
    Call<List<Model.Nutritionist>>getNutricionista(@Path("email") String email );

    @POST("nutritionist")
    Call<Nutritionist> postNutricionista(@Body Nutritionist nutritionist);


}
