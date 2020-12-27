package com.example.livescore_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptor extends RecyclerView.Adapter<Adaptor.ViewHolder> {




    //variabile
    private List<Model> modelList;
    private Context context;

    public Adaptor(List<Model> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        //metoda apelata in momentul crearii viewholder-ului
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.linie, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //metoda care va aduce datele meciurilor in viewHolder
        Model model = modelList.get(position);
        holder.gazde_tv.setText(model.getEchipa1());
        holder.oaspeti_tv.setText(model.getEchipa2());
        holder.tipMeci_tv.setText(model.getTipMeci());
        holder.statusMeci_tv.setText(model.getStatusMeci());
        holder.dataMeci_tv.setText(model.getDataMeci());


        //item clicks

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mai tarziu de facut
            }
        });
    }
    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //definirea obiectelor view
        TextView gazde_tv, oaspeti_tv, tipMeci_tv, statusMeci_tv, dataMeci_tv;
        CardView cardView;

       public ViewHolder(@NonNull View itemView){
           super(itemView);
           //initializarea obiectelor view
           itemView.findViewById(R.id.gazde_tv);
           itemView.findViewById(R.id.oaspeti_tv);
           itemView.findViewById(R.id.tipMeci_tv);
           itemView.findViewById(R.id.statusMeci_tv);
           itemView.findViewById(R.id.dataMeci_tv);
       }
    }
}
//adaptor personalizat