package Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.nu34life.R;

import java.util.List;

import Model.Patient;

public class AdaptadorPatienet extends BaseAdapter {

    private Context context;
    private List<Patient> listaPatiente;


    public AdaptadorPatienet(Context context, List<Patient> listaPatiente) {
        this.context = context;
        this.listaPatiente = listaPatiente;
    }

    @Override
    public int getCount() {
        return listaPatiente.size();
    }

    @Override
    public Object getItem(int position) {
        return listaPatiente.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaPatiente.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista = convertView;
        LayoutInflater inflate = LayoutInflater.from(context);
        vista = inflate.inflate(R.layout.itemlistview,null);
        ImageView image = (ImageView) vista.findViewById(R.id.ivIteamPerfil);
        TextView datos = (TextView) vista.findViewById(R.id.tvDatos);
        image.setImageResource(R.drawable.ic_account_circle_black_24dp);
        datos.setText("  "+listaPatiente.get(position).getName().toString() +"  " + listaPatiente.get(position).getLastName().toString());
        return vista;
    }
}
