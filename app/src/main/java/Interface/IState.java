package Interface;

import Model.Nutritionist;
import Model.State;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IState {


    @GET("states")
    Call<State> getStateId(@Query("idPatient") Long idPatient );

    @PUT("states/patient")
    Call<State> putState(@Body State state,@Query("email") String email, @Query("password") String password );
}
