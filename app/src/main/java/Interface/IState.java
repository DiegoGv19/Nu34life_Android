package Interface;

import Model.State;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IState {

    @GET("state/{id}")
    Call<State> getStateId(@Path("id") Long id );

    @PUT("state")
    Call<State> putState(@Body State state);
}
