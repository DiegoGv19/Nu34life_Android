package Interface;

import java.util.List;

import Model.Plan;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IPlan {

    @GET("plans/patient/{patientId}")
    Call<List<Plan>> getPlan(@Path("patientId") Long patientId,@Query("turn") String turn , @Query("day") String day);
    @DELETE("plans/{id}")
    Call<Plan> deletPlan(@Path("id") Long id);

    @POST("plans")
    Call<Plan> postPlan(@Body Plan plan);



}
