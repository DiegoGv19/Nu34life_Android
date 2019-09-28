package Interface;

import java.util.List;

import Model.Patient;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IPatient {
    @GET("patient/email/{email}")
    Call<List<Patient>>getPatient(@Path("email") String email );

    @GET("patient/{id}")
    Call<Patient>getPatientId(@Path("id") Long id );

    @GET("patient")
    Call<List<Patient>>getPatients();

    @POST("patient")
    Call<Patient>postPatient(@Body Patient patient);
    @PUT("patient")
    Call<Patient> putPatient(@Body Patient nutritionist);
}
