package com.example.hussnain.kafara.Ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hussnain.kafara.BackEnd.ApiHandler;
import com.example.hussnain.kafara.R;
import com.example.hussnain.kafara.ServerModel.Bank;
import com.example.hussnain.kafara.ServerModel.Page;

import java.util.ArrayList;

public class BanksActivity extends AppCompatActivity {
    private Context context;
    private RecyclerView recyclerView;
    private  TextView bankname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banks);
        this.context=this;
        recyclerView=findViewById(R.id.bankrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int pageno=0;
        ApiHandler.getInstance().getBank(pageno,new ApiHandler.BankCallback(){
            @Override
            public void onSuccess(Page <Bank> bank) {
                OurAdapter adapter=new OurAdapter((ArrayList <Bank>) bank.getData());
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFaliour(String message) {

            }
        });
    }
    public class OurAdapter extends RecyclerView.Adapter<OurAdapter.ViewHolder>{
         private final ArrayList<Bank> data;

        public OurAdapter(ArrayList <Bank> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview,parent,false));
        }
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          Bank name=data.get(position);
          holder.bankname.setText(name.getName());
        }

        @Override
        public int getItemCount() {
            return data.size();

    }
    class ViewHolder extends RecyclerView.ViewHolder{
            TextView bankname;
        public ViewHolder(View itemView) {
            super(itemView);
            bankname=itemView.findViewById(R.id.bankname);
        }
    }
}
}
