package Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.e.nu34life.R;

import java.util.List;

import Model.Steps;

public class AdaptadorPasos extends BaseAdapter {

    private Context context;
    private List<Steps> listPasos;

    public AdaptadorPasos(Context context, List<Steps> listPasos) {
        this.context = context;
        this.listPasos = listPasos;
    }

    @Override
    public int getCount() {
        return listPasos.size();
    }

    @Override
    public Object getItem(int position) {
        return listPasos.get(position);
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
        datos.setText("    "+listPasos.get(position).getStepNumber().toString()+")  "+listPasos.get(position).getInstruction());
        return vista;
    }
}
