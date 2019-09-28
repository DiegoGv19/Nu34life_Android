package Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.e.nu34life.R;

import java.util.List;

public class AdamptadorDias extends BaseAdapter{

    private Context context;
    private String[] listaDias;


    public AdamptadorDias(Context context,String[] listaDias) {
        this.listaDias = listaDias;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaDias.length;
    }

    @Override
    public Object getItem(int position) {
        return listaDias[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista = convertView;
        LayoutInflater inflate = LayoutInflater.from(context);
        vista = inflate.inflate(R.layout.itemdias,null);
        TextView datos = (TextView) vista.findViewById(R.id.tvdiaMostrar);
        datos.setText(listaDias[position]);
        return vista;
    }
}
