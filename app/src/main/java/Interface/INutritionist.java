package Interface;

import java.util.List;

import Model.Nutritionist;
import Model.Patient;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface INutritionist {


    @GET("nutritionists")
    Call<Model.Nutritionist>getNutricionista(@Query("email") String email, @Query("password") String password );

    @GET("nutritionists/patients/{id}")
    Call<List<Patient>>getPatientDelNutri( @Path("id") Long id);


    @POST("nutritionists")
    Call<Nutritionist> postNutricionista(@Body Nutritionist nutritionist);
    @POST("nutritionists/{id}")
    Call<Patient> postPatienteinNutricionista(@Body Patient patient, @Path("id") Long id);


}
